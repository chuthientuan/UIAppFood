package com.example.uiappfood.objects;
public class Item_food {
    private int img;
    private String title;
    private String price;

    public Item_food(int img, String title, String price) {
        this.img = img;
        this.title = title;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
