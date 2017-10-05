package com.kkard.seoulroad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public String userId, userPassword;
    /////////////// 뷰 객체 선언 /////////////
    EditText email, pass;
    Button loginBtn, joinBtn;
    ConstraintLayout backLayout;
    CheckBox saveID, autoLogin;
    SharedPreferences sh;

    /////////////////////// 변수 선언 //////////////////////
    private boolean isSave; // 아이디 저장 체크박스
    private boolean saveExist; // 아이디 저장 설정 여부
    private boolean isAuto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh = getPreferences(MODE_PRIVATE);
        Intent intent = getIntent();
        if(intent.getStringExtra("isAuto")!=null){
            SharedPreferences.Editor editor = sh.edit();
            editor.putString("isAuto","false");
            editor.apply();
        }
        String autoBool = sh.getString("isAuto","None");
        if(autoBool.equals("true")){ // 자동 로그인 할 경우
            userId = sh.getString("userAutoId","None"); // 글로벌 아이디 저장
            userPassword =sh.getString("userAutoPass","None"); // 글로벌 비밀번호 저장
            intent = new Intent(LoginActivity.this, FragmentActivity.class);
            intent.putExtra("userId",userId);
            startActivity(intent);
            finish();
        }
///////////////////// 뷰 객체 초기화 //////////////////
        email = (EditText) findViewById(R.id.emailInput);
        pass = (EditText) findViewById(R.id.passwordInput);
        loginBtn = (Button) findViewById(R.id.loginButton);
        joinBtn = (Button) findViewById(R.id.joinButton);
        backLayout = (ConstraintLayout) findViewById(R.id.backGround);
        saveID = (CheckBox)findViewById(R.id.saveID);
        autoLogin = (CheckBox)findViewById(R.id.autoLogin);
//////////////////////////////////////////////////////////////
        String userId = sh.getString("UserSaveId","None"); // 자동 저장 되어 있는 ID 가져오기
        if(userId.equals("None")){ // 자동 저장이 되어있지 않을 경우
            saveExist = false;
            isSave =false;
        }else{
            saveExist = true;
            saveID.setChecked(true);
            isSave =true;
            email.setText(userId);
        }

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
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idContent = email.getText().toString().trim();
                String passContent = pass.getText().toString().trim();
                                                                        //  첫 저장  -> 입력
                    if(isSave == (!saveExist)) {                        //  isSave = true  isExist = false
                        SharedPreferences.Editor editor = sh.edit();    //  저장되어있는상태 -> 입력 x
                        if (isSave) {                                   //  isSave = true  isExist = true
                            editor.putString("UserSaveId", idContent);  //  저장 해제 -> 삭제
                        } else {                                        //  isSave = false   isExist = true
                            editor.remove("UserSaveId");                //  저장하지않음 -> 아무것도 안함
                        }                                               //  isSave = false   isExist = false
                        editor.apply();
                    }

                if(idContent.getBytes().length <= 0 ){//빈값이 넘어올때의 처리
                    Toast.makeText(getApplicationContext(), "아이디를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else if( passContent.getBytes().length <= 0){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                }else if(!checkEmail(idContent)){
                    Toast.makeText(getApplicationContext(), "아이디를 이메일 형식으로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(passContent.length()!=4){
                    Toast.makeText(getApplicationContext(), "비밀번호는 4자리 입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    if(isAuto){
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("UserAutoId",idContent);
                        editor.putString("UserAutoPass",passContent);
                        editor.putString("isAuto","true");
                        editor.apply();
                    }
                    Intent intent = new Intent(LoginActivity.this, FragmentActivity.class);
                    intent.putExtra("userId",idContent);
                    startActivity(intent);
                    finish();
                }
            }
        });
        saveID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isSave = true;
                }else{
                    isSave = false;
                }
            }
        });
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isAuto = true;
                }else{
                    isAuto = false;
                }
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
}
