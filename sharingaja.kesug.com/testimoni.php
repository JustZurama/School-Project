<?php
$page_title = "Testimoni - SharingAja";
require_once 'templates/header.php'; 
require_once 'templates/sidebar.php'; 
?>
<header class="main-header"><h1>Ribuan Diantara Kita!</h1></header>
<div class="container-fluid">
    <div class="card"><div class="card-body p-4">
        <p>Kata mereka yang telah bergabung bersama SharingAja</p>
        <div class="row">
            <!-- Foto Profil Mitra -->
            <div class="row mb-5 text-center align-items-center">
                <div class="col"><img src="https://placehold.co/150x60/FFFFFF/CCCCCC?text=Logo+Sharingers+1" alt="Logo Sharingers 1" class="client-logo"></div>
                <div class="col"><img src="https://placehold.co/150x60/FFFFFF/CCCCCC?text=Logo+Sharingers+2" alt="Logo Sharingers 2" class="client-logo"></div>
                <div class="col"><img src="https://placehold.co/150x60/FFFFFF/CCCCCC?text=Logo+Sharingers+3" alt="Logo Sharingers 3" class="client-logo"></div>
            </div>
            <!-- Testimoni Mitra -->
            <div class="row">
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card testimonial-card h-100">
                            <div class="card-body">
                                <p class="card-text fst-italic">"SharingAja jadi tempat favoritku buat baca pengalaman orang lain soal dunia perkuliahan dan pengembangan diri. Rasanya nggak sendiri lagi menjalani semuanya."</p>
                                <footer class="blockquote-footer mt-3">Budi Santoso, <cite title="Source Title">Karyawan Swasta</cite></footer>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card testimonial-card h-100">
                            <div class="card-body">
                                <p class="card-text fst-italic">"Saya sering ikut diskusi di SharingAja saat ingin mencari perspektif baru tentang pekerjaan dan kehidupan. Komunitasnya aktif dan saling mendukung."</p>
                                <footer class="blockquote-footer mt-3">Siti Aminah, <cite title="Source Title">Pengusaha UKM</cite></footer>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-4 mb-4">
                        <div class="card testimonial-card h-100">
                            <div class="card-body">
                                <p class="card-text fst-italic">"Awalnya cuma iseng baca, tapi sekarang saya ikut menulis juga. Ternyata menyenangkan bisa berbagi cerita dan mendapat respons yang positif."</p>
                                <footer class="blockquote-footer mt-3">Rina Hartono, <cite title="Source Title">Ibu Rumah Tangga</cite></footer>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>    
</div>
<?php require_once 'templates/footer.php'; ?>