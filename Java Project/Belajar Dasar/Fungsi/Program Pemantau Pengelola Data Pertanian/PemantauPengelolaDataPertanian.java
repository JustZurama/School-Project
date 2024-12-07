import java.util.Scanner;

public class PemantauPengelolaDataPertanian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Deklarasi atribut instance untuk kelas tertentu
        Pemantauan pemantauan = new Pemantauan();
        
        int suhu1, suhu2, suhu3, suhu4; 
        int kelembapan;
        double phTanah;

        // Input data dari pengguna
        System.out.println("Masukkan nilai-nilai data pada sektor Pertanian untuk dikelola");
        System.out.println("==============================================================");
        while (true) {
            System.out.println("Masukkan 4 buah nilai data suhu udara");
            try {
                System.out.print("Masukkan suhu ke-1: ");
                suhu1 = scanner.nextInt(); // Jika input salah, akan dilemparkan exception
                System.out.print("Masukkan suhu ke-2: ");
                suhu2 = scanner.nextInt();
                System.out.print("Masukkan suhu ke-3: ");
                suhu3 = scanner.nextInt();
                System.out.print("Masukkan suhu ke-4: ");
                suhu4 = scanner.nextInt();
                break; // Keluar dari loop jika input benar
            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan kembali angka data untuk suhu.");
                scanner.next(); // Membersihkan input yang salah
            }
        }
        while (true) {
            try {
                System.out.print("Masukkan kelembapan: ");
                kelembapan = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan angka data untuk kelembapan.");
                scanner.next();
            }
        }
        while (true) {
            try {
                System.out.print("Masukkan PH tanah desimal): ");
                phTanah = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Input tidak valid. Harap masukkan angka data untuk PH tanah.");
                scanner.next();
            }
        }

        pauseForUserInput();
        
        // Menjumlahkan nilai suhu
        int totalSuhu = suhu1 + suhu2 + suhu3 + suhu4;
        
        // Menghitung nilai indeks kualitas tanaman
        double indeksKualitas = pemantauan.hitungIndeksKualitas(totalSuhu, phTanah, kelembapan);
        System.out.printf("Indeks Kualitas Tanaman: %.2f\n", indeksKualitas);

        // Menghitung nilai rata-rata suhu
        double rataRataSuhu = SensorData.rerataData(suhu1, suhu2, suhu3, suhu4);
        System.out.printf("Rata-rata Suhu: %.2f\n", rataRataSuhu);

        System.out.println("----------------------------------------------------------------");

        // Menampilkan data-data
        System.out.println("Data Sensor:");
        pemantauan.bacaDataSensor(totalSuhu, kelembapan, phTanah);

        // Membandingkan nilai data melalui fungsi yang dipanggil sebagai parameter
        boolean perluPenyiraman = pemantauan.evaluasiKondisi(indeksKualitas, data -> data <= 28.5);
        System.out.println("Status kondisi tanaman pada lingkungan: " + (perluPenyiraman ? "perlu dilakukan penyiraman." : "tidak perlu dilakukan penyiraman."));
    }

    public static void pauseForUserInput() {
        Scanner pauseInput = new Scanner(System.in);
        System.out.println("==============================================================");
        System.out.println("Tekan ENTER untuk melakukan perhitungan...");
        System.out.print("==============================================================");
        pauseInput.nextLine(); // Menunggu pengguna menekan Enter
    }

}

class SensorData {
    // Fungsi untuk menghitung rata-rata data sensor (menggunakan parameter varargs)
    public static double rerataData (double... nilai) {
        if (nilai.length == 0) {
            return 0; // Menghindari pembagian dengan nilai 0
        }
        
        double total = 0;
        for (double n : nilai) {
            total += n;
        }
        return total / nilai.length;
    }
}

class Pemantauan {
    // Fungsi untuk menghitung indeks kualitas tanaman
    public double hitungIndeksKualitas(int suhu, int kelembapan) {
        return (suhu + kelembapan) / 5;
    }
    public double hitungIndeksKualitas(double ph, double kelembapan) {
        return (ph * kelembapan) / 5;
    }
    public double hitungIndeksKualitas(int suhu, double ph, int kelembapan) {
        return (suhu + ph + kelembapan) / 5;
    }

    // Fungsi untuk membaca (data yang dipanggil) dan menampilkan data
    public void bacaDataSensor(double suhu, double kelembapan, double phTanah) {
        System.out.printf("Suhu: %.1f °C\n", suhu);
        System.out.printf("Kelembapan: %.1f %%\n", kelembapan);
        System.out.printf("PH Tanah: %.1f\n", phTanah);
    }

    // Fungsi untuk mengevaluasi apakah diperlukan penyiraman pada kondisi lingkungan tanaman
    public boolean evaluasiKondisi(double data, Evaluator evaluator) {
        return evaluator.evaluate(data);
    }
}

interface Evaluator {
    boolean evaluate(double value);
}