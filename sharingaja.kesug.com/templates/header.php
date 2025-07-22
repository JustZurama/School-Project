<?php
// Mulai session di setiap halaman yang menggunakan template ini
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}
// Dapatkan nama file saat ini
$current_page = basename($_SERVER['PHP_SELF']);
?>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo isset($page_title) ? $page_title : 'SharingAja'; ?></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="d-flex">

    <?php if ($current_page == 'index.php'): ?>
    <div class="main-content flex-grow-1">
        <header class="main-header">
            <div class="d-flex justify-content-between align-items-center">
                <h1>SharingAja</h1>
            </div>
            <nav class="nav top-nav mt-3">
                <a class="nav-link" href="index.php">Profile</a>
                <a class="nav-link" href="visi-misi.php">Visi dan Misi</a>
                <a class="nav-link" href="produk.php">Produk Kami</a>
                <a class="nav-link" href="kontak.php">Kontak</a>
                <a class="nav-link" href="about-us.php">About us</a>
                <a class="nav-link" href="testimoni.php">Apa Kata Mereka?</a>
            </nav>
        </header>
    <?php endif; ?>