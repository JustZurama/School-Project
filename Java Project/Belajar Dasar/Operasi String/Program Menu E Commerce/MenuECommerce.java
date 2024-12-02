/**
 * Disunting oleh: Muhammad Ramadhani Pratama
 * TI 1E: 2401301055
 * Politeknik Negeri Tanah Laut
 * =======================================================
 * Terakhir disunting: 06-11-2024
 * Untuk penyuntingan ulang mohon lakukan dengan hati-hati
 */

import java.util.Scanner;   // dipanggil untuk menambahkan kelas Scanner
import java.util.ArrayList;   // dipanggil untuk menambahkan kelas ArrayList

public class MenuECommerce {
    public static void main(String[] args) {
        
        Scanner dataInput = new Scanner(System.in); // untuk membuat data yg didasarkan pada Input user
        ArrayList<String> prodList = new ArrayList<>(); // untuk membuat variabel Array yang memuat serangkaian data

        // menambhakan produk kedalam daftar Array
        prodList.add("Laptop Gaming");prodList.add("Laptop Bisnis");prodList.add("Laptop Slim");prodList.add("Laptop Multitasking");prodList.add("Mouse Gaming");prodList.add("Mouse Wireless");prodList.add("Tas Laptop");prodList.add("Speaker Fullbass");prodList.add("Keyboard Kantor");prodList.add("Keyboard Gaming");prodList.add("Keyboard Mekanikal");prodList.add("HP Gaming");prodList.add("HP");
        
        char mainMenuChoice;    // menyimpan variabel untuk menu pilihan (fungsi Switch())
        char subMenuChoice;   // menyimpan variabel untuk menu pilihan di dalam suatu menu (pengoreksian produk kustom)
        char anyKey;        // variabel yang dipakai agar pengguna memasukkan kunci apapun sebelum memasuki menu selanjutnya
        
        do {    // perulangan do digunakan untuk membuat menu-menu yang bisa dipilih pengguna
System.out.println("=======================================================================================================================================================");   
            System.out.println("Pilih menu e-commerce.");   // pilihan menu yang ditampilkan
            System.out.println("(1) Lihat daftar produk.");
            System.out.println("(2) Memilih produk berdasarkan kata kunci.");
            System.out.println("(3) Membuat kustom produk dan deskripsinya.");
            System.out.println("(0) Keluar dari program.");
System.out.println("_______________________________________________________________________________"); // garis-garis ini dimaksudkan untuk membatasi
            System.out.print("Masukkan kunci untuk memilih menu... ");
            mainMenuChoice = dataInput.next().charAt(0);    // pengguna diminta untuk memasukkan kunci yang sesuai untuk masuk kedalam menu
            dataInput.nextLine();   // untuk membersihkan buffer input
            switch (mainMenuChoice) {   // switch didasarkan pada masukkan pengguna
                case '1':   // misalnya pengguna memasukkan angka '1', maka program akan memasuki menu pertama
System.out.println("=======================================================================================================================================================");   
                System.out.println("Daftar produk yang tersedia:");
                    for (String product : prodList) {
                        System.out.println("- " + product);
                    }   
System.out.println("_______________________________________________________________________________");
                    System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke menu awal... "); 
                    anyKey = dataInput.next().charAt(0);    // ketika program selesai dijalankan, maka ia akan meminta pengguna untuk memasukkan apapun
                    dataInput.nextLine();                         // ini dimaksudkan agar pengguna dapat membaca terlebih dahulu hasil output sebelum beralih ke menu selanjutnya  
                break;
                case '2':
System.out.println("=======================================================================================================================================================");   
                    System.out.print("Masukkan kata kunci untuk mencari produk yang tersedia... ");
                    String keyWord = dataInput.nextLine().toLowerCase();    // program akan langsung mengubah karakter pada string input-an menjadi lowercase, sehingga bisa disesuaikan dengan yang ada pada daftar
System.out.println("_______________________________________________________________________________");                    
                    System.out.println("Produk yang ditemukan: ");
                    boolean prodFound = false;              // variabel ini untuk menyatakan false terlebih dahulu
                    for (String productShow : prodList) {                   // for membandingkan daftar produk 
                        if (productShow.toLowerCase().contains(keyWord)) {  // jika kata kunci yang dimasukkan pengguna terdapat pada daftar produk
                            System.out.println("- " + productShow);         // maka nama produk yang sesuai kata kunci akan ditampilkan 
                            prodFound = true;                               // serta membuat agar variabel ini: true, dan menjalankan fungsi if
                        }
                    }
                    if (!prodFound) {   // pembuatan variabel "prodFound" dimaksudkan agar ketika pengguna berhasil mendapatkan daftar produk, 
                        System.out.println("Tidak ada produk yang sesuai dengan kata kunci.");  // bagian ini tidak dijalankan
                    }
System.out.println("_______________________________________________________________________________");
                    System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke menu awal... "); 
                    anyKey = dataInput.next().charAt(0);
                    dataInput.nextLine();
                break;
                case '3':
System.out.println("=======================================================================================================================================================");   
                    System.out.println("Masukkan nama untuk membuat produk kustom... ");
                    String productName  = dataInput.nextLine().toLowerCase();
                    System.out.println("Masukkan deskripsi produk kustom... ");
                    String productDesc  = dataInput.nextLine().toLowerCase();
System.out.println("_______________________________________________________________________________");
                    System.out.println(productName.toUpperCase()); // program menampilkan nama produk dalam huruf kapital 
                    System.out.println("Deskripsi produk:");    // deskripsi ditampilkan berupa huruf besar (uppercase) pada karakter pertama, kemudian huruf kecil (lowercase) pada karakter kedua hingga seterusnya
                    System.out.println(productDesc.substring(0, 1).toUpperCase() + productDesc.substring(1).toLowerCase()+"."); // menggunakan substring untuk menentukan bagian spesifik mana (index) yang diubah
                    do { // perulangan do yang di dalam menu untuk pengoreksian produk kustom
System.out.println("_______________________________________________________________________________");
                        System.out.println("Masukkan kunci di bawah untuk menu pengoreksian... "); 
                        System.out.println("(1) Mengganti karakter pada nama produk.");
                        System.out.println("(2) Mengganti kata/kalimat pada nama produk.");
                        System.out.println("(3) Mengganti deskripsi produk.");
                        System.out.println("(0) Kembali ke menu awal.");
                        System.out.print("Masukkan kunci yang sesuai... ");
                        subMenuChoice = dataInput.next().charAt(0);
                        dataInput.nextLine();
                        switch (subMenuChoice) {
                            case '1':
    System.out.println("=======================================================================================================================================================");   
                                System.out.println("Mengganti karakter pada nama produk.");
                                System.out.println("Nama produk awal: " + productName.toUpperCase());                            
    System.out.println("_______________________________________________________________________________");
                                System.out.print("Masukkan karakter yang ingin diganti: ");
                                char oldChar = dataInput.next().toLowerCase().charAt(0);    
                                System.out.print("Masukkan karakter pengganti: ");
                                char newChar = dataInput.next().toLowerCase().charAt(0);    

                                String replacedChar = productName.replace(oldChar, newChar); // mengganti karakter baru sesuai inputan              
                                System.out.println("Nama produk setelah penggantian karakter: " + replacedChar.toUpperCase());
    System.out.println("_______________________________________________________________________________");
                                System.out.println(replacedChar.toUpperCase());
                                System.out.println("Deskripsi produk:");
                                System.out.println(productDesc.substring(0, 1).toUpperCase() + productDesc.substring(1).toLowerCase()+".");
    System.out.println("_______________________________________________________________________________");
                                System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke pilihan menu pengoreksian... "); 
                                anyKey = dataInput.next().charAt(0);
                                dataInput.nextLine();
                            break;
                            case '2':
    System.out.println("=======================================================================================================================================================");   
                                System.out.println("Mengganti kata/kalimat pada nama produk.");
                                System.out.println("Nama produk awal: " + productName.toUpperCase());
    System.out.println("_______________________________________________________________________________");
                                System.out.print("Masukkan teks yang ingin diganti: ");
                                String oldText = dataInput.nextLine().toLowerCase();    // meminta pengguna memasukkan karakter/kalimat yang sesuai dengan nama produk
                                System.out.print("Masukkan teks pengganti: ");
                                String newText = dataInput.nextLine().toLowerCase();    // meminta pengguna memasukkan yang baru

                                String replacedText = productName.replace(oldText, newText);    // mengganti yang lama dengan yang baru
                                System.out.println("Nama produk setelah penggantian karakter: " + replacedText.toUpperCase());
    System.out.println("_______________________________________________________________________________");
                                System.out.println(replacedText.toUpperCase());
                                System.out.println("Deskripsi produk:");
                                System.out.println(productDesc.substring(0, 1).toUpperCase() + productDesc.substring(1).toLowerCase()+".");
    System.out.println("_______________________________________________________________________________");
                                System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke pilihan menu pengoreksian... "); 
                                anyKey = dataInput.next().charAt(0);
                                dataInput.nextLine();
                            break;
                            case '3':
    System.out.println("=======================================================================================================================================================");   
                                System.out.println("Mengganti deskripsi produk.");
                                System.out.println("Deskripsi produk awal: " + productDesc.substring(0, 1).toUpperCase() + productDesc.substring(1).toLowerCase()+".");
    System.out.println("_______________________________________________________________________________");
                                System.out.print("Masukkan deskripsi produk yang baru: ");
                                String newDesc = dataInput.nextLine().toLowerCase();
    System.out.println("_______________________________________________________________________________");
                                System.out.println(productName.toUpperCase());
                                System.out.println("Deskripsi produk:");
                                System.out.println(newDesc.substring(0, 1).toUpperCase() + newDesc.substring(1).toLowerCase()+".");
    System.out.println("_______________________________________________________________________________");
                                System.out.print("Program selesai dibuat. Masukkan kunci apa saja untuk kembali ke pilihan menu pengoreksian... "); 
                                anyKey = dataInput.next().charAt(0);
                                dataInput.nextLine();
                            break;
                            case '0': 
    System.out.println("=======================================================================================================================================================");   
                                System.out.println("Kembali ke menu awal...");
                            break;
                            default: 
    System.out.println("_______________________________________________________________________________");
                            System.out.println("Pilihan tidak valid, masukkan (1), (2), (3), atau (0).");
                            }
                    } while (subMenuChoice != '0');   
                break;
                case '0':
System.out.println("=======================================================================================================================================================");   
                    System.out.println("Keluar dari program.");
                break;
                default: // (pada menu awal- e commerce) ketika masukkan pengguna tidak sesuai dengan yang ditentukan, maka akan kembali ke menu sebelumnya
System.out.println("_______________________________________________________________________________");
                System.out.println("Pilihan tidak valid, masukkan (1), (2), atau (0).");
                }        
        }   while (mainMenuChoice != '0');  // (pada menu awal- e commerce) ketika pengguna memasukkan kunci selain '0', maka program akan terus berulang/berjalan kembali ke suatu menu (perulangan do)
    }
}

/**
 * Referensi:
 * Moduk Praktikum 7 dan 8: Operasi Perulangan. Tim Pengajar Algoritma & Pemrograman, Politeknik Tanah Laut.
 * Modul Pertemuan 10: Operasi String. Tim Pengajar Algoritma & Pemrograman, Politeknik Tanah Laut.
 */