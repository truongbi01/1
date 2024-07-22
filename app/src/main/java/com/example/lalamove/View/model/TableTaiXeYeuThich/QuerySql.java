package com.example.lalamove.View.model.TableTaiXeYeuThich;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuerySql {
    ConnectionHelper connectionHelper;
    Connection connection ;

    public boolean isTaiXeYeuThich(String sodienthoai , Context context){
        boolean flag = false;
        try{
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if(connection != null){
                String sql = "SELECT * FROM TaiXeYeuThich WHERE = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                if(rs != null){
                    flag = true;
                }else{
                    Toast.makeText(context,"Chưa có tài xế yêu thích ",Toast.LENGTH_SHORT).show();
                }


                rs.close();
                connection.close();
                preparedStatement.close();



            }else{
                Toast.makeText(context,"lỗi không truy xuất được dữ liệu",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage(),null );
        }
        return flag;
    }
}
