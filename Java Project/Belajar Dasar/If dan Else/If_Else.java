import java.util.Scanner;

class If_Else{
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        //======IF DAN ELSE======//
        System.out.println("======IF DAN ELSE======");

        System.out.println("Silahkan masukkan angka untuk dibandingkan dengan > 5...");
        int angka = input.nextInt();
        if (angka > 5) {
            System.out.println("Angka lebih besar dari 5.");            // Jika program bernilai true, maka kode menjalankan kondisi false.
        } 
        else {
            System.out.println("Angka kurang dari 5.");                 // Jika program bernilai false, maka kode menjalankan kondisi else.
        } {
            System.out.println("Program telah selesai dijalankan.");    // Program yang terakhir dijalankan pada kondisi if-else.
        }
        // Catatan: program akan melewati kondisi apapun yang ada di dalam if jika nilai tidak memenuhi syarat dari kondisi yang ditentukan. 

        //======IF BERSARANG======//
        System.out.println("======IF BERSARANG======");

        System.out.println("Masukkan umur anda saat ini...");
        int umur = input.nextInt();
        System.out.println("Apakah anda memiliki SIM? (Ketik 'ya' jika ya atau 'tidak') ");
        String memilikiSIM = input.nextLine();
        if (umur >= 18) {
            System.out.println("Anda adalah seorang dewasa (berumur lebih dari 18 tahun).");
            if (memilikiSIM.contains("ya")) {
                System.out.println("Anda boleh mengemudi.");
            }
            else {
                System.out.println("Anda tidak boleh mengemudi tanpa SIM.");
            } 
        } else {
            System.out.println("Anda masih di bawah umur.");
        }
    }
}