package com.example.muhammedraheezrahman.maf.Model;

public class Product {


    String title;
    String imageURL;
    float price;
    String category;
    boolean addedToWishList;
    boolean addedToCart;

    public Product(String title, String imageURL, float price, String category, boolean addedToWishList, boolean addedToCart) {
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.category = category;
        this.addedToWishList = addedToWishList;
        this.addedToCart = addedToCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAddedToWishList() {
        return addedToWishList;
    }

    public void setAddedToWishList(boolean addedToWishList) {
        this.addedToWishList = addedToWishList;
    }

    public boolean isAddedToCart() {
        return addedToCart;
    }

    public void setAddedToCart(boolean addedToCart) {
        this.addedToCart = addedToCart;
    }
}
