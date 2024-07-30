package com.example.lalamove.View.Home.NhanVienCongTy.BaoCaoDoanhThu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lalamove.R;
import java.util.List;

public class BaoCaoDoanhThuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BaoCaoDoanhThuAdapter adapter;
    private List<BaoCaoDoanhThu> baoCaoDoanhThuList;
    private Spinner monthSpinner; // Thêm Spinner để chọn tháng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bao_cao_doanh_thu);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        monthSpinner = findViewById(R.id.monthSpinner); // Khởi tạo Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.month_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedMonth = position + 1; // Lấy tháng được chọn
                fetchMonthlyReport(selectedMonth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchMonthlyReport(int thang) {
        BaoCaoDoanhThuDAO baoCaoDoanhThuDAO = new BaoCaoDoanhThuDAO();
        baoCaoDoanhThuList = baoCaoDoanhThuDAO.getMonthlyReport(thang);
        adapter = new BaoCaoDoanhThuAdapter(baoCaoDoanhThuList);
        recyclerView.setAdapter(adapter);
    }
}