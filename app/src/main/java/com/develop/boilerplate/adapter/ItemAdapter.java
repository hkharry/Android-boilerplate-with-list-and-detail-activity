package com.develop.boilerplate.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.develop.boilerplate.R;
import com.develop.boilerplate.model.Item;

import java.util.List;

/**
 * Created by hari on 8/7/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>  {

    private Context mContext;
    private List<Item> List;
    OnItemClickListener mItemClickListener;



    public ItemAdapter(Context mContext, List<Item> mList) {
        this.mContext = mContext;
        this.List = mList;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);



    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Item item = List.get(position);
        holder.title.setText(item.getName());
        holder.count.setText(item.getAddress());

    }

    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }


    @Override
    public int getItemCount() {
        return List.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }



    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}





