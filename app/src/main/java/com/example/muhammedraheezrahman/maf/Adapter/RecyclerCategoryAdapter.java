package com.example.muhammedraheezrahman.maf.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.muhammedraheezrahman.maf.Fragments.HomeFragment;
import com.example.muhammedraheezrahman.maf.Fragments.ShopFragment;
import com.example.muhammedraheezrahman.maf.R;

import java.util.ArrayList;
import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public TextView titleTv;
    public RelativeLayout containerLayout;
    public RecyclerCategoryAdapter.ClickCategoryItem clickCategoryItem;
    public ViewHolder(@NonNull View itemView,final RecyclerCategoryAdapter.ClickCategoryItem clickCategoryItem) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        titleTv = (TextView) itemView.findViewById(R.id.title_seond_line_tv);
        containerLayout = (RelativeLayout) itemView.findViewById(R.id.containerlayout);
        this.clickCategoryItem = clickCategoryItem;



    }
}
public class RecyclerCategoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private String[] imagelist;
    public  List<String> titlelist =new ArrayList<>();
    public  static List<String>  saticTitleLists = new ArrayList<>();
    private ClickCategoryItem clickCategoryItem;
    private int number;

    public RecyclerCategoryAdapter(Context context, String[] imagelist,List<String> titlelist,ClickCategoryItem clickCategoryItem) {
        this.context = context;
        this.imagelist = imagelist;
        this.titlelist = titlelist;
        this.clickCategoryItem = clickCategoryItem;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_list_items,viewGroup,false);
        return new ViewHolder(view,clickCategoryItem);

    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String URL = imagelist[i];
        final String title = titlelist.get(i);
        Glide.with(context).load(URL).into(viewHolder.imageView);
        viewHolder.titleTv.setText(String.valueOf(title));
        viewHolder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  string = titlelist.get(i);
                clickCategoryItem.clickOnCategoryCard(string);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.imagelist.length;
    }

    public static void addToList(List<String> list){
        saticTitleLists.addAll(list);
    }

    public static void clearStaticList(){
        saticTitleLists.clear();
    }

    public interface ClickCategoryItem{

        public  void clickOnCategoryCard(String title);

    }

}
