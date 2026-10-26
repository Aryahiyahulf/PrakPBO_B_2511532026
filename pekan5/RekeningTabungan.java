package pekan5;

public class RekeningTabungan extends Rekening {
	private double sukuBunga;
	
	public RekeningTabungan(String nomor, String nama, double saldoAwal,
			String pinAwal, double sukuBunga) {
		
		super(nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
		
	}
	
	public void tambahBungaAkhirBulan() {
		
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		//mencari riwayat Transaksi
		String idTrx = "TRX-B" + System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi(idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% Berhasil ditambahkan: Rp" + nominalBunga);
	}

}
