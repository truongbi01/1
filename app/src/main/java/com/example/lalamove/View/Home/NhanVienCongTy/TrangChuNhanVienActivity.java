package com.example.lalamove.View.Home.NhanVienCongTy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;

public class TrangChuNhanVienActivity extends AppCompatActivity {
    TextView tv_DangXuat_TrangChuNhanVienDichVu;
    LinearLayout ln_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu_nhan_vien_dich_vu_truong_phong);
        //Anh Xa
        AnhXa();

        tv_DangXuat_TrangChuNhanVienDichVu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ln_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuNhanVienActivity.this, CaiDatKhacActivity.class);
                startActivity(intent);
            }
        });
    }

    void AnhXa(){
        tv_DangXuat_TrangChuNhanVienDichVu = findViewById(R.id.tv_DangXuat_TrangChuNhanVienDichVu);
        ln_setting = findViewById(R.id.ln_setting);
    }
}