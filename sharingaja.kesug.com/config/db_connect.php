<?php

// Konfigurasi Database
define('DB_SERVER', 'localhost'); // Server database Anda, biasanya 'localhost'
define('DB_USERNAME', 'root');    // Username database Anda
define('DB_PASSWORD', '');        // Password database Anda
define('DB_NAME', 'pinjam_seratus_db'); // Nama database Anda

// Membuat koneksi ke database
$mysqli = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);

// Periksa koneksi
if ($mysqli === false) {
    // Jika koneksi gagal, hentikan skrip dan tampilkan pesan error
    die("ERROR: Could not connect. " . $mysqli->connect_error);
}

// Memulai session PHP untuk manajemen login
if (session_status() == PHP_SESSION_NONE) {
    session_start();
}

?>