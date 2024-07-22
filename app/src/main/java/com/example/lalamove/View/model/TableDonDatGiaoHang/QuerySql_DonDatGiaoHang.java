package com.example.lalamove.View.model.TableDonDatGiaoHang;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;

public class QuerySql_DonDatGiaoHang {
    ConnectionHelper connectionHelper ;
    Connection connection;
    public void sp_insert_DonDatGiaoHang_ChiTietDon(
            String sodienthoaikhachhhang,
            String sodienthoainguoigui,
            String noinhan,
            String sodienthoaiguoigiao,
            String noigiao,
            java.util.Date thoigiandathang,
            String maphuongtien,
            String ghichuchotaixe,
            int soluongthunghang,
            String maloaihang,
            String trangthai,
            int giatien,
            Context context) {
        try {
             connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "{call sp_insert_DonDatGiaoHang_ChiTietDon(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                CallableStatement callableStatement = con.prepareCall(sql);

                // Thiết lập giá trị cho các tham số
                callableStatement.setString(1, sodienthoaikhachhhang);
                callableStatement.setString(2, sodienthoainguoigui);
                callableStatement.setString(3, noinhan);
                callableStatement.setString(4, sodienthoaiguoigiao);
                callableStatement.setString(5, noigiao);
                callableStatement.setTimestamp(6, new java.sql.Timestamp(thoigiandathang.getTime()));
                callableStatement.setString(7, maphuongtien);
                callableStatement.setString(8, ghichuchotaixe);
                callableStatement.setInt(9, soluongthunghang);
                callableStatement.setString(10, maloaihang);
                callableStatement.setString(11, trangthai);
                callableStatement.setInt(12, giatien);

                // Thực thi stored procedure
                callableStatement.execute();
                Toast.makeText(context, "Thêm đơn hàng thành công!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage(), null);
        }
    }

}
