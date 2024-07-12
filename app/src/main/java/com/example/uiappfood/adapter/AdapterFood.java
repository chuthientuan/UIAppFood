package com.example.uiappfood.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uiappfood.activities.InfoFoodActivity;
import com.example.uiappfood.objects.Item_food;
import com.example.uiappfood.R;

import java.util.List;

public class AdapterFood extends RecyclerView.Adapter<AdapterFood.ViewHolder> {
    private final List<Item_food> list;
    private final Activity activity;

    public AdapterFood(List<Item_food> list, Activity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterFood.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item_food item = list.get(position);
        holder.imageView.setImageResource(item.getImg());
        holder.texttt.setText(item.getTitle());
        holder.textpr.setText(item.getPrice());
        // Click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, InfoFoodActivity.class);
            intent.putExtra("item_img", item.getImg());
            intent.putExtra("item_name", item.getTitle());
            intent.putExtra("item_price", item.getPrice());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView texttt;
        public TextView textpr;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            texttt = itemView.findViewById(R.id.texttt);
            textpr = itemView.findViewById(R.id.textpr);
        }
    }
}
