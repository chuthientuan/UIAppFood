package com.example.uiappfood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {
    TextView pro, order, offer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pro = findViewById(R.id.pro);
        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(myint);
            }
        });
        order = findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(MenuActivity.this, OrdersActivity.class);
                startActivity(myint);
            }
        });
        offer = findViewById(R.id.offer);
        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(MenuActivity.this, OfferActivity.class);
                startActivity(myint);
            }
        });
        // Check Internet
        if(!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
    }
}