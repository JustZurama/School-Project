<?php
// templates/sidebar.php

if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

// 1. TENTUKAN KONFIGURASI NAVIGASI DALAM ARRAY
// Ini adalah pusat kendali untuk semua tautan di sidebar.
// 'label' => Teks yang ditampilkan
// 'href'  => Path file tujuan dari root direktori
// 'icon'  => Kelas ikon Font Awesome
// 'auth'  => null (tampil untuk semua), true (hanya login), false (hanya tamu)
$nav_items = [
    // Grup Informasi (selalu tampil)
    ['group' => 'Informasi', 'label' => 'Home', 'href' => 'index.php', 'icon' => 'fas fa-home', 'auth' => null],
    ['group' => 'Informasi', 'label' => 'Artikel', 'href' => 'artikel.php', 'icon' => 'fas fa-newspaper', 'auth' => null],
    ['group' => 'Informasi', 'label' => 'Acara / Event', 'href' => 'event.php', 'icon' => 'fas fa-calendar-alt', 'auth' => null],
    ['group' => 'Informasi', 'label' => 'Galeri', 'href' => 'galeri.php', 'icon' => 'fas fa-images', 'auth' => null],
    
    // Grup Akun (logika login/logout)
    ['group' => 'Akun', 'label' => 'Dasbor', 'href' => 'dashboard/index2.php', 'icon' => 'fas fa-tachometer-alt', 'auth' => true],
    ['group' => 'Akun', 'label' => 'Logout', 'href' => 'logout.php', 'icon' => 'fas fa-sign-out-alt', 'auth' => true],
    ['group' => 'Akun', 'label' => 'Sign In', 'href' => 'login.php', 'icon' => 'fas fa-sign-in-alt', 'auth' => false],
    ['group' => 'Akun', 'label' => 'Sign Up', 'href' => 'register.php', 'icon' => 'fas fa-user-plus', 'auth' => false],
];

// 2. LOGIKA UNTUK MENENTUKAN PATH DAN HALAMAN AKTIF
$is_logged_in = isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true;

// Cek apakah kita berada di dalam subdirektori (seperti 'dashboard')
// str_contains() membutuhkan PHP 8.0+. Jika menggunakan versi lebih lama, ganti dengan strpos() !== false
$is_in_subdir = str_contains($_SERVER['SCRIPT_NAME'], '/dashboard/');
$path_prefix = $is_in_subdir ? '../' : '';

// Dapatkan nama file dari halaman yang sedang dibuka, misal: "index2.php"
$current_page_filename = basename($_SERVER['SCRIPT_NAME']);

?>
<div class="sidebar d-flex flex-column p-3">
    <div class="text-center mb-4">
        <a class="navbar-brand text-dark text-decoration-none" href="<?php echo $path_prefix; ?>index.php">
            <i class="fas fa-comment"></i>
            <strong>SharingAja</strong>
        </a>
        <div class="social-icons mt-2">
            <a href="#" class="text-secondary me-2"><i class="fab fa-facebook-f"></i></a>
            <a href="#" class="text-secondary me-2"><i class="fab fa-twitter"></i></a>
            <a href="#" class="text-secondary"><i class="fab fa-linkedin-in"></i></a>
        </div>
    </div>
    
    <ul class="nav flex-column nav-pills">
        <?php
        $current_group = '';
        foreach ($nav_items as $item):
            // 3. LOGIKA UNTUK MENAMPILKAN ITEM SESUAI STATUS LOGIN
            if ($item['auth'] === true && !$is_logged_in) continue; // Lewati jika harus login tapi belum login
            if ($item['auth'] === false && $is_logged_in) continue; // Lewati jika untuk tamu tapi sudah login

            // Cek apakah ini grup baru untuk mencetak judulnya
            if ($item['group'] !== $current_group):
                $current_group = $item['group'];
                $margin_top_class = ($current_group === 'Akun') ? 'mt-auto pt-3' : 'mt-2';
        ?>
                <li class="nav-item <?php echo $margin_top_class; ?>">
                    <strong class="text-muted small text-uppercase"><?php echo $current_group; ?></strong>
                </li>
        <?php 
            endif;

            // 4. LOGIKA UNTUK MENAMBAHKAN KELAS 'active'
            // Bandingkan nama file saat ini dengan nama file di href
            $is_active = ($current_page_filename == basename($item['href']));
            $active_class = $is_active ? 'active' : '';

            // Buat URL yang benar menggunakan path prefix
            $final_href = $path_prefix . $item['href'];
        ?>
            <li class="nav-item">
                <a class="nav-link <?php echo $active_class; ?>" href="<?php echo $final_href; ?>">
                    <i class="<?php echo $item['icon']; ?> me-2"></i><?php echo $item['label']; ?>
                </a>
            </li>
        <?php endforeach; ?>
    </ul>
</div>

<div class="main-content flex-grow-1">
