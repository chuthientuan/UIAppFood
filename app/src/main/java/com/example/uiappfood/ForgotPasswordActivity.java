package com.example.uiappfood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotPasswordActivity extends AppCompatActivity {
    Toolbar icon_back_forgot;
    EditText emailforgot, newpass, newconfirm;
    Button send;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = new DatabaseHelper(this);
        icon_back_forgot = findViewById(R.id.icon_back_forgot);
        icon_back_forgot.setNavigationOnClickListener(v -> finish());
        emailforgot = findViewById(R.id.emailforgot);
        newpass = findViewById(R.id.newpass);
        newconfirm = findViewById(R.id.newconfirm);
        send = findViewById(R.id.btnsend);
        send.setOnClickListener(v -> {
            String email = emailforgot.getText().toString();
            String password = newpass.getText().toString();
            String confirmpassword = newconfirm.getText().toString();
            if (email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this , "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!email.equals(db.getEmail())) {
                Toast.makeText(ForgotPasswordActivity.this , "Email not match", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmpassword)) {
                Toast.makeText(ForgotPasswordActivity.this , "Password not match", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean isUpdate = db.updatePassword(email, password);
            if (isUpdate) {
                Toast.makeText(ForgotPasswordActivity.this , "Password updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(ForgotPasswordActivity.this , "Failed to update password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}