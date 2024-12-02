public class Tipe_Data{
    public static void main(String[]args){
    
    String iniString="Elisa Renardia";
    String iniString2="Elia";
    char iniChar='F';
    int iniInt=18;
    double iniDouble=18.7;
    boolean iniBool=true;

    if (iniBool == true) {
        System.out.println("benar");
    }

    System.out.println("Namaku adalah " + iniString);
    System.out.println("Aku biasa dipanggil " + iniString2);
    System.out.println("Umurku " + iniInt + " tahun");
    System.out.println("Lebih tepatnya "+ iniDouble + " tahun");
    System.out.println("Karakter huruf favoritku adalah " + iniChar);
    System.out.println("Apakah aku terpelajar? Maka jawabannya adalah " + iniBool);
    }
}