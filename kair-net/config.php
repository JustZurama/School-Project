<?php

// Konfigurasi Database
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'root'); // Ganti dengan username database
define('DB_PASSWORD', ''); // Ganti dengan password database
define('DB_NAME', 'rentaldb');

// Atur zona waktu agar sesuai dengan zona waktu WITA
date_default_timezone_set('Asia/Makassar');

// Membuat koneksi ke database
$conn = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);

// Cek koneksi
if ($conn->connect_error) {
    die("Koneksi gagal: " . $conn->connect_error);
}

// Memulai session
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}
?>
