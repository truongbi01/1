package com.example.lalamove.View.model.TableTaiXe;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TaiXeSQL {
    Connection con;
    ConnectionHelper connectionHelper ;
    String bienso,maphuongtien,diemdanhgia,tenphuongtien;


    public void sp_insert_TaiKhoanTaiXe(String sodienthoai, String ten, String matkhau, String loaitaikhoan, String hinhdaidien, String sodienthoaitaixe , String bienso , String maphuongtien , String mavidientu , Context context){
       try{
           connectionHelper = new ConnectionHelper();
           con = connectionHelper.connectionClass();
           CallableStatement callableStatement;

           if(con != null){
               String sql = "{call sp_insert_TaiKhoan_TaiXe(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
               callableStatement = con.prepareCall(sql);
               callableStatement.setString(1,sodienthoai);
               callableStatement.setString(2,ten);
               callableStatement.setString(3,matkhau);
               callableStatement.setString(4,loaitaikhoan);
               callableStatement.setString(5,hinhdaidien);
               callableStatement.setString(6,sodienthoaitaixe);
               callableStatement.setString(7,bienso);
               callableStatement.setString(8,maphuongtien);
               callableStatement.setString(9,mavidientu);

               callableStatement.execute();
               Toast.makeText(context, "Đăng ký tài xế thành công !!", Toast.LENGTH_SHORT).show();
               // Đóng kết nối và các resource
               callableStatement.close();
               con.close();
           }else{
               Toast.makeText(context, "Có lỗi xảy ra , thử lại sau", Toast.LENGTH_SHORT).show();

           }

           con.close();
       }catch (Exception e){
           Log.e(TAG, "sp_insert_TaiKhoanTaiXe: "+ e.getMessage()  );
       }



    }

    // Tìm kiếm thông tin tài khoản theo số điện thoại và email
    public boolean isTaiKhoanTonTai(String sodienthoaiDK, Context context) {
        boolean flag = false;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT sodienthoai FROM TaiKhoan WHERE sodienthoai = ? ";
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, sodienthoaiDK);


                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    flag = true; // Tồn tại số điện thoại hoặc email trong cơ sở dữ liệu
                }

                // Đóng kết nối và các resource
                rs.close();
                preparedStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return flag;
    }
    public String sp_select_tenphuongtien (String mapt, Context context) {
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT tenphuongtien " +
                        "FROM LoaiPhuongTien " +
                        "WHERE maphuongtien = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, mapt);

                ResultSet rs = preparedStatement.executeQuery(); // Không cần truyền tham số vào phương thức

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    tenphuongtien = rs.getString("tenphuongtien");
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                }
                preparedStatement.close();
                rs.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return tenphuongtien;
    }
    public ArrayList<String> sp_select_taikhoan_taixe (String sdt, Context context)
    {
        ArrayList<String> kq = new ArrayList<>();
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT bienso,maphuongtien,diemdanhgia " +
                        "FROM TaiKhoanTaiXe " +
                        "WHERE sodienthoaitaixe = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, sdt);

                ResultSet rs = preparedStatement.executeQuery(); // Không cần truyền tham số vào phương thức

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    bienso = rs.getString("bienso");
                    maphuongtien=rs.getString("maphuongtien");
                    diemdanhgia=rs.getString("diemdanhgia");
                    kq.add(bienso);
                    kq.add(diemdanhgia);
                    kq.add(sp_select_tenphuongtien(maphuongtien,context));
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                }
                preparedStatement.close();
                rs.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return kq;
    }
}
