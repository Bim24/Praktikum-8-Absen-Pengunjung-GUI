import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Absen {

    ArrayList<Pengunjung> listPengunjung;

    public Absen() {
        Scanner in = new Scanner(System.in);
        this.listPengunjung = new ArrayList<>();

        while (true) {

            System.out.println("Nama Lengkap:");
            String namaLengkap = in.nextLine();
            if (namaLengkap.isEmpty()) {
                System.out.println("Error: Tidak boleh kosong!");
                continue;
            }
            if (cekDuplikat(namaLengkap)) {
                System.out.println("Error: Nama " + namaLengkap + " sudah absen!");
                continue;
            }

            System.out.println("Asal Kota:");
            String asalKota = in.nextLine();
            if (asalKota.isEmpty()) {
                System.out.println("Error: Tidak boleh kosong!");
                continue;
            }
            if (!cekAsalKota(asalKota)) {
                System.out.println("Warning: Asal kota harus dari Jawa Timur");
                continue;
            }

            System.out.println("Nomor Ponsel:");
            String nomorPonsel = in.nextLine();
            if (!cekNomorPonsel(nomorPonsel)) {
                System.out.println("Error: Format nomor ponsel salah!");
                continue;
            }

            System.out.println("Email:");
            String email = in.nextLine();
            if (!cekEmail(email)) {
                System.out.println("Error: Format email salah!");
                continue;
            }
            
            
            this.listPengunjung.add(new Pengunjung(namaLengkap, asalKota, nomorPonsel, email));

            cetakDaftarPengunjung();
        }
    }

    /**
     * Cek apakah nama yang diberikan sudah ada di list pengunjung.
     * @param nama Nama yang akan dicek
     * @return     Bernilai true apabila ada nama yang sama di list.
     */
    boolean cekDuplikat(String nama) {
        for (Pengunjung pengunjung : listPengunjung) {
            if (pengunjung.namaLengkap.equals(nama)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cek apakah asal kota ada di Jawa Timur
     * @param kota Nama kota yang akan dicek
     * @return     Bernilai true apabila kota ada di Jawa Timur
     */
    boolean cekAsalKota(String kota) {
        String[] kotaJatim = {
                "Kabupaten Bangkalan",
                "Kabupaten Banyuwangi",
                "Kabupaten Blitar",
                "Kabupaten Bojonegoro",
                "Kabupaten Bondowoso",
                "Kabupaten Gresik",
                "Kabupaten Jember",
                "Kabupaten Jombang",
                "Kabupaten Kediri",
                "Kabupaten Lamongan",
                "Kabupaten Lumajang",
                "Kabupaten Madiun",
                "Kabupaten Magetan",
                "Kabupaten Malang",
                "Kabupaten Mojokerto",
                "Kabupaten Nganjuk",
                "Kabupaten Ngawi",
                "Kabupaten Pacitan",
                "Kabupaten Pamekasan",
                "Kabupaten Pasuruan",
                "Kabupaten Ponorogo",
                "Kabupaten Probolinggo",
                "Kabupaten Sampang",
                "Kabupaten Sidoarjo",
                "Kabupaten Situbondo",
                "Kabupaten Sumenep",
                "Kabupaten Trenggalek",
                "Kabupaten Tuban",
                "Kabupaten Tulungagung",
                "Kota Batu",
                "Kota Blitar",
                "Kota Kediri",
                "Kota Madiun",
                "Kota Malang",
                "Kota Mojokerto",
                "Kota Pasuruan",
                "Kota Probolinggo",
                "Kota Surabaya"};
        for (int i = 0; i < kotaJatim.length; i++) {
            if (kotaJatim[i].toUpperCase().equals(kota.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mengecek format nomor ponsel
     * @param s Nomor ponsel yang akan dicek
     * @return  Nilai true apabila format benar dan false apabila salah
     */
    boolean cekNomorPonsel(String s) {
        return Pattern.matches("[+]?[0-9]{6,19}", s);
    }

    /**
     * Mengecek format email
     * @param email Email yang akan dicek
     * @return      Nilai true apabila format benar
     */
    final boolean cekEmail(String email) {
        return Pattern.matches("^(.+)@(.+)$", email);
    }

    /**
     * Mencetak semua daftar pengunjung dalam bentuk tabel pada konsol.
     * Contoh:
     * +------------------+---------------+-------------------+----------------+
     * | NAMA LENGKAP     | ASAL KOTA     | NOMOR PONSEL      | EMAIL          |
     * +------------------+---------------+-------------------+----------------+
     * | Budi Hartono     | Malang        | 1234512345        | budi@gmail.com |
     * | Iwan Sutrisno    | Banyuwangi    | 23456723456       | iwan@gmail.com |
     * +------------------+---------------+-------------------+----------------+
     */
    public void cetakDaftarPengunjung() {
        String garis = "+--------------------+----------------------+---------------+----------------------+%n";
        String format = "| %-18s | %-20s | %-13s | %-20s |%n";
        System.out.format(garis);
        System.out.format(format, "NAMA LENGKAP", "ASAL KOTA", "NOMOR PONSEL","EMAIL");
        System.out.format(garis);
        for (Pengunjung p : listPengunjung) {
            System.out.format(format, p.namaLengkap, p.asalKota, p.nomorPonsel, p.email);
        }
        System.out.format(garis);
    }
}
