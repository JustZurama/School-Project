import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerManagement customerManagement = new CustomerManagement();

        while (true) {
            System.out.println("=== Aplikasi Manajemen Pelanggan ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Hapus Pelanggan");
            System.out.println("3. Tampilkan Daftar Pelanggan");
            System.out.println("4. Cari Pelanggan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Masukkan Nomor Telepon: ");
                    String phoneNumber = scanner.nextLine();
                    customerManagement.addCustomer(name, email, phoneNumber);
                    break;

                case 2:
                    System.out.print("Masukkan Email atau Nomor Telepon untuk dihapus: ");
                    String identifierToRemove = scanner.nextLine();
                    customerManagement.removeCustomer(identifierToRemove);
                    break;

                case 3:
                    customerManagement.displayCustomers();
                    break;

                case 4:
                    System.out.print("Masukkan Nama, Email, atau Nomor Telepon untuk mencari: ");
                    String identifierToSearch = scanner.nextLine();
                    customerManagement.searchCustomer(identifierToSearch);
                    break;

                case 5:
                    System.out.println("Keluar dari aplikasi. Terima kasih!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }

            System.out.println();
        }
    }
}

class CustomerManagement {
    private ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer(String name, String email, String phoneNumber) {
        customers.add(new Customer(name, email, phoneNumber));
        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    public void removeCustomer(String identifier) {
        Customer customerToRemove = null;
        for (Customer customer : customers) {
            if (customer.getEmail().equalsIgnoreCase(identifier) || customer.getPhoneNumber().equals(identifier)) {
                customerToRemove = customer;
                break;
            }
        }

        if (customerToRemove != null) {
            customers.remove(customerToRemove);
            System.out.println("Pelanggan berhasil dihapus!");
        } else {
            System.out.println("Pelanggan tidak ditemukan!");
        }
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Tidak ada pelanggan yang terdaftar.");
        } else {
            System.out.println("Daftar Pelanggan:");
            for (int i = 0; i < customers.size(); i++) {
                System.out.println((i + 1) + ". " + customers.get(i));
            }
        }
    }

    public void searchCustomer(String identifier) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(identifier) ||
                customer.getEmail().equalsIgnoreCase(identifier) ||
                customer.getPhoneNumber().equals(identifier)) {
                System.out.println("Pelanggan ditemukan: " + customer);
                return;
            }
        }
        System.out.println("Pelanggan tidak ditemukan!");
    }
}

class Customer {
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Nama: " + name + ", Email: " + email + ", Telepon: " + phoneNumber;
    }
}


