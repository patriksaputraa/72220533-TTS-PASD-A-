package com.javafx;

import java.util.StringTokenizer;

public class Pasien {
    private String kodePasien, nama, alamat, keluhan;

    public Pasien(String kodePasien, String nama, String alamat, String keluhan) {
        this.kodePasien = kodePasien;
        this.nama = nama;
        this.alamat = alamat;
        this.keluhan = keluhan;
    }

    public boolean cekKodePasien() {
        boolean check_kodePasien = true;
        char temp_kodePasien;
        for (int i = 0; i < this.kodePasien.length(); i++) {
            temp_kodePasien = this.kodePasien.charAt(i);
            if (!(Character.isDigit(temp_kodePasien))) {
                check_kodePasien = false;
                break;
            }
        }
        return check_kodePasien;
    }

    public void setKodePasien(String kodePasien) {
        kodePasien += "000000000";
        if (cekKodePasien() == true) {
            this.kodePasien = kodePasien.substring(0, 9);
        }
    }

    public String getKodePasien() {
        return kodePasien;
    }

    public void setNama(String nama) {
        String temp_nama = "";
        String capitalized = "";
        StringTokenizer st = new StringTokenizer(nama, " ");
        for (int i = 0; i < st.countTokens() + 1; i++) {
            temp_nama = st.nextToken();
            capitalized += temp_nama.substring(0, 1).toUpperCase() + temp_nama.substring(1);
            if (i < st.countTokens()) {
                capitalized += " ";
            }
        }
        this.nama = capitalized;
    }

    public String getNama() {
        String format_nama = this.nama + "                   ";
        return format_nama.substring(0, 20);
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat.toUpperCase();
    }

    public String getAlamat() {
        String format_alamat = this.alamat + "                             ";
        return format_alamat.substring(0, 30);
    }

    public void setKeluhan(String keluhan) {
        if (keluhan.length() > 50) {
            this.keluhan = keluhan.substring(0, 50);
        }
    }

    public String getKeluhan() {
        String format_keluhan = this.keluhan + "                             ";
        return format_keluhan.substring(0, 31);
    }

    public boolean cekString() {
        boolean check_string = true;
        char temp_string;
        for (int i = 0; i < this.nama.length(); i++) {
            temp_string = this.nama.charAt(i);
            if (Character.isDigit(temp_string)) {
                check_string = false;
                break;
            }
        }
        return check_string;
    }

    public String getPasien() {
        return getKodePasien() + "," + getNama() + "," + getAlamat() + "," + getKeluhan();
    }

    public String getFormatedPasien(){
        return " " + getKodePasien() + " |" + getNama() + "|" + getAlamat() + "|" + getKeluhan() + "|";
    }
}
