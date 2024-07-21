package com.example.lalamove.ListLoaiXe;

public class PhuongTien {
    public String getTenPhuongTien() {
        return tenPhuongTien;
    }

    public void setTenPhuongTien(String tenPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
    }

    public int getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(int trongLuong) {
        this.trongLuong = trongLuong;
    }

    String tenPhuongTien;
    int trongLuong;

    public PhuongTien(String tenPhuongTien, int trongLuong) {
        this.tenPhuongTien = tenPhuongTien;
        this.trongLuong = trongLuong;
    }

    @Override
    public String toString() {
        return "PhuongTien{" +
                "tenPhuongTien='" + tenPhuongTien + '\'' +
                ", trongLuong=" + trongLuong +
                '}';
    }
}