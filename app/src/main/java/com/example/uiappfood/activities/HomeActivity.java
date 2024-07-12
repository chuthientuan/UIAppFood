package com.example.uiappfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiappfood.objects.Item_food;
import com.example.uiappfood.network.NetworkUtil;
import com.example.uiappfood.R;
import com.example.uiappfood.adapter.AdapterFood;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Toolbar menu, cart;
    RecyclerView reclefood;
    AdapterFood adp;
    List<Item_food> list;
    BottomNavigationView bottomnav;
    private long backpressTime = 0;

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
        menu.setOnClickListener(v -> {
            Intent myint = new Intent(HomeActivity.this, MenuActivity.class);
            startActivity(myint);
        });
        bottomnav = findViewById(R.id.bottomnav);
        bottomnav.setOnNavigationItemSelectedListener(menuItem -> {
            if(menuItem.getItemId()==R.id.history) {
                Intent myint = new Intent(HomeActivity.this, HistoryActivity.class);
                startActivity(myint);
            }
            else if (menuItem.getItemId()==R.id.nav_favorites) {
                Intent myint = new Intent(HomeActivity.this, FavoritesListActivity.class);
                startActivity(myint);
            }
            else if (menuItem.getItemId()==R.id.user) {
                Intent my = new Intent(HomeActivity.this, ProfileHomeActivity.class);
                startActivity(my);
            }
            return false;
        });
        cart = findViewById(R.id.cart);
        cart.setOnClickListener(v -> {
            Intent myint = new Intent(HomeActivity.this, OrdersActivity.class);
            startActivity(myint);
        });
        // Check Internet
        if(!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (backpressTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backpressTime = System.currentTimeMillis();
    }
}