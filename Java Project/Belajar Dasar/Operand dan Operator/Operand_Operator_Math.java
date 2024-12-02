import java.util.Scanner;

public class Operand_Operator_Math{
    
    /** OPERAND adalah nilai asal yang dipakai dalam sebuah proses operasi. Sedangkan OPERATOR
        adalah instruksi yang diberikan untuk mendapatkan hasil dari proses tersebut. */
    
    /** Ada enam jenis kelompok pembagian dalam Pemrograman Java. Berikut akan disampaikan fungsi-fungsinya. */ 

    public static void main(String[]args) {  
        
        
        //======OPERATOR ARITMETIKA=====//
        int a = 10;
        int b = 5;
        int hasil;

        System.out.println("=====OPERASI ARITMATIKA");
        System.out.println(">>>Variabel a = 10 dan b = 5.");

        // Penjumlahan
        hasil = a + b;              
        System.out.println("Penjumlahan dari a + b = " + hasil);

        // Pengurangan
        hasil = a - b;              
        System.out.println("Pengurangan dari a - b = " + hasil);
        
        // Perkalian
        hasil = a * b;              
        System.out.println("Perkalian dari a * b = " + hasil);

        // Pemabagian
        hasil = a / b;              
        System.out.println("Pembagian dari a : b = " + hasil);

        // Sisa hasil bagi (Modulus)
        hasil = a % b;              
        System.out.println("Modulus dari a % b = " + hasil);

         //======OPERATOR PENUGASAN======//     // Alias Assignment Operator, berfungsi untuk meberikan tugas
                                                // pada variabel tertentu. Biasanya untuk mengisi nilai.
         a = 5;
         b = 10;
         
         System.out.println("=====OPERATOR PENUGASAN (ASSIGNMENT OPERATOR)");
         System.out.println(">>>Variabel a = 5 dan b = 10");
         
         // penambahan
         b += a;
         // sekarang b = 15
         System.out.println("Penambahan dari b + a = " + b);
 
         // pengurangan
         b -= a;
         // sekarang b = 10 (karena 15-5)
         System.out.println("Pengurangan dari b - a = " + b);
 
         // perkalian
         b *= a;
         // sekarang b = 50 (karena 10*5)
         System.out.println("Perkalian dari b * a = " + b);
 
         // Pembagian
         b /= a;
         // sekarang b=10
         System.out.println("Pembagian dari b : a = " + b);
 
         // Sisa bagi
         b %= a;
         // sekarang b=0
         System.out.println("Modulus dari b % a = " + b);

         //======OPERATOR PEMBANDING======//    // Tugas dari operator ini adalah membandingkan. Nilai yang dihasilkan dalam operator ini berupa
                                                // boolean yaitu antara “true” dan “false”.
        int nilaiA = 15;
        int nilaiB = 5;
        boolean hasilAB;
        
        System.out.println("=====OPERATOR PEMBANDING");
        System.out.println(">>>Variabel: A = 15 dan B = 5");

        // Lebih dari
        hasilAB = nilaiA > nilaiB;  
        System.out.println("(Lebih dari) A > B = " + hasilAB);

        // Kurang dari
        hasilAB = nilaiA < nilaiB;  
        System.out.println("(Kurang dari) A < B = " + hasilAB);

        // Lebih dari sama dengan
        hasilAB = nilaiA >= nilaiB; 
        System.out.println("(Lebih dari samadengan) A >= B = " + hasilAB);

        // Kurang dari sama dengan
        hasilAB = nilaiA <= nilaiB; 
        System.out.println("(Kurang dari samadengan) A <= = " + hasilAB);

        // Sama dengan
        hasilAB = nilaiA == nilaiB; 
        System.out.println("(Samadengan) A == B = " + hasilAB);

        // Tidak sama dengan
        hasilAB = nilaiA != nilaiB; 
        System.out.println("(Tidak samadengan) A != B =" + hasilAB);

        //=====OPERATOR LOGIKA=====//           // Operator Logika digunakan untuk melakukan operasi-operasi logika dalam matematika,
                                                // yang kemudian diekspresikan dalam bentuk Boolean.
                                                // Terdapat tiga operator utama dalam Operasi ini.

        int X = 70;
        int Y = 80;
        int Z = 90;

        System.out.println("======OPERATOR LOGIKA");
        System.out.println(">>>Variabel: X = 70, Y = 80, dan Z = 90");

        //  Konjungsi (AND)                     // Akan bernilai 'true', jika kedua nilai 'true'
        boolean x = X > Y && Y < Z;
        boolean y = X < Y && Y < Z;
        System.out.println("Konjungsi dari X > Y && Y < Z = " + x);
        System.out.println("Konjungsi dari X < Y && Y < Z = " + y);

        //  Disjungsi (OR)                      // Akan bernilai 'true', jika salah satu nilai 'true'
        boolean x1 = X > Y && Y < Z || X > Y;
        boolean y2 = X < Y || Y < Z && X < Y;
        System.out.println("Disjungsi dari X > Y && Y < Z || X > Y = " + x1);
        System.out.println("Disjungsi dari X < Y || Y < Z && X < Y = " + y2);

        // Negasi (NOT)                         // Akan bernilai 'true', jika nilai bernilai 'false' (berkebalikan)
        boolean x3 = ! y2;
        boolean y3 = ! (x1 && x);
        System.out.println("Negasi dari X > Y && Y < Z || X > Y = " + x3);
        System.out.println("Negasi dari X > Y && Y < Z || X > Y = " + y3);

        //=====OPERATOR BITWISE=====//          // Operator Bitwise adalah karakter yang digunakan untuk melakukan operasi
                                                // pada data pada level bit, bukan byte atau unit data yang lebih besar.
                                                // Operator bitwise digunakan untuk menangani operasi logika bilangan biner dalam bentuk bit. 
        int p = 60;     // 60 = 0011 1100
        int q = 13;     // 13 = 0000 1101
        int p1 = 0;


        System.out.println("=====OPERATOR BITWISE");
        System.out.println(">>>Variabel: P = 60 dan Q = 13");

                                                // Melakukan operasi AND pada setiap pasangan bit dari dua bilangan.
        // Konjugsi (AND)                       // Jika kedua bit bernilai 1, hasilnya adalah 1, jika tidak, hasilnya adalah 0.
        p1 = p & q; /* 12 = 0000 1100 */
        System.out.println("Konjugsi nilai bit dari P & Q = " + p1);

        // Disjungsi (OR)                       // Jika salah satu atau kedua bit bernilai 1, hasilnya adalah 1, jika tidak, hasilnya adalah 0.
        p1 = p | q; /* 61 = 0011 1101 */
        System.out.println("Disjungsi nilai bit dari P | Q = " + p1);

        // Exclusive OR (XOR)                   // Jika kedua bit berbeda, hasilnya adalah 1, jika sama, hasilnya adalah 0.
        p1 = p ^ q; /* 49 = 0011 0001 */
        System.out.println("Atau eksklusif nilai bit dari P ^ Q = " + p1);

        // Negasi (NOT)                         // Membalik semua bit dari sebuah bilangan.
        p1 = ~p; /* -61 = 1100 0011 */
        System.out.println("Negasi/Kebalikan nilai bit dari ~P = " + p1);
        p1 = ~q; /* -14 = 1111 0010 */
        System.out.println("Negasi/Kebalikan nilai bit dari ~Q = " + p1);

        // Shift Left                           // Menggeser semua bit ke kiri sejumlah posisi tertentu.
        p1 = p << 2; /* 240 = 1111 0000 */
        System.out.println("Pergeseran nilai bit Kiri dari P << 2 = " + p1);
        
        // Shift Right                          // Menggeser semua bit ke kanan sejumlah posisi tertentu.
        p1 = p >> 2; /* 15 = 0000 1111 */
        System.out.println("Pergeseran nilai bit Kanan dari P >> 2 = " + p1);
        
                                                // Sama seperti shift right, tetapi bit-bit yang kosong di sebelah kiri
        // Unsigned Shift Right                 // selalu diisi dengan 0, terlepas dari tanda bilangan.
        p1 = p >>> 2; /* 15 = 0000 1111 */
        System.out.println("Pergeseran nilai bit kosong dari  P >>> 2 = " + p1);

        //=====OPERATOR TERNARY=====//          // Operator ternary adalah operator yang terdiri dari 3 operand.
                                                // Dan merupakan penulisan singkat dari kondisi if else.

        System.out.println("=====OPERATOR TERNARY");

        Scanner userread = new Scanner(System.in);

        System.out.println("Masukkan jumlah nilaimu... (0-100)");
        int nilai = userread.nextInt();         // Tentukan nilai dari variabel tipe data.
                                                // Dalam hal ini jumlah integer yang diambil dari Input Data User.
        String ucapan = nilai >= 70 ? "Selamat anda lulus. Teruskan bakatmu!" : "Maaf, anda tidak lulus. Teruslah berjuang!";
        System.out.println(ucapan);             // Jika memenuhi kriteria nilai (>=70),
                                                // maka sistem akan membaca pernyataan pertama (kiri)

        //=====OPERATOR INCREMENT/DECREMENT=====// Adalah operator yang berguna untuk menaikkan satu nilai (increment)
                                                // dan menurunkan 1 angka (decrement).

        int B = 20;

        System.out.println("=====OPERATOR INCREMENT/DECREMENT");
        System.out.println(">>>Variabel dari B = " + B);

        System.out.println("Pre-increment dari B = " + ++B);    // Pre-increment akan langsung mencetak nilai yang dioperasikan
        System.out.println("Jika dilakukan Post-increment, maka B akan dicetak menjadi " + B++ + " terlebih dahulu");
        System.out.println("Dan setelah dicetak kedua-kalinya, maka nilai B akan menjadi " + B);

        System.out.println("Pre-decrement dari B = " + --B);    // Pre-increment akan langsung mencetak nilai yang dioperasikan
        System.out.println("Jika dilakukan Post-decrement, maka B akan dicetak menjadi " + B-- + " terlebih dahulu");
        System.out.println("Dan setelah dicetak kedua-kalinya, maka nilai B akan menjadi " + B);
    }
}