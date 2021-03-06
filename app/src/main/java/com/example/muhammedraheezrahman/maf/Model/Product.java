package com.example.muhammedraheezrahman.maf.Model;

public class Product {


    String title;
    String imageURL;
    float price;
    String category;
    int addedToWishList;
    int addedToCart;
    int id;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_ADDEDTOCART = "addedToCart";

    public static final String TABLE_NAME = "product";

    public static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_IMAGE + " TEXT,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_ADDEDTOCART + " INTEGER"
            + " )";

    public Product() {
    }

    public Product(String title, String imageURL, float price, String category, int addedToWishList, int addedToCart) {
        this.title = title;
        this.imageURL = imageURL;
        this.price = price;
        this.category = category;
        this.addedToWishList = addedToWishList;
        this.addedToCart = addedToCart;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int isAddedToWishList() {
        return addedToWishList;
    }

    public void setAddedToWishList(int addedToWishList) {
        this.addedToWishList = addedToWishList;
    }

    public int isAddedToCart() {
        return addedToCart;
    }

    public void setAddedToCart(int addedToCart) {
        this.addedToCart = addedToCart;
    }
}
