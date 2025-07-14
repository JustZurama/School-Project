<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Kair-Net Management</title>
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
        $error = '';

        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            $username = $_POST['username'];
            $password = $_POST['password'];

            $sql = "SELECT id, username, password FROM admins WHERE username = ?";
            if ($stmt = $conn->prepare($sql)) {
                $stmt->bind_param("s", $username);
                if ($stmt->execute()) {
                    $stmt->store_result();
                    if ($stmt->num_rows == 1) {
                        $stmt->bind_result($id, $username, $hashed_password);
                        if ($stmt->fetch()) {
                            if (password_verify($password, $hashed_password)) {
                                $_SESSION["loggedin"] = true;
                                $_SESSION["id"] = $id;
                                $_SESSION["username"] = $username;
                                header("location: dashboard.php");
                                exit;
                            } else {
                                $error = "Password yang Anda masukkan salah.";
                            }
                        }
                    } else {
                        $error = "Tidak ada akun yang ditemukan dengan username tersebut.";
                    }
                } else {
                    $error = "Terjadi kesalahan. Silakan coba lagi nanti.";
                }
                $stmt->close();
            }
        }
        $conn->close();
        ?>

        <div class="bg-white shadow-lg rounded-xl p-8">
            <div class="text-center mb-6">
                <h1 class="text-3xl font-bold text-slate-800">Login</h1>
                <p class="text-slate-500">Silakan masuk untuk melanjutkan</p>
            </div>
            
            <?php if(!empty($error)): ?>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-lg relative mb-4" role="alert">
                    <span class="block sm:inline"><?php echo $error; ?></span>
                </div>
            <?php endif; ?>

            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                <div class="mb-4">
                    <label for="username" class="block text-slate-700 text-sm font-medium mb-2">Username</label>
                    <input type="text" name="username" id="username" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="mb-6">
                    <label for="password" class="block text-slate-700 text-sm font-medium mb-2">Password</label>
                    <input type="password" name="password" id="password" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <button type="submit" class="w-full bg-blue-600 text-white font-bold py-2 px-4 rounded-lg hover:bg-blue-700 transition duration-300">Login</button>
            </form>
            <p class="text-center text-sm text-slate-500 mt-6">
                Belum punya akun? <a href="register.php" class="font-medium text-blue-600 hover:text-blue-500">Daftar di sini</a>
            </p>
        </div>
    </div>
</body>
</html>
