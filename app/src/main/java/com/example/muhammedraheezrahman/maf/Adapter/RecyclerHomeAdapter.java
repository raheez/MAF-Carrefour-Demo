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
import com.example.muhammedraheezrahman.maf.R;

import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public TextView titleTv;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        titleTv = (TextView) itemView.findViewById(R.id.title_seond_line_tv);
    }
}
public class RecyclerHomeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private String[] imagelist;
    private String[] titlelist;

    public RecyclerHomeAdapter(Context context, String[] imagelist,String[] titlelist) {
        this.context = context;
        this.imagelist = imagelist;
        this.titlelist = titlelist;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_list_items,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String URL = imagelist[i];
        String title = titlelist[i];
        Glide.with(context).load(URL).into(viewHolder.imageView);
        viewHolder.titleTv.setText(String.valueOf(title));

    }

    @Override
    public int getItemCount() {
        return this.imagelist.length;
    }
}
