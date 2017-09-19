package com.kkard.seoulroad.Festival;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kkard.seoulroad.R;

import java.util.ArrayList;

/**
 * Created by SuGeun on 2017-09-06.
 */

public class RecyclerAdapter extends RecyclerView.Adapter
{
    private Context context;
    private ArrayList<ListItem> items;

    private int lastPosition = -1;

    public RecyclerAdapter(Context context, ArrayList items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycle,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
      }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((ViewHolder)holder).imageView.setImageResource(items.get(position).image);
    setAnimation(((ViewHolder)holder).imageView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
