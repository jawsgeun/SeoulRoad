package com.kkard.seoulroad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by SuGeun on 2017-08-08.
 */

public class RegistActivity extends AppCompatActivity{
    private EditText password,passConf ,email ,name ;
    private ConstraintLayout backLayout;
    private Button joinBtn;
    private boolean passwordFlag = false,passConfFlag= false ,emailFlag= false ,nameFlag= false ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinBtn = (Button)findViewById(R.id.joinEnter); // 가입하기 버튼
        password = (EditText)findViewById(R.id.joinPass); // 비밀번호
        passConf = (EditText)findViewById(R.id.joinPassConf); // 비밀번호 확인
        email = (EditText)findViewById(R.id.joinEmail); // 이메일
        name = (EditText)findViewById(R.id.joinName); // 이름
        backLayout = (ConstraintLayout)findViewById(R.id.joinBack);

        backLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(password.getWindowToken(),0);
                imm.hideSoftInputFromWindow(passConf.getWindowToken(),0);
                imm.hideSoftInputFromWindow(name.getWindowToken(),0);
                return false;
            }
        });
        name.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!nameFlag){
                    name.setText("");
                    nameFlag = true;
                }
                return false;
            }
        });
        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!emailFlag){
                    email.setText("");
                    emailFlag = true;
                }
                return false;
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!passwordFlag){
                    password.setText("");
                    password.setInputType(0x00000081); // 숫자 비밀번호 방식(***)으로 변경
                    passwordFlag = true;
                }
                return false;
            }
        });
        passConf.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!passConfFlag){
                    passConf.setText("");
                    passConf.setInputType(0x00000081); // 숫자 비밀번호 방식(***)으로 변경
                    passConfFlag = true;
                }
                return false;
            }
        });
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!passConf.getText().toString().equals(password.getText().toString())){ // 비밀번호 확인 다를시
                    Toast.makeText(getApplicationContext(),"비밀번호 불일치",Toast.LENGTH_SHORT).show();
                }else{ // 이름 형식 확인, 이메일 형식 확인 조건문 추가해야함
                    Toast.makeText(getApplicationContext(),"가입되었습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegistActivity.this,LoginActivity.class));
        finish();
    }
}
