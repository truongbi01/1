package com.example.lalamove.View.Home.KhachHang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lalamove.ListLoaiXe.VehicleAdapter;
import com.example.lalamove.ListLoaiXe.PhuongTien;
import com.example.lalamove.View.model.TableLoaiPhuongTien.QuerySql;
import com.example.lalamove.R;
import com.example.lalamove.View.Login.DangNhapActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home_KhachHang extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText diadiemtrakhach;
    private EditText chondiadiem;
    private LinearLayout additionalInterface;
    private ListView vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu_khachhang);

        // AnhXa
        AnhXa();

        // Inflate the additional interface layout
        additionalInterface = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_trangchu_menuthanhtoan, null);

        // Set click listeners
        diadiemtrakhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdditionalInterface();
            }
        });

        chondiadiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdditionalInterface();
            }
        });

        diadiemtrakhach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hideAdditionalInterface();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        chondiadiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hideAdditionalInterface();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                showAdditionalInterface();
                drawerLayout.closeDrawers();
                int menuID = item.getItemId();
                if (menuID == R.id.nav_orders) {

                    return true;

                } else if (menuID == R.id.nav_recharge) {

                    return true;

                } else if (menuID == R.id.nav_favorite_drivers) {

                    return true;

                } else if (menuID == R.id.nav_settings) {

                    return true;

                } else if (menuID == R.id.nav_help_center) {

                    return true;

                } else if (menuID == R.id.nav_logout) {
                    ChuyenTrang(Home_KhachHang.this, DangNhapActivity.class);
                    return true;

                } else
                    return true;
            }
        });

        // Tải dữ liệu từ database và thiết lập danh sách phương tiện
        QuerySql querySql = new QuerySql();
        ArrayList<PhuongTien> vehicles = new ArrayList<>(querySql.getDataPhuongTien());

        VehicleAdapter adapter = new VehicleAdapter(this, vehicles);
        vehicleList.setAdapter(adapter);

        // Xử lý sự kiện nhấn vào icon menu
        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Xử lý sự kiện nhấn vào icon mũi tên trong navigation view
        View headerView = navigationView.getHeaderView(0);
        ImageView arrowIcon = headerView.findViewById(R.id.arrow_icon);
        arrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Xử lý sự kiện nhấn vào button trong layout_trangchu_menuthanhtoan
        Button loginButton = additionalInterface.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Home_KhachHang.this, BuocTiepTheoActivity.class);
                startActivity(intent);*/
            }
        });
    }

    private void showAdditionalInterface() {
        // Thêm giao diện bổ sung vào layout chính
        FrameLayout mainLayout = findViewById(R.id.main_layout);
        if (mainLayout.indexOfChild(additionalInterface) == -1) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            params.gravity = Gravity.BOTTOM;
            mainLayout.addView(additionalInterface, params);
        }
    }

    private void hideAdditionalInterface() {
        // Ẩn giao diện bổ sung khỏi layout chính
        FrameLayout mainLayout = findViewById(R.id.main_layout);
        if (mainLayout.indexOfChild(additionalInterface) != -1) {
            mainLayout.removeView(additionalInterface);
        }
    }

    void AnhXa() {
        // Ánh xạ các view
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        diadiemtrakhach = findViewById(R.id.diadiemtrakhach);
        chondiadiem = findViewById(R.id.chondiadiem);
        vehicleList = findViewById(R.id.vehicle_list);
    }

    void ChuyenTrang(Context context, Class<?> lop) {
        Intent i = new Intent(context, lop);
        startActivity(i);
    }
}
