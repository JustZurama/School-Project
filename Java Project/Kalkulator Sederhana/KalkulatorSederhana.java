/*
 * === Program Aplikasi Kalkulator Sederhana ===
 * Disusun oleh:
 * Nama: Muhammad Ramadhani Pratama
 * NIM: 2401301055
 * Kelas: TI
 * Institusi: Politeknik Negeri Tanah Laut
 */

import java.util.Scanner;

public class KalkulatorSederhana {
    public static void main(String[] args) {
        
        // Membuat atribut instance dari kelas OperasiPerhitungan
        OperasiPerhitungan operator = new OperasiPerhitungan();
        Scanner inputData = new Scanner(System.in);
        
        System.out.println("Program Aplikasi kalkulator sederhana");
        System.out.println("============================");

        char userInput;
        do {
            double num1, num2; // Ini dideklarasikan sebagai variabel untuk bilangan yang akan dihitung

            // Meminta pengguna memasukkan angka untuk kemudian dilakukan perhitungan
            System.out.print("Masukkan angka pertama: ");
            num1 = inputData.nextDouble(); 
            System.out.print("Masukkan angka kedua: ");
            num2 = inputData.nextDouble(); 
            
            // Memanggil fungsi untuk menampilkan menu pilihan operasi perhitungan atau menutup program
            tampilkanMenu();
            // Meminta pengguna untuk memasukkan penentuan pilihan operasi
            userInput = inputData.next().charAt(0);
            
            double result = 0; // Ini dideklarasikan sebagai nol untuk kemudian diisi dengan hasil perhitungan

            switch (userInput) {
                case '1':
                    // Melakukan perhitungan (melalui pemanggilan masing-masing fungsi operasi) berdasarkan pilihan pengguna
                    result = operator.penambahan(num1, num2);
                    System.out.println(result);
                    pauseForUserInput();
                    break;

                case '2':
                    result = operator.pengurangan(num1, num2);
                    System.out.println(result);
                    pauseForUserInput();
                    break;

                case '3':
                    result = operator.perkalian(num1, num2);
                    System.out.println(result);
                    pauseForUserInput();
                    break;

                case '4':
                    if (num2 != 0) {
                    result = operator.pembagian(num1, num2);
                    System.out.println(result);
                    } else {
                        System.out.println("Kesalahan: Pembagian dengan nol (0)");
                    }
                    pauseForUserInput();
                    break;

                case '0':
                    System.out.println("============================");
                    System.out.println("Menutup program...");
                    break;
        
                default: 
                System.out.println("Kunci yang anda masukkan tidak valid, masukkan angka (1), (2), (3), (4) atau (0).");
            }
        } while (userInput != '0');
        inputData.close();
    }

    // Fungsi untuk menampilkan menu pilihan-pilihan operasi
    public static void tampilkanMenu() {
        System.out.println("============================");
        System.out.println("Pilih operasi yang diinginkan atau tutup program: ");
        System.out.println("(1) - Penambahan (+)");
        System.out.println("(2) - Pengurangan (-)");
        System.out.println("(3) - Perkalian (*)");
        System.out.println("(4) - Pembagian (÷)");
        System.out.println("(0) - Tutup program");
        System.out.print("Masukkan pilihanmu... ");
    }

    // Fungsi untuk menjeda sementara program
    public static void pauseForUserInput() {
        Scanner pauseInput = new Scanner(System.in);
        System.out.println("============================");
        System.out.println("Tekan ENTER untuk kembali ke awal program...");
        System.out.print("============================");
        pauseInput.nextLine(); // Menunggu pengguna menekan Enter
    }
}

// Kelas berisi fungsi-fungsi untuk melakukan operasi perhitungan
class OperasiPerhitungan {
    public double penambahan(double a, double b) {
        System.out.println("============================");
        System.out.print("Hasil dari " + a + " + " + b + " = ");
        return a + b;
    } 
    
    public double pengurangan(double a, double b) {
        System.out.println("============================");
        System.out.print("Hasil dari " + a + " - " + b + " = ");
        return a - b;
    }
    
    public double perkalian(double a, double b) {
        System.out.println("============================");
        System.out.print("Hasil dari " + a + " * " + b + " = ");
        return a * b;
    }
    
    public double pembagian(double a, double b) {
        System.out.println("============================");
        System.out.print("Hasil dari " + a + " ÷ " + b + " = ");
        return a / b;
    }
}

