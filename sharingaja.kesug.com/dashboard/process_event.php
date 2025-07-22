<?php
require_once '../config/db_connect.php';

if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    exit("Akses ditolak.");
}

$user_id = $_SESSION['id'];
$action = $_REQUEST['action'] ?? '';

// Fungsi upload gambar yang sudah ada
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
    $title = $_POST['title'];
    $description = $_POST['description'];
    $event_date = $_POST['event_date'];
    $location = $_POST['location'];
    $image_path = (isset($_FILES['image']) && $_FILES['image']['error'] == 0) ? uploadImage($_FILES['image']) : null;

    $stmt = $mysqli->prepare("INSERT INTO events (user_id, title, description, event_date, location, image_path) VALUES (?, ?, ?, ?, ?, ?)");
    $stmt->bind_param("isssss", $user_id, $title, $description, $event_date, $location, $image_path);
    if ($stmt->execute()) {
        header("location: index2.php?message=Event berhasil dibuat.");
    } else {
        header("location: manage_event.php?error=Gagal membuat event.");
    }
}

// UPDATE
if ($action === 'update' && $_SERVER['REQUEST_METHOD'] === 'POST') {
    $id = $_POST['id'];
    $title = $_POST['title'];
    $description = $_POST['description'];
    $event_date = $_POST['event_date'];
    $location = $_POST['location'];
    
    if (isset($_FILES['image']) && $_FILES['image']['error'] == 0) {
        $image_path = uploadImage($_FILES['image']);
        $stmt = $mysqli->prepare("UPDATE events SET title = ?, description = ?, event_date = ?, location = ?, image_path = ? WHERE id = ? AND user_id = ?");
        $stmt->bind_param("sssssii", $title, $description, $event_date, $location, $image_path, $id, $user_id);
    } else {
        $stmt = $mysqli->prepare("UPDATE events SET title = ?, description = ?, event_date = ?, location = ? WHERE id = ? AND user_id = ?");
        $stmt->bind_param("ssssii", $title, $description, $event_date, $location, $id, $user_id);
    }
    
    if ($stmt->execute()) {
        header("location: index2.php?message=Event berhasil diperbarui.");
    } else {
        header("location: manage_event.php?id=$id&error=Gagal memperbarui event.");
    }
}

// DELETE
if ($action === 'delete' && isset($_GET['id'])) {
    $id = $_GET['id'];
    $stmt = $mysqli->prepare("DELETE FROM events WHERE id = ? AND user_id = ?");
    $stmt->bind_param("ii", $id, $user_id);
    if ($stmt->execute()) {
        header("location: index2.php?message=Event berhasil dihapus.");
    } else {
        header("location: index2.php?error=Gagal menghapus event.");
    }
}
?>