<?php
require_once 'config.php';
header('Content-Type: application/json');

// Cek login
if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    echo json_encode(['success' => false, 'message' => 'Akses ditolak. Silakan login terlebih dahulu.']);
    exit;
}

$admin_id = $_SESSION['id'];
$action = $_GET['action'] ?? '';

// Harga PS
$harga_ps = [
    'PS2' => 3000,
    'PS3' => 5000,
    'PS4' => 7000,
    'PS5' => 9000
];

switch ($action) {
    case 'tambah_sewa':
        $nama_klien = $_POST['nama_klien'];
        $tipe_ps = $_POST['tipe_ps'];
        $lama_sewa = (int)$_POST['lama_sewa'];
        
        if (empty($nama_klien) || empty($tipe_ps) || $lama_sewa <= 0 || !isset($harga_ps[$tipe_ps])) {
            echo json_encode(['success' => false, 'message' => 'Data tidak valid.']);
            exit;
        }

        $total_biaya = $harga_ps[$tipe_ps] * $lama_sewa;
        $waktu_mulai = date('Y-m-d H:i:s');
        $waktu_selesai = date('Y-m-d H:i:s', strtotime("+$lama_sewa hours"));

        $sql = "INSERT INTO sesi_sewa (admin_id, nama_klien, tipe_ps, lama_sewa, waktu_mulai, waktu_selesai, total_biaya) VALUES (?, ?, ?, ?, ?, ?, ?)";
        $stmt = $conn->prepare($sql);
        // PERBAIKAN: Mengubah 'ississs' menjadi 'ississi'
        $stmt->bind_param("ississi", $admin_id, $nama_klien, $tipe_ps, $lama_sewa, $waktu_mulai, $waktu_selesai, $total_biaya);
        
        if ($stmt->execute()) {
            echo json_encode(['success' => true]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Gagal menyimpan data.']);
        }
        $stmt->close();
        break;

    case 'get_sewa_berjalan':
        $sql = "SELECT id, nama_klien, tipe_ps, total_biaya, waktu_mulai, waktu_selesai FROM sesi_sewa WHERE admin_id = ? AND status = 'berjalan' ORDER BY waktu_selesai ASC";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $admin_id);
        $stmt->execute();
        $result = $stmt->get_result();
        $data = [];
        while ($row = $result->fetch_assoc()) {
            $row['waktu_habis'] = strtotime($row['waktu_selesai']) < time();
            $data[] = $row;
        }
        echo json_encode($data);
        $stmt->close();
        break;

    case 'selesaikan_sewa':
        $id = $_GET['id'] ?? 0;
        $sql = "UPDATE sesi_sewa SET status = 'selesai' WHERE id = ? AND admin_id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ii", $id, $admin_id);
        if ($stmt->execute()) {
            echo json_encode(['success' => true]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Gagal menyelesaikan sesi.']);
        }
        $stmt->close();
        break;
        
    case 'get_sewa_by_id':
        $id = $_GET['id'] ?? 0;
        $sql = "SELECT id, nama_klien, tipe_ps FROM sesi_sewa WHERE id = ? AND admin_id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ii", $id, $admin_id);
        $stmt->execute();
        $result = $stmt->get_result();
        $data = $result->fetch_assoc();
        echo json_encode($data);
        $stmt->close();
        break;

    case 'update_sewa':
        $id = $_POST['id'];
        $nama_klien = $_POST['nama_klien'];
        $tipe_ps = $_POST['tipe_ps'];
        $tambah_jam = (int)$_POST['tambah_jam'];

        // Ambil data lama
        $stmt_get = $conn->prepare("SELECT lama_sewa, waktu_selesai FROM sesi_sewa WHERE id = ? AND admin_id = ?");
        $stmt_get->bind_param("ii", $id, $admin_id);
        $stmt_get->execute();
        $result_get = $stmt_get->get_result();
        $sewa_lama = $result_get->fetch_assoc();
        $stmt_get->close();

        if (!$sewa_lama) {
            echo json_encode(['success' => false, 'message' => 'Data tidak ditemukan.']);
            exit;
        }

        $lama_sewa_baru = $sewa_lama['lama_sewa'] + $tambah_jam;
        $total_biaya_baru = $harga_ps[$tipe_ps] * $lama_sewa_baru;
        $waktu_selesai_baru = date('Y-m-d H:i:s', strtotime($sewa_lama['waktu_selesai'] . " +$tambah_jam hours"));

        $sql = "UPDATE sesi_sewa SET nama_klien = ?, tipe_ps = ?, lama_sewa = ?, total_biaya = ?, waktu_selesai = ? WHERE id = ? AND admin_id = ?";
        $stmt = $conn->prepare($sql);
        // PERBAIKAN: Mengubah 'ssisiii' menjadi 'ssiisii'
        $stmt->bind_param("ssiisii", $nama_klien, $tipe_ps, $lama_sewa_baru, $total_biaya_baru, $waktu_selesai_baru, $id, $admin_id);
        
        if ($stmt->execute()) {
            echo json_encode(['success' => true]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Gagal memperbarui data.']);
        }
        $stmt->close();
        break;
        
    case 'get_statistik':
        $filter = $_GET['filter'] ?? 'minggu';
        $labels = [];
        $values = [];
        
        $where_clause = "admin_id = ? AND status = 'selesai'";
        $group_by = "";
        
        switch ($filter) {
            case 'minggu':
                $where_clause .= " AND YEARWEEK(waktu_selesai, 1) = YEARWEEK(CURDATE(), 1)";
                $group_by = "DATE_FORMAT(waktu_selesai, '%W')"; // Group by day name
                $days_of_week = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];
                $labels = $days_of_week;
                $values = array_fill_keys($days_of_week, 0);
                break;
            case 'bulan':
                $where_clause .= " AND YEAR(waktu_selesai) = YEAR(CURDATE()) AND MONTH(waktu_selesai) = MONTH(CURDATE())";
                $group_by = "DAY(waktu_selesai)";
                $days_in_month = cal_days_in_month(CAL_GREGORIAN, date('m'), date('Y'));
                $labels = range(1, $days_in_month);
                $values = array_fill_keys($labels, 0);
                break;
            case 'tahun':
                $where_clause .= " AND YEAR(waktu_selesai) = YEAR(CURDATE())";
                $group_by = "MONTHNAME(waktu_selesai)";
                $months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
                $labels = $months;
                $values = array_fill_keys($months, 0);
                break;
        }

        $sql = "SELECT COUNT(*) as count, $group_by as label FROM sesi_sewa WHERE $where_clause GROUP BY label";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $admin_id);
        $stmt->execute();
        $result = $stmt->get_result();

        while($row = $result->fetch_assoc()){
            if (isset($values[$row['label']])) {
                $values[$row['label']] = (int)$row['count'];
            }
        }
        
        echo json_encode(['labels' => array_keys($values), 'values' => array_values($values)]);
        $stmt->close();
        break;

    case 'get_data_for_excel':
        $filter = $_GET['filter'] ?? 'minggu';
        $where_clause = "admin_id = ? AND status = 'selesai'";
        switch ($filter) {
            case 'minggu':
                $where_clause .= " AND YEARWEEK(waktu_selesai, 1) = YEARWEEK(CURDATE(), 1)";
                break;
            case 'bulan':
                $where_clause .= " AND YEAR(waktu_selesai) = YEAR(CURDATE()) AND MONTH(waktu_selesai) = MONTH(CURDATE())";
                break;
            case 'tahun':
                $where_clause .= " AND YEAR(waktu_selesai) = YEAR(CURDATE())";
                break;
        }

        $sql = "SELECT nama_klien AS 'Nama Klien', tipe_ps AS 'Tipe PS', lama_sewa AS 'Lama Sewa (Jam)', total_biaya AS 'Total Biaya', waktu_mulai AS 'Waktu Mulai', waktu_selesai AS 'Waktu Selesai' FROM sesi_sewa WHERE $where_clause ORDER BY waktu_selesai DESC";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("i", $admin_id);
        $stmt->execute();
        $result = $stmt->get_result();
        $data = $result->fetch_all(MYSQLI_ASSOC);
        echo json_encode($data);
        $stmt->close();
        break;

    default:
        echo json_encode(['success' => false, 'message' => 'Aksi tidak valid.']);
        break;
}

$conn->close();
?>
