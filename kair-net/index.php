<?php
// Memulai session untuk memeriksa status login
session_start();

// Cek jika variabel session "loggedin" sudah ada dan nilainya true
if (isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true) {
    // Jika sudah login, arahkan ke dashboard.php
    header("location: dashboard.php");
    exit;
} else {
    // Jika belum login, arahkan ke login.php
    header("location: login.php");
    exit;
}
?>