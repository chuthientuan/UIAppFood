package com.example.uiappfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class InfoFoodActivity extends AppCompatActivity {
    ImageView imginfoitem;
    TextView nameitem, infoprice, infodelivery, policy;
    Toolbar icon_back_item, icon_heart_list;
    Boolean click = true;
    ArrayList<Itemheartfood> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_food);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Check Internet
        if(!NetworkUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
        // Info item
        imginfoitem = findViewById(R.id.imginfoitem);
        nameitem = findViewById(R.id.nameitem);
        infoprice = findViewById(R.id.infoprice);
        infodelivery = findViewById(R.id.infodelivery);
        policy = findViewById(R.id.policy);
        Intent myint = getIntent();
        int img = myint.getIntExtra("item_img", 0);
        String name = myint.getStringExtra("item_name");
        String price = myint.getStringExtra("item_price");
        // Update data
        imginfoitem.setImageResource(img);
        nameitem.setText(name);
        infoprice.setText(price);
        //
        icon_back_item = findViewById(R.id.icon_back_item);
        icon_back_item.setNavigationOnClickListener(v -> finish());
        icon_heart_list = findViewById(R.id.icon_heart_list);
        icon_heart_list.setNavigationOnClickListener(v -> {
            if(click) {
                icon_heart_list.setNavigationIcon(R.drawable.icon_heart_bold);
                click = false;
            }
            else {
                icon_heart_list.setNavigationIcon(R.drawable.icon_heart_item);
                click = true;
            }
            int imgfood = imginfoitem.getDrawable().getConstantState().getChangingConfigurations();
            String namefood = nameitem.getText().toString();
            String pricefood = nameitem.getText().toString();
            list = new ArrayList<>();
            list.add(new Itemheartfood(img, name, price));
        });
    }
}