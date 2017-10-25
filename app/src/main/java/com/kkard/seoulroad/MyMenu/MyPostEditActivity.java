package com.kkard.seoulroad.MyMenu;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkard.seoulroad.R;


/**
 * Created by SuGeun on 2017-10-25.
 */

public class MyPostEditActivity extends AppCompatActivity {
    private ImageButton toolbarBack;
    private TextView toolbarTitle, userId, like, date;
    private EditText comment;
    private ImageView img;
    private Intent intent;
    private Button enter;
    private int lineColor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_post_edit);
        intent = getIntent();
        InitView();
        lineColor = Color.parseColor("#E73A62");
        comment.getBackground().setColorFilter(lineColor, PorterDuff.Mode.SRC_ATOP);
        SetListener();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void InitView(){
        toolbarBack = (ImageButton)findViewById(R.id.btn_toolbar_back);
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar);
        userId = (TextView)findViewById(R.id.mypostedit_userid);
        like = (TextView)findViewById(R.id.mypostedit_like);
        date = (TextView)findViewById(R.id.mypostedit_date);
        comment = (EditText)findViewById(R.id.mypostedit_comment);
        img = (ImageView)findViewById(R.id.mypostedit_img);
        enter = (Button)findViewById(R.id.mypostedit_enter);
    }

    private void SetListener(){
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //디비 부분 : comment update 해야함
                finish();
            }
        });
        toolbarTitle.setText("수정하기");
        userId.setText(intent.getStringExtra("userId"));
        like.setText(intent.getStringExtra("like"));
        date.setText(intent.getStringExtra("date"));
        comment.setText(intent.getStringExtra("comment"));
        img.setImageResource(intent.getIntExtra("img",R.drawable.abc));
    }
}
