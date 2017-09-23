package com.kkard.seoulroad.Visit;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.kkard.seoulroad.Circleindicator_C.DepthTransformerAdeptor;
import com.kkard.seoulroad.Circleindicator_C.IndicatorAdapter;
import com.kkard.seoulroad.Circleindicator_C.LoopViewPager;
import com.kkard.seoulroad.R;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by SuGeun on 2017-08-30.
 */

public class VActivity extends Fragment {
    FloatingActionButton fab1, fab2, fab3;
    FloatingActionMenu fam;
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

        //Depth
//        UltraViewPager viewPager = (UltraViewPager)view.findViewById(R.id.viewpager);
//        DepthTransformerAdeptor dta = new DepthTransformerAdeptor(true);
//        viewPager.setPageTransformer(false, new UltraDepthScaleTransformer());
//
//        viewPager.setAutoScroll(5000);
//        viewPager.setInfiniteLoop(true);
//        viewPager.setInfiniteRatio(3);
//
//        viewPager.setAdapter(dta);
//        viewPager.setMultiScreen(0.6f);
//        viewPager.setItemRatio(1.0f);
//        viewPager.setAutoMeasureHeight(true);
//        indicator.setViewPager(viewPager.getViewPager());
        //까지 수정해야 할것 페이지수 3 loop 될수 있게 크기좀 키우기

        viewPager.setAdapter(mPageAdapter);
        indicator.setViewPager(viewPager);


        fam = (FloatingActionMenu)view.findViewById(R.id.material_design_android_floating_action_menu);
        fam.setClosedOnTouchOutside(true);
        fab1 = (FloatingActionButton)view.findViewById(R.id.material_design_floating_action_menu_item1);
        fab2 = (FloatingActionButton)view.findViewById(R.id.material_design_floating_action_menu_item2);


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
