package com.example.lalamove.View.Home.TaiXe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.View.model.TableTaiKhoan.TaiKhoanSQL;
import com.example.lalamove.View.model.TableTaiXe.TaiXeSQL;
import com.example.lalamove.View.model.XacThucvaDinhDang.DinhDang;

import java.util.ArrayList;

public class ThongTinTK_TaiXe extends AppCompatActivity {

    private static final String TAG = "ThongTinTK_TaiXe";

    private ImageButton btnBack;
    private EditText edt_tentk_tx,edt_sdt_tx,edt_diemdg,edt_loaipt,edt_biensopt,edt_mkcu,edt_mkmoi;
    private Button btn_edit,btn_doimk;
    private TaiXeSQL taiXeSQL;
    private TaiKhoanSQL taiKhoanSQL;
    private SharedPreferences pref;
    private String soDienThoai,mkcu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hosochitiettaixe); // Tên file layout tương ứng
        init();
        taiXeSQL = new TaiXeSQL();
        taiKhoanSQL = new TaiKhoanSQL();
        // Xử lý sự kiện nhấn nút quay lại
        btnBack.setOnClickListener(v -> finish());
        loadThongTinTaiKhoan();
        btn_edit.setOnClickListener(c->{
            updatetttk();
        });
        btn_doimk.setOnClickListener(c->{
            doimk();
        });

    }
    private void loadThongTinTaiKhoan() {
        pref = this.getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        soDienThoai = pref.getString("sodienthoai",""); // Hoặc lấy từ Intent hoặc SharedPreferences

        String loaiTaiKhoan = taiKhoanSQL.getLoaiTaiKhoan(soDienThoai, this);

        // Nếu tài khoản tồn tại, lấy thông tin và hiển thị lên giao diện
        if (!loaiTaiKhoan.isEmpty()) {
            ArrayList<String> kq = taiKhoanSQL.sp_select_taikhoan(soDienThoai,this);
            // Ví dụ: Lấy thông tin từ cơ sở dữ liệu và hiển thị lên EditText
            // Bạn cần thêm các phương thức để lấy tên, số điện thoại và Gmail từ cơ sở dữ liệu
            edt_tentk_tx.setText(kq.get(0));
            edt_sdt_tx.setText(kq.get(1));
            mkcu = kq.get(2);
            ArrayList<String> kq2 = taiXeSQL.sp_select_taikhoan_taixe(soDienThoai,this);
            edt_biensopt.setText(kq2.get(0));
            edt_diemdg.setText(kq2.get(1));
            edt_loaipt.setText(kq2.get(2));
        } else {
            Toast.makeText(this, "Không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
        }
    }
    void init()
    {
        btn_edit=findViewById(R.id.btn_edit_tttkTX);
        btn_doimk=findViewById(R.id.btn_doimk_tttkTX);
        btnBack=findViewById(R.id.btn_back_hosotx);
        edt_tentk_tx=findViewById(R.id.Ten);
        edt_sdt_tx=findViewById(R.id.sdt);
        edt_biensopt=findViewById(R.id.biensophuongtien);
        edt_diemdg=findViewById(R.id.diemdanhgia);
        edt_loaipt=findViewById(R.id.loaiphuongtien);
        edt_mkcu=findViewById(R.id.mkcu_hscttx);
        edt_mkmoi=findViewById(R.id.mkmoi_hscttx);

    }
    private void updatetttk() {
        String ten = edt_tentk_tx.getText().toString();
        String sdt = edt_sdt_tx.getText().toString();
        taiKhoanSQL.updatetttk(sdt,ten,this);
        finish();
    }
    private void doimk()
    {
        if(!DinhDang.isDinhDangMatKhau(edt_mkmoi.getText().toString()))
        {
            edt_mkmoi.setError("Sai dinh dang mat khau");
        }
        else{
            if(mkcu.compareTo(edt_mkcu.getText().toString())==0)
            {
                taiKhoanSQL.sp_update_mkTaiKhoan(soDienThoai,edt_mkcu.getText().toString(),this);
                finish();
            }
            else {
                Toast.makeText(this,"Mat khau cu khong dung",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
