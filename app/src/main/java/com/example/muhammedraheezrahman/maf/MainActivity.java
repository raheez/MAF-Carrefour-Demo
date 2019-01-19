package com.example.muhammedraheezrahman.maf;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.muhammedraheezrahman.maf.Fragments.CartFragment;
import com.example.muhammedraheezrahman.maf.Fragments.HomeFragment;
import com.example.muhammedraheezrahman.maf.Fragments.ShopFragment;

public class MainActivity extends AppCompatActivity  implements ShopFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener,CartFragment.OnFragmentInteractionListener{

    private BottomNavigationView bottomNavigationView;
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
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void changeBottomNavSelection(int a) {
        bottomNavigationView.getMenu().findItem(a).setChecked(true);

//        bottomNavigationView.setSelectedItemId(R.id.navigation_shoping);
    }

//    @Override
//    public void onFragmentInteraction(Uri uri) {
//        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
//    }
}
