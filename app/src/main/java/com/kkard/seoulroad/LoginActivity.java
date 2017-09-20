package com.kkard.seoulroad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SuGeun on 2017-08-08.
 */

public class LoginActivity extends Activity {
    ///////////////Back 버튼 2번 종료 관련 변수////////////
    private final long FINISH_INTERVAL_TIME = 2000; //2초안에 Back 버튼 누르면 종료
    private long   backPressedTime = 0;

    /////////////// 뷰 객체 선언 /////////////
    EditText email, pass;
    Button loginBtn;
    TextView joinBtn;
    ConstraintLayout backLayout;
    CheckBox autoLogin;

    /////////////////////// 변수 선언 //////////////////////
    boolean emailFlag= false; // 처음으로 이메일 입력창 누르는지
    boolean passFlag= false; // 처음으로 비번 입력창 누르는지
    boolean anyPlay=false; // 이메일이나 비번을 입력중인지

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tmp);
///////////////////// 뷰 객체 초기화 //////////////////
        email = (EditText) findViewById(R.id.emailInput);
        pass = (EditText) findViewById(R.id.passwordInput);
        loginBtn = (Button) findViewById(R.id.loginButton);
        joinBtn = (TextView) findViewById(R.id.joinButton);
        backLayout = (ConstraintLayout) findViewById(R.id.backGround);
        autoLogin = (CheckBox)findViewById(R.id.autoLogin);
//////////////////////////////////////////////////////////////

        backLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(email.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(pass.getWindowToken(),0);
                return false;
            }
        });

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkEmail(email.getText().toString())){
                    Toast.makeText(getApplicationContext(), "아이디를 이메일 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                    switch (checkPass(pass.getText().toString())){
                        case 0: // 통과
                            startActivity(new Intent(LoginActivity.this, FragmentActivity.class));
                            finish();
                            break;
                        case 1: // 숫자형식 ㄴㄴ
                            Toast.makeText(getApplicationContext(), "비밀번호는 4자리 숫자입니다.", Toast.LENGTH_SHORT).show();
                            break;
                        case 2: // 4자리 ㄴㄴ
                            Toast.makeText(getApplicationContext(), "비밀번호는 4자리 입니다.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"자동 로그인 활성화",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"자동 로그인 비활성화",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email.setText("");
//            }
//        });
//        pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pass.setText("");
//            }
//    });
        email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!emailFlag) {
                    email.setText("");
                    emailFlag=true;
                }
                return false;
            }
        });
        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(!passFlag){
                    pass.setText("");
                    pass.setInputType(0x00000081); // 숫자 비밀번호 방식(***)으로 변경
                    passFlag=true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "종료를 원하시면 뒤로 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
        }

    }
    /////////////// 이메일 포맷 체크 ////////////////
    public static boolean checkEmail(String email){
        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;
    }
    /////////////// 비밀번호 포맷 체크 (숫자 4자리)///////////////
    public static int checkPass(String s) {
        if(s.length()==4) {
            try {
                Double.parseDouble(s);
                return 0; // 정상
            } catch (NumberFormatException e) {
                return 1; // 숫자 형식 아님
            }
        }else{
            return 2; // 4 자리가 아님
        }
    }
}
