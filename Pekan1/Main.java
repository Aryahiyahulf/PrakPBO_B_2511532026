package Pekan1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;

		boolean isRunning = true;

		System.out.println("=== SISTEM PERBANKAN MINI ===");

		while (isRunning) {

			System.out.println("\nMenu Utama");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("0. Keluar");
			System.out.print("Pilih Menu: ");

			int pilihan = input.nextInt();
			input.nextLine();

			switch (pilihan) {

			case 1:
				System.out.print("Masukan No Rekening: ");
				String no = input.nextLine();

				System.out.print("Masukan Nama Pemilik: ");
				String nama = input.nextLine();

				System.out.print("Masukan Saldo Awal: ");
				double saldo = input.nextDouble();

				
				Rekening rekeningBaru = new Rekening(no, nama, saldo);

				
				daftarRekening.add(rekeningBaru);

				
				akunAktif = rekeningBaru;

				System.out.println("Akun berhasil ditambahkan ke dalam sistem.");
				break;

			case 2:

				if (akunAktif == null) {

					System.out.println(
							"Error: Mohon maaf, Anda belum memiliki nomor rekening!");

				} else {

					System.out.print("Masukan nominal setor: ");
					double setor = input.nextDouble();

					akunAktif.setorTunai(setor);
				}

				break;

			case 3:

				if (akunAktif == null) {

					System.out.println(
							"Error: Mohon maaf, Anda belum memiliki nomor rekening!");

				} else {

					System.out.print("Masukan nominal tarik: ");
					double tarik = input.nextDouble();

					
					akunAktif.tarikTunai(tarik);
				}

				break;

			case 4:

				if (akunAktif == null) {

					System.out.println(
							"Error: Anda belum membuka rekening!");

				} else {

					akunAktif.cekInformasi();
				}

				break;

			case 0:

				isRunning = false;

				System.out.println(
						"Sistem ditutup. Terima kasih!");

				break;

			default:

				System.out.println(
						"Pilihan tidak valid");
			}
		}

		input.close();
	}
}