package com.example.uiappfood.objects;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static FavoritesManager instance;
    private List<Itemheartfood> list;

    private FavoritesManager() {
        list = new ArrayList<>();
    }

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    public void addFavorite(Itemheartfood item) {
        list.add(item);
    }

    public List<Itemheartfood> getFavorites() {
        return new ArrayList<>(list);
    }
}
