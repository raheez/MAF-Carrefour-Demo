package com.example.muhammedraheezrahman.maf;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.muhammedraheezrahman.maf.Adapter.RecyclerProductAdapter;
import com.example.muhammedraheezrahman.maf.Database.DatabaseHelper;
import com.example.muhammedraheezrahman.maf.Fragments.CartFragment;
import com.example.muhammedraheezrahman.maf.Fragments.HomeFragment;
import com.example.muhammedraheezrahman.maf.Fragments.ShopFragment;
import com.example.muhammedraheezrahman.maf.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ShopFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener,CartFragment.OnFragmentInteractionListener{

    private BottomNavigationView bottomNavigationView;
    private TextView notificationNo;
    private View notificationBadge;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        loadFragments(fragment);
                        return true;

                    case R.id.navigation_shoping:
                        fragment = new ShopFragment();
                        loadFragments(fragment);
                        return true;

                    case R.id.navigation_cart:
                        fragment = new CartFragment();
                        loadFragments(fragment);
                        return true;

                }
                return false;
            }
        });

        load_home_fragment_first_time();



        String smartphoneImageURL[] = new String[]{"https://cdn.vox-cdn.com/thumbor/3kLqQ-F8ldNtSKXtSJKXabx0RGg=/0x0:705x470/1200x800/filters:focal(297x179:409x291)/cdn.vox-cdn.com/uploads/chorus_image/image/61477359/galaxy_a7_main_1.0.jpg"
                ,"https://images.sellbrite.com/production/14465/SAM-G892U_64-GRY-TMOB-9/668e6064-1586-552c-b321-a00f1f1bac1f.jpg"
                ,"http://www.three.co.uk/static/images/device_pages/MobileVersion/Huawei/Mate_20_Pro/Black/desktop/1.jpg"
                ,"https://i.gadgets360cdn.com/large/asus_zenfone_max_pro_m1_1525410691515.jpg"
                ,"https://www.jarir.com/media/catalog/product/cache/1/image/400x400/9df78eab33525d08d6e5fb8d27136e95/5/1/513984.jpg"
                ,"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone8/plus/iphone8-plus-silver-select-2018?wid=513&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1522347733364"
                ,"https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/BlackBerry-Priv.jpg/1200px-BlackBerry-Priv.jpg"
                ,"https://photos.dialcom.lk/big/items/-passport-mobile-phones-price-in-sri-lanka_374_jpg"};
        databaseHelper = new DatabaseHelper(getApplicationContext());

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Samsung Note9 64GB",smartphoneImageURL[0],2890,"smartPhone",0,0));
        productList.add(new Product("Samsung S8 128GB",smartphoneImageURL[1],2200,"smartPhone",0,0));
        productList.add(new Product("Huawei Mate 20 pro 256GB",smartphoneImageURL[2],2600,"smartPhone",0,0));
        productList.add(new Product("Asus Zenfone 64GB",smartphoneImageURL[3],1400 ,"smartPhone",0,0));
        productList.add(new Product("Apple Iphone Xsmax 256GB ",smartphoneImageURL[4],3300,"smartPhone",0,0));
        productList.add(new Product("Apple Iphone 8 128GB",smartphoneImageURL[5],2090,"smartPhone",0,0));
        productList.add(new Product("Blackberry Priv",smartphoneImageURL[6],2700,"smartPhone",0,0));
        productList.add(new Product("Blackberry Passport",smartphoneImageURL[7],2100,"smartPhone",0,0));

        databaseHelper.insertProducts(productList);


    }

    private void load_home_fragment_first_time(){
        Fragment fragment = new HomeFragment();
        loadFragments(fragment);
        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);

    }

    private void loadFragments(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



    @Override
    public void onFragmentInteraction() {
        int number = databaseHelper.getProductsInCart().size();
        addBadgeView(number);
    }

    @Override
    public void changeBottomNavSelection(int a) {
        bottomNavigationView.getMenu().findItem(a).setChecked(true);

    }


    private void addBadgeView(int number) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(2);

        notificationBadge = LayoutInflater.from(this).inflate(R.layout.badge_bottom_nav, menuView, false);

        notificationNo = (TextView) notificationBadge.findViewById(R.id.notificationsTv);
        bottomNavigationView.refreshDrawableState();

        if (number!=0){

            itemView.addView(notificationBadge);
            notificationNo.setText(" "+String.valueOf(number)+" ");

        }


    }


}
