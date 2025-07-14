<?php
// Memulai session
session_start();
 
// Menghapus semua variabel session
$_SESSION = array();
 
// Menghancurkan session
session_destroy();
 
// Redirect ke halaman login
header("location: login.php");
exit;
?>
