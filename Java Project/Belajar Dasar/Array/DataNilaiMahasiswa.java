public class DataNilaiMahasiswa {
    public static void main(String[] args) {
        // Data nilai mahasiswa [5][3] -> 5 siswa, 3 mata pelajaran
        int[][] nilai = {
            {80, 90, 85},
            {75, 65, 80},
            {85, 80, 60},
            {60, 70, 80},
            {90, 80, 75}
        };

        // Nama mata pelajaran
        String[] mataPelajaran = {"Matematika", "Sains", "Bahasa Inggris"};

        // Looping untuk menghitung total dan rata-rata setiap siswa
        for (int i = 0; i < nilai.length; i++) {
            int total = 0;
            double rataRata = 0.0;

            System.out.println("Nilai Siswa " + (i + 1) + ":");

            // Loop untuk setiap mata pelajaran
            for (int j = 0; j < nilai[i].length; j++) {
                System.out.println(mataPelajaran[j] + ": " + nilai[i][j]);
                total += nilai[i][j];
            }

            // Hitung rata-rata
            rataRata = (double) total / nilai[i].length;

            System.out.println("Total Nilai: " + total);
            // Menampilkan rata-rata dengan 1 angka di belakang koma
            System.out.printf("Rata-Rata: %.1f\n", rataRata);
            System.out.println();
        }
    }
}