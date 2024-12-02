import java.util.Scanner;

public class BasicArrayOperations {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[] intArray = new int[5];
        System.out.println("Masukkan 5 angka untuk array integer:");
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = input.nextInt();
        }

        System.out.println("Elemen dari array integer:");
        for (int i = 0; i < intArray.length; i++) {
            System.out.println("Elemen ke-" + i + ": " + intArray[i]);
        }

        intArray[1] = 100;
        System.out.println("Setelah mengubah elemen kedua:");
        for (int i = 0; i < intArray.length; i++) {
            System.out.println("Elemen ke-" + i + ": " + intArray[i]);
        }

        int total = 0;
        for (int number : intArray) {
            total += number;
        }
        double average = (double) total / intArray.length;

        System.out.println("Total: " + total);
        System.out.println("Rata-rata: " + average);
        
        System.out.println("Masukkan angka yang ingin dicari: ");
        int searchValue = input.nextInt();
        boolean found = false;
        
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == searchValue) {
                System.out.println("Nilai " + searchValue + " ditemukan pada indeks: " + i);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Nilai tidak ditemukan dalam array.");
        }
        
        input.close();
    }
}