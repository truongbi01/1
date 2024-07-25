package com.example.lalamove.View.model.TableTaiKhoan;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;

public class TaiKhoanSQL {
    Connection con;
    ConnectionHelper connectionHelper;

    public void sp_update_mkTaiKhoan(String sodienthoai,String mkmoi, Context context) {
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            CallableStatement callableStatement;

            if (con != null) {
                String sql = "{call sp_update_mkTaiKhoan(?, ?) }";
                callableStatement = con.prepareCall(sql);
                callableStatement.setString(1, sodienthoai);
                callableStatement.setString(2, mkmoi);
                callableStatement.execute();
                Toast.makeText(context, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                // Đóng kết nối và các resource
                callableStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra , thử lại sau", Toast.LENGTH_SHORT).show();

            }
            con.close();
        } catch (Exception e) {
            Log.e(TAG, "sp_update_mkTaiKhoan: " + e.getMessage());
        }
    }
}
