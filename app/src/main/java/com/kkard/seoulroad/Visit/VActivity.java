package com.kkard.seoulroad.Visit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.kkard.seoulroad.R;
import com.kkard.seoulroad.Recycler.Data;
import com.kkard.seoulroad.Recycler.ListImageItem;
import com.kkard.seoulroad.Recycler.ViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuGeun on 2017-08-30.
 */

public class VActivity extends Fragment {
    FloatingActionButton fab1, fab2;
    FloatingActionMenu fam;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_v,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        recyclerView = (RecyclerView)getView().findViewById(R.id.v_recycle_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ViewAdapter(getData(),context);
        recyclerView.setAdapter(adapter);

//////// 플로팅 액션 메뉴 //////////

        fab1 = (FloatingActionButton)view.findViewById(R.id.material_design_floating_action_menu_item1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private List<Data> getData() { // 방문록 부분 데이터 받아오는 곳

        List<Data> finalList = new ArrayList<>();

            List<ListImageItem> imageItems = new ArrayList<>();

            ListImageItem listImageItem = new ListImageItem();
            listImageItem.setItemImage(R.drawable.abc);
            imageItems.add(listImageItem);
            imageItems.add(listImageItem);
             imageItems.add(listImageItem);
        Data data = new Data();
        data.setViewType(ViewAdapter.VIEW_TYPE_PAGER);
        finalList.add(data);
        for(int i=0;i<10;i++){
            data = new Data();
            data.setViewType(ViewAdapter.VIEW_TYPE_IMAGE);
            data.setListImageItemList(imageItems);
            finalList.add(data);
        }

        return finalList;

    }
}
