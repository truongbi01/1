package com.example.lalamove.View.Home.NhanVienCongTy.BaoCaoDoanhThu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalamove.R;

import java.util.List;

public class BaoCaoDoanhThuAdapter extends RecyclerView.Adapter<BaoCaoDoanhThuAdapter.ViewHolder> {

    private List<BaoCaoDoanhThu> baoCaoDoanhThuList;

    public BaoCaoDoanhThuAdapter(List<BaoCaoDoanhThu> baoCaoDoanhThuList) {
        this.baoCaoDoanhThuList = baoCaoDoanhThuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_bao_cao_doanh_thu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaoCaoDoanhThu baoCaoDoanhThu = baoCaoDoanhThuList.get(position);
        holder.tvNgay.setText(baoCaoDoanhThu.getNgay());
        holder.tvSoDonVanChuyen.setText(String.valueOf(baoCaoDoanhThu.getSoDonVanChuyen()));
        holder.tvSoDonBiHuy.setText(String.valueOf(baoCaoDoanhThu.getSoDonBiHuy()));
        holder.tvDoanhThu.setText(String.valueOf(baoCaoDoanhThu.getDoanhThu()));
    }

    @Override
    public int getItemCount() {
        return baoCaoDoanhThuList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgay, tvSoDonVanChuyen, tvSoDonBiHuy, tvDoanhThu, tvThang; // Thêm TextView cho tháng

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvSoDonVanChuyen = itemView.findViewById(R.id.tvSoDonVanChuyen);
            tvSoDonBiHuy = itemView.findViewById(R.id.tvSoDonBiHuy);
            tvDoanhThu = itemView.findViewById(R.id.tvDoanhThu);
           // Khởi tạo TextView cho tháng
        }
    }
}