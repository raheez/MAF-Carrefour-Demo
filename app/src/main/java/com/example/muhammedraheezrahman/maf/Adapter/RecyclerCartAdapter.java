package com.example.muhammedraheezrahman.maf.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.muhammedraheezrahman.maf.Model.Product;
import com.example.muhammedraheezrahman.maf.R;

import java.util.ArrayList;
import java.util.List;

class CartProductViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public TextView titleTv;
    public TextView priceTv;
    public Button addToCart;
    public RecyclerCartAdapter.CartItemClickListener cartItemClickListener;
    public CartProductViewHolder(@NonNull final View itemView, final RecyclerCartAdapter.CartItemClickListener itemClickListener) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.productImage);
        titleTv = (TextView) itemView.findViewById(R.id.title);
        priceTv = (TextView) itemView.findViewById(R.id.priceTv);
    }

}

public class RecyclerCartAdapter extends RecyclerView.Adapter<CartProductViewHolder> {

   public static List<Product> productList = new ArrayList<>();
   private Context context;
   private CartItemClickListener itemClickListener;

    public RecyclerCartAdapter(List<Product> productList, Context context, CartItemClickListener clickListener) {
        this.productList = productList;
        this.context = context;
        this.itemClickListener = clickListener;
    }



    @NonNull
    @Override
    public CartProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_item,viewGroup,false);
        return new CartProductViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductViewHolder viewHolder, int i) {
        Product product = productList.get(i);
        viewHolder.titleTv.setText(String.valueOf(product.getTitle()));
        viewHolder.priceTv.setText(String.valueOf(product.getPrice() + "AED"));
        Glide.with(context).load(product.getImageURL()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface  CartItemClickListener{
        public void addToCart(int id);
    }


}
