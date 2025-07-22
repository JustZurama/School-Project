<?php 
require_once 'config/db_connect.php';
$page_title = "Galeri - PinjamSeratus";
require_once 'templates/header.php'; 
require_once 'templates/sidebar.php'; 

// Ambil semua item galeri dari database
$result = $mysqli->query("SELECT galleries.*, users.full_name FROM galleries JOIN users ON galleries.user_id = users.id ORDER BY galleries.created_at DESC");
?>

<header class="main-header">
    <h1>Galeri Foto</h1>
    <p class="text-muted">Momen-momen sedih, pahit, asam dan manis apa aja sih yang dibagiin sharingers?</p>
</header>

<div class="container-fluid">
    <div class="row">
        <?php if ($result->num_rows > 0): ?>
            <?php while($item = $result->fetch_assoc()): ?>
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card">
                        <a href="uploads/<?php echo htmlspecialchars($item['image_path']); ?>" data-bs-toggle="modal" data-bs-target="#imageModal" data-bs-imgsrc="uploads/<?php echo htmlspecialchars($item['image_path']); ?>">
                            <img src="uploads/<?php echo htmlspecialchars($item['image_path']); ?>" class="card-img-top" alt="<?php echo htmlspecialchars($item['title']); ?>" style="height: 250px; object-fit: cover;">
                        </a>
                        <div class="card-body">
                            <h6 class="card-title"><?php echo htmlspecialchars($item['title']); ?></h6>
                            <p class="card-text small text-muted"><?php echo htmlspecialchars($item['description']); ?></p>
                        </div>
                    </div>
                </div>
            <?php endwhile; ?>
        <?php else: ?>
            <div class="col">
                <div class="alert alert-info">Belum ada foto di galeri.</div>
            </div>
        <?php endif; ?>
    </div>
</div>

<!-- Modal untuk menampilkan gambar lebih besar -->
<div class="modal fade" id="imageModal" tabindex="-1" aria-labelledby="imageModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-center">
        <img src="" class="img-fluid" id="modalImage" alt="Gambar Galeri">
      </div>
    </div>
  </div>
</div>

<script>
// Script untuk mengisi modal dengan gambar yang diklik
const imageModal = document.getElementById('imageModal');
imageModal.addEventListener('show.bs.modal', event => {
  const button = event.relatedTarget;
  const imgSrc = button.getAttribute('data-bs-imgsrc');
  const modalImage = imageModal.querySelector('#modalImage');
  modalImage.src = imgSrc;
});
</script>

<?php require_once 'templates/footer.php'; ?>
