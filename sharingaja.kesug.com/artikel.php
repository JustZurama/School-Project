<?php 
require_once 'config/db_connect.php';
$page_title = "Artikel - SharingAja";
require_once 'templates/header.php'; 
require_once 'templates/sidebar.php'; 

// Ambil semua artikel dari database
$result = $mysqli->query("SELECT articles.*, users.full_name FROM articles JOIN users ON articles.user_id = users.id ORDER BY articles.created_at DESC");
?>

<header class="main-header">
    <h1>Kumpulan Artikel</h1>
    <p class="text-muted">Apa aja sih yang diceritain sharingers?</p>
</header>

<div class="container-fluid">
    <div class="row">
        <?php if ($result->num_rows > 0): ?>
            <?php while($article = $result->fetch_assoc()): ?>
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card h-100">
                        <?php if(!empty($article['image_path'])): ?>
                            <img src="uploads/<?php echo htmlspecialchars($article['image_path']); ?>" class="card-img-top" alt="<?php echo htmlspecialchars($article['title']); ?>" style="height: 200px; object-fit: cover;">
                        <?php else: ?>
                            <img src="https://placehold.co/600x400/EFEFEF/AAAAAA?text=Artikel" class="card-img-top" alt="<?php echo htmlspecialchars($article['title']); ?>">
                        <?php endif; ?>
                        <div class="card-body">
                            <h5 class="card-title"><?php echo htmlspecialchars($article['title']); ?></h5>
                            <p class="card-text text-muted small">
                                Oleh <?php echo htmlspecialchars($article['full_name']); ?> pada <?php echo date('d M Y', strtotime($article['created_at'])); ?>
                            </p>
                            <p class="card-text">
                                <?php echo htmlspecialchars(substr($article['content'], 0, 100)); ?>...
                            </p>
                            <a href="detail_artikel.php?id=<?php echo htmlspecialchars($article['id']); ?>" class="btn btn-primary">Baca Selengkapnya</a>
                        </div>
                    </div>
                </div>
            <?php endwhile; ?>
        <?php else: ?>
            <div class="col">
                <div class="alert alert-info">Belum ada artikel yang dipublikasikan.</div>
            </div>
        <?php endif; ?>
    </div>
</div>

<?php require_once 'templates/footer.php'; ?>
