package com.example.uiappfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uiappfood.R;
import com.example.uiappfood.network.NetworkUtil;

public class ProfileHomeActivity extends AppCompatActivity {
    Toolbar usback;
    RadioGroup idgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usback = findViewById(R.id.usback);
        usback.setNavigationOnClickListener(v -> finish());
        idgroup = findViewById(R.id.idgroup);
        idgroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
        });
        //Check Internet
        if (!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
    }
}