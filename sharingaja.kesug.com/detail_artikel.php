<?php
require_once 'config/db_connect.php';

// Pastikan ada ID artikel yang dikirim melalui URL
if (isset($_GET['id']) && !empty($_GET['id'])) {
    $article_id = $_GET['id'];

    // Ambil detail artikel dari database berdasarkan ID
    $stmt = $mysqli->prepare("SELECT articles.*, users.full_name FROM articles JOIN users ON articles.user_id = users.id WHERE articles.id = ?");
    $stmt->bind_param("i", $article_id);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $article = $result->fetch_assoc();
        $page_title = htmlspecialchars($article['title']) . " - Nama Perusahaan";
    } else {
        // Jika artikel tidak ditemukan, arahkan ke halaman artikel atau tampilkan pesan error
        header("Location: artikel.php");
        exit();
    }
    $stmt->close();
} else {
    // Jika tidak ada ID artikel, arahkan ke halaman artikel
    header("Location: artikel.php");
    exit();
}

require_once 'templates/header.php';
require_once 'templates/sidebar.php';
?>

<header class="main-header">
    <h1><?php echo htmlspecialchars($article['title']); ?></h1>
    <p class="text-muted small">
        Oleh <?php echo htmlspecialchars($article['full_name']); ?> pada <?php echo date('d M Y H:i', strtotime($article['created_at'])); ?>
    </p>
</header>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-8 offset-lg-2">
            <div class="article-detail-content">
                <?php if(!empty($article['image_path'])): ?>
                    <img src="uploads/<?php echo htmlspecialchars($article['image_path']); ?>" class="img-fluid mb-4 rounded" alt="<?php echo htmlspecialchars($article['title']); ?>">
                <?php endif; ?>

                <p><?php echo nl2br(htmlspecialchars($article['content'])); ?></p>
            </div>
            <hr>
            <a href="artikel.php" class="btn btn-secondary">Kembali ke Artikel</a>
        </div>
    </div>
</div>

<?php require_once 'templates/footer.php'; ?>