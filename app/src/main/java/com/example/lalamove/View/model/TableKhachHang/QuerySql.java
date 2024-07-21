    package com.example.lalamove.View.model.TableKhachHang;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.widget.Toast;

    import com.example.lalamove.View.Home.KhachHang.Home_KhachHang;
    import com.example.lalamove.View.Home.NhanVienCongTy.TrangChuQuanLiNhanSuActivity;
    import com.example.lalamove.View.Home.TaiXe.TrangChuTaiXeActivity;
    import com.example.lalamove.database.data.ConnectionHelper;

    import java.sql.CallableStatement;
    import java.sql.Connection;
    import java.sql.ResultSet;

    // Ensure the ConnectionHelper class is properly handling the database connection
// Make sure resources such as drawables exist in your project

    public class QuerySql {
        Connection con;
        ConnectionHelper connectionHelper;
        private static final int SOLANDANGNHAPTOIDA = 3;
        private static final long LOCK_DURATION = 20 * 60 * 1000; // 20 minutes

        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;

        public void sp_insert_TaiKhoan(String sodienthoai, String ten, String matkhau, String loaitaikhoan, String hinhdaidien, Context context) {
            try {
                connectionHelper = new ConnectionHelper();
                con = connectionHelper.connectionClass();
                if (con != null) {
                    String sql = "{call sp_insert_TaiKhoan(?, ?, ?, ?, ?)}";
                    CallableStatement callableStatement = con.prepareCall(sql);
                    callableStatement.setString(1, sodienthoai);
                    callableStatement.setString(2, ten);
                    callableStatement.setString(3, matkhau);
                    callableStatement.setString(4, loaitaikhoan);
                    callableStatement.setString(5, hinhdaidien);

                    callableStatement.execute();
                    Toast.makeText(context, "Đăng ký thành công !!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Có lỗi xảy ra , thử lại sau", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace(); // In ra lỗi để debug
            }
        }

        public void sp_search_taikhoan(String sodienthoaiDn, String matkhauDn, Context context) {
            try {
                sharedPreferences = context.getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                connectionHelper = new ConnectionHelper();
                con = connectionHelper.connectionClass();

                if (con != null) {
                    // Kiểm tra xem tài khoản có đang bị khóa không
                    long lockTime = sharedPreferences.getLong(sodienthoaiDn + "_lockTime", 0);
                    if (System.currentTimeMillis() < lockTime) {
                        Toast.makeText(context, "Tài khoản này đang bị khóa. Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String sql = "{call sp_search_taikhoan(?)}";
                    CallableStatement callableStatement = con.prepareCall(sql);
                    callableStatement.setString(1, sodienthoaiDn);
                    ResultSet rs = callableStatement.executeQuery();

                    if (rs.next()) {
                        String matkhau = rs.getString("matkhau");
                        String loaitaikhoan = rs.getString("loaitaikhoan");

                        if (!matkhauDn.equals(matkhau)) {
                            int soLanDangNhap = sharedPreferences.getInt(sodienthoaiDn + "_loginAttempts", 0) + 1;
                            editor.putInt(sodienthoaiDn + "_loginAttempts", soLanDangNhap);
                            if (soLanDangNhap >= SOLANDANGNHAPTOIDA) {
                                //Thời gian khóa = thời gian hiện tại + 20ph
                                long thoiGianKhoa = System.currentTimeMillis() + LOCK_DURATION;
                                editor.putLong(sodienthoaiDn + "_lockTime", thoiGianKhoa);
                                Toast.makeText(context, "Tài khoản này đã nhập sai quá 3 lần. Bị khóa trong 20 phút.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            }
                            editor.apply();
                            return;
                        }

                        // Đăng nhập thành công, reset số lần đăng nhập sai
                        editor.remove(sodienthoaiDn + "_loginAttempts");
                        editor.remove(sodienthoaiDn + "_lockTime");
                        editor.apply();

                        // Chuyển hướng người dùng dựa trên loại tài khoản
                        if (loaitaikhoan.equals("KhachHang")) {
                            Intent intent = new Intent(context, Home_KhachHang.class);
                            context.startActivity(intent);
                        } else if (loaitaikhoan.equals("TaiXe")) {
                            Intent intent = new Intent(context, TrangChuTaiXeActivity.class);
                            context.startActivity(intent);
                        } else if (loaitaikhoan.equals("NhanVienCongTy")) {
                            Intent intent = new Intent(context, TrangChuQuanLiNhanSuActivity.class);
                            context.startActivity(intent);
                        }
                    } else {
                        Toast.makeText(context, "Số điện thoại không tồn tại", Toast.LENGTH_SHORT).show();
                    }

                    rs.close();
                    callableStatement.close();
                    con.close();
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


