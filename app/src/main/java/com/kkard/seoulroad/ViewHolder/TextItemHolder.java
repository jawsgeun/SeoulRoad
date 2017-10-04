package com.kkard.seoulroad.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kkard.seoulroad.R;

/**
 * Created by SuGeun on 2017-10-03.
 */
public class TextItemHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;

    public TextItemHolder(View view) {
        super(view);

        tvTitle = (TextView) view.findViewById(R.id.tv_item);
    }
}
