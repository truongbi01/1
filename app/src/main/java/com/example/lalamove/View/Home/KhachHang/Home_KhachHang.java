package com.example.lalamove.View.Home.KhachHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lalamove.ListLoaiXe.VehicleAdapter;
import com.example.lalamove.ListLoaiXe.PhuongTien;
import com.example.lalamove.View.model.TableLoaiPhuongTien.QuerySql;
import com.example.lalamove.R;
import com.example.lalamove.View.Login.DangNhapActivity;
import com.example.lalamove.View.model.TinhKhoangCach;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Home_KhachHang extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText edt_NoiGiao, edt_NoiNhan;
    private LinearLayout additionalInterface, ln_listXeMay;
    private ListView vehicleList;
    private Button btn_next;
    private CheckBox cb_item;
    private TextView tv_tongtien;
    private LinearLayout thanhtoantt;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu_khachhang);

        // AnhXa
        AnhXa();

        thanhtoantt.setVisibility(View.GONE); // Ẩn thanh toán ban đầu

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

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
        adapter.setOnVehicleSelectedListener(new VehicleAdapter.OnVehicleSelectedListener() {
            @Override
            public void onVehicleSelected(int position) {
                checkAndShowPayment();
            }
        });

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
                Intent intent = new Intent(Home_KhachHang.this, BuocTiepTheoActivity.class);
                startActivity(intent);
            }
        });

        edt_NoiGiao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkAndShowPayment();
            }
        });

        edt_NoiNhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkAndShowPayment();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void checkAndShowPayment() {
        String noiGiao = edt_NoiGiao.getText().toString().trim();
        String noiNhan = edt_NoiNhan.getText().toString().trim();
        boolean isAddressFilled = !noiGiao.isEmpty() && !noiNhan.isEmpty();
        VehicleAdapter adapter = (VehicleAdapter) vehicleList.getAdapter();
        boolean isVehicleSelected = adapter != null && adapter.getSelectedPosition() != -1;

        if (isAddressFilled && isVehicleSelected) {
            TinhKhoangCach calculator = new TinhKhoangCach(this);
            double distance = calculator.getDistance(noiNhan, noiGiao);

            if (distance >= 0) {
                // Tính giá dựa trên khoảng cách và phương tiện được chọn
                double price = calculatePrice(distance, adapter.getSelectedPosition());

                // Hiển thị khoảng cách và giá
                tv_tongtien.setText(String.format("%.2f km - %.2f đ", distance, price));
                thanhtoantt.setVisibility(View.VISIBLE);
                btn_next.setEnabled(true);
            } else {
                // Xử lý lỗi khi không thể tính khoảng cách
                Toast.makeText(this, "Không thể tính khoảng cách. Vui lòng kiểm tra lại địa chỉ.", Toast.LENGTH_SHORT).show();
                thanhtoantt.setVisibility(View.GONE);
                btn_next.setEnabled(false);
            }
        } else {
            thanhtoantt.setVisibility(View.GONE);
            btn_next.setEnabled(false);
        }
    }

    private double calculatePrice(double distance, int vehicleType) {
        double basePrice;
        switch (vehicleType) {
            case 0: // Xe máy
                basePrice = 10000;
                break;
            case 1: // Xe tai nhỏ
                basePrice = 35000;
                break;
            case 2: // Xe van nhỏ
                basePrice = 30000;
                break;
            case 3: // Xe tải trung
                basePrice = 65000;
                break;
            case 4: // Xe van trung
                basePrice = 50000;
                break;
            case 5: // Xe tải lớn
                basePrice = 100000;
                break;
            case 6: // Xe van lớn
                basePrice = 90000;
                break;
            default: // Xe tải lớn
                basePrice = 0;
                break;
        }
        return basePrice * distance;
    }

    @SuppressLint({"InflateParams", "CutPasteId"})
    void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        edt_NoiGiao = findViewById(R.id.edt_diadiemtrakhach_Home_KhachHang);
        edt_NoiNhan = findViewById(R.id.edt_chondiadiem_Home_KhachHang);
        vehicleList = findViewById(R.id.vehicle_list);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        btn_next = findViewById(R.id.btn_buoctieptheo);
        btn_next.setEnabled(false); // Nút "btn_next" vô hiệu hóa khi bắt đầu
        ln_listXeMay = (LinearLayout) getLayoutInflater().inflate(R.layout.vehicle_list_item, null);
        cb_item = ln_listXeMay.findViewById(R.id.check_icon);
        thanhtoantt = findViewById(R.id.thanhtoantt);
    }

    void ChuyenTrang(Context context, Class<?> lop) {
        Intent i = new Intent(context, lop);
        startActivity(i);
    }
}