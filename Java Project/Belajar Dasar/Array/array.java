import java.util.Scanner;

public class array {
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        
///////////////////////////////////////////////////////////////////////////////////
        // inisialisasi array
        int[] scores = {85, 90, 70, 88, 92};

        // menghitung total dan rata-rata
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total = scores[i];
        }
        double average = total / (double) scores.length;

        // menampilkan hasil
        System.out.println("Total skor: " + total);
        System.out.println("Rata-rata skor: " + average);
///////////////////////////////////////////////////////////////////////////////////
        // deklarasi array dengan 5 elemen
        int[] intArray = new int[5];

        System.out.println("Masukkan 5 angka untuk array integer... ");
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = input.nextInt(); // inisialisasi secara manual
        }

        // deklarasi array string dengan nilai langsung
        String[] monthArray = {"Januari", "Fenruari", "Maret", "April", "Mei"};

        // menampilkan elemen dari intArray
        System.out.println("Elemen dari array integer: ");
        for (int num : intArray) {
            System.out.println(num);
        }

        //Menampilkan elemen dari monthArray
        System.out.println("Nama bulan dalam array: ");
        for (String month : monthArray) {
            System.out.println(month);
        }

//////////////////////////////////////////////////////////////////////////////////////
// Basic Array Operation        
        

        for (int i = 0; i < intArray.length; i++) {
        System.err.println("Elemen ke-" + i + ": " + intArray[i]);
        // Mengubah elemen kedua
        intArray[1] = 100; // Mengubah nilai menjadi 100
        }
        
        System.err.println("Setelah mengubah elemen kedua: ");
        for (int i = 0; i < intArray.length; i++) {
            System.err.println("Elemen ke-" + i + ": " + intArray[i]);
        }

        // Menghitung total dan rata-rata
        
    }
}