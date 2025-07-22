<?php
// login.php
// Memulai session untuk memeriksa apakah pengguna sudah login
session_start();

// Jika pengguna sudah login, alihkan ke dasbor
if (isset($_SESSION["loggedin"]) && $_SESSION["loggedin"] === true) {
    header("location: dashboard/index2.php");
    exit;
}
?>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - PinjamSeratus</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Inter', sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; align-items: center; height: 100vh; }
        .login-card { width: 100%; max-width: 450px; border: none; border-radius: 15px; box-shadow: 0 8px 25px rgba(0,0,0,0.1); }
        .login-card .card-header { background-color: #fff; border-bottom: none; padding: 2rem 2rem 1rem; text-align: center; }
        .login-card .card-header .navbar-brand { font-size: 1.5rem; font-weight: 700; }
        .btn-primary { background-color: #0d6efd; border-color: #0d6efd; font-weight: 600; padding: 12px; }
        .form-control:focus { box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25); border-color: #86b7fe; }
    </style>
</head>
<body>
    <div class="login-card card">
        <div class="card-header">
            <a class="navbar-brand text-dark text-decoration-none" href="index.php">
                <i class="fas fa-comment"></i>
                <strong>SharingAja</strong>
            </a>
            <h4 class="mt-3">Selamat Datang Kembali</h4>
            <p class="text-muted">Silakan masuk untuk melanjutkan.</p>
        </div>
        <div class="card-body p-4">
            <?php 
            // Menampilkan pesan error atau sukses dari URL
            if(!empty($_GET['error'])){
                echo '<div class="alert alert-danger">' . htmlspecialchars($_GET['error']) . '</div>';
            }
            if(!empty($_GET['success'])){
                echo '<div class="alert alert-success">' . htmlspecialchars($_GET['success']) . '</div>';
            }
            ?>
            <form action="login_process.php" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Alamat Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-4">
                    <label for="password" class="form-label">Kata Sandi</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Sign In</button>
                </div>
            </form>
            <div class="text-center mt-4">
                <p class="text-muted">Belum punya akun? <a href="register.php">Daftar di sini</a></p>
            </div>
        </div>
    </div>
</body>
</html>
