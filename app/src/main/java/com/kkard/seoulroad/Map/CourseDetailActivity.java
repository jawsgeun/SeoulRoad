package com.kkard.seoulroad.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kkard.seoulroad.R;
import com.kkard.seoulroad.Recycler.Data;
import com.kkard.seoulroad.Recycler.ViewAdapter;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuGeun on 2017-10-22.
 */

public class CourseDetailActivity extends AppCompatActivity{
    private ImageButton backBtn;
    private TextView toolbarTitle;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Intent intent;
    private int courseNum; // 0 : 남산회현, 1 : 중림중천, 2 : 청파효창 3 : 서울역통합

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        intent = getIntent();
        courseNum = intent.getIntExtra("courseNum",-1);
        Log.e("@@@@@@@@@@@@@@@2",String.valueOf(courseNum));
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar);
        toolbarTitle.setText("남산회현 코스");
        backBtn = (ImageButton) findViewById(R.id.btn_toolbar_back);

        context = getApplicationContext();
        recyclerView = (RecyclerView)findViewById(R.id.course_recycle_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ViewAdapter(getData(),context);
        recyclerView.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private List<Data> getData() { // 코스 번호에 따라서 다르게 디비 가져와야함
        List<Data> finalList = new ArrayList<>();
        Data data = new Data();
        List<String> content = new ArrayList<>(); // 이미지, 제목 , 내용 순서
        data.setViewType(ViewAdapter.VIEW_TYPE_COURSE);
        content.add("이미지 삽입 디비 구축 후 수정");
        content.add("문화역 서울 284");
        content.add("최초의 서울역은 1900년 염천교 부근에 10편짜리 목제 건물로 지어졌습니다. 당시의 이름은" +
                " '남대문정거장' 으로 남대문을 통해 경성의 중심부로 물자를 조달하기 위해 만들어졌습니다. " +
                "지금의 위치에 서울역(경성역)이 지어진 물자를 조달하기 위해 만들어졌습니다. 지금의 위치에 서울역(경성역)이 지어진 물자를 어쩌구 저쩌구" +
                "나도 모르겠군요 더이상은 저도 몰라염 하하하핳하하하하");
        data.setmCourseContent(content);
        finalList.add(data);

        data = new Data();
        content = new ArrayList<>();
        data.setViewType(ViewAdapter.VIEW_TYPE_COURSE);
        content.add("이미지 삽입 디비 구축 후 수정");
        content.add("남대문 교회");
        content.add("최초의 서울역은 1900년 염천교 부근에 10편짜리 목제 건물로 지어졌습니다. 당시의 이름은" +
                " '남대문정거장' 으로 남대문을 통해 경성의 중심부로 물자를 조달하기 위해 만들어졌습니다. " +
                "지금의 위치에 서울역(경성역)이 지어진 물자를 조달하기 위해 만들어졌습니다. 지금의 위치에 서울역(경성역)이 지어진 물자를 어쩌구 저쩌구" +
                "나도 모르겠군요 더이상은 저도 몰라염 하하하핳하하하하");
        data.setmCourseContent(content);
        finalList.add(data);

        data = new Data();
        content = new ArrayList<>();
        data.setViewType(ViewAdapter.VIEW_TYPE_COURSE);
        content.add("이미지 삽입 디비 구축 후 수정");
        content.add("백범 광장");
        content.add("최초의 서울역은 1900년 염천교 부근에 10편짜리 목제 건물로 지어졌습니다. 당시의 이름은" +
                " '남대문정거장' 으로 남대문을 통해 경성의 중심부로 물자를 조달하기 위해 만들어졌습니다. " +
                "지금의 위치에 서울역(경성역)이 지어진 물자를 조달하기 위해 만들어졌습니다. 지금의 위치에 서울역(경성역)이 지어진 물자를 어쩌구 저쩌구" +
                "나도 모르겠군요 더이상은 저도 몰라염 하하하핳하하하하");
        data.setmCourseContent(content);
        finalList.add(data);

        return finalList;
    }
}
