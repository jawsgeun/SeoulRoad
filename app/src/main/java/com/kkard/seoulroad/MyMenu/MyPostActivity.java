package com.kkard.seoulroad.MyMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kkard.seoulroad.R;
import com.kkard.seoulroad.Visit.VRegitActivity;
import com.kkard.seoulroad.utils.DialogView_C;

/**
 * Created by KyungHWan on 2017-10-06.
 */

public class MyPostActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button mBtn;
    private DialogView_C mDialog;

    private View.OnClickListener leftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };
    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDialog.dismiss();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypost);
        imageView = (ImageView) findViewById(R.id.mypost_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new DialogView_C(v.getContext(),-1,"몇번째 방문자입니다.","아이디@이메일.com","xxx개","주저리주저리");
                mDialog.show();
            }
        });
        mBtn = (Button)findViewById(R.id.mypost_btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(v.getContext(), VRegitActivity.class);
                startActivity(itn);
                finish();
            }
        });
    }
}
