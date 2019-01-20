package com.example.muhammedraheezrahman.maf.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muhammedraheezrahman.maf.Model.Product;
import com.example.muhammedraheezrahman.maf.R;

import java.util.ArrayList;
import java.util.List;

class HomeProductViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public TextView titleTv;
    public TextView priceTv;
    public HomeProductViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        titleTv = (TextView) itemView.findViewById(R.id.productTitleTv);
        priceTv = (TextView) itemView.findViewById(R.id.productPriceTv);
    }
}

public class RecyclerHomeProductAdapter extends RecyclerView.Adapter<HomeProductViewHolder> {

   private List<Product> productList = new ArrayList<>();
   private Context context;

    public RecyclerHomeProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item_with_bg,viewGroup,false);
        return new HomeProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeProductViewHolder viewHolder, int i) {
        Product product = productList.get(i);
        viewHolder.titleTv.setText(String.valueOf(product.getTitle()));
        viewHolder.priceTv.setText(String.valueOf(product.getPrice() + "AED"));
        Glide.with(context).load(product.getImageURL()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
