<?php
require_once 'config.php';

// Cek jika user belum login, redirect ke halaman login
if (!isset($_SESSION["loggedin"]) || $_SESSION["loggedin"] !== true) {
    header("location: login.php");
    exit;
}

$username = htmlspecialchars($_SESSION["username"]);
?>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Kair-Net Management</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/xlsx/dist/xlsx.full.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Inter', sans-serif; }
        .tab-active { 
            background-color: white;
            color: #2563eb;
            font-weight: 600;
        }
        .tab-inactive {
            color: white;
        }
    </style>
</head>
<body class="bg-slate-50">

    <!-- Header -->
    <header class="bg-blue-600 shadow-md sticky top-0 z-10 text-white">
        <div class="container mx-auto px-6 py-4">
            <div class="flex items-center justify-between">
                <div>
                    <h1 class="text-2xl font-bold">Kair Net</h1>
                    <p class="text-sm">Sistem Menejemen Rental PlayStation</p>
                </div>
                <div class="flex items-center">
                    <span class="mr-4">Login sebagai <strong><?php echo $username; ?></strong></span>
                    <a href="logout.php" class="bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition">Logout</a>
                </div>
            </div>
            <!-- Navigasi Tab -->
            <nav class="mt-4 border-b border-blue-400">
                <button onclick="showTab('beranda')" class="py-2 px-4 tab-active rounded-t-lg font-semibold" id="tab-beranda">Beranda</button>
                <button onclick="showTab('daftarSewa')" class="py-2 px-4 tab-inactive hover:bg-white hover:text-blue-600 rounded-t-lg font-semibold" id="tab-daftarSewa">Daftar Sesi</button>
                <button onclick="showTab('statistik')" class="py-2 px-4 tab-inactive hover:bg-white hover:text-blue-600 rounded-t-lg font-semibold" id="tab-statistik">Statistik</button>
            </nav>
        </div>
    </header>


    <!-- Konten Utama -->
    <main class="container mx-auto px-6 py-8">
        <!-- Notifikasi -->
        <div id="notification-area" class="fixed top-24 right-6 z-20"></div>

        <!-- Tab Beranda: Tambah Penyewa Baru -->
        <div id="beranda" class="tab-content">
            <div class="flex justify-between items-center mb-6">
                <h2 class="text-2xl font-bold text-slate-800">Tambah Penyewa Baru</h2>
                <span class="text-sm text-slate-500" id="current-date"></span>
            </div>
            <form id="form-tambah-sewa">
                <div class="mb-4">
                    <label for="nama_klien" class="block text-slate-700 font-medium mb-2">Nama Klien</label>
                    <input type="text" id="nama_klien" name="nama_klien" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="mb-4">
                    <label for="tipe_ps" class="block text-slate-700 font-medium mb-2">Jenis PlayStation</label>
                    <select id="tipe_ps" name="tipe_ps" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                        <option value="">-- Pilih PS --</option>
                        <option value="PS2" data-harga="3000">PS 2 (Rp 3.000,00)</option>
                        <option value="PS3" data-harga="5000">PS 3 (Rp 5.000,00)</option>
                        <option value="PS4" data-harga="7000">PS 4 (Rp 7.000,00)</option>
                        <option value="PS5" data-harga="9000">PS 5 (Rp 9.000,00)</option>
                    </select>
                </div>
                <div class="mb-6">
                    <label for="lama_sewa" class="block text-slate-700 font-medium mb-2">Lama Sewa (Jam)</label>
                    <input type="number" id="lama_sewa" name="lama_sewa" min="1" value="1" class="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>
                <div class="bg-blue-50 p-6 rounded-lg text-center mb-6">
                    <p class="text-slate-600 text-lg">Total Biaya:</p>
                    <p id="total_biaya" class="text-4xl font-bold text-blue-600">Rp 0</p>
                </div>
                    <button type="submit" class="w-full bg-blue-600 text-white font-bold py-3 px-4 rounded-lg hover:bg-blue-700 transition duration-300 text-lg">Mulai Sesi</button>
            </form>
        </div>

        <!-- Tab Daftar Sewa -->
        <div id="daftarSewa" class="tab-content hidden">
             <div class="flex justify-between items-center mb-6">
                <h2 class="text-2xl font-bold text-slate-800">Daftar Sewa yang Sedang Berjalan</h2>
                <span class="text-sm text-slate-500" id="current-date-sewa"></span>
            </div>
            <div id="daftar-sewa-container" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                <!-- Kartu sewa akan dimuat di sini oleh JavaScript -->
            </div>
        </div>

        <!-- Tab Statistik -->
        <div id="statistik" class="tab-content hidden">
            <div class="bg-white p-8 rounded-xl shadow-lg">
                <div class="flex justify-between items-center mb-6">
                    <h2 class="text-2xl font-bold text-slate-800">Data Statistik Penyewaan</h2>
                    <div>
                        <select id="filter-statistik" class="border border-slate-300 rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <option value="minggu">Per Minggu Ini</option>
                            <option value="bulan">Per Bulan Ini</option>
                            <option value="tahun">Per Tahun Ini</option>
                        </select>
                        <button id="unduh-excel" class="bg-green-600 text-white font-bold py-2 px-4 rounded-lg hover:bg-green-700 transition ml-2">Unduh Excel</button>
                    </div>
                </div>
                <canvas id="statistikChart"></canvas>
            </div>
        </div>
    </main>

    <!-- Modal Edit -->
    <div id="editModal" class="fixed inset-0 bg-black bg-opacity-50 z-30 hidden items-center justify-center">
        <div class="bg-white p-8 rounded-xl shadow-lg max-w-lg w-full">
            <h2 class="text-2xl font-bold text-slate-800 mb-6">Edit Sesi Penyewaan</h2>
            <form id="form-edit-sewa">
                <input type="hidden" id="edit_id" name="id">
                <div class="mb-4">
                    <label for="edit_nama_klien" class="block text-slate-700 font-medium mb-2">Nama Klien</label>
                    <input type="text" id="edit_nama_klien" name="nama_klien" class="w-full px-4 py-2 border border-slate-300 rounded-lg" required>
                </div>
                <div class="mb-4">
                    <label for="edit_tipe_ps" class="block text-slate-700 font-medium mb-2">Jenis PlayStation</label>
                    <select id="edit_tipe_ps" name="tipe_ps" class="w-full px-4 py-2 border border-slate-300 rounded-lg" required>
                         <option value="PS2" data-harga="3000">PS 2 (Rp 3.000,00)</option>
                         <option value="PS3" data-harga="5000">PS 3 (Rp 5.000,00)</option>
                         <option value="PS4" data-harga="7000">PS 4 (Rp 7.000,00)</option>
                         <option value="PS5" data-harga="9000">PS 5 (Rp 9.000,00)</option>
                    </select>
                </div>
                <div class="mb-6">
                    <label for="edit_lama_sewa" class="block text-slate-700 font-medium mb-2">Tambah Durasi Sewa (Jam)</label>
                    <input type="number" id="edit_lama_sewa" name="tambah_jam" min="0" value="0" class="w-full px-4 py-2 border border-slate-300 rounded-lg">
                    <p class="text-sm text-slate-500 mt-1">Isi dengan 0 jika tidak ada penambahan waktu.</p>
                </div>
                <div class="flex justify-end gap-4">
                    <button type="button" onclick="closeModal()" class="bg-slate-200 text-slate-800 font-bold py-2 px-4 rounded-lg hover:bg-slate-300">Batal</button>
                    <button type="submit" class="bg-blue-600 text-white font-bold py-2 px-4 rounded-lg hover:bg-blue-700">Simpan Perubahan</button>
                </div>
            </form>
        </div>
    </div>


<script>
document.addEventListener('DOMContentLoaded', function() {
    // Harga PS
    const HARGA_PS = {
        'PS2': 3000,
        'PS3': 5000,
        'PS4': 7000,
        'PS5': 9000
    };

    // Elemen UI
    const formSewa = document.getElementById('form-tambah-sewa');
    const tipePsEl = document.getElementById('tipe_ps');
    const lamaSewaEl = document.getElementById('lama_sewa');
    const totalBiayaEl = document.getElementById('total_biaya');
    const currentDateEl = document.getElementById('current-date');
    const currentDateSewaEl = document.getElementById('current-date-sewa');
    const daftarSewaContainer = document.getElementById('daftar-sewa-container');
    const notificationArea = document.getElementById('notification-area');
    
    // Elemen Edit Modal
    const editModal = document.getElementById('editModal');
    const formEditSewa = document.getElementById('form-edit-sewa');

    // Fungsi untuk memformat angka menjadi Rupiah
    function formatRupiah(angka) {
        return new Intl.NumberFormat('id-ID', { style: 'currency', currency: 'IDR', minimumFractionDigits: 0 }).format(angka);
    }

    // Fungsi untuk menghitung total biaya
    function hitungTotalBiaya() {
        const tipePS = tipePsEl.value;
        const lamaSewa = parseInt(lamaSewaEl.value) || 0;
        if (tipePS && HARGA_PS[tipePS]) {
            const total = HARGA_PS[tipePS] * lamaSewa;
            totalBiayaEl.textContent = formatRupiah(total);
        } else {
            totalBiayaEl.textContent = formatRupiah(0);
        }
    }

    // Event listener untuk form tambah sewa
    tipePsEl.addEventListener('change', hitungTotalBiaya);
    lamaSewaEl.addEventListener('input', hitungTotalBiaya);

    // Tampilkan tanggal hari ini
    function tampilkanTanggal() {
        const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
        const tanggalHariIni = new Date().toLocaleDateString('id-ID', options);
        if(currentDateEl) currentDateEl.textContent = `Hari ini: ${tanggalHariIni}`;
        if(currentDateSewaEl) currentDateSewaEl.textContent = `Hari ini: ${tanggalHariIni}`;
    }

    // Fungsi untuk menampilkan notifikasi
    function showNotification(message, type = 'success') {
        const notifId = 'notif-' + Date.now();
        const bgColor = type === 'success' ? 'bg-green-500' : (type === 'error' ? 'bg-red-500' : 'bg-blue-500');
        const notification = `
            <div id="${notifId}" class="w-80 ${bgColor} text-white p-4 rounded-lg shadow-lg mb-4 transform transition-all duration-300 translate-x-full">
                ${message}
            </div>
        `;
        notificationArea.insertAdjacentHTML('beforeend', notification);
        
        setTimeout(() => {
            document.getElementById(notifId).classList.remove('translate-x-full');
        }, 10);
        
        setTimeout(() => {
            const notifElement = document.getElementById(notifId);
            if (notifElement) {
                notifElement.classList.add('translate-x-full');
                setTimeout(() => notifElement.remove(), 300);
            }
        }, 5000);
    }

    // Submit form tambah sewa
    formSewa.addEventListener('submit', function(e) {
        e.preventDefault();
        const formData = new FormData(formSewa);
        fetch('api.php?action=tambah_sewa', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                showNotification('Sesi baru berhasil ditambahkan!');
                formSewa.reset();
                hitungTotalBiaya();
                loadDaftarSewa(); // Muat ulang daftar sewa
                showTab('daftarSewa'); // Pindah ke tab daftar sewa
            } else {
                showNotification(data.message || 'Gagal menambahkan sesi.', 'error');
            }
        })
        .catch(() => showNotification('Terjadi kesalahan jaringan.', 'error'));
    });

    // Muat daftar sewa yang sedang berjalan
    function loadDaftarSewa() {
        fetch('api.php?action=get_sewa_berjalan')
            .then(response => response.json())
            .then(data => {
                daftarSewaContainer.innerHTML = '';
                if (data.length > 0) {
                    data.forEach(sewa => {
                        const card = `
                            <div class="bg-white p-6 rounded-xl shadow-lg border-l-4 ${sewa.waktu_habis ? 'border-red-500' : 'border-blue-500'}" id="sewa-${sewa.id}" data-waktu-selesai="${sewa.waktu_selesai}">
                                <h3 class="text-xl font-bold text-slate-800">${sewa.nama_klien}</h3>
                                <div class="text-slate-600 mt-2 space-y-1">
                                    <p><strong>Tipe PS:</strong> ${sewa.tipe_ps}</p>
                                    <p><strong>Total Biaya:</strong> ${formatRupiah(sewa.total_biaya)}</p>
                                    <p><strong>Waktu Mulai:</strong> ${new Date(sewa.waktu_mulai).toLocaleTimeString('id-ID')}</p>
                                    <p><strong>Waktu Selesai:</strong> ${new Date(sewa.waktu_selesai).toLocaleTimeString('id-ID')}</p>
                                </div>
                                <hr class="my-4">
                                <div class="text-center">
                                    <p class="text-sm font-medium text-slate-500">Sisa Waktu:</p>
                                    <p class="sisa-waktu text-3xl font-bold ${sewa.waktu_habis ? 'text-red-500' : 'text-slate-800'}">
                                        ${sewa.waktu_habis ? 'WAKTU HABIS!' : 'Memuat...'}
                                    </p>
                                </div>
                                <div class="mt-4 flex gap-2">
                                    <button onclick="openEditModal(${sewa.id})" class="w-full bg-yellow-500 text-white font-bold py-2 px-4 rounded-lg hover:bg-yellow-600">Edit</button>
                                    <button onclick="selesaikanSesi(${sewa.id})" class="w-full bg-red-500 text-white font-bold py-2 px-4 rounded-lg hover:bg-red-600">Selesaikan</button>
                                </div>
                            </div>
                        `;
                        daftarSewaContainer.insertAdjacentHTML('beforeend', card);
                    });
                } else {
                    daftarSewaContainer.innerHTML = '<p class="text-slate-500 col-span-full text-center">Tidak ada sesi sewa yang sedang berjalan.</p>';
                }
            });
    }

    // Timer countdown
    setInterval(() => {
        document.querySelectorAll('[data-waktu-selesai]').forEach(card => {
            const sisaWaktuEl = card.querySelector('.sisa-waktu');
            const waktuSelesai = new Date(card.dataset.waktuSelesai).getTime();
            const sekarang = new Date().getTime();
            const selisih = waktuSelesai - sekarang;

            if (selisih > 0) {
                const jam = Math.floor((selisih % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const menit = Math.floor((selisih % (1000 * 60 * 60)) / (1000 * 60));
                const detik = Math.floor((selisih % (1000 * 60)) / 1000);
                sisaWaktuEl.textContent = `${String(jam).padStart(2, '0')}:${String(menit).padStart(2, '0')}:${String(detik).padStart(2, '0')}`;
            } else {
                if (sisaWaktuEl.textContent !== 'WAKTU HABIS!') {
                    sisaWaktuEl.textContent = 'WAKTU HABIS!';
                    sisaWaktuEl.classList.remove('text-slate-800');
                    sisaWaktuEl.classList.add('text-red-500');
                    card.classList.remove('border-blue-500');
                    card.classList.add('border-red-500');
                    const klienNama = card.querySelector('h3').textContent;
                    showNotification(`Waktu sewa untuk ${klienNama} telah habis!`, 'warning');
                }
            }
        });
    }, 1000);

    // Fungsi untuk menyelesaikan sesi
    window.selesaikanSesi = function(id) {
        if (confirm('Apakah Anda yakin ingin menyelesaikan sesi ini?')) {
            fetch(`api.php?action=selesaikan_sewa&id=${id}`, { method: 'POST' })
                .then(res => res.json())
                .then(data => {
                    if (data.success) {
                        showNotification('Sesi berhasil diselesaikan.');
                        loadDaftarSewa();
                    } else {
                        showNotification(data.message || 'Gagal menyelesaikan sesi.', 'error');
                    }
                });
        }
    };
    
    // Fungsi untuk modal edit
    window.openEditModal = function(id) {
        fetch(`api.php?action=get_sewa_by_id&id=${id}`)
        .then(res => res.json())
        .then(data => {
            if(data) {
                document.getElementById('edit_id').value = data.id;
                document.getElementById('edit_nama_klien').value = data.nama_klien;
                document.getElementById('edit_tipe_ps').value = data.tipe_ps;
                document.getElementById('edit_lama_sewa').value = 0; // Reset penambahan jam
                editModal.classList.remove('hidden');
                editModal.classList.add('flex');
            } else {
                showNotification('Gagal memuat data sesi.', 'error');
            }
        });
    }

    window.closeModal = function() {
        editModal.classList.add('hidden');
        editModal.classList.remove('flex');
    }

    formEditSewa.addEventListener('submit', function(e) {
        e.preventDefault();
        const formData = new FormData(formEditSewa);
        fetch('api.php?action=update_sewa', {
            method: 'POST',
            body: formData
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                showNotification('Sesi berhasil diperbarui.');
                closeModal();
                loadDaftarSewa();
            } else {
                showNotification(data.message || 'Gagal memperbarui sesi.', 'error');
            }
        });
    });

    // Statistik
    let statistikChart;
    const filterStatistikEl = document.getElementById('filter-statistik');

    function loadStatistik(filter = 'minggu') {
        fetch(`api.php?action=get_statistik&filter=${filter}`)
            .then(response => response.json())
            .then(data => {
                const ctx = document.getElementById('statistikChart').getContext('2d');
                if (statistikChart) {
                    statistikChart.destroy();
                }
                statistikChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: data.labels,
                        datasets: [{
                            label: 'Jumlah Penyewa',
                            data: data.values,
                            backgroundColor: 'rgba(59, 130, 246, 0.5)',
                            borderColor: 'rgba(59, 130, 246, 1)',
                            borderWidth: 1,
                            borderRadius: 5
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                                ticks: {
                                    stepSize: 1
                                }
                            }
                        },
                        plugins: {
                            legend: {
                                display: false
                            }
                        }
                    }
                });
            });
    }

    filterStatistikEl.addEventListener('change', function() {
        loadStatistik(this.value);
    });
    
    // Unduh Excel
    document.getElementById('unduh-excel').addEventListener('click', function() {
        const filter = filterStatistikEl.value;
        fetch(`api.php?action=get_data_for_excel&filter=${filter}`)
        .then(res => res.json())
        .then(data => {
            if(data && data.length > 0){
                const worksheet = XLSX.utils.json_to_sheet(data);
                const workbook = XLSX.utils.book_new();
                XLSX.utils.book_append_sheet(workbook, worksheet, "Statistik Penyewaan");
                XLSX.writeFile(workbook, `Statistik_Penyewaan_${filter}.xlsx`);
                showNotification('File Excel berhasil diunduh.');
            } else {
                showNotification('Tidak ada data untuk diunduh.', 'error');
            }
        });
    });


    // Navigasi Tab
    window.showTab = function(tabName) {
        document.querySelectorAll('.tab-content').forEach(tab => tab.classList.add('hidden'));
        document.getElementById(tabName).classList.remove('hidden');

        document.querySelectorAll('nav button').forEach(btn => {
            btn.classList.remove('tab-active');
            btn.classList.add('tab-inactive');
        });
        document.getElementById('tab-' + tabName).classList.add('tab-active');
        document.getElementById('tab-' + tabName).classList.remove('tab-inactive');
        
        if (tabName === 'daftarSewa') {
            loadDaftarSewa();
        } else if (tabName === 'statistik') {
            loadStatistik(filterStatistikEl.value);
        }
    }

    // Inisialisasi
    tampilkanTanggal();
    hitungTotalBiaya();
    loadDaftarSewa();
});
</script>
</body>
</html>
