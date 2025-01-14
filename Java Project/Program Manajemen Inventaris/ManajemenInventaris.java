/* 
 * Program Manajemen Inventaris
 * 
 * Disusun oleh:
 * Ahmad Khairi (2401301104)
 * Gusti Chandra Miftahul Munir (2401301016)
 * Muhammad Ramadhani Pratama (2401301055)
 * 
 * Politeknik Negeri Tanah Laut
 */

// Catatan: untuk penyuntingan ulang, mohon lakukan dengan hati-hati.

import java.io.*;
import java.util.*;

public class ManajemenInventaris {

    public static void main(String[] args) {
        // Membuat atribut untuk instance kelas
        OperasiManajemenInventaris operasi = new OperasiManajemenInventaris();

        Scanner scanner = new Scanner(System.in);
        String namaFile = "inventaris.txt";

        // Deklarasi variabel inventaris
        String[][] inventaris;

        // Memuat inventaris dari file jika ada        
        inventaris = operasi.muatDariFile(namaFile);

        boolean running = true;
        while (running) {
            System.out.println("\n================================");
            System.out.println("Menu Manajemen Inventaris:");
            System.out.println("1 - Tampilkan daftar barang pada inventaris");
            System.out.println("2 - Cari barang berdasarkan kode");
            System.out.println("3 - Tambah barang baru ke daftar");
            System.out.println("4 - Hapus barang dari daftar");
            System.out.println("5 - Simulasi penjualan");
            System.out.println("6 - Simpan perubahan data");
            System.out.println("0 - Keluar dari program");
            System.out.println("Pilih menu dengan memasukkan karakter yang sesuai.");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    System.out.println("\n================================");
                    operasi.tampilkanInventaris(inventaris);
                    operasi.pauseForUserInput();
                    break;
                case 2:
                    System.out.print("Masukkan kode unik barang pada daftar: ");
                    String kode = scanner.nextLine();
                    operasi.cariBarang(kode, inventaris);
                    operasi.pauseForUserInput();
                    break;
                case 3:
                    System.out.println("\n================================");
                    System.out.print("Masukkan kode unik barang: ");
                    String kodeBaru = scanner.nextLine();
                    System.out.print("Masukkan nama barang: ");
                    String namaBarang = scanner.nextLine();
                    System.out.print("Masukkan harga barang: ");
                    String hargaBarang = scanner.nextLine();
                    System.out.print("Masukkan jumlah stok: ");
                    String jumlahStok = scanner.nextLine();
                    inventaris = operasi.tambahBarang(new String[]{kodeBaru, namaBarang, hargaBarang, jumlahStok}, inventaris);
                    System.out.println("Data barang telah disimpan.");
                    operasi.pauseForUserInput();
                    break;
                case 4:
                    System.out.println("\n================================");
                    System.out.print("Masukkan kode unik barang yang ingin dihapus dari daftar: ");
                    String kodeHapus = scanner.nextLine();
                    inventaris = operasi.hapusBarang(kodeHapus, inventaris);
                    operasi.pauseForUserInput();
                    break;                
                case 5:
                    System.out.println("\n================================");
                    System.out.print("Masukkan kode unik barang pada daftar: ");
                    String kodeJual = scanner.nextLine();
                    String[] barangDitemukan = operasi.cariBarang(kodeJual, inventaris);
                    if (barangDitemukan != null) {
                        System.out.print("Masukkan jumlah yang terjual: ");
                        int jumlahTerjual = scanner.nextInt();
                        inventaris = operasi.penjualanBarang(kodeJual, jumlahTerjual, inventaris);
                    } else {
                        System.out.println("Operasi simulasi penjualan gagal.");
                    }
                    operasi.pauseForUserInput();
                    break;
                case 6:
                    System.out.println("\n================================");
                    System.out.print("Menyimpan informasi ke file");
                    operasi.simpanKeFile(inventaris, namaFile);
                    break;
                case 0:
                    System.out.println("\n================================");
                    System.out.print("Menutup program");
                    operasi.programDelay();
                    System.out.println("Program ditutup.");
                    running = false;
                    break;
                default:
                    System.out.println("\n================================");
                    System.out.println("Pilihan tidak valid. Masukkan karakter yang sesuai dengan yang ditentukan.");
            }
        }
        scanner.close();
    }
}

class OperasiManajemenInventaris {
    
    // Metode untuk menampilkan seluruh inventaris
    public void tampilkanInventaris(String[][] inventaris) {
        System.out.println("Daftar Inventaris:");
        for (String[] barang : inventaris) {
            System.out.println(barang[0] + ", " + barang[1] + ", Harga: " + barang[2] + ", Stok: " + barang[3]);
        }
        System.out.println("Daftar berhasil ditampilkan.");
    }

    // Metode untuk mencari barang berdasarkan kode barang
    public String[] cariBarang(String kodeBarang, String[][] inventaris) {
        System.out.println("Barang yang ditemukan:");
        for (String[] barang : inventaris) {
            if (barang[0].equals(kodeBarang)) {
                System.out.println(barang[0] + ", " + barang[1] + ", Harga: " + barang[2] + ", Stok: " + barang[3]);
                return barang;
            }
        }
        System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan.");
        return null;
    }
    
    // Metode untuk menambahkan barang ke inventaris
    public String[][] tambahBarang(String[] barang, String[][] inventaris) {
        String[][] newInventaris = Arrays.copyOf(inventaris, inventaris.length + 1);
        newInventaris[inventaris.length] = barang;
        return newInventaris;
    }

    // Metode untuk menghapus barang berdasarkan kode barang
    public String[][] hapusBarang(String kodeBarang, String[][] inventaris) {
        List<String[]> daftarBaru = new ArrayList<>();
        boolean ditemukan = false;

        for (String[] barang : inventaris) {
            if (!barang[0].equals(kodeBarang)) {
                daftarBaru.add(barang);
            } else {
                ditemukan = true;
            }
        }

        if (ditemukan) {
            System.out.println("Barang dengan kode " + kodeBarang + " berhasil dihapus dari daftar.");
        } else {
            System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan.");
        }

        return daftarBaru.toArray(new String[0][0]);
    }

    
    // Metode untuk melakukan penjualan barang
    public String[][] penjualanBarang(String kodeBarang, int jumlahTerjual, String[][] inventaris) {
        for (String[] barang : inventaris) {
            if (barang[0].equals(kodeBarang)) {
                int stokLama = Integer.parseInt(barang[3]);
                if (stokLama >= jumlahTerjual) {
                    barang[3] = String.valueOf(stokLama - jumlahTerjual);
                    System.out.println("Penjualan berhasil. Stok tersisa: " + barang[3]);
                } else {
                    System.out.println("Penjualan gagal. Stok tidak mencukupi.");
                }
                return inventaris;
            }
        }
        System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan.");
        return inventaris;
    }

    // Metode untuk menyimpan inventaris ke file
    public void simpanKeFile(String[][] inventaris, String namaFile) {
        programDelay(); // Memberikan animasi delay    
        
        // Menyimpan file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (String[] barang : inventaris) {
                writer.write(String.join(",", barang));
                writer.newLine();
            }
            System.out.println("Penyimpanan sukses!");
            System.out.println("Perubahan data berhasil disimpan ke file: " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan file: " + e.getMessage());
        }
    }

    // Metode untuk memuat inventaris dari file
    public String[][] muatDariFile(String namaFile) {
        System.out.print("Memuat inventaris dari file");
        programDelay(); // Memberikan animasi delay

        // Memulai pemuatan file
        List<String[]> inventaris = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inventaris.add(line.split(","));
            }
            System.out.println("Inventaris berhasil dimuat dari file: " + namaFile);
        } catch (IOException e) {
            System.out.println("File tidak ditemukan atau terjadi kesalahan: " + e.getMessage());
        }
        return inventaris.toArray(new String[0][0]);
    }

    // Metode untuk memberikan delay pada program (abaikan, karena bukan bagian penting program)
    public void programDelay() {
        int titik = 3; // Jumlah titik untuk animasi delay
            for (int i = 0; i < titik; i++) {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    System.out.println("Thread terganggu: " + e.getMessage());
                }
                System.out.print(".");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread terganggu: " + e.getMessage());
            }
        System.out.println();
    }

    // Metode untuk menjeda sementara program (abaikan, karena bukan bagian penting program)
    public void pauseForUserInput() {
        Scanner pauseInput = new Scanner(System.in);
        System.out.println("================================");
        System.out.print("Tekan ENTER untuk kembali ke menu awal...");
        pauseInput.nextLine();
    }

}