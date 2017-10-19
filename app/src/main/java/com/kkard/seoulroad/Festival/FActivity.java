package com.kkard.seoulroad.Festival;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkard.seoulroad.Calendar_C.DatePickerController;
import com.kkard.seoulroad.Calendar_C.SimpleMonthAdapter;
import com.kkard.seoulroad.R;
import com.kkard.seoulroad.Recycler.Data;
import com.kkard.seoulroad.Recycler.ViewAdapter;

import java.util.ArrayList;
import java.util.List;




/**
 * Created by SuGeun on 2017-08-21.
 */

public class FActivity extends Fragment implements DatePickerController {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DayPickerView_C calendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_f,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getContext();

        calendarView = (DayPickerView_C) getView().findViewById(R.id.calendar_view);
        calendarView.setController(FActivity.this);

        recyclerView = (RecyclerView)getView().findViewById(R.id.fest_recycle_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ViewAdapter(getData(),context);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public int getMaxYear() {
        return 0;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {

    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

    }
    private List<Data> getData() {

        List<Data> finalList = new ArrayList<>();

        Data data = new Data();
        data.setViewType(ViewAdapter.VIEW_TYPE_TEXT_MAIN);
        // 맨위 리스트
        List<String> content = new ArrayList<>();
        content.add("대한민국 환경조경대전 '광장의 재발견'");
        content.add("김형학 화훼 작가의 '서울로 자연의 철학'");
        content.add("서울로 100일의 꽃 세밀화전");
        content.add("서울로 가드너의 '정원이 놀다'");
        content.add("어린이 참가작 전시");
        data.setTextList("주요전시",content);
        finalList.add(data);
        // 부수적인 것
        data = new Data();
        data.setViewType(ViewAdapter.VIEW_TYPE_TEXT);
        data.setTextList("10 : 00","100일의 식물이야기 산책 (개막)");
        finalList.add(data);
        data = new Data();
        data.setViewType(ViewAdapter.VIEW_TYPE_TEXT);
        data.setTextList("11 : 00","100일의 꽃 그리기");
        finalList.add(data);
        data = new Data();
        data.setViewType(ViewAdapter.VIEW_TYPE_TEXT);
        data.setTextList("13 : 00","100일의 인증사진");
        finalList.add(data);
        data = new Data();
        data.setViewType(ViewAdapter.VIEW_TYPE_TEXT);
        data.setTextList("14 : 00","100일의 꽃 그려버리기");
        finalList.add(data);


        return finalList;

    }
}
