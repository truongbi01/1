package com.example.lalamove.View.Home.KhachHang.DonHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.ListLoaiXe.PhuongTien;
import com.example.lalamove.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DonHangDangTaiAdapter extends ArrayAdapter<DonHang> {
    private int selectedPosition = -1;

    public DonHangDangTaiAdapter(@NonNull Context context, int resource, List<DonHang> objects) {
        super(context, resource, objects);
    }


    public int getSelectedPosition() {
        return selectedPosition;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DonHang donHang = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_list_donhang, parent, false);
        }

        // Anh xa
        TextView tv_thu_ListDonHang = convertView.findViewById(R.id.tv_thu_ListDonHang);
        TextView tv_thang_ngay_ListDonHang = convertView.findViewById(R.id.tv_thang_ngay_ListDonHang);
        TextView tv_ThoiGianDatHang_ListDonHang = convertView.findViewById(R.id.tv_ThoiGianDatHang_ListDonHang);
        TextView tv_DiemDen_ListDonHang = convertView.findViewById(R.id.tv_DiemDen_ListDonHang);
        TextView tv_DiemGiao_ListDonHang = convertView.findViewById(R.id.tv_DiemGiao_ListDonHang);
        TextView tv_TenLoaiPhuongTien_ListDonHang = convertView.findViewById(R.id.tv_TenLoaiPhuongTien_ListDonHang);
        TextView tv_GiaTien_ListDonHang = convertView.findViewById(R.id.tv_GiaTien_ListDonHang);


        if (donHang != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
            tv_thu_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            tv_thang_ngay_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            tv_ThoiGianDatHang_ListDonHang.setText(dateFormat.format(donHang.getThoiGianDatHang()));

            tv_DiemDen_ListDonHang.setText(donHang.getNoiNhan());
            tv_DiemGiao_ListDonHang.setText(donHang.getNoiGiao());
            tv_TenLoaiPhuongTien_ListDonHang.setText(donHang.getMaPhuongTien());
            tv_GiaTien_ListDonHang.setText(String.format(Locale.getDefault(), "%,d đ", donHang.getGiaTien()));
        }

        convertView.setOnClickListener(v -> {
            selectedPosition = (selectedPosition == position) ? -1 : position;
            notifyDataSetChanged();
        });

        return convertView;
    }
}