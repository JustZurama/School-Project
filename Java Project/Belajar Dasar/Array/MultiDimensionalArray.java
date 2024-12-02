public class MultiDimensionalArray {
    public static void main (String[] args) {
        
        // Tabel matriks array
        int [] [] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int [] [] matrixB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        // Menampilkan tabel matriks
        System.out.println("Matriks A: ");
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                System.out.print(matrixA[i] [j] + " ");
            }
            System.out.println();
        }
        System.out.println("Matriks B: ");
        for (int i = 0; i < matrixB.length; i++) {
            for (int j = 0; j < matrixB.length; j++) {
                System.out.print(matrixB[i] [j] + " ");
            }
            System.out.println();
        }

        int [] [] result = new int [3] [3];

        // Menjumlahkan matriks A dan B
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                result [i] [j] = matrixA [i] [j] + matrixB [i] [j];
            }
        }
        // Menampilkan hasil penjumlahan
        System.out.println("Hasil penjumlahan matriks A dan B: ");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                System.out.print(result[i] [j] + " ");
            }
            System.out.println();
        }

        //////////////////////////
        int [] [] matrixMaxMin = {
            {34, 76, 23},
            {11, 89, 12},
            {73, 62, 100}
        };

        int matrixMax = matrixMaxMin [0] [0];
        int matrixMin = matrixMaxMin [0] [0];

        // Mencari nilai minimum dan maksimum
        for (int i = 0; i < matrixMaxMin.length; i++) {
            for (int j = 0; j < matrixMaxMin[i].length; j++) {
                if (matrixMaxMin [i] [j] > matrixMax) {
                    matrixMax = matrixMaxMin [i] [j];
                }
                if (matrixMaxMin [i] [j] < matrixMin) {
                    matrixMin = matrixMaxMin [i] [j];
                }
            }
        }

        System.out.println("Nilai maksimum dalam matriks: " + matrixMax);
        System.out.println("Nilai minimum dalam matriks: " + matrixMin);

        ////////////////////////////////////////////////////
        double[] columnSums = new double[matrixA[0].length]; 
        double[] columnAverages = new double[matrixA[0].length]; 

        // Menghitung total untuk setiap kolom 
        for  (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[i].length; j++) {
                columnSums[j] += matrixA[i] [j]; 
            }
        }
        // Menghitung nilai rata-rata
        for (int j = 0; j < columnSums.length; j++) {
            columnAverages[j] = columnSums[j] / matrixA.length;
        }
        // Menampilkan nilai rata-rata
        System.out.println("Rata-rata untuk setiap kolom pada tabel matriks A: ");
        for (int j = 0; j < columnAverages.length; j++) {
            System.out.println("Rata-rata kolom ke-" + (j + 1) + ": " + columnAverages[j]);
        }
    }
}