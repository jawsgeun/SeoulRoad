package com.kkard.seoulroad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.kkard.seoulroad.LoginActivity.checkEmail;

/**
 * Created by SuGeun on 2017-08-08.
 */

public class RegistActivity extends AppCompatActivity{
    private EditText password,passConf ,email ,name ;
    private ConstraintLayout backLayout;
    private Button joinBtn;
    private ImageView backBtn;
    private TextView toolbarTitle;
    private boolean passwordFlag = false,passConfFlag= false ,emailFlag= false ,nameFlag= false ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        joinBtn = (Button)findViewById(R.id.joinEnter); // 가입하기 버튼
        password = (EditText)findViewById(R.id.joinPass); // 비밀번호
        passConf = (EditText)findViewById(R.id.joinPassConf); // 비밀번호 확인
        email = (EditText)findViewById(R.id.joinEmail); // 이메일
        name = (EditText)findViewById(R.id.joinName); // 이름
        backLayout = (ConstraintLayout)findViewById(R.id.joinBack);
        backBtn = (ImageView)findViewById(R.id.btn_toolbar_back);
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar);

        toolbarTitle.setText("회원가입");

        backBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(RegistActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                finish();
                return false;
            }
        });

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
//        name.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(!nameFlag){
//                    name.setText("");
//                    nameFlag = true;
//                }
//                return false;
//            }
//        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        email.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(!emailFlag){
//                    email.setText("");
//                    emailFlag = true;
//                }
//                return false;
//            }
//        });
//
//        password.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(!passwordFlag){
//                    password.setText("");
//                    password.setInputType(0x00000081); // 숫자 비밀번호 방식(***)으로 변경
//                    passwordFlag = true;
//                }
//                return false;
//            }
//        });
//        passConf.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(!passConfFlag){
//                    passConf.setText("");
//                    passConf.setInputType(0x00000081); // 숫자 비밀번호 방식(***)으로 변경
//                    passConfFlag = true;
//                }
//                return false;
//            }
//        });
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameContent = name.getText().toString().trim();
                String emailContent = email.getText().toString().trim();
                String passContent = password.getText().toString().trim();
                String passConfContent = passConf.getText().toString().trim();

                if(nameContent.getBytes().length <= 0 ){//빈값이 넘어올때의 처리
                    Toast.makeText(getApplicationContext(), "이름를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else if( emailContent.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(passContent.getBytes().length <= 0 ){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else if( passConfContent.getBytes().length <= 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 한번 더 입력하세요.", Toast.LENGTH_SHORT).show();
                }else if(!checkEmail(emailContent)){
                    Toast.makeText(getApplicationContext(), "아이디를 이메일 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(!passConf.getText().toString().equals(password.getText().toString())){ // 비밀번호 확인 다를시
                    Toast.makeText(getApplicationContext(),"비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                }else if(passContent.length()!=4){
                    Toast.makeText(getApplicationContext(), "비밀번호는 4자리 입니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "가입되었습니다", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                    finish();
                }
//                else{
//                switch (checkPass(passContent)){
//                    case 0: // 통과
//                        Toast.makeText(getApplicationContext(),"가입되었습니다",Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(RegistActivity.this,LoginActivity.class));
//                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
//                        finish();
//                        break;
//                    case 1: // 숫자형식 ㄴㄴ
//                        Toast.makeText(getApplicationContext(), "올바른 비밀번호 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2: // 4자리 ㄴㄴ
//                        Toast.makeText(getApplicationContext(), "비밀번호는 4자리 입니다.", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegistActivity.this,LoginActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
    }
}
