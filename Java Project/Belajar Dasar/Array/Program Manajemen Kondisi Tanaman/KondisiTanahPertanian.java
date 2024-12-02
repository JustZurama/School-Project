import java.util.Scanner;

public class KondisiTanahPertanian {
    public static void main(String[] args) {
        // Deklarasi variabel Scanner dan hasil perhitungan
        Scanner userInput = new Scanner(System.in);
        int[][] kelembaban = new int[4][5];
        int total = 0;
        double rataRataBaris;
        double rataRataKolom;

        System.out.println("==============================================================");
        System.out.println("Program Monitor Kondisi Tanah pada Pertanian.");

        pauseForUserInput(); // Menjeda program 

        System.out.println("Masukkan 5 buah data (berupa angka) kondisi dari kelembaban tanah.");
        for (int i = 0; i < kelembaban.length; i++) {
            for (int j = 0; j < kelembaban[i].length; j++) {
                System.out.print("Data kelembaban pada baris ke-" + (i+1) + " kolom ke-" + (j+1) + ": ");
                kelembaban[i] [j] = userInput.nextInt();
            }
        }

        // Menampilkan data kelembaban
        System.out.println("==============================================================");
        System.out.println("Dari data-data kelembaban tanah yang dimasukkan, didapat:");
        for (int i = 0; i < kelembaban.length; i++) {
            System.out.print("Area " + (i + 1) + ": " + "[ ");
            for (int j = 0; j < kelembaban[i].length; j++) {
                System.out.print(kelembaban[i][j] + ", ");
            }
            System.out.print("]");
            System.out.println();
        }

        pressToCalculate();
        
        // Menghitung rata-rata kelembaban per baris
        System.out.println("==============================================================");
        System.out.println("Rata-rata Kelembaban per Baris:");
        for (int i = 0; i < kelembaban.length; i++) {
            for (int j = 0; j < kelembaban[i].length; j++) {
                total += kelembaban[i][j];
            }
            rataRataBaris = total / kelembaban[i].length;
            System.out.printf("Baris %d: %.1f%%\n", i + 1, rataRataBaris);
        }

        // Menghitung rata-rata kelembaban per kolom
        System.out.println("\nRata-rata Kelembaban per Kolom:");
        for (int j = 0; j < kelembaban[0].length; j++) {
            for (int i = 0; i < kelembaban.length; i++) {
                total += kelembaban[i][j];
            }
            rataRataKolom = total / kelembaban.length;
            System.out.printf("Kolom %d: %.1f%%\n", j + 1, rataRataKolom);
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
