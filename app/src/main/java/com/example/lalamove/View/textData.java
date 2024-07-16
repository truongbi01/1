package com.example.lalamove.View;

import android.os.Bundle;
import android.telecom.ConnectionRequest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lalamove.R;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class textData extends AppCompatActivity {
    Connection con;
    TextView tv_sodienthoai , tv_matkhau;
    Button btn_layDuLieu;
    String ConnectionRequest = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_textsql);

        //Ánh xạ dữ liệu
        AnhXa();

        //Xử lý hành động nhân button
        btn_layDuLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTextFromSQL(v);
            }
        });

    }

    private void GetTextFromSQL(View v) {
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if(con!= null){
                String query = "SELECT * FROM TaiKhoan Where VaiTro = 'QUANLYNHANSU' ";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    tv_sodienthoai.setText(rs.getString(1));
                    tv_matkhau.setText(rs.getString(3));
                }
            }
            else{
                ConnectionRequest ="Check Connection";
                // Xử lý khi không kết nối được
                // Ví dụ:
                Toast.makeText(textData.this, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            e.printStackTrace(); // In ra lỗi để debug
        }
    }

    //Ánh xạ
    void AnhXa(){
        tv_sodienthoai = findViewById(R.id.tv_sodienthoai);
        tv_matkhau = findViewById(R.id.tv_matkhau);
        btn_layDuLieu = findViewById(R.id.btn_layDuLieu);
    }
}
