package com.example.muhammedraheezrahman.maf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.muhammedraheezrahman.maf.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "product_db";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Product.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Product.TABLE_NAME);
        onCreate(db);
    }

    public void insertProducts(List<Product> list){
        deleteItems();
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (Product product : list){
            values.put(Product.COLUMN_TITLE, product.getTitle());
            values.put(Product.COLUMN_CATEGORY,product.getCategory());
            values.put(Product.COLUMN_IMAGE,product.getImageURL());
            values.put(Product.COLUMN_PRICE,product.getPrice());
            values.put(Product.COLUMN_ADDEDTOCART,product.isAddedToCart());

            long id = database.insert(Product.TABLE_NAME,null,values);
        }

        database.close();
    }

    public List<Product> getProducts(){
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM "+ Product.TABLE_NAME ;
        Cursor cursor = database.rawQuery(query,null);
        List<Product> productList = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(Product.COLUMN_TITLE)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(Product.COLUMN_CATEGORY)));
                product.setImageURL(cursor.getString(cursor.getColumnIndex(Product.COLUMN_IMAGE)));
                product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                product.setAddedToCart(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ADDEDTOCART)));
                product.setId(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)));
                productList.add(product);

            }while (cursor.moveToNext());

        }
        database.close();
        return productList;
    }



    public void deleteItems(){

        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM "+Product.TABLE_NAME);
        database.close();
    }

    public void addToCart(int id){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Product.COLUMN_ADDEDTOCART,1);
         database.update(Product.TABLE_NAME,contentValues,Product.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
         database.close();
    }

    public List<Product> getProductsInCart(){
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.query(Product.TABLE_NAME,new String[]{Product.COLUMN_TITLE,Product.COLUMN_PRICE,Product.COLUMN_ADDEDTOCART,
                                                                        Product.COLUMN_IMAGE,Product.COLUMN_CATEGORY,Product.COLUMN_ID},Product.COLUMN_ADDEDTOCART + " = ?",new String[]{String.valueOf(1)},null,null,null,null);
        List<Product> productList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{

                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(Product.COLUMN_TITLE)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(Product.COLUMN_CATEGORY)));
                product.setImageURL(cursor.getString(cursor.getColumnIndex(Product.COLUMN_IMAGE)));
                product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                product.setAddedToCart(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ADDEDTOCART)));
                product.setId(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)));
                productList.add(product);

            }while (cursor.moveToNext());

        }
        database.close();
        return productList;
    }

    public List<Product> getProductsByCategory(String word){
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query(Product.TABLE_NAME,new String[]{Product.COLUMN_TITLE,Product.COLUMN_PRICE,Product.COLUMN_ID,Product.COLUMN_CATEGORY,Product.COLUMN_ADDEDTOCART,Product.COLUMN_IMAGE},
                                                         Product.COLUMN_CATEGORY + " LIKE ? ", new String[]{"%"+word+"%"},null,null,null,null );
        if (cursor.moveToFirst()){
            do{

                Product product = new Product();
                product.setTitle(cursor.getString(cursor.getColumnIndex(Product.COLUMN_TITLE)));
                product.setCategory(cursor.getString(cursor.getColumnIndex(Product.COLUMN_CATEGORY)));
                product.setImageURL(cursor.getString(cursor.getColumnIndex(Product.COLUMN_IMAGE)));
                product.setPrice(cursor.getFloat(cursor.getColumnIndex(Product.COLUMN_PRICE)));
                product.setAddedToCart(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ADDEDTOCART)));
                product.setId(cursor.getInt(cursor.getColumnIndex(Product.COLUMN_ID)));
                productList.add(product);

            }while (cursor.moveToNext());

        }
        database.close();

        return productList;
    }
}
