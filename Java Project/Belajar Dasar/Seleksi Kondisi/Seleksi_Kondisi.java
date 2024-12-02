/** PENYELEKSIAN KONDISI
    
    Itu adalah salah satu struktur dalam pemrograman yang digunakan untuk
    melakukan pengujian terhadap satu, dua, atau lebih dari dua kondisi. Pada dasarnya, kita
    memilih statement atau perintah yang akan dieksekusi berdasarkan kondisi tertentu yang telah
    kita tentukan sebelumnya */ 

/* Disunting oleh:
 * Muhammad Ramadhani Pratama
 * TI 1E
 * 2401301055 */

// Sunting kembali dengan hati-hati.

import java.util.Scanner;

public class Seleksi_Kondisi {
    public static void main(String[] args) {

        //=========IF==========//

        int angka = 1;

        if (angka > 5) {
            System.out.println("Angka lebih dari 5.");              // Program akan menjalankan kondisi pertama jika kondisi memenuhi syarat, alias benar.
        } else {
            System.out.println("Angka kurang dari 5.");             // Program akan menjalankan kondisi selanjutnya jika kondisi tidak memenuhi syarat, alias salah.
        } {
            System.out.println("Program selesai dijalankan.");
        }

        //======NESTED_IF=======//                                    // Kondisi IF yang sifatnya lebih kompleks

        int umur = 18;
        boolean memilikiSIM = false;

        if (umur >= 18) {
            System.out.println("Anda adalah seorang dewasa.");
            if (memilikiSIM) {                                        // Kondisi IF ini hanya akan dijalankan jika pernyataan memenuhi syarat IF utama (yang paling atas).
                System.out.println("Anda boleh berkendara.");
            } else {
                System.out.println("Anda tidak memiliki SIM.");
            }
        } else {
            System.out.println("Anda masih di bawah umur.");
        }

        //======IF-ELSE-IF=======//
        Scanner inputData = new Scanner(System.in);
        
        System.out.print("Masukkan sebuah angka bilangan bulat...");
        int Angka = inputData.nextInt();
        
        if (Angka > 0) {
            System.out.println("Angka positif");
        } else if (Angka < 0) {                                     // Jika kondisi bernilai true, maka blok kode pertama akan dijalankan, dan pengecekan kondisi selanjutnya akan dihentikan.
            System.out.println("Angka negatif");
        } else {
            System.out.println("Angka nol");
        }
        
        //=====CONTAINS_METHOD====//

        System.out.println("Masukkan kalimat yang mengandung kata >>Java<<...");
        String kalimat = inputData.nextLine();

        if (kalimat.contains("Java")) {                             // Method ini akan memeriksa apakah kalimat yang dimasukkan terdapat kata "Java".
            System.out.println("Kata mengandung >>Java<<");         // Kondisi IF-ELSE akan dijalankan jika terdapat kata "Java" atau tidak.
        } else {
            System.out.println("String tidak mengandung kata 'Java'");
        } 
        
        

        //=====SWITCH_CASE=====//

        System.out.print("Masukkan angka (1-3)...");
        int angka13 = inputData.nextInt();
        
        switch (angka13) {                                          // digunakan untuk mengevaluasi ekspresi tunggal dan kemudian mengeksekusi blok kode yang sesuai dengan nilai ekspresi tersebut.
            case 1: System.out.println("Anda memasukkan angka satu.");  // Jika mengisi angka 1, maka ini akan dieksekusi.
            break;
            case 2: System.out.println("Anda memasukkan angka dua.");
            break;
            case 3: System.out.println("Anda memasukkan angka tiga.");
            break;
            default: System.out.println("Angka yang Anda masukkan tidak valid.");   // JIka tidak ada pernyataan yang memenuhi syarat, maka ini akan dieksekusi.
        }
        
        //=======OPERATOR_TERNARY======//
        int angkA = 5;
        String hasil;

        hasil = (angkA % 2 == 0) ? "Genap" : "Ganjil";              // Pernyataan pertama (kiri) akan dijalankan jika nilai memenuhi
        System.out.println("Angka " + angkA + " adalah bilangan " + hasil);
    }
}