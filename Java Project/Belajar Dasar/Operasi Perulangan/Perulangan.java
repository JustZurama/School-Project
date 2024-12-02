public class Perulangan {
    public static void main(String[] args) {
        
         for(int ulang = 0; ulang < 10; ulang++) {
            System.out.println("Perulangan ke... " + ulang);          // Program akan mencetak ulang
        
        
        for(int loop = 0; loop < 5; loop++) {
            System.out.println(loop + " ");
        } }
        
        int ulang = 1;

        while (ulang<=10) {
            System.out.println("Perulangan ke... " + ulang);
            ulang++;
        } 

        do {
            System.out.println("Perulangan ke... " + ulang);
            ulang++;
        } while (ulang<=10);
    }
}