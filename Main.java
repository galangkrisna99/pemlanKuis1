import java.util.*;

// Kelas Induk
abstract class Student {
    protected String nama;
    protected int saldo;
    protected String tipe;

    public Student(String nama) {
        this.nama = nama;
        this.saldo = 0;
    }

    public String getNama() {
        return nama;
    }

    public int getSaldo() {
        return saldo;
    }

    public void save(int jumlah) {
        this.saldo += jumlah;
    }

    // Metode abstrak untuk pengambilan karena beda perlakuan
    public abstract boolean take(int jumlah);

    public String getTipe() {
        return tipe;
    }
}

// Kelas Turunan Reguler
class Reguler extends Student {
    public Reguler(String nama) {
        super(nama);
        this.tipe = "REGULER";
    }

    @Override
    public boolean take(int jumlah) {
        if (this.saldo >= jumlah) {
            this.saldo -= jumlah;
            return true;
        }
        return false;
    }
}

// Kelas Turunan Beasiswa
class Beasiswa extends Student {
    public Beasiswa(String nama) {
        super(nama);
        this.tipe = "BEASISWA";
    }

    @Override
    public boolean take(int jumlah) {
        if (this.saldo >= jumlah) {
            // Beasiswa dapat potongan 1000
            this.saldo -= (jumlah - 1000);
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Student> daftarSiswa = new LinkedHashMap<>();

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];

            switch (command) {
                case "CREATE":
                    String tipe = parts[1];
                    String namaBaru = parts[2];
                    if (daftarSiswa.containsKey(namaBaru)) {
                        System.out.println("Akun sudah terdaftar");
                    } else {
                        Student s = tipe.equals("REGULER") ? new Reguler(namaBaru) : new Beasiswa(namaBaru);
                        daftarSiswa.put(namaBaru, s);
                        System.out.println(tipe + " " + namaBaru + " berhasil dibuat");
                    }
                    break;

                case "SAVE":
                    String namaSimpan = parts[1];
                    int jmlSimpan = Integer.parseInt(parts[2]);
                    if (daftarSiswa.containsKey(namaSimpan)) {
                        Student s = daftarSiswa.get(namaSimpan);
                        s.save(jmlSimpan);
                        System.out.println("Saldo " + namaSimpan + ": " + s.getSaldo());
                    } else {
                        System.out.println("Akun tidak ditemukan");
                    }
                    break;

                case "TAKE":
                    String namaTarik = parts[1];
                    int jmlTarik = Integer.parseInt(parts[2]);
                    if (daftarSiswa.containsKey(namaTarik)) {
                        Student s = daftarSiswa.get(namaTarik);
                        if (s.take(jmlTarik)) {
                            System.out.println("Saldo " + namaTarik + ": " + s.getSaldo());
                        } else {
                            System.out.println("Saldo " + namaTarik + " tidak cukup");
                        }
                    } else {
                        System.out.println("Akun tidak ditemukan");
                    }
                    break;

                case "CHECK":
                    String namaCek = parts[1];
                    if (daftarSiswa.containsKey(namaCek)) {
                        Student s = daftarSiswa.get(namaCek);
                        System.out.println(s.getNama() + " | " + s.getTipe() + " | saldo: " + s.getSaldo());
                    } else {
                        System.out.println("Akun tidak ditemukan");
                    }
                    break;
            }
        }
        sc.close();
    }
}