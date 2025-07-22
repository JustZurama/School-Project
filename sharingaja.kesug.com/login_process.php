<?php

// Sertakan file koneksi
require_once "config/db_connect.php";

// Ambil data dari form
$email = $_POST['email'];
$password = $_POST['password'];

// Validasi input
if (empty($email) || empty($password)) {
    header("location: login.php?error=Email dan kata sandi harus diisi.");
    exit;
}

// Siapkan statement SELECT
$sql = "SELECT id, full_name, email, password FROM users WHERE email = ?";

if ($stmt = $mysqli->prepare($sql)) {
    $stmt->bind_param("s", $email);

    if ($stmt->execute()) {
        $stmt->store_result();

        // Cek jika email ada
        if ($stmt->num_rows == 1) {
            $stmt->bind_result($id, $full_name, $db_email, $hashed_password);
            if ($stmt->fetch()) {
                // Verifikasi kata sandi
                if (password_verify($password, $hashed_password)) {
                    // Kata sandi benar, mulai session baru
                    session_start();

                    // Simpan data di variabel session
                    $_SESSION["loggedin"] = true;
                    $_SESSION["id"] = $id;
                    $_SESSION["email"] = $db_email;
                    $_SESSION["full_name"] = $full_name;

                    // Redirect ke halaman dasbor
                    header("location: dashboard/index2.php");
                    exit;
                } else {
                    // Kata sandi salah
                    header("location: login.php?error=Email atau kata sandi salah.");
                    exit;
                }
            }
        } else {
            // Email tidak ditemukan
            header("location: login.php?error=Email atau kata sandi salah.");
            exit;
        }
    } else {
        echo "Oops! Something went wrong. Please try again later.";
    }
    $stmt->close();
}
$mysqli->close();
?>