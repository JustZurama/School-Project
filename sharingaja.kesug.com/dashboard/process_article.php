<?php
require_once '../config/db_connect.php';

if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    echo "Akses ditolak.";
    exit;
}

$user_id = $_SESSION['id'];
$action = $_REQUEST['action'] ?? ''; // Bisa dari POST atau GET (untuk delete)

// Fungsi untuk upload gambar
function uploadImage($file) {
    $target_dir = "../uploads/";
    // Buat nama file unik untuk menghindari tumpang tindih
    $filename = uniqid() . '-' . basename($file["name"]);
    $target_file = $target_dir . $filename;
    
    // Cek tipe file, ukuran, dll (bisa ditambahkan validasi lebih lanjut)
    if (move_uploaded_file($file["tmp_name"], $target_file)) {
        return $filename;
    }
    return null;
}

// CREATE
if ($action === 'create' && $_SERVER['REQUEST_METHOD'] === 'POST') {
    $title = $_POST['title'];
    $content = $_POST['content'];
    $image_path = null;

    if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
        $image_path = uploadImage($_FILES['image']);
    }

    $stmt = $mysqli->prepare("INSERT INTO articles (user_id, title, content, image_path) VALUES (?, ?, ?, ?)");
    $stmt->bind_param("isss", $user_id, $title, $content, $image_path);
    if ($stmt->execute()) {
        header("location: index2.php?message=Artikel berhasil dibuat.");
    } else {
        header("location: manage_article.php?error=Gagal membuat artikel.");
    }
}

// UPDATE
if ($action === 'update' && $_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $title = $_POST['title'];
    $content = $_POST['content'];
    $image_path = null;

    if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
        $image_path = uploadImage($_FILES['image']);
        // Query untuk update gambar
        $stmt = $mysqli->prepare("UPDATE articles SET title = ?, content = ?, image_path = ? WHERE id = ? AND user_id = ?");
        $stmt->bind_param("sssii", $title, $content, $image_path, $id, $user_id);
    } else {
        // Query jika tidak ada gambar baru
        $stmt = $mysqli->prepare("UPDATE articles SET title = ?, content = ? WHERE id = ? AND user_id = ?");
        $stmt->bind_param("ssii", $title, $content, $id, $user_id);
    }
    
    if ($stmt->execute()) {
        header("location: index2.php?message=Artikel berhasil diperbarui.");
    } else {
        header("location: manage_article.php?id=$id&error=Gagal memperbarui artikel.");
    }
}

// DELETE
if ($action === 'delete' && isset($_GET['id'])) {
    $id = $_GET['id'];
    
    // (Opsional) Hapus file gambar dari server
    $res = $mysqli->query("SELECT image_path FROM articles WHERE id = $id AND user_id = $user_id");
    if($res->num_rows === 1) {
        $img = $res->fetch_assoc();
        if(!empty($img['image_path']) && file_exists("../uploads/".$img['image_path'])) {
            unlink("../uploads/".$img['image_path']);
        }
    }

    $stmt = $mysqli->prepare("DELETE FROM articles WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $id, $user_id);
    if ($stmt->execute()) {
        header("location: index2.php?message=Artikel berhasil dihapus.");
    } else {
        header("location: index2.php?error=Gagal menghapus artikel.");
    }
}
?>
