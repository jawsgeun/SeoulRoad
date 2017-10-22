package com.kkard.seoulroad.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kkard.seoulroad.R;

/**
 * Created by SuGeun on 2017-08-30.
 */

public class MapActivity extends Fragment{
    Button recommandBtn;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_map,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recommandBtn = (Button)getView().findViewById(R.id.recommand_btn);
        context=getContext();
        recommandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),CourseDetailActivity.class));
                ((Activity)context).finish();
            }
        });
    }
}
