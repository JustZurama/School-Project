import java.util.Scanner;

public class Praktikum_34 {                     // Berikut adalah source code dari tugas Algoritma Pemrograman
                                                // Disusun oleh Muhammad Ramadhani Pratama (2401301055)
    public static void main(String[] args) {
        
        Scanner inputdata = new Scanner(System.in);

        //=====PENGHITUNG KELILING PERSEGI======//
        
        System.out.println("======>>>>>BERIKUT ADALAH PROGRAM PENGHITUNG KELILING PERSEGI");
        
        System.out.println("Silahkan masukkan nilai variabel P (Panjang)...");
        int NilaiP = inputdata.nextInt();
        System.out.println("Maka nilai variabel P = " + NilaiP + " CM.");
        
        System.out.println("Silahkan masukkan nilai variabel L (Lebar)...");
        int NilaiL = inputdata.nextInt();
        System.out.println("Maka nilai variabel P = " + NilaiL + " CM.");
        
        System.out.println("Kemudian akan dilakukan pengerjaan operasi dengan rumus K=2(" + NilaiP + "+" + NilaiL + ")");
        
        double NilaiK = 2 * (NilaiP + NilaiL);
        System.out.println("Maka keliling (dalam bentuk boolean) dari Persegi Panjang tersebut adalah K= " + NilaiK + " CM.");
        
        //=====HASIL OPERASI LOGIKA=============//

        System.out.println("======>>>>>BERIKUT ADALAH PROGRAM HASIL OPERASI LOGIKA");

        boolean A = true;
        boolean B = false;

        boolean A1 = A && A;
        System.out.println("Hasil logika dari A && A adalah " + A1);
        
        boolean A2 = A && B;
        System.out.println("Hasil logika dari A && b adalah " + A2);
        
        boolean B1 = A || B;
        System.out.println("Hasil logika dari A || B adalah " + B1);
        
        boolean B2 = B || B;
        System.out.println("Hasil logika dari B || B adalah " + B2);
        
        boolean C1 = !A;
        System.out.println("Hasil logika dari !A adalah " + C1);
        
        boolean C2 = !B;
        System.out.println("Hasil logika dari !A adalah " + C2);

        //=====PENGHITUNG NILAI RATA-RATA=======//

        System.out.println("======>>>>>BERIKUT ADALAH PROGRAM PENGHITUNG NILAI RATA-RATA dari TIGA NILAI");

        System.out.println("Masukkan tiga nilai yang ingin dihitung nilai rata-ratanya.");
        
        System.out.println("Silahkkan masukkan nilai pertama...");
        int Angka1 = inputdata.nextInt();
        System.out.println("Nilai pertama adalah " + Angka1);

        System.out.println("Silahkan masukkan nilai kedua...");
        int Angka2 = inputdata.nextInt();
        System.out.println("Nilai pertama adalah " + Angka2);

        System.out.println("Silahkan masukkan nilai ketiga...");
        int Angka3 = inputdata.nextInt();
        System.out.println("Nilai ketiga adalah " + Angka3);

        System.out.println("Kemudian akan dilakukan beberapa operasi penghitungan dengan rumus X=(F=jumlah nilai-nilai):(N=jumlah banyak data)");
        int FJumlah = Angka1 + Angka2 + Angka3;
        double XHasil = FJumlah / 3;
        System.out.println("Maka rata-rata dari nilai-nilai tersebut adalah " + XHasil);
    }
}