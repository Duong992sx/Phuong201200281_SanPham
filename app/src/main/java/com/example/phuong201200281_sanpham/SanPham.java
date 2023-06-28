package com.example.phuong201200281_sanpham;

public class SanPham {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    public boolean isKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(boolean khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    private int id;
    private String tensanpham;
    private int giatien;
    private boolean khuyenmai;

    public SanPham(int id, String tensanpham, int giatien, int khuyenmai) {
        this.id = id;
        this.tensanpham = tensanpham;
        this.giatien = giatien;
        this.khuyenmai = khuyenmai==1;
    }
     public SanPham( String tensanpham, int giatien, boolean khuyenmai) {
        this.tensanpham = tensanpham;
        this.giatien = giatien;
        this.khuyenmai = khuyenmai;
    }

}
