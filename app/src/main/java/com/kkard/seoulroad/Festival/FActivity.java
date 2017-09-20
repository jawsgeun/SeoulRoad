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

import java.util.ArrayList;

import me.nlmartian.silkcal.DatePickerController;
import me.nlmartian.silkcal.DayPickerView;
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

        recyclerView = (RecyclerView)getView().findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        ArrayList items = new ArrayList<ListItem>();

        items.add(new ListItem(R.drawable.abc));
        items.add(new ListItem(R.drawable.listview_off));
        items.add(new ListItem(R.drawable.listview_on));

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(context,items);
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
}
