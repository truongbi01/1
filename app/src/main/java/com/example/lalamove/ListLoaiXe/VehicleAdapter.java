package com.example.lalamove.ListLoaiXe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalamove.R;

import java.util.ArrayList;

public class VehicleAdapter extends ArrayAdapter<PhuongTien> {
    private int selectedPosition = -1;


    public VehicleAdapter(Context context, ArrayList<PhuongTien> vehicles) {
        super(context, 0, vehicles);

    }
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhuongTien vehicle = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        }

        ImageView vehicleIcon = convertView.findViewById(R.id.vehicle_icon);
        TextView vehicleName = convertView.findViewById(R.id.vehicle_name);
        CheckBox checkIcon = convertView.findViewById(R.id.check_icon);

        // Cập nhật tên và icon cho phương tiện
        vehicleName.setText(vehicle.getTenPhuongTien() + " " + vehicle.getTrongLuong() + "Kg");

        // Thiết lập icon tùy thuộc vào loại phương tiện
        if (vehicle.getTenPhuongTien().equals("Xe May")) {
            vehicleIcon.setImageResource(R.drawable.ic_scooter);
        } else if (vehicle.getTenPhuongTien().equals("Xe Tai Nho") || vehicle.getTenPhuongTien().equals("Xe Van Nho") ) {
            vehicleIcon.setImageResource(R.drawable.ic_truck);
        } else {
            // Đặt icon mặc định nếu loại phương tiện không khớp
            vehicleIcon.setImageResource(R.drawable.ic_semi_truck); // Thay ic_default bằng tên drawable của bạn
        }

        // Quản lý hiển thị trạng thái đã chọn
        if (position == selectedPosition) {

            convertView.setBackgroundResource(R.drawable.vehicle_item_background_after);
        } else {

            convertView.setBackgroundResource(R.drawable.vehicle_item_background);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = (selectedPosition == position) ? -1 : position;
                notifyDataSetChanged();

            }
        });

        return convertView;
    }


}
