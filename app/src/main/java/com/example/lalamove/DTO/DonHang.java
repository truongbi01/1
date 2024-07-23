package com.example.lalamove.DTO;

import java.util.Date;

public class DonHang {
    public DonHang(int maDonHang, String soDienThoaiKhachHang, String soDienThoaiNguoiGui, String noiNhan, String soDienThoaiNguoiNhan, String noiGiao, Date thoiGianDatHang, String maPhuongTien, int giaTien) {
        this.maDonHang = maDonHang;
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
        this.soDienThoaiNguoiGui = soDienThoaiNguoiGui;
        this.noiNhan = noiNhan;
        this.soDienThoaiNguoiNhan = soDienThoaiNguoiNhan;
        this.noiGiao = noiGiao;
        this.thoiGianDatHang = thoiGianDatHang;
        this.maPhuongTien = maPhuongTien;
        this.giaTien = giaTien;
    }

    private int maDonHang;
    private String soDienThoaiKhachHang;
    private String soDienThoaiNguoiGui;
    private String noiNhan;
    private String soDienThoaiNguoiNhan;
    private String noiGiao ;
    private Date thoiGianDatHang;

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getSoDienThoaiKhachHang() {
        return soDienThoaiKhachHang;
    }

    public void setSoDienThoaiKhachHang(String soDienThoaiKhachHang) {
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
    }

    public String getSoDienThoaiNguoiGui() {
        return soDienThoaiNguoiGui;
    }

    public void setSoDienThoaiNguoiGui(String soDienThoaiNguoiGui) {
        this.soDienThoaiNguoiGui = soDienThoaiNguoiGui;
    }

    public String getNoiNhan() {
        return noiNhan;
    }

    public void setNoiNhan(String noiNhan) {
        this.noiNhan = noiNhan;
    }

    public String getSoDienThoaiNguoiNhan() {
        return soDienThoaiNguoiNhan;
    }

    public void setSoDienThoaiNguoiNhan(String soDienThoaiNguoiNhan) {
        this.soDienThoaiNguoiNhan = soDienThoaiNguoiNhan;
    }

    public String getNoiGiao() {
        return noiGiao;
    }

    public void setNoiGiao(String noiGiao) {
        this.noiGiao = noiGiao;
    }

    public Date getThoiGianDatHang() {
        return thoiGianDatHang;
    }

    public void setThoiGianDatHang(Date thoiGianDatHang) {
        this.thoiGianDatHang = thoiGianDatHang;
    }

    public String getMaPhuongTien() {
        return maPhuongTien;
    }

    public void setMaPhuongTien(String maPhuongTien) {
        this.maPhuongTien = maPhuongTien;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    private String maPhuongTien;
    private int giaTien;
}
