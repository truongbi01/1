package com.example.lalamove.View.Home.NhanVienCongTy.BaoCaoDoanhThu;

import android.util.Log;
import com.example.lalamove.database.data.ConnectionHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaoCaoDoanhThuDAO {
    private ConnectionHelper connectionHelper;

    public BaoCaoDoanhThuDAO() {
        connectionHelper = new ConnectionHelper();
    }

    public List<BaoCaoDoanhThu> getDailyReport() {
        List<BaoCaoDoanhThu> reportList = new ArrayList<>();
        try {
            Connection connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "{call sp_GetDailyReport()}";
                CallableStatement stmt = connection.prepareCall(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String ngay = rs.getString("Ngay");
                    int soDonVanChuyen = rs.getInt("SoDonVanChuyen");
                    int soDonBiHuy = rs.getInt("SoDonHuy");
                    int doanhThu = rs.getInt("DoanhThu");
                    reportList.add(new BaoCaoDoanhThu(ngay, soDonVanChuyen, soDonBiHuy, doanhThu));
                }
                rs.close();
                stmt.close();
                connection.close();
            }
        } catch (Exception e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return reportList;
    }
    public List<BaoCaoDoanhThu> getMonthlyReport(int month) {
        List<BaoCaoDoanhThu> reportList = new ArrayList<>();
        try {
            Connection connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "{call sp_GetMonthlyReport(?)}";
                CallableStatement stmt = connection.prepareCall(query);
                stmt.setInt(1, month);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String ngay = rs.getString("Ngay");
                    int soDonVanChuyen = rs.getInt("SoDonVanChuyen");
                    int soDonBiHuy = rs.getInt("SoDonHuy");
                    int doanhThu = rs.getInt("DoanhThu");
                    reportList.add(new BaoCaoDoanhThu(ngay, soDonVanChuyen, soDonBiHuy, doanhThu));
                }
                rs.close();
                stmt.close();
                connection.close();
            }
        } catch (Exception e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return reportList;
    }
}