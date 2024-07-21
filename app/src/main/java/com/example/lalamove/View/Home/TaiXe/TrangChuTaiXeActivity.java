package com.example.lalamove.View.Home.TaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.Login.DangNhapActivity;

public class TrangChuTaiXeActivity extends AppCompatActivity {
    private TextView tv_TaiXe_DangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trang_chu_tai_xe);
        //Ánh xạ
        AnhXa();
        //Đăng xuất
        tv_TaiXe_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    //Ánh xạ
    void AnhXa(){
        tv_TaiXe_DangXuat= findViewById(R.id.tv_TaiXe_DangXuat);
    }




}
