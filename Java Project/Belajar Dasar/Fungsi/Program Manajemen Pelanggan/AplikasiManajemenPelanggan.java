/* Aplikasi Program Manajemen Pelanggan
 * 
 * Disusun oleh:
 * Nama: Muhammad Ramadhani Pratama
 * NIM_Kelas: 2401301055
 * Politeknik Negeri Tanah Laut
 * 
 * Catatan: Untuk penyuntingan ulang, mohon lakukan dengan hati-hati!
 */

import java.util.Scanner;
import java.util.ArrayList;

// Kelas untuk menampilkan menu utama sekaligus titik/inti jalannya program
public class AplikasiManajemenPelanggan {
    
    public static void main(String[] args) {
        // Deklarasi
        Scanner userInput = new Scanner(System.in);
        ManajemenPelanggan manajemenPelanggan = new ManajemenPelanggan();

        char mainMenuChoice;
        do {
            menuUtama(); // Menampilkan teks pilihan-pilihan menu utama
            mainMenuChoice = userInput.next().charAt(0);
            userInput.nextLine();

            switch (mainMenuChoice) {
                case '1': // Menu untuk 'menambahkan pelanggan ke daftar'
                    System.out.println("================================================================"); // Abaikan baris-baris untuk menampilkan garis
                    System.out.print("Masukkan Nama: ");
                    String nama = userInput.nextLine();
                    System.out.print("Masukkan Email: ");
                    String email = userInput.nextLine();
                    System.out.print("Masukkan Nomor Telepon: ");
                    String nomorTelepon = userInput.nextLine();
                    manajemenPelanggan.menambahPelanggan(nama, email, nomorTelepon); // Memanggil sekaligus memetakan nilai-nilai ke dalam fungsi
                    pauseForUserInput(); // Untuk menjeda sementara program
                    break;
                    
                case '2': // Menu untuk 'menghapus pelanggan dari daftar'
                    System.out.println("================================================================");
                    System.out.print("Masukkan nama pelanggan yang ingin dihapus dari daftar: ");
                    String masukanUntukMenghapus = userInput.nextLine();
                    manajemenPelanggan.menghapusPelanggan(masukanUntukMenghapus); // Memanggil sekaligus memetakan nilai-nilai ke dalam fungsi
                    pauseForUserInput();
                    break;

                case '3': // Menu untuk 'menampilkan daftar pelanggan'
                    System.out.println("================================================================");
                    manajemenPelanggan.tampilkanDaftarPelanggan(); // Memanggil sekaligus memetakan nilai-nilai ke dalam fungsi
                    pauseForUserInput();
                    break;

                case '4': // Menu untuk 'mencari pelanggan'
                    System.out.println("================================================================");
                    System.out.print("Masukkan Nama, Email, atau Nomor Telepon Pelanggan untuk mencarinya: ");
                    String masukanUntukMencari = userInput.nextLine();
                    manajemenPelanggan.mencariPelanggan(masukanUntukMencari); // Memanggil sekaligus memetakan nilai-nilai ke dalam fungsi
                    pauseForUserInput();
                    break;

                case '0': // Pilihan untuk 'menutup program'
                    System.out.println("================================================================");
                    System.out.println("Menutup program...");
                    System.out.println("Program telah ditutup.");
                    break;
                    
                default: // Bagian program jika pengguna memasukkan data 'yang tidak valid' pada menu utama
                    System.out.println("================================================================");
                    System.out.println("Pilihan tidak valid. Masukkan (1), (2), (3), (4) atau (0).");
            }
        } while (mainMenuChoice != '0');
    }

    // Fungsi untuk menampilkan menu utama
    public static void menuUtama() {
        System.out.println("================================================================");
        System.out.println("Menu Utama Aplikasi Manajemen Pelanggan.");
        System.out.println("(1) Tambah Pelanggan");
        System.out.println("(2) Hapus Pelanggan");
        System.out.println("(3) Tampilkan Daftar Pelanggan");
        System.out.println("(4) Cari Pelanggan");
        System.out.println("(0) Tutup program");
        System.out.print("Masukkan pilihan Anda: ");
    }
    
    // Fungsi untuk menjeda sementara program hingga pengguna menekan enter
    public static void pauseForUserInput() {
        System.out.println("================================================================");
        Scanner pauseInput = new Scanner(System.in);
        System.out.print("Tekan ENTER untuk melanjutkan program...");
        pauseInput.nextLine();
    }

}

// Kelas tempat pemetaan nilai-nilai (dengan kata lain: operasi) masukan pengguna akan pendataan/manajemen data pelanggan
class ManajemenPelanggan {
    private ArrayList<Pelanggan> dataPelanggan = new ArrayList<>();

    // Fungsi untuk menambahkan pelanggan ke dalam data pelanggan
    public void menambahPelanggan(String nama, String email, String nomorTelepon) {
        // Menambahkan data masukan ke dalam arrayList yang dikirim dan disimpan di fungsi Pelanggan pada kelas Pelanggan
        dataPelanggan.add(new Pelanggan(nama, email, nomorTelepon));
        System.out.println("Pelanggan berhasil ditambahkan!");
    }
    
    // Fungsi untuk menghapus pelanggan dari data pelanggan
    public void menghapusPelanggan(String identifier) {
        Pelanggan pelangganDihapus = null;
        for (Pelanggan pelanggan : dataPelanggan) {
            if (pelanggan.getNama().equalsIgnoreCase(identifier)) {
                pelangganDihapus = pelanggan;
                break;
            }
        }
        if (pelangganDihapus != null) {
            dataPelanggan.remove(pelangganDihapus);
            System.out.println("Pelanggan berhasil dihapus!");
        } else {
            System.out.println("Pelanggan tidak ditemukan atau masukkan nama dengan benar!");
        }
    }
    
    // Fungsi untuk menampilkan daftar pelanggan pada data pelanggan
    public void tampilkanDaftarPelanggan() {
        if (dataPelanggan.isEmpty()) { 
            // Jika tidak ada pelanggan yang terdaftar
            System.out.println("Tidak ada pelanggan yang terdaftar di dalam data.");
        } else {
            System.out.println("Daftar Pelanggan:");
            for (int i = 0; i < dataPelanggan.size(); i++) {
                System.out.println((i + 1) + ". " + dataPelanggan.get(i));
            }
            System.out.println("Daftar berhasil ditampilkan!");
        }
    }
    
    // Fungsi untuk mencari pelanggan yang ada pada data pelanggan
    public void mencariPelanggan(String identifier) {
        boolean found = false; // Untuk melacak jika pengguna dapat ditemukan 
        int nomorUrut = 1;

        System.out.println("Pelanggan yang berhasil ditemukan: ");
        for (Pelanggan pelanggan : dataPelanggan) {
            // Setiap masukan pengguna dibandingkan dengan data yang tersimpan
            if (pelanggan.getNama().toLowerCase().contains(identifier.toLowerCase()) ||
                pelanggan.getEmail().toLowerCase().contains(identifier.toLowerCase())  ||
                pelanggan.getNomorTelepon().contains(identifier)) {
                System.out.println(nomorUrut + ". " + pelanggan);
                found = true; 
            }
            nomorUrut++; // Dibuat untuk dapat menampilkan nomor urutan setelah 1
        }
        if (!found) {
            System.out.println("Pelanggan tidak ditemukan!");
        }
    }
}

// Kelas sebagai 'wadah' penyimpanan data-data pelanggan
class Pelanggan {
    private String nama;
    private String email;
    private String nomorTelepon;

    public Pelanggan(String nama, String email, String nomorTelepon) {
        // Untuk memastikan bahwa nilai parameter diteruskan ke atribut instance dari objek yang sedang dibuat atau diproses
        this.nama = nama;
        this.email = email;
        this.nomorTelepon = nomorTelepon;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }
  
    public String toString() {
        return "Nama: " + nama + ", Email: " + email + ", Nomor Telepon: " + nomorTelepon;
    }
}

/* Referensi:
 * 1. 'Modul Praktikum 7 & 8: Operasi Perulangan'. Politeknik Negeri Tanah Laut: Tim Pengajar 'Algoritma dan Pemrograman' Politala.
 * 2. 'Modul Praktikum 13: Fungsi pada Java Bagian 1'. Politeknik Negeri Tanah Laut: Tim Pengajar 'Algoritma dan Pemrograman' Politala.
 * 3. 'JAVA_dasar_programming'. GitHub: Kelas Terbuka. Tautan: https://github.com/kelasterbuka/JAVA_dasar_programming.
 * 4. 'JAVA_dasar_programming/34-fungsi atau method (pengenalan)/'. GitHub: Kelas Terbuka. Tautan: https://github.com/kelasterbuka/JAVA_dasar_programming/tree/master/34-fungsi%20atau%20method%20(pengenalan).
 */