package com.kkard.seoulroad.Visit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkard.seoulroad.Circleindicator_C.IndicatorAdapter;
import com.kkard.seoulroad.Circleindicator_C.LoopViewPager;
import com.kkard.seoulroad.R;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by SuGeun on 2017-08-30.
 */

public class VActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_v,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IndicatorAdapter mPageAdapter = new IndicatorAdapter();
        LoopViewPager viewPager = (LoopViewPager)view.findViewById(R.id.viewpager);
        CircleIndicator indicator  = (CircleIndicator)view.findViewById(R.id.indicator);
        viewPager.setAdapter(mPageAdapter);
        indicator.setViewPager(viewPager);

    }
}
