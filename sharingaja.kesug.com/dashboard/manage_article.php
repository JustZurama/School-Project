<?php
require_once '../config/db_connect.php';

if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    header("location: ../login.php");
    exit;
}

$article = ['id' => '', 'title' => '', 'content' => ''];
$page_title = "Tambah Artikel Baru";
$form_action = "process_article.php";

// Mode Edit: jika ada ID di URL
if (isset($_GET['id'])) {
    $id = $_GET['id'];
    $stmt = $mysqli->prepare("SELECT * FROM articles WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $id, $_SESSION['id']);
    $stmt->execute();
    $result = $stmt->get_result();
    if ($result->num_rows === 1) {
        $article = $result->fetch_assoc();
        $page_title = "Edit Artikel";
    } else {
        // Artikel tidak ditemukan atau bukan milik user
        header("location: index2.php?error=Artikel tidak ditemukan");
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
            <form action="<?php echo $form_action; ?>" method="post" enctype="multipart/form-data">
                <!-- Hidden input untuk ID (saat edit) dan Aksi -->
                <input type="hidden" name="id" value="<?php echo $article['id']; ?>">
                <input type="hidden" name="action" value="<?php echo empty($article['id']) ? 'create' : 'update'; ?>">

                <div class="mb-3">
                    <label for="title" class="form-label">Judul Artikel</label>
                    <input type="text" class="form-control" id="title" name="title" value="<?php echo htmlspecialchars($article['title']); ?>" required>
                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">Isi Artikel</label>
                    <textarea class="form-control" id="content" name="content" rows="10" required><?php echo htmlspecialchars($article['content']); ?></textarea>
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Gambar Utama (Opsional)</label>
                    <input class="form-control" type="file" id="image" name="image">
                    <?php if (!empty($article['image_path'])): ?>
                        <img src="../uploads/<?php echo $article['image_path']; ?>" alt="Current Image" class="img-thumbnail mt-2" width="200">
                    <?php endif; ?>
                </div>
                <a href="index2.php" class="btn btn-secondary">Batal</a>
                <button type="submit" class="btn btn-primary">Simpan Artikel</button>
            </form>
        </div>
    </div>
</div>

<?php require_once '../templates/footer.php'; ?>