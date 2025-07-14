<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Daftar Akun - Kair-Net Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Inter', sans-serif; }
    </style>
</head>
<body class="bg-slate-100 flex items-center justify-center h-screen">
    <div class="w-full max-w-md">
        <?php
        require_once 'config.php';
        $success_msg = $error_msg = '';

        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $nama_lengkap = trim($_POST['nama_lengkap']);
            $username = trim($_POST['username']);
            $password = trim($_POST['password']);
            
            // Hash password
            $hashed_password = password_hash($password, PASSWORD_DEFAULT);

            $sql = "INSERT INTO admins (nama_lengkap, username, password) VALUES (?, ?, ?)";
            
            if ($stmt = $conn->prepare($sql)) {
                $stmt->bind_param("sss", $nama_lengkap, $username, $hashed_password);
                
                if ($stmt->execute()) {
                    $success_msg = "Pendaftaran berhasil! Anda sekarang bisa login.";
                } else {
                    if ($conn->errno == 1062) { // Error code for duplicate entry
                        $error_msg = "Username sudah digunakan. Silakan pilih yang lain.";
                    } else {
                        $error_msg = "Terjadi kesalahan. Silakan coba lagi.";
                    }
                }
                $stmt->close();
            }
        }
        $conn->close();
        ?>
        <div class="bg-white shadow-lg rounded-xl p-8">
            <div class="text-center mb-6">
                <h1 class="text-3xl font-bold text-slate-800">Buat Akun Baru</h1>
                <p class="text-slate-500">Daftarkan diri untuk mengelola rental</p>
            </div>

            <?php if(!empty($success_msg)): ?>
                <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded-lg relative mb-4" role="alert">
                    <span class="block sm:inline"><?php echo $success_msg; ?></span>
                </div>
            <?php endif; ?>
            <?php if(!empty($error_msg)): ?>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-lg relative mb-4" role="alert">
                    <span class="block sm:inline"><?php echo $error_msg; ?></span>
                </div>
            <?php endif; ?>

            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                <div class="mb-4">
                    <label for="nama_lengkap" class="block text-slate-700 text-sm font-medium mb-2">Nama Lengkap</label>
                    <input type="text" name="nama_lengkap" id="nama_lengkap" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="mb-4">
                    <label for="username" class="block text-slate-700 text-sm font-medium mb-2">Username</label>
                    <input type="text" name="username" id="username" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="mb-6">
                    <label for="password" class="block text-slate-700 text-sm font-medium mb-2">Password</label>
                    <input type="password" name="password" id="password" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <button type="submit" class="w-full bg-blue-600 text-white font-bold py-2 px-4 rounded-lg hover:bg-blue-700 transition duration-300">Daftar</button>
            </form>
            <p class="text-center text-sm text-slate-500 mt-6">
                Sudah punya akun? <a href="login.php" class="font-medium text-blue-600 hover:text-blue-500">Login di sini</a>
            </p>
        </div>
    </div>
</body>
</html>
