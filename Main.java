import java.util.*;

abstract class Vehicle {
protected String kode;
    protected String nama;
    protected int hargaSewa;
    protected boolean tersedia;
    protected String tipe;

    public Vehicle(String kode, String nama, int hargaSewa) {
        this.kode = kode;
        this.nama = nama;
        this.hargaSewa = hargaSewa;
        this.tersedia = true;
    }

    public abstract int hitungSewa(int hari, boolean pakaiPromo);

    public String getStatusString() {
        return tersedia ? "TERSEDIA" : "DISEWA";
    }
}
class Car extends Vehicle {
    public Car(String kode, String nama, int hargaSewa) {
        super(kode, nama, hargaSewa);
        this.tipe = "CAR";
    }

    @Override
    public int hitungSewa(int hari, boolean pakaiPromo) {
        int total = this.hargaSewa * hari;
        if (pakaiPromo) {
            total -= 20000;
        }
        return Math.max(0, total); // Total minimum adalah 0
    }
}

class Bike extends Vehicle {
    public Bike(String kode, String nama, int hargaSewa) {
        super(kode, nama, hargaSewa);
        this.tipe = "BIKE";
    }

    @Override
    public int hitungSewa(int hari, boolean pakaiPromo) {
        int total = this.hargaSewa * hari;
        if (pakaiPromo) {
            total -= 10000;
        }
        return Math.max(0, total);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> daftarKendaraan = new LinkedHashMap<>();

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            String[] p = input.split(" ");
            String command = p[0];

            switch (command) {
                case "ADD":
                    String tipe = p[1];
                    String kodeAdd = p[2];
                    String namaAdd = p[3];
                    int hargaAdd = Integer.parseInt(p[4]);

                    if (daftarKendaraan.containsKey(kodeAdd)) {
                        System.out.println("Kendaraan sudah terdaftar");
                    } else {
                        Vehicle v = tipe.equals("CAR") ? new Car(kodeAdd, namaAdd, hargaAdd) : new Bike(kodeAdd, namaAdd, hargaAdd);
                        daftarKendaraan.put(kodeAdd, v);
                        System.out.println(tipe + " " + kodeAdd + " berhasil ditambahkan");
                    }
                    break;

                case "RENT":
                    String kodeRent = p[1];
                    int hari = Integer.parseInt(p[2]);
                    boolean promo = (p.length == 4 && p[3].equals("PROMO"));

                    if (!daftarKendaraan.containsKey(kodeRent)) {
                        System.out.println("Kendaraan tidak ditemukan");
                    } else {
                        Vehicle v = daftarKendaraan.get(kodeRent);
                        if (!v.tersedia) {
                            System.out.println("Kendaraan sedang disewa");
                        } else {
                            int total = v.hitungSewa(hari, promo);
                            v.tersedia = false;
                            System.out.println("Total sewa " + kodeRent + ": " + total);
                        }
                    }
                    break;

                case "RETURN":
                    String kodeReturn = p[1];
                    if (!daftarKendaraan.containsKey(kodeReturn)) {
                        System.out.println("Kendaraan tidak ditemukan");
                    } else {
                        Vehicle v = daftarKendaraan.get(kodeReturn);
                        if (v.tersedia) {
                            System.out.println("Kendaraan belum disewa");
                        } else {
                            v.tersedia = true;
                            System.out.println(kodeReturn + " berhasil dikembalikan");
                        }
                    }
                    break;

                case "DETAIL":
                    String kodeDetail = p[1];
                    if (!daftarKendaraan.containsKey(kodeDetail)) {
                        System.out.println("Kendaraan tidak ditemukan");
                    } else {
                        Vehicle v = daftarKendaraan.get(kodeDetail);
                        System.out.println(v.kode + " | " + v.tipe + " | " + v.nama + " | harga: " + v.hargaSewa + " | status: " + v.getStatusString());
                    }
                    break;

                case "COUNT":
                    System.out.println("Total kendaraan: " + daftarKendaraan.size());
                    break;
            }
        }
        sc.close();
    }
}
    