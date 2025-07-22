<?php

require_once '../config/db_connect.php';

// Proteksi halaman
if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    header("location: ../login.php");
    exit;
}

$user_id = $_SESSION['id'];
$page_title = "Dasbor Pengguna";
require_once '../templates/header.php';
require_once '../templates/sidebar.php';

// Ambil data untuk setiap modul
$articles = $mysqli->query("SELECT id, title, created_at FROM articles WHERE user_id = $user_id ORDER BY created_at DESC");
$events = $mysqli->query("SELECT id, title, event_date FROM events WHERE user_id = $user_id ORDER BY event_date DESC");
$galleries = $mysqli->query("SELECT id, title, created_at FROM galleries WHERE user_id = $user_id ORDER BY created_at DESC");
?>

    <header class="main-header">
        <h1>Dasbor Manajemen Konten</h1>
        <p>Selamat datang, <?php echo htmlspecialchars($_SESSION['full_name']); ?>! Kamu mau cerita apa?</p>
    </header>

    <div class="container-fluid">
        <!-- Notifikasi -->
        <?php if(isset($_GET['message'])): ?>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <?php echo htmlspecialchars($_GET['message']); ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <?php endif; ?>
        <?php if(isset($_GET['error'])): ?>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <?php echo htmlspecialchars($_GET['error']); ?>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <?php endif; ?>

        <!-- Manajemen Artikel -->
        <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5><i class="fas fa-newspaper me-2"></i>Kelola Artikel</h5>
                <a href="manage_article.php" class="btn btn-primary btn-sm"><i class="fas fa-plus"></i> Tambah</a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead><tr><th>Judul</th><th>Tanggal</th><th>Aksi</th></tr></thead>
                        <tbody>
                            <?php if ($articles->num_rows > 0): while($row = $articles->fetch_assoc()): ?>
                            <tr>
                                <td><?php echo htmlspecialchars($row['title']); ?></td>
                                <td><?php echo date('d M Y', strtotime($row['created_at'])); ?></td>
                                <td>
                                    <a href="manage_article.php?id=<?php echo $row['id']; ?>" class="btn btn-sm btn-secondary">Edit</a>
                                    <a href="process_article.php?action=delete&id=<?php echo $row['id']; ?>" class="btn btn-sm btn-danger" onclick="return confirm('Yakin?')">Hapus</a>
                                </td>
                            </tr>
                            <?php endwhile; else: ?>
                            <tr><td colspan="3" class="text-center">Belum ada artikel.</td></tr>
                            <?php endif; ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Manajemen Event -->
        <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5><i class="fas fa-calendar-alt me-2"></i>Kelola Event</h5>
                <a href="manage_event.php" class="btn btn-primary btn-sm"><i class="fas fa-plus"></i> Tambah</a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead><tr><th>Nama Event</th><th>Tanggal</th><th>Aksi</th></tr></thead>
                        <tbody>
                            <?php if ($events->num_rows > 0): while($row = $events->fetch_assoc()): ?>
                            <tr>
                                <td><?php echo htmlspecialchars($row['title']); ?></td>
                                <td><?php echo date('d M Y', strtotime($row['event_date'])); ?></td>
                                <td>
                                    <a href="manage_event.php?id=<?php echo $row['id']; ?>" class="btn btn-sm btn-secondary">Edit</a>
                                    <a href="process_event.php?action=delete&id=<?php echo $row['id']; ?>" class="btn btn-sm btn-danger" onclick="return confirm('Yakin?')">Hapus</a>
                                </td>
                            </tr>
                            <?php endwhile; else: ?>
                            <tr><td colspan="3" class="text-center">Belum ada event.</td></tr>
                            <?php endif; ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Manajemen Galeri -->
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5><i class="fas fa-images me-2"></i>Kelola Galeri</h5>
                <a href="manage_gallery.php" class="btn btn-primary btn-sm"><i class="fas fa-plus"></i> Tambah</a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead><tr><th>Judul Foto</th><th>Tanggal Upload</th><th>Aksi</th></tr></thead>
                        <tbody>
                            <?php if ($galleries->num_rows > 0): while($row = $galleries->fetch_assoc()): ?>
                            <tr>
                                <td><?php echo htmlspecialchars($row['title']); ?></td>
                                <td><?php echo date('d M Y', strtotime($row['created_at'])); ?></td>
                                <td>
                                    <a href="manage_gallery.php?id=<?php echo $row['id']; ?>" class="btn btn-sm btn-secondary">Edit</a>
                                    <a href="process_gallery.php?action=delete&id=<?php echo $row['id']; ?>" class="btn btn-sm btn-danger" onclick="return confirm('Yakin?')">Hapus</a>
                                </td>
                            </tr>
                            <?php endwhile; else: ?>
                            <tr><td colspan="3" class="text-center">Belum ada foto.</td></tr>
                            <?php endif; ?>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

<?php require_once '../templates/footer.php'; ?>
