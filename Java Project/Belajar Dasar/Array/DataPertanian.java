/**
 * Membuat IoT (dengan Java Array) yang Menyimpan dan Memanipulasi Data pada Sektor Pertanian
 * 
 * Disusun oleh:
 *  Muhammad Ramadhani Pratama
 *  TI 1E: 2401301055
 * Politeknik Negeri Tanah Laut
 * 
 * (Untuk penyuntingan ulang, mohon lakukan dengan hati-hati!)
 */

import java.util.Scanner;

public class DataPertanian {
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);

        // Mendeklarasikan dan inisialisasi data Array
        double[] kelembaban = new double [6];
        double[] suhu = new double [6];
        
        System.out.println("Program penyimpanan dan manipulasi data pada Sektor Pertanian.");
        pauseForUserInput(); 
        // Untuk menjeda program sementara, agar pengguna dapat membaca hasil program terlebih dahulu (lihat pada metode-nya di bawah metode main)
        
        System.out.println("==============================================================");
        System.out.println("Data kelembaban tanah dan suhu udara.");
        System.out.println();
        
        // Program akan meminta pengguna untuk mengisi nilai data-data 
        System.out.println("Masukkan data (berupa angka) kelembaban dari 5 sensor tanah.");
        for (int i = 1; i < kelembaban.length; i++) {
            System.out.print("Data kelembaban pada sensor ke-" + i + ": ");
            kelembaban[i] = userInput.nextDouble();
        }
        System.out.println("Masukkan data (berupa angka) suhu dari 5 sensor.");
        for (int i = 1; i < suhu.length; i++) {
            System.out.print("Data suhu pada sensor ke-" + i + ": ");
            suhu[i] = userInput.nextDouble();
        }
        
        pauseForUserInput();
        
        System.out.println("==============================================================");
        System.out.println("Data yang berhasil dimasukkan dan disimpan.");
        
        System.out.println("Data kelembaban tanah:");
        for (int i = 1; i < kelembaban.length; i++) {
            System.out.println("- Kelembaban pada sensor ke-" + i + ": " + kelembaban[i] + "%.");
        }
        System.out.println("Data suhu udara:");
        for (int i = 1; i < suhu.length; i++) {
            System.out.println("- Suhu pada sensor ke-" + i + ": " + suhu[i] + "°C.");
        }

        pauseForUserInput();
        
        System.out.println("==============================================================");
        System.out.println("Hasil manipulasi dan perhitungan data yang tesimpan:");
        System.out.println();
        
        // Menjumlahkan kesemuan total nilai data kelembaban dan suhu 
        double totalLembap = 0;
        for (double number : kelembaban) {
            totalLembap += number;
        }
        double totalSuhu = 0;
        for (double number : suhu) {
            totalSuhu += number;
        }
        
        // Menhitung nilai rata-rata kelembaban dan suhu
        double averageLembap = (double) totalLembap / kelembaban.length;
        double averageSuhu = (double) totalSuhu / suhu.length;

        // Menampilkan hasil perhitungan rata-rata
        System.out.println("Jumlah total nilai data kelembaban: " + totalLembap + "%.");
        System.out.println("Jumlah total nilai data suhu: " + totalSuhu + "°C.");
        System.out.println();
        System.out.println("Rata-rata nilai data kelembaban: " + averageLembap + "%.");
        System.out.println("Rata-rata nilai data suhu: " + averageSuhu + "°C.");
        
        System.out.println();
        // Menyimpan data dengan nilai penentu untuk membandingkan nilai yang tersimpan pada Array
        double belowLembap = 30.0;
        double overSuhu = 35.0; 
        // Mencari, membandingkan, dan menampilkan nilai (yang ada pada Array) dengan nilai pembanding ("nilai penentu")
        System.out.println("Sensor dengan kelembaban di bawah 30.0%:");
        for (int i = 1; i < kelembaban.length; i++) {
            if (kelembaban[i] < belowLembap) {
                System.out.println("- Sensor ke-" + i + " dengan kelembaban: " + kelembaban[i] + "%.");
            }
        }
        System.out.println();
        System.out.println("Sensor dengan suhu di atas 35.0°C:");
        for (int i = 1; i < suhu.length; i++) {
            if (suhu[i] > overSuhu) {
                System.out.println("- Sensor ke-" + i + " dengan kelembaban: " + suhu[i] + "°C.");
            }
        }
    }
    
    public static void pauseForUserInput() {
        Scanner pauseInput = new Scanner(System.in);
        System.out.println("Tekan ENTER untuk melanjutkan program...");
        pauseInput.nextLine(); // Menunggu pengguna menekan Enter
    }
}