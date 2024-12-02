/* Program Perhitungan Matematika.
 * Disusun oleh:
 * Muhammad Ramadhani Pratama
 * 2401301055
 * TI 1E
 * Kamis, 17 Oktober 2024
 * Politeknik Negeri Tanah Laut
 */

 // Kode sumber ini masih jauh dari kata "sempurna", saya sangat terbuka untuk menerima kritik dan saran.
 // Untuk penyuntingan kembali, mohon lakukan dengan hati-hati!

 //**********************************************************************************************************************************************************************************************//

import java.util.Scanner;   // Untuk mengambil kelas masukkan data pengguna.
import java.time.LocalDateTime; // Untuk mengambil kelas waktu lokal.
import java.time.format.DateTimeFormatter;  // Untuk mengambil kelas pemformatan tanggal/waktu. 

public class Program_pilihan_perhitungan {
    public static void main(String[] args) {
        
        Scanner inputData = new Scanner(System.in); // Membuat tipe data masukkan pengguna.
        LocalDateTime now = LocalDateTime.now();    // Memanggil waktu saat ini berdasarkan sistem komputer.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm");  //Membuat format penanggalan.
        String formatTGL = now.format(formatter);   // Membuat data yang berdasarkan tanggal yang telah diformat.

        char userInput;
        do {    // Menggunakan perulangan do, akan menyetak terlebih dahulu menu pilihan-pilihan.
                System.out.println("==========================================================================================");
                System.out.println(formatTGL); // Untuk mencetak waktu sistem saat ini.
                System.out.println("******************************************************************************************");
                System.out.println("Menu pilihan program, pilihlah menu-menu untuk program-program berikut dengan mengetik angka yang ditentukan.");
                System.out.println("(1) - Tabel perkalian matematika 1-10.");
                System.out.println("(2) - Perhitungan bilangan prima dari angka yang diinginkan hingga seterusnya.");
                System.out.println("(3) - Perhitungan nilai rata-rata.");
                System.out.println("(0) - Keluar dari program.");
                System.out.println("==========================================================================================");
                System.out.print("Masukkan pilihanmu... ");
                userInput = inputData.next().charAt(0); // Untuk meminta pengguna memasukkan nilai data.
                switch (userInput) {        // Switch digunakan agar kunci yang dimasukkan oleh peng-input sesuai dengan kunci yang ditentukan sebelumnya.
                    case '1':
                    System.out.println("==========================================================================================");
                    System.out.println("*******************************-TABEL PERKALIAN 1-10-*************************************");
                    // Membuat tabel perkalian dengan fungsi for bersarang sederhana
                    for (int baris = 1; baris <= 10; baris++) {     // Fungsi for yang utama (luar) akan membuat baris (vertikal) perkalian. 
                        for (int kolom = 1; kolom <= 10; kolom++) { // Fungsi for yang di dalam akan membuat kolom (horizontal) perkalian. 
                            System.out.print("|" + baris * kolom + "\t");   // Kedua hasil ekspresi akan dicetak.
                        }
                        System.out.println();
                    }
                    System.out.println("==========================================================================================");
                    System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke menu awal... "); 
                    char anyKey = inputData.next().charAt(0); // Ini disengaja agar pengguna dapat membaca hasil program dengan mudah terlebih dahulu.
                    break; // break digunakan untuk menghentikan eksekusi program untuk kasus ini.
                    case '2':
                    System.out.println("==========================================================================================");
                    System.out.println("******************************-PERHITUNGAN BILANGAN PRIMA-********************************");
                    
                    System.out.print("Masukkan batas awal bilangan prima yang ingin dihitung... ");
                    int batasAwal = inputData.nextInt();    // Menentukan batas awal bilangan prima yang ingin dihitung.
                    System.out.print("Masukkan batas akhir bilangan prima yang ingin dihitung... ");
                    int batasHtg = inputData.nextInt();     // Menentukan batas akhir-nya
                    
                    if (batasAwal < 2) {
                        System.out.println(batasAwal + " >>>>> Batas awal bilangan tersebut bukanlah bilangan prima. Namun akan tetap disertakan.");
                    }
                    System.out.println("******************************************************************************************");
                    System.out.println("Bilangan prima dari " + batasAwal + " hingga " + batasHtg + " adalah: ");
                    
                    for (; batasAwal <= batasHtg; batasAwal++) {
                        boolean bknPrima = true;
                        for (int i = 2; i <= Math.sqrt(batasAwal); i++) {   // Angka akan dihitung pembagiannya mulai dari 2 hingga akar kuadratnya (square root).
                            if (batasAwal % i == 0) {
                                bknPrima = false;   // Jika tidak terdapat angka (selain 1 dan dirinya) yang bisa membagi habis angka yang dimasukkan, maka angka akan dicetak.
                                break;
                            }
                        }
                        if (bknPrima) {
                            System.out.print(batasAwal + " ");
                        }
                    }
                    System.out.println();
                    System.out.println("==========================================================================================");
                    System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke menu awal... ");
                    anyKey = inputData.next().charAt(0);
                    break;
                    case '3':
                    System.out.println("==========================================================================================");
                    System.out.println("*****************************-PERHITUNGAN NILAI RATA-RATA-********************************");
                    
                    System.out.println("Masukkan tiga nilai yang ingin dihitung nilai rata-ratanya.");
                    
                    System.out.print("Silahkan masukkan nilai pertama... ");
                    int Angka1 = inputData.nextInt();
                    System.out.print("Silahkan masukkan nilai kedua... ");
                    int Angka2 = inputData.nextInt();
                    System.out.print("Silahkan masukkan nilai ketiga... ");
                    int Angka3 = inputData.nextInt();

                    System.out.println("Kemudian akan dilakukan beberapa operasi penghitungan dengan rumus X=(F=jumlah nilai-nilai):(N=jumlah banyak data)");
                    int FJumlah = Angka1 + Angka2 + Angka3;
                    double XHasil = FJumlah / 3;
                    System.out.println("Maka rata-rata dari nilai-nilai tersebut adalah " + XHasil);
                    
                    System.out.println("==========================================================================================");
                    System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke menu awal... ");
                    anyKey = inputData.next().charAt(0);
                    break;
                    case '0':
                    System.out.println("==========================================================================================");
                    System.out.println("Program telah ditutup.");
                    break;
                    default: 
                    System.out.println("Kunci yang anda masukkan tidak valid, masukkan angka (1), (2), (3), atau (0).");
                }
        } while (userInput != '0'); // Selama masukkan pengguna bukan '0', maka program (menu pilihan-pilihan) akan terus berlanjut dijalankan (berulang kembali ke menu awal).
    }
}

/* Referensi
 * Modul Algoritma & Pemrograman Praktikum 7 & 8: Operasi Perulangan, 4. Pernyataan do-while. Politeknik Negeri Tanah Laut.
 * Github.com: "Tugas-Percabangan" oleh lazuardh. Tautan: https://github.com/lazuardh/Tugas-Percabangan.
 * Laporan Alogoritma & Pemrograman I, Pertemuan 3 & 4: "Membuat Program Perhitungan" oleh Muhammad Ramadhani Pratama (NIM: 2401301055). Politeknik Negeri Tanah Laut.
 */