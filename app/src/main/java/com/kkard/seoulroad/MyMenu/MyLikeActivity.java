package com.kkard.seoulroad.MyMenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kkard.seoulroad.FragmentActivity;
import com.kkard.seoulroad.R;
import com.kkard.seoulroad.Recycler.Data;
import com.kkard.seoulroad.Recycler.ViewAdapter;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuGeun on 2017-10-21.
 */

public class MyLikeActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private TextView toolbarTitle;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int pageNum;
    private Intent intent;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylike);
        intent = getIntent();
        pageNum = intent.getIntExtra("pageNum",0);
        InitView();
        toolbarTitle.setText("좋아요");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ViewAdapter(getData(),context);
        recyclerView.setAdapter(adapter);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MyLikeActivity.this, FragmentActivity.class);
                intent.putExtra("pageNum",pageNum);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(MyLikeActivity.this, FragmentActivity.class);
        intent.putExtra("pageNum",pageNum);
        startActivity(intent);
        finish();
    }
    private List<Data> getData(){

        List<Data> finalList = new ArrayList<>();
        Data data = new Data();
        List<String> content = new ArrayList<>();
        data.setViewType(ViewAdapter.VIEW_TYPE_POST);
        content.add("유저 아이디");
        content.add("null");
        content.add("300");
        content.add("2017.10.20");
        content.add("매우 예쁘군요");
        content.add("수정 불가");
        data.setmPostContent(content);
        finalList.add(data);

        data = new Data();
        content = new ArrayList<>();
        data.setViewType(ViewAdapter.VIEW_TYPE_POST);
        content.add("유저 아이디");
        content.add("null");
        content.add("30");
        content.add("3033.10.20");
        content.add("매우 별로에요요");
        content.add("수정 불가");
        data.setmPostContent(content);
        finalList.add(data);

        return finalList;
    }
    private void InitView(){
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar);
        backBtn = (ImageButton) findViewById(R.id.btn_toolbar_back);
        context = getApplicationContext();
        recyclerView = (RecyclerView)findViewById(R.id.mylike_recycle_view);
        layoutManager = new LinearLayoutManager(context);
    }
}
