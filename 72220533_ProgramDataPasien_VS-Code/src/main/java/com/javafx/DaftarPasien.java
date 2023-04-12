package com.javafx;

import java.io.IOException;

public class DaftarPasien {
    Pasien[] data;
    private int cacah;
    private FileTeks file;

    DaftarPasien(int tampung) throws IOException{
        this.data = new Pasien[tampung];
        this.cacah = 0;
        this.file = new FileTeks("src/main/java/com/javafx/ListPasien.csv");
        isiPasien();
    }

    public void isiPasien() throws IOException{
        String[] array_csv = this.file.read();
        String[] row;
        String kodePasien,nama,alamat,keluhan;
        if(!array_csv[0].equalsIgnoreCase("")){
            for(int i=0; i<array_csv.length;i++){
                row = array_csv[i].split(",");
                kodePasien = row[0];
                nama = row[1];
                alamat = row[2];
                keluhan = row[3];
                this.tambah(kodePasien, nama, alamat, keluhan);
            }
        }
    }

    public void saveToFile() throws IOException{
        String save = "";
        for (int i = 0; i < this.cacah; i++) {
            save += data[i].getPasien() + "\n";            
        }
        this.file.write(save);
    }
    
    public void growSize(){
        Pasien temp[] = null;
        if(cacah==data.length){
            temp = new Pasien[data.length+1];
            for(int i=0;i<data.length;i++){
                temp[i] = data[i];
            }
        }
        data=temp;
    }

    public void decreaseSize(){
        Pasien temp[] = null;
        if(cacah==data.length){
            temp = new Pasien[data.length-1];
            for(int i=0;i<data.length;i++){
                temp[i] = data[i];
            }
        }
        data=temp;
    }

    public void tambah(String nim, String nama, String kota, String keluhan){
        Pasien add = new Pasien(nim,nama,kota,keluhan);
        add.setKodePasien(nim);
        add.setNama(nama);
        add.setAlamat(kota);
        add.setKeluhan(keluhan);
        if(this.cacah<data.length){
            this.data[this.cacah++] = add;
        }
        growSize();
    }

    public String getAll(){
        String semua = "|No|Kode Pasien|     Nama Pasien    |            Alamat            |            Keluhan            |\n";
        semua       += "|--|-----------|--------------------|------------------------------|-------------------------------|\n";
        for (int i = 0; i < this.cacah; i++) {
            if(i<9){
                semua += "|0" + (i+1) + "|" + this.data[i].getFormatedPasien() + "\n";
            }else{
                semua += "|" + (i+1) + "|" + this.data[i].getFormatedPasien() + "\n";
            }
        }
        return semua;
    }

    public void bubbleKodePasien(String sort){
        int p,b;
        Pasien temp;
        if(sort.equals("Ascending")){
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getKodePasien().compareTo(this.data[b+1].getKodePasien())>0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }else{
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getKodePasien().compareTo(this.data[b+1].getKodePasien())<0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleNama(String sort){
        int p,b;
        Pasien temp;
        if(sort.equals("Ascending")){
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getNama().compareTo(this.data[b+1].getNama())>0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }else{
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getNama().compareTo(this.data[b+1].getNama())<0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleAlamat(String sort){
        int p,b;
        Pasien temp;
        if(sort.equals("Ascending")){
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getAlamat().compareTo(this.data[b+1].getAlamat())>0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }else{
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getAlamat().compareTo(this.data[b+1].getAlamat())<0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }
    }

    public void bubbleKeluhan(String sort){
        int p,b;
        Pasien temp;
        if(sort.equals("Ascending")){
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getKeluhan().compareTo(this.data[b+1].getKeluhan())>0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }else{
            for (p = 1; p < this.cacah; p++) {
                for (b = 0; b < (cacah-p); b++) {
                    if(this.data[b].getKeluhan().compareTo(this.data[b+1].getKeluhan())<0){
                        temp = this.data[b];
                        this.data[b] = this.data[b+1];
                        this.data[b+1] = temp;
                    }
                }
            }
        }
    }

    public void deleteData(){
        cacah--;
        Pasien temp[] = null;
        temp = new Pasien[data.length - 1];
        for (int i = 0; i < data.length-1; i++) {
            temp[i] = this.data[i];
        }
        this.data=temp;
    }

    public boolean getCekKodePasien(){
        boolean cek = false;
        if(this.data[cacah-1].cekKodePasien()){
            cek = true;
        }
        return cek;
    }
    
    public boolean getCekString(){
        boolean cek = false;
        if(this.data[cacah-1].cekString()){
            cek = true;
        }
        return cek;
    }
}
