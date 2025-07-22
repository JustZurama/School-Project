<?php
// Hancurkan login session
session_start();

// Unset semua variabel session
$_SESSION = array();

// Hancurkan session
session_destroy();

// Redirect ke halaman utama
header("location: ../index.php");
exit;
?>