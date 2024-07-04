package com.example.uiappfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Toolbar menu;
    RecyclerView reclefood;
    AdapterFood adp;
    List<Item_food> list;
    BottomNavigationView bottomnav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reclefood = findViewById(R.id.reclefood);
        reclefood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        list = new ArrayList<>();
        list.add(new Item_food(R.drawable.item_food1, "Veggie tomato mix", "N1,900"));
        list.add(new Item_food(R.drawable.item_food2, "Spicy fish sauce", "N2,300.99"));
        list.add(new Item_food(R.drawable.img_food_chicken, "Fried chicken", "N1,900"));
        list.add(new Item_food(R.drawable.img_food_egg, "Egg and cucmber", "N1,900"));
        list.add(new Item_food(R.drawable.img_food3, "Moi-moi and ekpa", "N1,900"));
        adp = new AdapterFood(list, this);
        reclefood.setAdapter(adp);
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myint = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(myint);
            }
        });
        bottomnav = findViewById(R.id.bottomnav);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.history) {
                    Intent myint = new Intent(HomeActivity.this, HistoryActivity.class);
                    startActivity(myint);
                }
                else if (menuItem.getItemId()==R.id.user) {
                    Intent my = new Intent(HomeActivity.this, ProfileHomeActivity.class);
                    startActivity(my);
                }
                return false;
            }
        });
        // Check Internet
        if(!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
    }
}