package com.kkard.seoulroad.MyMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kkard.seoulroad.FragmentActivity;
import com.kkard.seoulroad.R;

import java.util.ArrayList;

/**
 * Created by SuGeun on 2017-09-01.
 */

public class NoticeActivity extends AppCompatActivity{
    private ExpandableListView expandableListView;
    private ExpandableAdapter adapter;
    private TextView toolbarTitle;
    private ImageButton toolbarBack;
    private ArrayList<NoticeParentData> parentDatas;
    private ArrayList<NoticeChildData> childListDatas;
    private int pageNum;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        intent = getIntent();
        pageNum = intent.getIntExtra("pageNum",0);
        expandableListView = (ExpandableListView)findViewById(R.id.expand_menu);
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar);
        toolbarTitle.setText("게시판");
        toolbarBack = (ImageButton)findViewById(R.id.btn_toolbar_back);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(NoticeActivity.this, FragmentActivity.class);
                intent.putExtra("pageNum",pageNum);
                startActivity(intent);
                finish();
            }
        });
        setData();
        adapter = new ExpandableAdapter(this,parentDatas,childListDatas);
        expandableListView.setAdapter(adapter);
    }
    private void setData(){
        parentDatas = new ArrayList<NoticeParentData>();
        childListDatas = new ArrayList<NoticeChildData>();

        parentDatas.add(new NoticeParentData(ExpandableAdapter.TYPE_NOTICE,"꺼짐 오류 수정 버전 2.1 로 업데이트 되었습니다"));
        childListDatas.add(new NoticeChildData("이쪽의 내용은 디자이너 분들이 안줘서 내맘대로 할거에요.. \n하나도 안 감사합니다."));

        parentDatas.add(new NoticeParentData(ExpandableAdapter.TYPE_NOTICE,"페이스북 연동 기능 추가되었습니다. 로그인 창에서 페이스북 연동하기 버튼을 클릭하면"));
        childListDatas.add(new NoticeChildData("페이스북 연동 기능 추가되었습니다. 로그인 창에서 페이스북 연동하기 버튼을 클릭하면 자동으로 페이스북과 연결됩니다.\n많은 이용 바랍니다. 앞으로도 사용자들의 편의를 위하겠습니다.\n감사합니다."));

        parentDatas.add(new NoticeParentData(ExpandableAdapter.TYPE_QNA,"비밀번호를 수정하고 싶은데 어떻게 해야하나요?"));
        childListDatas.add(new NoticeChildData("여기 게시판 들어오기 전에 메뉴에 있잖아.....\n어휴..."));

        parentDatas.add(new NoticeParentData(ExpandableAdapter.TYPE_QNA,"qr코드 인식이 안되요. 방법이 없나요?"));
        childListDatas.add(new NoticeChildData("핸드폰을 바꾸세요. 요즘 좋은 스마트 폰들이 많이 나와있답니다 ^^\n감사합니다."));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        intent = new Intent(NoticeActivity.this, FragmentActivity.class);
        intent.putExtra("pageNum",pageNum);
        startActivity(intent);
        finish();
    }
}
