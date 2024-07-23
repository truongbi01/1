package com.example.lalamove.View.Home.KhachHang.DonHang;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;

import java.util.List;

public class FragmentDonHangDaHuy extends Fragment {
    private DonHangDangTaiAdapter adapter;
    private ListView lv_DonHang_DaHuy;
    private static final String trangthai = "Đã hủy";
    private SharedPreferences sharedPreferences;
    private LinearLayout ln_hienthi_DaHuy;
    private boolean flag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_da_huy, container, false);

        // Lấy dữ liệu từ SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String soDienThoai = sharedPreferences.getString("sodienthoai", "");

        // Ánh xạ các view và tải dữ liệu
        AnhXa(v,soDienThoai);
        if(!flag){
            ln_hienthi_DaHuy.setVisibility(View.VISIBLE);
            lv_DonHang_DaHuy.setVisibility(View.GONE);
        }else{
            ln_hienthi_DaHuy.setVisibility(View.GONE);
            lv_DonHang_DaHuy.setVisibility(View.VISIBLE);
        }
        return v;
    }


    void AnhXa(View v, String soDienThoai) {
        ln_hienthi_DaHuy = v.findViewById(R.id.ln_hienthi_DaHuy);
        lv_DonHang_DaHuy = v.findViewById(R.id.lv_DonHang_DaHuy);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDataAll(getContext(), lv_DonHang_DaHuy, this::setAdapter, soDienThoai, trangthai);
    }

    private void setAdapter(List<DonHang> listDonHang) {
        if (getContext() != null) {
            if (listDonHang != null && !listDonHang.isEmpty()) {
                adapter = new DonHangDangTaiAdapter(getContext(), R.layout.layout_list_donhang, listDonHang);
                lv_DonHang_DaHuy.setAdapter(adapter);
                flag = true;
                adapter.notifyDataSetChanged();

            }
            else
                flag = false;
        }
    }
}