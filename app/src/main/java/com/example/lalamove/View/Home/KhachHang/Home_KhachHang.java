package com.example.lalamove.View.Home.KhachHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lalamove.ListLoaiXe.MenuThanhToanActivity;
import com.example.lalamove.ListLoaiXe.VehicleAdapter;
import com.example.lalamove.ListLoaiXe.PhuongTien;
import com.example.lalamove.View.model.TableLoaiPhuongTien.QuerySql;
import com.example.lalamove.R;
import com.example.lalamove.View.Login.DangNhapActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home_KhachHang extends AppCompatActivity  {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText edt_NoiGiao,edt_NoiNhan;

    private LinearLayout additionalInterface , ln_listXeMay;
    private ListView vehicleList;
    private Button btn_next;
    private CheckBox cb_item;
    private boolean isVehicleSelected = false;
    String noiNhan , noiGiao;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu_khachhang);

        // AnhXa
        AnhXa();




        // Set click listeners


        edt_NoiNhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Lấy dữ liệu từ edt_NoiNhan khi nội dung thay đổi
                noiNhan = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Không cần thực hiện hành động gì ở đây nếu không cần thiết
            }
        });


        edt_NoiGiao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Lấy dữ liệu từ edt_NoiNhan khi nội dung thay đổi
                noiGiao = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

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


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_KhachHang.this, MenuThanhToanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAdditionalInterface() {
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
        FrameLayout mainLayout = findViewById(R.id.main_layout);
        if (mainLayout.indexOfChild(additionalInterface) != -1) {
            mainLayout.removeView(additionalInterface);
        }
    }

    private void checkConditions() {
        boolean isAddressFilled = edt_NoiGiao.getText().length() > 0  && edt_NoiNhan.getText().length() > 0;
        btn_next.setEnabled(isAddressFilled );
    }




    @SuppressLint({"InflateParams", "CutPasteId"})
    void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        edt_NoiGiao = findViewById(R.id.edt_diadiemtrakhach_Home_KhachHang);
        edt_NoiNhan = findViewById(R.id.edt_chondiadiem_Home_KhachHang);
        vehicleList = findViewById(R.id.vehicle_list);


        additionalInterface = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_trangchu_menuthanhtoan, null);
        btn_next = additionalInterface.findViewById(R.id.login_button);
        //btn_next.setEnabled(false); // Nút "btn_next" vô hiệu hóa khi bắt đầu
        btn_next.setEnabled(false); // Nút "btn_next" vô hiệu hóa khi bắt đầu

        ln_listXeMay = (LinearLayout)  getLayoutInflater().inflate(R.layout.vehicle_list_item,null);
        cb_item = ln_listXeMay.findViewById(R.id.check_icon);
    }

    void ChuyenTrang(Context context, Class<?> lop) {
        Intent i = new Intent(context, lop);
        startActivity(i);
    }
}
