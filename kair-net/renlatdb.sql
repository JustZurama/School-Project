-- Membuat database (jika belum ada)
CREATE DATABASE IF NOT EXISTS rentaldb;

-- Menggunakan database `rentaldb`
USE rentaldb;

-- Tabel untuk menyimpan data admin
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_lengkap` varchar(255) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabel untuk menyimpan sesi penyewaan
CREATE TABLE `sesi_sewa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `nama_klien` varchar(255) NOT NULL,
  `tipe_ps` varchar(10) NOT NULL,
  `lama_sewa` int(11) NOT NULL,
  `waktu_mulai` datetime NOT NULL,
  `waktu_selesai` datetime NOT NULL,
  `total_biaya` int(11) NOT NULL,
  `status` enum('berjalan','selesai') NOT NULL DEFAULT 'berjalan',
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `sesi_sewa_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

