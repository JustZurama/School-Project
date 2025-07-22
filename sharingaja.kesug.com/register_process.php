<?php
// register_process.php
// Operasi CREATE

// Sertakan file koneksi database
require_once "config/db_connect.php";

// Ambil data dari form
$full_name = $_POST['full_name'];
$email = $_POST['email'];
$password = $_POST['password'];
$confirm_password = $_POST['confirm_password'];

// Validasi sederhana
if (empty($full_name) || empty($email) || empty($password)) {
    header("location: register.php?error=Semua field harus diisi.");
    exit;
}

if ($password !== $confirm_password) {
    header("location: register.php?error=Konfirmasi kata sandi tidak cocok.");
    exit;
}

// Cek apakah email sudah terdaftar
$sql_check = "SELECT id FROM users WHERE email = ?";
if ($stmt_check = $mysqli->prepare($sql_check)) {
    $stmt_check->bind_param("s", $email);
    $stmt_check->execute();
    $stmt_check->store_result();
    
    if ($stmt_check->num_rows > 0) {
        header("location: register.php?error=Email sudah terdaftar.");
        exit;
    }
    $stmt_check->close();
}

// Hash kata sandi untuk keamanan
$hashed_password = password_hash($password, PASSWORD_DEFAULT);

// Siapkan statement INSERT untuk membuat user baru
$sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";

if ($stmt = $mysqli->prepare($sql)) {
    // Bind variabel ke statement sebagai parameter
    $stmt->bind_param("sss", $full_name, $email, $hashed_password);

    // Eksekusi statement
    if ($stmt->execute()) {
        // Jika berhasil, redirect ke halaman login
        header("location: login.php?success=Pendaftaran berhasil. Silakan login.");
        exit;
    } else {
        header("location: register.php?error=Terjadi kesalahan. Coba lagi nanti.");
        exit;
    }

    // Tutup statement
    $stmt->close();
}

// Tutup koneksi
$mysqli->close();
?>
