package com.example.lalamove.View.Home.TaiXe.DonHang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableChiTietDonGiao.ChiTiet_QuerySql;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;
import com.example.lalamove.View.model.TableTaiKhoanTaiXe.TaiXe_QuerySql;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChiTietDonHangActivity extends AppCompatActivity {

    // TextView variables
    TextView tv_ChiTietDonHang_SoSaoDanhGia, tv_ChiTietDonHang_ThoiGian_Thu, tv_ChiTietDonHang_ThoiGian_Gio,
            tv_ChiTietDonHang_ThoiGian_Thang, tv_ChiTietDonHang_SoDienThoaiNguoiGui, tv_ChiTietDonHang_ChiTietDonHang,
            tv_ChiTietDonHang_MaDonHang, tv_ChiTietDonHang_SoDienThoaiTaiXe, tv_ChiTietDonHang_tongtien,
            tv_ChiTietDonHang_TenTaiXe, tv_ChiTietDonHang_TenPhuongTien, tv_ChiTietDonHang_DiemGiao, tv_ChiTietDonHang_DiemNhan,
            tv_ChiTietDonHang_TrangThai,tv_ChiTietDonHang_SoDienThoaiNguoiNhan;

    AppCompatButton btn_huy_chitietdonhang_taixe,btn_hoanthanh_chitietdonhang_taixe;
    ImageView imgv_ChiTietDonHang_Back;


    SharedPreferences sharedPreferences,sharedPreferences2, sharedPreferences3;
    SharedPreferences.Editor editor2,editor3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietdonhang);

        // AnhXa
        AnhXa();

        // Lay Share
        LayDuLieuTuShare();

        // Set Dữ liệu
        SetDuLieu();

        //phân role
        TaiKhoanSQL taiKhoanSQL = new TaiKhoanSQL();
        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(sharedPreferences.getString("sodienthoai",""), ChiTietDonHangActivity.this);
        if(loaiTaiKhoan.equals("KhachHang")){
            btn_hoanthanh_chitietdonhang_taixe.setVisibility(View.GONE);
        }



        //Xóa khi bấm back
        imgv_ChiTietDonHang_Back.setOnClickListener(v -> XoaDuLieu());


    }
    private void LayDuLieuTuShare() {
        sharedPreferences = getSharedPreferences("ThongTinDangNhap",Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("ChiTietDonHang", Context.MODE_PRIVATE);
        sharedPreferences3 = getSharedPreferences("ChiTietTaiXe", Context.MODE_PRIVATE);

    }
    private void SetDuLieu(){
        String tenPhuongtien = sharedPreferences2.getString("tenphuongtien", "");
        String noiNhan = sharedPreferences2.getString("diachinhandon", "");
        int giaTien = sharedPreferences2.getInt("tongtien", 0);
        int maDonHang = sharedPreferences2.getInt("madonhang", 0);
        String sodienthoaitaixe = sharedPreferences2.getString("sodienthoaitaixe", "");
        String noiGiao = sharedPreferences2.getString("diachigiao", "");
        String trangThai = sharedPreferences2.getString("trangthai", "");
        String sdtNguoiNhan = sharedPreferences2.getString("sodienthoainguoinhan","");
        String sdtNguoiGui = sharedPreferences2.getString("sodienthoainguoigui","");

        ChiTiet_QuerySql chiTietQuerySql = new ChiTiet_QuerySql(this);
        Timestamp thoiGianNhanHang = chiTietQuerySql.getThoiGianNhanDon(sodienthoaitaixe, maDonHang);

        DinhDangThoiGian(thoiGianNhanHang);

        TaiXe_QuerySql taiXeQuerySql = new TaiXe_QuerySql();
        taiXeQuerySql.getTenTaiXe_SoSao(sodienthoaitaixe, this);

        String tenTaiXe = sharedPreferences3.getString("tentaixe", "");
        int diemDanhGia = sharedPreferences3.getInt("diemdanhgia", 0);

        tv_ChiTietDonHang_SoDienThoaiNguoiGui.setText(sdtNguoiGui);
        tv_ChiTietDonHang_SoDienThoaiNguoiNhan.setText(sdtNguoiNhan);
        tv_ChiTietDonHang_SoDienThoaiTaiXe.setText(sodienthoaitaixe);
        tv_ChiTietDonHang_MaDonHang.setText(String.valueOf(maDonHang));
        tv_ChiTietDonHang_TrangThai.setText(trangThai);
        tv_ChiTietDonHang_TenTaiXe.setText(tenTaiXe);
        tv_ChiTietDonHang_SoSaoDanhGia.setText(String.format(Locale.getDefault(), "%d.0", diemDanhGia));
        tv_ChiTietDonHang_TenPhuongTien.setText(tenPhuongtien);
        tv_ChiTietDonHang_DiemNhan.setText(noiNhan);
        tv_ChiTietDonHang_DiemGiao.setText(noiGiao);
        tv_ChiTietDonHang_tongtien.setText(String.format(Locale.getDefault(), "%,d đ", giaTien));
    }
    private void DinhDangThoiGian(Timestamp thoiGianNhanHang){
        if (thoiGianNhanHang != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
            String formattedDayOfWeek = dateFormat.format(thoiGianNhanHang);

            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = dateFormat.format(thoiGianNhanHang);

            dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            String formattedTime = dateFormat.format(thoiGianNhanHang);

            tv_ChiTietDonHang_ThoiGian_Thu.setText(formattedDayOfWeek);
            tv_ChiTietDonHang_ThoiGian_Thang.setText(formattedDate);
            tv_ChiTietDonHang_ThoiGian_Gio.setText(formattedTime);
        } else {
            tv_ChiTietDonHang_ThoiGian_Thu.setText("N/A");
            tv_ChiTietDonHang_ThoiGian_Thang.setText("N/A");
            tv_ChiTietDonHang_ThoiGian_Gio.setText("N/A");
        }
    }
    private void XoaDuLieu(){
        editor2 = sharedPreferences2.edit();
        editor3 = sharedPreferences3.edit();

        editor2.clear();
        editor3.clear();

        editor2.apply();
        editor3.apply();

        finish();
    }


    void AnhXa() {

        tv_ChiTietDonHang_TrangThai = findViewById(R.id.tv_ChiTietDonHang_TrangThai);
        tv_ChiTietDonHang_DiemGiao = findViewById(R.id.tv_ChiTietDonHang_DiemGiao);
        tv_ChiTietDonHang_DiemNhan = findViewById(R.id.tv_ChiTietDonHang_DiemNhan);
        tv_ChiTietDonHang_TenPhuongTien = findViewById(R.id.tv_ChiTietDonHang_TenPhuongTien);
        tv_ChiTietDonHang_TenTaiXe = findViewById(R.id.tv_ChiTietDonHang_TenTaiXe);
        tv_ChiTietDonHang_SoSaoDanhGia = findViewById(R.id.tv_ChiTietDonHang_SoSaoDanhGia);
        tv_ChiTietDonHang_ThoiGian_Thu = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Thu);
        tv_ChiTietDonHang_ThoiGian_Thang = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Thang);
        tv_ChiTietDonHang_ThoiGian_Gio = findViewById(R.id.tv_ChiTietDonHang_ThoiGian_Gio);
        tv_ChiTietDonHang_SoDienThoaiNguoiGui = findViewById(R.id.tv_ChiTietDonHang_SoDienThoaiNguoiGui);
        tv_ChiTietDonHang_SoDienThoaiNguoiNhan = findViewById(R.id.tv_ChiTietDonHang_SoDienThoaiNguoiNhan);
        tv_ChiTietDonHang_MaDonHang = findViewById(R.id.tv_ChiTietDonHang_MaDonHang);
        tv_ChiTietDonHang_SoDienThoaiTaiXe = findViewById(R.id.tv_ChiTietDonHang_SoDienThoaiTaiXe);
        tv_ChiTietDonHang_tongtien = findViewById(R.id.tv_ChiTietDonHang_tongtien);
        btn_huy_chitietdonhang_taixe = findViewById(R.id.btn_huy_chitietdonhang_taixe);
        btn_hoanthanh_chitietdonhang_taixe = findViewById(R.id.btn_hoanthanh_chitietdonhang_taixe);
        imgv_ChiTietDonHang_Back = findViewById(R.id.imgv_ChiTietDonHang_Back);
    }


}
