package Hard;

import java.util.Scanner;
public class KliniksSapi {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String nama = "";
        int berat = 0;
        String layanan = "";
        String kelas = "";

        while (true) {
            System.out.print(""); 
            nama = sc.nextLine();
            if (nama.matches("[a-zA-Z]+")) {
                break;
            } else {
                System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
            }
        }

        while (true) {
            System.out.print(""); 
            if (sc.hasNextInt()) {
                berat = sc.nextInt();
                sc.nextLine(); 
                if (berat >= 1) {
                    break;
                } else {
                    System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
                }
            } else {
                System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
                sc.nextLine();
            }
        }

        // 3. Validasi Jenis Layanan
        while (true) {
            System.out.print(""); 
            layanan = sc.nextLine().toLowerCase();
            if (layanan.equals("spa") || layanan.equals("potong_kuku") || layanan.equals("grooming")) {
                break;
            } else {
                System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
            }
        }

        while (true) {
            System.out.print(""); 
            kelas = sc.nextLine().toLowerCase();
            if (kelas.equals("reguler") || kelas.equals("vip")) {
                break;
            } else {
                System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
            }
        }

        double hargaPerKg = 0;
        if (layanan.equals("spa")) hargaPerKg = 8000;
        else if (layanan.equals("potong_kuku")) hargaPerKg = 6000;
        else if (layanan.equals("grooming")) hargaPerKg = 10000;

        double biayaDasar = berat * hargaPerKg;

        double diskon = 0;
        if (berat > 30) {
            diskon = 0.1 * biayaDasar;
        }

        double biayaTambahanVIP = 0;
        if (kelas.equals("vip")) {
            biayaTambahanVIP = 0.2 * biayaDasar;
        }

        double subtotal = biayaDasar - diskon + biayaTambahanVIP;

        double pajak = 0.08 * subtotal;

        double totalBiaya = subtotal + pajak;
        boolean isGratis = nama.equalsIgnoreCase("Moo") || 
                           nama.equalsIgnoreCase("Mooo") || 
                           nama.equalsIgnoreCase("Moooo");
        
        if (isGratis) {
            totalBiaya = 0.0;
        }

        System.out.println("============ NOTA KLINIK SAPI ============");
        System.out.println("Nama Sapi: " + nama);
        System.out.println("Berat: " + berat + " kg");
        System.out.println("Jenis Layanan: " + layanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + biayaDasar);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Biaya Tambahan VIP: Rp " + biayaTambahanVIP);
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Pajak: Rp " + pajak);
        System.out.println("Total Biaya: Rp " + totalBiaya);
        System.out.println("==========================================");

        if (isGratis) {
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
        
        sc.close();
    }
    
}
