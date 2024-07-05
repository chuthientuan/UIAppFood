package com.example.uiappfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {
    TextView pro, order, offer, textsignout;
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
        pro.setOnClickListener(v -> {
            Intent myint = new Intent(MenuActivity.this, ProfileActivity.class);
            startActivity(myint);
        });
        order = findViewById(R.id.order);
        order.setOnClickListener(v -> {
            Intent myint = new Intent(MenuActivity.this, OrdersActivity.class);
            startActivity(myint);
        });
        offer = findViewById(R.id.offer);
        offer.setOnClickListener(v -> {
            Intent myint = new Intent(MenuActivity.this, OfferActivity.class);
            startActivity(myint);
        });
        // Check Internet
        if(!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
        textsignout = findViewById(R.id.textsignout);
        textsignout.setOnClickListener(v -> {
            Intent myint = new Intent(MenuActivity.this, Login.class);
            startActivity(myint);
        });
    }
}