package com.kkard.seoulroad.Festival;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kkard.seoulroad.R;

/**
 * Created by SuGeun on 2017-09-06.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public ViewHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.image_list);
    }
}

