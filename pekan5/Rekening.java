package pekan5;

import java.util.*;

public class Rekening {

	private String nomorRekening;
	private String namaPemilik;
	protected double saldo;
	private String pin;
	
	protected ArrayList<Transaksi> riwayatTransaksi;

	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {

		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		// validasi pin didalam constructor
		if (pinAwal != null && pinAwal.matches("\\d{6}")) {
		    this.pin = pinAwal;
		   
		} else {
		    System.out.println("Peringatan: PIN harus 6 digit angka! Menggunakan PIN default 123456");
		    this.pin = "123456";
		}
		this.riwayatTransaksi = new ArrayList<>();

		System.out.println("Rekening atas nama " + namaPemilik
				+ " berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public String getNomorRekening() {return nomorRekening;}
	public String getNamaPemilik() {return namaPemilik;}
	
	public boolean otentikasi(String inputPin) {
		return this.pin.equals(inputPin);
	}

	public void transfer(Rekening tujuan, double nominal, String pinInput) {
		if (!otentikasi(pinInput)) {
			System.out.println("Transfer gagal: PIN tidak sesuai.");
			return;
		}

		if (tujuan == null) {
			System.out.println("Transfer gagal: Rekening tujuan tidak ditemukan.");
			return;
		}

		if (tujuan == this) {
			System.out.println("Transfer gagal: Rekening tujuan harus berbeda.");
			return;
		}

		if (this.tarikTunai(nominal)) {
			tujuan.setorTunai(nominal);
			System.out.println("Transfer ke rekening " + tujuan.getNomorRekening()
					+ " berhasil.");
		}
	}

	public void setorTunai(double nominal) {

		if (nominal > 0) {
			saldo += nominal;

			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);

			System.out.println("Setor tunai Rp" + nominal
					+ " berhasil. Saldo saat ini: Rp" + saldo);

		} else {

			System.out.println("Transaksi Gagal: Nominal setor harus lebih dari 0.");

		}
	}

	public void cekInformasi() {

		System.out.println("--- INFO REKENING ---");
		System.out.println("No.Rekening : " + nomorRekening);
		System.out.println("Nama Pemilik : " + namaPemilik);
		System.out.println("Saldo : Rp" + saldo);
		System.out.println("---------------------");

	}

	
	public boolean tarikTunai(double nominal) {
	    if (nominal <= 0) {
	        System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
	        return false;
	    } else if (nominal > saldo) {
	        System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
	        return false;
	    } else {
	        saldo -= nominal;

	        String idTrx = "TRX-T-" + System.currentTimeMillis();
	        riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));

	        System.out.println("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
	        return true; 
	    }
	}
	public void cetakMutasi() {

		if (riwayatTransaksi.isEmpty()) {

			System.out.println("Belum ada transaksi pada rekening ini");

		} else {

			System.out.println("=== 3 TRANSAKSI TERBARU ===");

			int mulai = Math.max(0, riwayatTransaksi.size() - 3);

			for (int i = mulai; i < riwayatTransaksi.size(); i++) {
				riwayatTransaksi.get(i).cetakDetail();
			}

		}
	}
	
	

}