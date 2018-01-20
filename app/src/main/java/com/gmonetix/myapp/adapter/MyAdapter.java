package com.gmonetix.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.gmonetix.myapp.R;
import com.gmonetix.myapp.model.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav Bordoloi on 1/19/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private List<Page> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Page> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.page_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Page page = list.get(position);
        holder.textView.setText(page.getTitle());

        Glide.with(context).load("https://graph.facebook.com/" + page.getId() + "/picture?type=large").into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            textView = (TextView) itemView.findViewById(R.id.item_title);
        }

    }

}
