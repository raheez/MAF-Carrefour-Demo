package com.example.muhammedraheezrahman.maf;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomPagerAdapter extends PagerAdapter {
    private String[] imageList;
    private Activity activity;

    public CustomPagerAdapter(String[] imageList, Activity activity) {
        this.imageList = imageList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)activity).getLayoutInflater();
        View view = inflater.inflate(R.layout.image_item,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.bannerImageView);
        Glide.with(activity.getApplicationContext()).load(imageList[position]).into(imageView);
        ((ViewPager)container).addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageList.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (View) o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View) object);
    }
}
