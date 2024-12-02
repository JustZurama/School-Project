import java.util.Scanner;

public class ManajemenProduksiTanaman {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int[][] hasilProduksi = new int[3][6];
        int total = 0;
        double rataRataBulan;

        System.out.println("==============================================================");
        System.out.println("Program Manajemen Hasil Produksi Tanaman.");
        
        pauseForUserInput();
        
        // Bagian ini akan meminta data-data tiap "tanaman" dari masukan pengguna 
        System.out.println("==============================================================");
        System.out.println("Masukkan 6 buah data (berupa angka) hasil produksi tanaman.");
        // Meminta data hasil produksi tomat
        for (int i = 0; i < 1; i++) { 
            System.out.println("Data Produksi Tomat: ");
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                System.out.print("Data ke " + (j+1) + ": ");
                hasilProduksi[i] [j] = userInput.nextInt();
            }
        }
        // Meminta data hasil produksi cabai
        for (int i = 1; i < 2; i++) { 
            System.out.println("Data Produksi Cabai: ");
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                System.out.print("Data ke " + (j+1) + ": ");
                hasilProduksi[i] [j] = userInput.nextInt();
            }
        }
        // Meminta data hasil produksi jagung
        for (int i = 2; i < 3; i++) { 
            System.out.println("Data Produksi Jagung: ");
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                System.out.print("Data ke " + (j+1) + ": ");
                hasilProduksi[i] [j] = userInput.nextInt();
            }
        }

        // Menampilkan data hasil produksi tanaman
        System.out.println("==============================================================");
        System.out.println("Dari data-data hasil produksi tanaman yang dimasukkan, didapat sebagai berikut.");
        System.out.println("Hasil produksi tanaman (dalam satuan kg.): ");
        // Menampilkan data hasil produksi tomat
        for (int i = 0; i < 1; i++) {
            System.out.print("Hasil produksi tomat  : [ ");
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                System.out.print(hasilProduksi[i][j] + ", ");
            }
            System.out.print("]");
            System.out.println();
        }
        // Menampilkan data hasil produksi cabai
        for (int i = 1; i < 2; i++) {
            System.out.print("Hasil produksi cabai  : [ ");
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                System.out.print(hasilProduksi[i][j] + ", ");
            }
            System.out.print("]");
            System.out.println();
        }
        // Menampilkan data hasil produksi jagung
        for (int i = 2; i < 3; i++) {
            System.out.print("Hasil produksi jagung : [ ");
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                System.out.print(hasilProduksi[i][j] + ", ");
            }
            System.out.print("]");
            System.out.println();
        }

        pressToCalculate();
 
        // Menghitung rata-rata produksi per tanaman dan per bulan
        System.out.println("==============================================================");
        System.out.println("Jumlah produksi per tanaman.");
        // Menghitung rata-rata produksi tomat
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                total += hasilProduksi[i][j];
            }
            System.out.println("Produksi tomat: " + total + "kg.");
        }
        // Menghitung rata-rata produksi cabai
        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                total += hasilProduksi[i][j];
            }
            System.out.println("Produksi cabai: " + total + "kg.");
        }
        // Menghitung rata-rata produksi jagung
        for (int i = 2; i < 3; i++) {
            for (int j = 0; j < hasilProduksi[i].length; j++) {
                total += hasilProduksi[i][j];
            }
            System.out.println("Produksi jagung: " + total + "kg.");
        }
        // Menghitung rata-rata per bulan (per kolom data)
        System.out.println("\nRata-rata hasil produksi per bulan:");
        for (int j = 0; j < hasilProduksi[0].length; j++) {
            for (int i = 0; i < hasilProduksi.length; i++) {
                total += hasilProduksi[i][j];
            }
            rataRataBulan = (double) total / hasilProduksi.length;
            System.out.printf("Bulan ke- %d: %.1f%%\n", j + 1, rataRataBulan);
        }
    }
    public static void pauseForUserInput() {
        Scanner pauseInput = new Scanner(System.in);
        System.out.println("Tekan ENTER untuk melanjutkan program...");
        pauseInput.nextLine(); // Menunggu pengguna menekan Enter
    }
    public static void pressToCalculate() {
        Scanner pressCalculate = new Scanner(System.in);
        System.out.println("Tekan ENTER untuk melakukan perhitungan...");
        pressCalculate.nextLine(); // Menunggu pengguna menekan Enter
    }
}