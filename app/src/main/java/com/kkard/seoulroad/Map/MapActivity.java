package com.kkard.seoulroad.Map;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkard.seoulroad.R;

/**
 * Created by SuGeun on 2017-08-30.
 */

public class MapActivity extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_map,container,false);
    }
}
