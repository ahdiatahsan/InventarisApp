package com.example.inventarisapp.DataBase;

public class DataModel {

    public static final String TABLE_SQLite = "tbl_inventaris";
    public static final String COLUMN_KODE = "kode";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_JUMLAH = "jumlah";
    public static final String COLUMN_SATUAN = "satuan";
    public static final String COLUMN_KONDISI = "kondisi";
    public static final String COLUMN_TANGGAL = "tanggal";
    public static final String COLUMN_KETERANGAN = "keterangan";


    private int kode;
    private String nama;
    private String jumlah;
    private String satuan;
    private String kondisi;
    private String tanggal;
    private String keterangan;

    public DataModel(int kode, String nama, String jumlah, String satuan, String kondisi, String tanggal, String keterangan) {
        this.kode = kode;
        this.nama = nama;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.kondisi = kondisi;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }

    public DataModel(){

    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


}