package com.example.uiappfood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterHeart extends ArrayAdapter<Itemheartfood> {
    Activity activity;
    int idlayout;
    List<Itemheartfood> list;

    public AdapterHeart(Activity activity, int idlayout, List<Itemheartfood> list) {
        super(activity, idlayout, list);
        this.activity = activity;
        this.idlayout = idlayout;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater ilt =activity.getLayoutInflater();
        convertView = ilt.inflate(idlayout, null);
        Itemheartfood item = list.get(position);
        ImageView imgfoodheart = convertView.findViewById(R.id.imgfoodheart);
        imgfoodheart.setImageResource(item.getImg());
        TextView editname = convertView.findViewById(R.id.editname);
        editname.setText(item.getName());
        TextView editprice = convertView.findViewById(R.id.editprice);
        editprice.setText(item.getPrice());
        return convertView;
    }
}
