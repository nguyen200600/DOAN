package com.example.doan;

public class ModelUniversity {
    String ten,ma,namthanhlap,sdt,url,hptb,diachi,dacdiem;

    public ModelUniversity(){

    }

    public ModelUniversity(String ten, String ma, String namthanhlap, String sdt, String url, String hptb, String diachi, String dacdiem) {
        this.ten = ten;
        this.ma = ma;
        this.namthanhlap = namthanhlap;
        this.sdt = sdt;
        this.url = url;
        this.hptb = hptb;
        this.diachi = diachi;
        this.dacdiem = dacdiem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNamthanhlap() {
        return namthanhlap;
    }

    public void setNamthanhlap(String namthanhlap) {
        this.namthanhlap = namthanhlap;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHptb() {
        return hptb;
    }

    public void setHptb(String hptb) {
        this.hptb = hptb;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDacdiem() {
        return dacdiem;
    }

    public void setDacdiem(String dacdiem) {
        this.dacdiem = dacdiem;
    }
}

