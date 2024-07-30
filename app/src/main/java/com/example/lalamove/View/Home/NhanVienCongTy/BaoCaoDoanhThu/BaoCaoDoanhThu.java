package com.example.lalamove.View.Home.NhanVienCongTy.BaoCaoDoanhThu;

public class BaoCaoDoanhThu {
    private String ngay;
    private int soDonVanChuyen;
    private int soDonBiHuy;
    private int doanhThu;
    private int thang;

    public BaoCaoDoanhThu(String ngay, int soDonVanChuyen, int soDonBiHuy, int doanhThu) {
        this.ngay = ngay;
        this.soDonVanChuyen = soDonVanChuyen;
        this.soDonBiHuy = soDonBiHuy;
        this.doanhThu = doanhThu;

    }

    public String getNgay() {
        return ngay;
    }

    public int getSoDonVanChuyen() {
        return soDonVanChuyen;
    }

    public int getSoDonBiHuy() {
        return soDonBiHuy;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

}