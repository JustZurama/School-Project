<?php 
require_once 'config/db_connect.php';
$page_title = "Acara & Event - PinjamSeratus";
require_once 'templates/header.php'; 
require_once 'templates/sidebar.php'; 

// Ambil semua event dari database
$result = $mysqli->query("SELECT events.*, users.full_name FROM events JOIN users ON events.user_id = users.id ORDER BY events.event_date DESC");
?>

<header class="main-header">
    <h1>Acara & Event Mendatang</h1>
    <p class="text-muted">Yuk ikutan acara bareng sharingers!</p>
</header>

<div class="container-fluid">
    <div class="row">
        <?php if ($result->num_rows > 0): ?>
            <?php while($event = $result->fetch_assoc()): ?>
                <div class="col-md-12 mb-4">
                    <div class="card flex-md-row">
                        <?php if(!empty($event['image_path'])): ?>
                            <img src="uploads/<?php echo htmlspecialchars($event['image_path']); ?>" class="card-img-left" alt="<?php echo htmlspecialchars($event['title']); ?>" style="width: 100%; max-width: 300px; object-fit: cover;">
                        <?php else: ?>
                            <img src="https://placehold.co/600x400/EFEFEF/AAAAAA?text=Event" class="card-img-left" alt="<?php echo htmlspecialchars($event['title']); ?>" style="width: 100%; max-width: 300px; object-fit: cover;">
                        <?php endif; ?>
                        <div class="card-body">
                            <h5 class="card-title"><?php echo htmlspecialchars($event['title']); ?></h5>
                            <p class="card-text text-muted">
                                <i class="fas fa-calendar-alt me-2"></i><?php echo date('l, d F Y', strtotime($event['event_date'])); ?><br>
                                <i class="fas fa-map-marker-alt me-2"></i><?php echo htmlspecialchars($event['location']); ?>
                            </p>
                            <p class="card-text"><?php echo htmlspecialchars($event['description']); ?></p>
                            <p class="card-text"><small class="text-muted">Dibuat oleh: <?php echo htmlspecialchars($event['full_name']); ?></small></p>
                        </div>
                    </div>
                </div>
            <?php endwhile; ?>
        <?php else: ?>
            <div class="col">
                <div class="alert alert-info">Belum ada event yang dijadwalkan.</div>
            </div>
        <?php endif; ?>
    </div>
</div>

<?php require_once 'templates/footer.php'; ?>
