import java.util.EmptyStackException;
import java.util.Scanner;

class BoundedStack {
    private int maxSize;
    private String[] stackArray;
    private int top;

    public BoundedStack(int size) {
        this.maxSize = size;
        this.stackArray = new String[maxSize];
        this.top = -1;
    }

    public void resize(int newSize) {
        if (newSize < top + 1) {
            System.out.println("Ukuran baru terlalu kecil untuk menyimpan elemen yang sudah ada.");
            return;
        }
        String[] newArray = new String[newSize];
        for (int i = 0; i <= top; i++) {
            newArray[i] = stackArray[i];
        }
        stackArray = newArray;
        maxSize = newSize;
        System.out.println("Ukuran stack diperbarui menjadi " + newSize);
    }

    public void push(String value) {
        if (isFull()) {
            System.out.println("Stack penuh, tidak dapat menambahkan elemen.");
            return;
        }
        stackArray[++top] = value;
        System.out.println("Elemen '" + value + "' ditambahkan ke dalam stack.");
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        String value = stackArray[top--];
        System.out.println("Elemen '" + value + "' dihapus dari stack.");
        return value;
    }

    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack kosong.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println((top - i + 1) + ". " + stackArray[i]);
        }
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

public class LogHistori {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Aplikasi Pengelolaan Log Histori ===");
        System.out.print("\nMasukan batasan awal stack untuk histori: ");
        int batasanStack = scanner.nextInt();
        scanner.nextLine(); // Pemanggilan objek untuk metode nextLine() ini digunaknan untuk membersihkan buffer
        BoundedStack stack = new BoundedStack(batasanStack);

        boolean running = true;
        while (running) {
            System.out.println("\n================================");
            System.out.println("Menu Pengelolaan Log Histori Pencarian:");
            System.out.println("Batasan Stack saat ini: " + batasanStack);
            System.out.println("1 - Perbarui batasan stack");
            System.out.println("2 - Tampilkan daftar histori");
            System.out.println("3 - Tambahkan data pencarian");
            System.out.println("4 - Hapus data pencarian terakhir");
            System.out.println("0 - Tutup program");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukan batasan ukuran baru: ");
                    int newSize = scanner.nextInt();
                    scanner.nextLine();
                    stack.resize(newSize);
                    batasanStack = newSize;
                    break;
                case 2:
                    System.out.println("Daftar histori saat ini: ");
                    stack.displayStack();
                    break;
                case 3:
                    System.out.print("Masukkan data: ");
                    String data = scanner.nextLine();
                    stack.push(data);
                    break;
                case 4:
                    try {
                        stack.pop();
                    } catch (EmptyStackException e) {
                        System.out.println("Stack kosong, tidak dapat menghapus elemen.");
                    }
                    break;
                case 0:
                    System.out.println("Program ditutup.");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Masukkan angka yang sesuai.");
            }
        }
        scanner.close();
    }
}
