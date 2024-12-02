// Disebut dengan "Scanner", yaitu ketika program meminta pengguna untuk memasukkan data

import java.util.Scanner;                               // Kita harus mengimpor class Scanner dulu

class Input_User_Data {
    public static void main (String[] args ) {
        Scanner userread = new Scanner(System.in);      // Membuat objek Scanner
        
        System.out.println("Masukkan nama...");       // Print untuk menunjukkan teks sebelum melakukan input
        String nama = userread.nextLine();              // Read input data pengguna
        System.out.println("Nama kamu adalah " + nama); // Write Output data pengguna
        
        System.out.println("silahkan masukkan nim...");
        long nim = userread.nextLong();
        System.out.println("NIM kamu adalah " + nim);

        System.out.println("Silahkan masukkan semester pendidikanmu...");
        int semester = userread.nextInt();
        System.out.println("Kamu adalah mahasiswa semester " + semester);

        System.out.println("Apakah kamu mahasiswa aktif? Masukkan jawabanmu...");
        String status = userread.nextLine();
        System.out.println("Status kemahasiswaan kamu " + status);

        System.out.println("Jadi, berdasarkan data kemahasiswaan yang telah dimasukkan adalah...");
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Semester ke: " + semester);
        System.out.println("Status kemahasiswaan: " + status);
    }
}