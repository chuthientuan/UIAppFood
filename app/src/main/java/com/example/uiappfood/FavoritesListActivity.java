package com.example.uiappfood;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class FavoritesListActivity extends AppCompatActivity {
    ListView listviewfavorite;
    List<Itemheartfood> list;
    AdapterHeart adp;
    Toolbar icon_back_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorites_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listviewfavorite = findViewById(R.id.listviewfavorite);
        list = FavoritesManager.getInstance().getFavorites();
        adp = new AdapterHeart(FavoritesListActivity.this, R.layout.favorite_item, list);
        listviewfavorite.setAdapter(adp);
        icon_back_item = findViewById(R.id.icon_back_item);
        icon_back_item.setNavigationOnClickListener(v -> finish());
    }
}