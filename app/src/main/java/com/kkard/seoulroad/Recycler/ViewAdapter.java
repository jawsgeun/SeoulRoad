package com.kkard.seoulroad.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkard.seoulroad.Circleindicator_C.IndicatorAdapter;
import com.kkard.seoulroad.R;
import com.kkard.seoulroad.ViewHolder.ImageItemHolder;
import com.kkard.seoulroad.ViewHolder.PagerItemHolder;
import com.kkard.seoulroad.ViewHolder.TextItemHolder;
import com.kkard.seoulroad.utils.Check;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SuGeun on 2017-10-03.
 */

public class ViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_TEXT = 51;
    public final static int VIEW_TYPE_PAGER = 52;
    public static final int VIEW_TYPE_IMAGE = 53;

    private List<Data> mDataList = new ArrayList<>();

    public ViewAdapter(List<Data> list, Context context) {

        if (list != null && list.size() > 0)
            mDataList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case VIEW_TYPE_TEXT:
                View userView = inflater.inflate(R.layout.layout_recycle_text, parent, false);
                viewHolder = new TextItemHolder(userView);
                break;

            case VIEW_TYPE_PAGER:
                View blockbusterView = inflater.inflate(R.layout.layout_recycle_pager, parent, false);
                viewHolder = new PagerItemHolder(blockbusterView);
                break;

            case VIEW_TYPE_IMAGE:
                View userImageView = inflater.inflate(R.layout.layout_recycle_image, parent, false);
                viewHolder = new ImageItemHolder(userImageView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case VIEW_TYPE_TEXT:
                TextItemHolder textHolder = (TextItemHolder) holder;
                configureTextItem(textHolder, position);
                break;

            case VIEW_TYPE_PAGER:
                PagerItemHolder pagerHolder = (PagerItemHolder) holder;
                configurePagerHolder(pagerHolder, position);
                break;

            case VIEW_TYPE_IMAGE:
                ImageItemHolder imageHolder = (ImageItemHolder) holder;
                configureImageHolder(imageHolder, position);
                break;
        }
    }

    private void configureTextItem(TextItemHolder holder, int position) {

        Data data = mDataList.get(position);

        if (!Check.isEmpty(data.getTextItem()))
            holder.tvTitle.setText(data.getTextItem());
    }

    private void configurePagerHolder(PagerItemHolder holder, int position) {

        IndicatorAdapter mPageAdapter = new IndicatorAdapter();
        holder.viewPager.setAdapter(mPageAdapter);
        holder.indicator.setViewPager(holder.viewPager);

}
    private void configureImageHolder(ImageItemHolder holder, int position){
        Data data = mDataList.get(position);

        if(!data.getListImageItemList().isEmpty()){
            holder.imageView1.setImageResource(data.getListImageItemList().get(0).getItemImage());
            holder.imageView2.setImageResource(data.getListImageItemList().get(1).getItemImage());
            holder.imageView3.setImageResource(data.getListImageItemList().get(2).getItemImage());
        }

    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getItemViewType(int position) {

        return mDataList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }
}
