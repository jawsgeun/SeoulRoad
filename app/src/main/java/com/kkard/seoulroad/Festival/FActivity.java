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

import com.kkard.seoulroad.R;
import com.kkard.seoulroad.Recycler.Data;
import com.kkard.seoulroad.Recycler.ViewAdapter;

import java.util.ArrayList;
import java.util.List;

import me.nlmartian.silkcal.DatePickerController;
import me.nlmartian.silkcal.SimpleMonthAdapter;


/**
 * Created by SuGeun on 2017-08-21.
 */

public class FActivity extends Fragment implements DatePickerController {

    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
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

        for (int i = 1; i <= 3; i++) {

            Data data = new Data();

                data.setViewType(ViewAdapter.VIEW_TYPE_TEXT);
                data.setTextItem("List Item: " + i);

            finalList.add(data);
        }

        return finalList;

    }
}
