package com.example.lalamove.View.model.TableDonDatGiaoHang;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Handler;

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

    public void getDataAll(Context context, ListView lvDonHang, Consumer<List<DonHang>> callback, String sodienthoai,String trangthai) {
        List<DonHang> listDonHang = new ArrayList<>();
        try {
            connectionHelper = new ConnectionHelper();
            Connection con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT ddgh.MaDonHang, ddgh.SoDienThoaiKhachHang, ddgh.SoDienThoaiNguoiGui, " +
                        "ddgh.NoiNhan, ddgh.SoDienThoaiguoiGiao, ddgh.NoiGiao, " +
                        "ddgh.ThoiGianDatHang, ddgh.MaPhuongTien, ctdg.GiaTien, ddgh.TrangThai " +
                        "FROM DonDatGiaoHang ddgh " +
                        "INNER JOIN ChiTietDonGiao ctdg ON ddgh.MaDonHang = ctdg.MaDonHang " +
                        "WHERE ddgh.TrangThai = ? AND ddgh.SoDienThoaiKhachHang = ?";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1,trangthai);
                preparedStatement.setString(2, sodienthoai);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int maDonHang = rs.getInt("MaDonHang");
                    String soDienThoaiKhachHang = rs.getString("SoDienThoaiKhachHang");
                    String soDienThoaiNguoiGui = rs.getString("SoDienThoaiNguoiGui");
                    String noiNhan = rs.getString("NoiNhan");
                    String soDienThoaiNguoiNhan = rs.getString("SoDienThoaiguoiGiao");
                    String noiGiao = rs.getString("NoiGiao");
                    Date thoiGianDatHang = rs.getTimestamp("ThoiGianDatHang");
                    String maPhuongTien = rs.getString("MaPhuongTien");
                    int giaTien = rs.getInt("GiaTien");

                    DonHang donHang = new DonHang(maDonHang, soDienThoaiKhachHang, soDienThoaiNguoiGui, noiNhan,
                            soDienThoaiNguoiNhan, noiGiao, thoiGianDatHang, maPhuongTien, giaTien);
                    listDonHang.add(donHang);
                }
                Log.d("QuerySql_DonDatGiaoHang", "Số lượng đơn hàng: " + listDonHang.size());
                callback.accept(listDonHang);

            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in getDataAll: " + e.getMessage(), e);
            Toast.makeText(context, "Có lỗi xảy ra: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



}
