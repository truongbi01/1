package com.example.lalamove.View.Home.TaiXe;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Login.DangNhapActivity;
import com.example.lalamove.View.model.TableLoaiPhuongTien.LoaiPhuongTien;
import com.example.lalamove.View.model.TableTaiXe.TaiXe;

public class DangKyTaiXeActivity extends AppCompatActivity {
    private EditText edt_sodienthoai_TaiXe, edt_matkhau_TaiXe, edt_ten_TaiXe, edt_Bienso_vanchuyen;
    private Spinner spinner_phuongtien;
    private Button btn_DangKy_TaiKhoanTaiXe;
    private ImageView showPasswordButton;
    private String loaiTaiKhoan = "TaiXe";
    private boolean isPasswordVisible = false;
    private TextView tv_DaCoTaiKhoan_TaiXe ;

    TaiXe taiXe;
    LoaiPhuongTien loaiPhuongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky_tai_xe);

        taiXe = new TaiXe();
        loaiPhuongTien = new LoaiPhuongTien();

        //AnhXa
        AnhXa();

        //tải dữ liệu phương tiện
        loadPhuongTienData();

        tv_DaCoTaiKhoan_TaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyTaiXeActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });

        showPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    edt_matkhau_TaiXe.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPasswordButton.setImageResource(R.drawable.ic_show);
                } else {
                    edt_matkhau_TaiXe.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPasswordButton.setImageResource(R.drawable.ic_hide);
                }
                isPasswordVisible = !isPasswordVisible;
                edt_matkhau_TaiXe.setSelection(edt_matkhau_TaiXe.getText().length());
            }
        });

        btn_DangKy_TaiKhoanTaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String soDienThoai = edt_sodienthoai_TaiXe.getText().toString();
                String matKhau = edt_matkhau_TaiXe.getText().toString();
                String ten =(edt_ten_TaiXe.getText().toString());
                ten = ten.trim().replaceAll("\\s+", " ");
                String bienso = edt_Bienso_vanchuyen.getText().toString();
                String tenphuongtien = spinner_phuongtien.getSelectedItem().toString();
                String maphuongtien = loaiPhuongTien.getMaPhuongTien(tenphuongtien, DangKyTaiXeActivity.this);

                // Validate input
                if (!isDinhDangSoDienThoai(soDienThoai)) {
                    edt_sodienthoai_TaiXe.setError("Số điện thoại phải đủ 10 số");
                } else if (taiXe.isTaiKhoanTonTai(soDienThoai, DangKyTaiXeActivity.this)) {
                    edt_sodienthoai_TaiXe.setError("Số điện thoại đã được đăng ký");
                } else if (!isDinhDangMatKhau(matKhau)) {
                    edt_matkhau_TaiXe.setError("1 ký tự in hoa, và 1 số");
                } else if (!isDinhDangTen(ten)) {
                    edt_ten_TaiXe.setError("Tên không đúng định dạng");
                } else if (!isDinhDangBienSoXe(bienso)) {
                    edt_Bienso_vanchuyen.setError("Biển số xe có 5 số");
                } else {
                    try {
                        taiXe.sp_insert_TaiKhoanTaiXe(soDienThoai, ten, matKhau, loaiTaiKhoan, null, soDienThoai, bienso, maphuongtien, null, DangKyTaiXeActivity.this);
                        Intent intent = new Intent(DangKyTaiXeActivity.this, DangNhapActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "Lỗi khi đăng ký tài khoản: " + e.getMessage());
                    }
                }
            }
        });
    }

    void AnhXa() {
        edt_sodienthoai_TaiXe = findViewById(R.id.edt_sodienthoai_TaiXe);
        edt_matkhau_TaiXe = findViewById(R.id.edt_matkhau_TaiXe);
        edt_ten_TaiXe = findViewById(R.id.edt_ten_TaiXe);
        spinner_phuongtien = findViewById(R.id.spinner_phuongtien);
        btn_DangKy_TaiKhoanTaiXe = findViewById(R.id.btn_DangKy_TaiKhoanTaiXe);
        showPasswordButton = findViewById(R.id.show_password);
        edt_Bienso_vanchuyen = findViewById(R.id.edt_Bienso_vanchuyen);
        tv_DaCoTaiKhoan_TaiXe= findViewById(R.id.tv_DaCoTaiKhoan_TaiXe);
    }

    void loadPhuongTienData() {
        try {
            LoaiPhuongTien loaiPhuongTien = new LoaiPhuongTien(this, spinner_phuongtien);
            loaiPhuongTien.loadData();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi tải dữ liệu phương tiện: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isDinhDangSoDienThoai(String phoneNumber) {
        String regex = "^[0-9]{10}$";
        return phoneNumber.matches(regex);
    }

    public boolean isDinhDangBienSoXe(String biensoxe) {
        String regex = "^[0-9]{5}$";
        return biensoxe.matches(regex);
    }

    public boolean isDinhDangEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public boolean isDinhDangMatKhau(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        return password.matches(regex);
    }

    public boolean isDinhDangTen(String name) {
        String regex = "^[\\p{L} .'-]+$";
        return name.matches(regex);
    }
}
