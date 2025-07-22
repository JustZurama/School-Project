<?php
// dashboard/manage_event.php
require_once '../config/db_connect.php';

// Proteksi halaman
if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    header("location: ../login.php");
    exit;
}

// Inisialisasi variabel
$event = ['id' => '', 'title' => '', 'description' => '', 'event_date' => '', 'location' => ''];
$page_title = "Tambah Event Baru";

// Mode Edit: jika ada ID di URL
if (isset($_GET['id'])) {
    $id = $_GET['id'];
    $stmt = $mysqli->prepare("SELECT * FROM events WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $id, $_SESSION['id']);
    $stmt->execute();
    $result = $stmt->get_result();
    if ($result->num_rows === 1) {
        $event = $result->fetch_assoc();
        $page_title = "Edit Event";
    } else {
        header("location: index2.php?error=Event tidak ditemukan");
        exit;
    }
}
?>
<?php require_once '../templates/header.php'; ?>
<?php require_once '../templates/sidebar.php'; ?>

<header class="main-header">
    <h1><?php echo $page_title; ?></h1>
</header>

<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <form action="process_event.php" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="<?php echo $event['id']; ?>">
                <input type="hidden" name="action" value="<?php echo empty($event['id']) ? 'create' : 'update'; ?>">

                <div class="mb-3">
                    <label for="title" class="form-label">Nama Event</label>
                    <input type="text" class="form-control" id="title" name="title" value="<?php echo htmlspecialchars($event['title']); ?>" required>
                </div>
                <div class="mb-3">
                    <label for="event_date" class="form-label">Tanggal Event</label>
                    <input type="date" class="form-control" id="event_date" name="event_date" value="<?php echo htmlspecialchars($event['event_date']); ?>" required>
                </div>
                <div class="mb-3">
                    <label for="location" class="form-label">Lokasi</label>
                    <input type="text" class="form-control" id="location" name="location" value="<?php echo htmlspecialchars($event['location']); ?>" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Deskripsi</label>
                    <textarea class="form-control" id="description" name="description" rows="5" required><?php echo htmlspecialchars($event['description']); ?></textarea>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Gambar/Poster Event (Opsional)</label>
                    <input class="form-control" type="file" id="image" name="image">
                    <?php if (!empty($event['image_path'])): ?>
                        <img src="../uploads/<?php echo $event['image_path']; ?>" alt="Current Image" class="img-thumbnail mt-2" width="200">
                    <?php endif; ?>
                </div>
                <a href="index2.php" class="btn btn-secondary">Batal</a>
                <button type="submit" class="btn btn-primary">Simpan Event</button>
            </form>
        </div>
    </div>
</div>

<?php require_once '../templates/footer.php'; ?>