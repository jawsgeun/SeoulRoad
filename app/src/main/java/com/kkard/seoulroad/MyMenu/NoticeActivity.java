package com.kkard.seoulroad.MyMenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import com.kkard.seoulroad.R;

import java.util.ArrayList;

/**
 * Created by SuGeun on 2017-09-01.
 */

public class NoticeActivity extends Activity{
    ExpandableListView expandableListView;
    ExpandableAdapter adapter;
    ArrayList<NoticeParentData> parentDatas;
    ArrayList<ArrayList<NoticeChildData>> childListDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        expandableListView = (ExpandableListView)findViewById(R.id.expand_menu);
        setData();
        adapter = new ExpandableAdapter(this,parentDatas,childListDatas);
        expandableListView.setAdapter(adapter);
    }
    private void setData(){
        parentDatas = new ArrayList<NoticeParentData>();
        childListDatas = new ArrayList<ArrayList<NoticeChildData>>();

        int sizeList =0;

        parentDatas.add(new NoticeParentData(sizeList,"비밀번호를 잊어버리면 어떻게 하나요?","2017/03/06"));
        childListDatas.add(new ArrayList<NoticeChildData>());
        childListDatas.get(sizeList).add(new NoticeChildData("알아서 하세요 ^^"));

        sizeList ++;

        parentDatas.add(new NoticeParentData(sizeList,"암호를 잊어버리면 어떻게 하나요?","2027/06/06"));
        childListDatas.add(new ArrayList<NoticeChildData>());
        childListDatas.get(sizeList).add(new NoticeChildData("진짜 알아서 하세요 ^-^"));

        sizeList ++;

        parentDatas.add(new NoticeParentData(sizeList,"암호를 잊어버리면 이렇게 하나요?","1597/11/06"));
        childListDatas.add(new ArrayList<NoticeChildData>());
        childListDatas.get(sizeList).add(new NoticeChildData("아니니까 알아서 하세요 ^-^"));
    }
}
