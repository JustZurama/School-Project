<?php
require_once '../config/db_connect.php';

if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    exit("Akses ditolak.");
}

$user_id = $_SESSION['id'];
$action = $_REQUEST['action'] ?? '';

// Fungsi upload gambar
function uploadImage($file) {
    $target_dir = "../uploads/";
    $filename = uniqid() . '-' . basename($file["name"]);
    $target_file = $target_dir . $filename;
    if (move_uploaded_file($file["tmp_name"], $target_file)) {
        return $filename;
    }
    return null;
}

// CREATE
if ($action === 'create' && $_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
        $title = $_POST['title'];
        $description = $_POST['description'];
        $image_path = uploadImage($_FILES['image']);
        
        if ($image_path) {
            $stmt = $mysqli->prepare("INSERT INTO galleries (user_id, title, description, image_path) VALUES (?, ?, ?, ?)");
            $stmt->bind_param("isss", $user_id, $title, $description, $image_path);
            if ($stmt->execute()) {
                header("location: index2.php?message=Foto berhasil ditambahkan.");
            } else {
                header("location: manage_gallery.php?error=Gagal menyimpan data.");
            }
        } else {
            header("location: manage_gallery.php?error=Gagal mengupload gambar.");
        }
    } else {
        header("location: manage_gallery.php?error=File gambar wajib diisi.");
    }
}

// UPDATE
if ($action === 'update' && $_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $title = $_POST['title'];
    $description = $_POST['description'];
    
    // Hanya update judul dan deskripsi, gambar tidak diubah di form edit ini
    $stmt = $mysqli->prepare("UPDATE galleries SET title = ?, description = ? WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ssii", $title, $description, $id, $user_id);
    
    if ($stmt->execute()) {
        header("location: index2.php?message=Info foto berhasil diperbarui.");
    } else {
        header("location: manage_gallery.php?id=$id&error=Gagal memperbarui info foto.");
    }
}

// DELETE
if ($action === 'delete' && isset($_GET['id'])) {
    $id = $_GET['id'];
    $stmt = $mysqli->prepare("DELETE FROM galleries WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $id, $user_id);
    if ($stmt->execute()) {
        header("location: index2.php?message=Foto berhasil dihapus dari galeri.");
    } else {
        header("location: index2.php?error=Gagal menghapus foto.");
    }
}
?>