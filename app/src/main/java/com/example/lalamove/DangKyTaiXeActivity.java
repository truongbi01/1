package com.example.lalamove;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DangKyTaiXeActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private ImageView showPasswordButton;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky_tai_xe);

        passwordEditText = findViewById(R.id.password);
        showPasswordButton = findViewById(R.id.show_password);

        showPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showPasswordButton.setImageResource(R.drawable.ic_show); // Update icon
                } else {
                    // Show password
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPasswordButton.setImageResource(R.drawable.ic_hide); // Update icon
                }
                isPasswordVisible = !isPasswordVisible;
                // Move the cursor to the end of the text
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });
    }
}