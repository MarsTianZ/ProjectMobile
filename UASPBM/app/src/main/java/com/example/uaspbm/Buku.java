package com.example.uaspbm;

import java.util.Date;

public class Buku {

    private String id, judul, penulis, penerbit, deskripsi, cover;
    private Date tahun_terbit;

    public Buku() {
    }

    public Buku(String id, String judul, String penulis, String penerbit, Date tahun_terbit, String deskripsi, String cover){
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahun_terbit = tahun_terbit;
        this.deskripsi = deskripsi;
        this.cover = cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Date getTahun_terbit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(Date tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}