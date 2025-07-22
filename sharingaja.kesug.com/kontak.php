<?php
$page_title = "Kontak - SharingAja";
require_once 'templates/header.php'; 
require_once 'templates/sidebar.php'; 
?>
<header class="main-header"><h1>Hubungi Kami</h1></header>
<div class="container-fluid">
    <div class="card"><div class="card-body p-4">
        <p>Kami senang mendengar dari Anda. Silakan hubungi kami melalui detail di bawah ini atau kirimkan pesan melalui formulir.</p>
        <div class="row">
            <div class="col-md-6">
                <h5>Informasi Kontak</h5>
                <p><i class="fas fa-map-marker-alt fa-fw me-2"></i>Tinggal dimana aja, Kota kecilku, Negeri Indonesia tercinta</p>
                <p><i class="fas fa-phone fa-fw me-2"></i>(021) 123-4567</p>
                <p><i class="fas fa-envelope fa-fw me-2"></i>sharingaja123@domain.com</p>
            </div>
            <div class="col-md-6">
                <h5>Kirim Pesan</h5>
                <form>
                    <div class="mb-3"><input type="text" class="form-control" placeholder="Nama Anda"></div>
                    <div class="mb-3"><input type="email" class="form-control" placeholder="Email Anda"></div>
                    <div class="mb-3"><textarea class="form-control" rows="4" placeholder="Pesan Anda"></textarea></div>
                    <button type="submit" class="btn btn-primary">Kirim</button>
                </form>
            </div>
        </div>
    </div></div>
</div>
<?php require_once 'templates/footer.php'; ?>