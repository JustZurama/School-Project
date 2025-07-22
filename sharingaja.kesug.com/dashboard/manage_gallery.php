<?php
// Pastikan sesi sudah dimulai. Ini adalah praktik yang aman.
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

require_once '../config/db_connect.php';

// --- 1. PROTEKSI HALAMAN ---
// Memastikan hanya pengguna yang sudah login yang bisa mengakses halaman ini.
if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    header("location: ../login.php");
    exit;
}

// --- 2. INISIALISASI VARIABEL ---
// Inisialisasi array $item dengan semua kunci yang akan digunakan di form.
$item = [
    'id' => '', 
    'title' => '', 
    'description' => '',
    'image_path' => null
];
$page_title = "Tambah Foto ke Galeri";
$user_id = $_SESSION['id']; // Simpan user_id ke variabel agar lebih mudah dibaca.

// --- 3. LOGIKA MODE EDIT ---
// Jika ada ID di URL, berarti kita dalam mode edit.
if (isset($_GET['id']) && !empty($_GET['id'])) {
    $id = $_GET['id'];
    $page_title = "Edit Info Foto";

    // Ambil data dari database dengan aman menggunakan prepared statement.
    $stmt = $mysqli->prepare("SELECT id, title, description, image_path FROM galleries WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $id, $user_id);
    $stmt->execute();
    $result = $stmt->get_result();
    
    if ($result->num_rows === 1) {
        $item = $result->fetch_assoc(); // Timpa variabel $item dengan data dari database.
    } else {
        // Jika item tidak ditemukan atau bukan milik user, redirect dengan pesan error.
        header("location: index2.php?error=Item galeri tidak ditemukan atau Anda tidak punya hak akses.");
        exit;
    }
    $stmt->close();
}
?>
<?php require_once '../templates/header.php'; ?>
<?php require_once '../templates/sidebar.php'; ?>

<header class="main-header">
    <h1><?php echo htmlspecialchars($page_title); ?></h1>
</header>

<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <form action="process_gallery.php" method="post" enctype="multipart/form-data">
                <!-- PERBAIKAN: Menggunakan ?? '' untuk memberikan nilai default jika kunci tidak ada -->
                <input type="hidden" name="id" value="<?php echo htmlspecialchars($item['id'] ?? ''); ?>">
                <input type="hidden" name="action" value="<?php echo empty($item['id']) ? 'create' : 'update'; ?>">

                <div class="mb-3">
                    <label for="title" class="form-label">Judul Foto</label>
                    <input type="text" class="form-control" id="title" name="title" value="<?php echo htmlspecialchars($item['title'] ?? ''); ?>" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Deskripsi Singkat (Opsional)</label>
                    <!-- PERBAIKAN: Menggunakan ?? '' untuk mencegah error pada textarea -->
                    <textarea class="form-control" id="description" name="description" rows="3"><?php echo htmlspecialchars($item['description'] ?? ''); ?></textarea>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">File Gambar</label>
                    <!-- Logika ini sudah aman karena `empty()` tidak menyebabkan error -->
                    <input class="form-control" type="file" id="image" name="image" accept="image/jpeg, image/png, image/gif" <?php echo empty($item['id']) ? 'required' : ''; ?>>
                    <small class="form-text text-muted">Hanya format JPG, PNG, GIF yang diizinkan.</small>
                    
                    <?php if (!empty($item['image_path'])): ?>
                        <div class="mt-3">
                            <p class="mb-1">Gambar Saat Ini:</p>
                            <img src="../uploads/<?php echo htmlspecialchars($item['image_path']); ?>" alt="Current Image" class="img-thumbnail" width="200">
                        </div>
                    <?php endif; ?>
                </div>
                <hr>
                <a href="index2.php" class="btn btn-secondary">Batal</a>
                <button type="submit" class="btn btn-primary">Simpan ke Galeri</button>
            </form>
        </div>
    </div>
</div>

<?php require_once '../templates/footer.php'; ?>