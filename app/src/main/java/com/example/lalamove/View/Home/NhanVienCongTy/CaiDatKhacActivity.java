package com.example.lalamove.View.Home.NhanVienCongTy;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.lalamove.R;

public class CaiDatKhacActivity extends AppCompatActivity {

    private Switch eInvoiceSwitch;
    private Switch deliveryVerificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cai_dat_khac);

        ImageButton backButton = findViewById(R.id.backButton);
        eInvoiceSwitch = findViewById(R.id.eInvoiceSwitch);
        deliveryVerificationSwitch = findViewById(R.id.deliveryVerificationSwitch);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setupSwitch(eInvoiceSwitch, "Nhận hóa đơn điện tử");
        setupSwitch(deliveryVerificationSwitch, "Kiểm chứng đơn giao hàng");
    }

    private void setupSwitch(Switch switchView, String featureName) {
        switchView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int color = isChecked ? R.color.orange_fresh : R.color.switch_off;
            switchView.getThumbDrawable().setTint(ContextCompat.getColor(this, color));
            switchView.getTrackDrawable().setTint(ContextCompat.getColor(this, color));

            String status = isChecked ? "đã bật" : "đã tắt";
            Toast.makeText(this, featureName + " " + status, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}