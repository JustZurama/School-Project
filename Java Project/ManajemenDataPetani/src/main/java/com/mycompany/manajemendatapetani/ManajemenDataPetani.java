/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.manajemendatapetani;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * Disusun oleh:
 * @author Muhammad Ramadhani Pratama
 * NIM/Kelas: 2401301055/TI_1E
 * Politeknik Negeri Tanah Laut
 * 
 */

import java.io.*;
import java.util.*;

public class ManajemenDataPetani {
    public static void main(String[] args) {
        List<Petani> daftarPetani = new ArrayList<>();
        String inputFile = "petani.txt";
        String outputFile = "output_petani.txt";
        double rataRataUmur = 0;

        // Membaca data dari file petani.txt
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            System.out.println("Membaca data dari " + inputFile + ":");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String nama = data[0].trim();
                int umur = Integer.parseInt(data[1].trim());
                String tanaman = data[2].trim();
                Petani petani = new Petani(nama, umur, tanaman);
                daftarPetani.add(petani);
                System.out.println("Nama: " + nama + ", Umur: " + umur + ", Tanaman: " + tanaman);
            }
        } catch (IOException e) {
            System.err.println("Error membaca file: " + e.getMessage());
            return;
        }

        // Menghitung rata-rata umur
        int totalUmur = 0;
        for (Petani petani : daftarPetani) {
            totalUmur += petani.umur;
        }
        rataRataUmur = (double) totalUmur / daftarPetani.size();
        System.out.printf("Rata-rata umur petani: %.1f%n", rataRataUmur);

        // Mengurutkan daftar petani berdasarkan nama
        Collections.sort(daftarPetani, Comparator.comparing(p -> p.nama));

        // Menulis data ke output_petani.txt
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            System.out.println("\nMenulis data ke " + outputFile + ":");
            for (Petani petani : daftarPetani) {
                bw.write(petani.toString());
                bw.newLine();
                System.out.println(petani);
            }
            bw.write("Rata-rata umur petani: " + String.format("%.1f", rataRataUmur));
        } catch (IOException e) {
            System.err.println("Error menulis file: " + e.getMessage());
        }

        System.out.println("Data petani telah ditulis ke " + outputFile);
    }
}

class Petani {
    String nama;
    int umur;
    String tanaman;

    public Petani(String nama, int umur, String tanaman) {
        this.nama = nama;
        this.umur = umur;
        this.tanaman = tanaman;
    }

    public String toString() {
        return nama + " - Umur: " + umur + ", Tanaman: " + tanaman;
    }
}