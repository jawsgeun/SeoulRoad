package com.kkard.seoulroad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by SuGeun on 2017-10-18.
 */

public class ModifyActivity extends AppCompatActivity{
    private EditText password,passConf ,email ,name ;
    private ConstraintLayout backLayout;
    private Button modBtn;
    private ImageView backBtn;
    private TextView toolbarTitle, checkName, checkEmail, checkPass, checkPassC;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        InitView();
        backBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(ModifyActivity.this,FragmentActivity.class));
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
                checkInput();
                return false;
            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkInput();
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkInput();
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkInput();
            }
        });
        passConf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                checkInput();
            }
        });
        passConf.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // 뷰의 id를 식별, 키보드의 완료 키 입력 검출
                if(v.getId()==R.id.modPassConf && actionId== EditorInfo.IME_ACTION_DONE){
                    checkInput();
                }
                return false;
            }
        });
        modBtn.setOnClickListener(new View.OnClickListener() {
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
                }else if(!checkInput()) {
                    Toast.makeText(getApplicationContext(), "정보를 정확하게 입력해 주세요", Toast.LENGTH_SHORT).show();
                }else if(CompareDB(nameContent, emailContent, passContent)){
                    Toast.makeText(getApplicationContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ModifyActivity.this, FragmentActivity.class));
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
                    finish();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ModifyActivity.this,FragmentActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        finish();
    }
    private boolean checkInput(){
        String name = this.name.getText().toString();
        String email = this.email.getText().toString();
        String pass = this.password.getText().toString();
        String passC = this.passConf.getText().toString();
        int cnt=0;
        if(!(Pattern.matches("^[a-zA-Z]*$",name)||Pattern.matches("^[가-힣]*$",name))){ // 한글 또는 영어가 아닐경우
            checkName.setVisibility(View.VISIBLE);
            cnt++;
        }else{
            checkName.setVisibility(View.INVISIBLE);
        }
        if(!(LoginActivity.checkEmail(email)||email.getBytes().length==0)){ // 이메일 형식이 아닐경우
            checkEmail.setVisibility(View.VISIBLE);
            cnt++;
        }else{
            checkEmail.setVisibility(View.INVISIBLE);
        }
        if(pass.length() != 4 && pass.length() != 0 ){ // 비밀번호가 4자리 이상 인 경우
            checkPass.setVisibility(View.VISIBLE);
            cnt++;
        }else{
            checkPass.setVisibility(View.INVISIBLE);
        }
        if(!passC.equals(password.getText().toString())){ // 비밀번호와 일치하지 않을 경우
            checkPassC.setVisibility(View.VISIBLE);
            cnt++;
        }else{
            checkPassC.setVisibility(View.INVISIBLE);
        }
        if(cnt==0)return true;
        else return false;
    }
    private boolean CompareDB (String name, String Id, String Pass){
        SharedPreferences pre = getSharedPreferences("DB",MODE_PRIVATE);
        if(!pre.getString("G_NAME","g_name error").equals(name)){
            Toast.makeText(getApplicationContext(),"이름이 일치하지 않습니다",Toast.LENGTH_SHORT).show();
            return false;
        }else if(!pre.getString("G_ID","g_id error").equals(Id)){
            Toast.makeText(getApplicationContext(),"이메일이 일치하지 않습니다",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            SharedPreferences.Editor editor = pre.edit();
            editor.putString("G_PASS",Pass);
            editor.apply();
            return true;
        }

    }
        private void InitView(){
        modBtn = (Button)findViewById(R.id.modEnter); // 수정완료 버튼
        password = (EditText)findViewById(R.id.modPass); // 비밀번호
        passConf = (EditText)findViewById(R.id.modPassConf); // 비밀번호 확인
        email = (EditText)findViewById(R.id.modEmail); // 이메일
        name = (EditText)findViewById(R.id.modName); // 이름
        backLayout = (ConstraintLayout)findViewById(R.id.modBack);// 뒷배경
        backBtn = (ImageView)findViewById(R.id.btn_toolbar_back); // 툴바 내 뒤로가기 이미지 버튼
        toolbarTitle = (TextView)findViewById(R.id.text_toolbar); // 툴바 제목
        checkName = (TextView)findViewById(R.id.checkName); // 이름 경고창
        checkEmail = (TextView)findViewById(R.id.checkEmail); // 이메일 경고창
        checkPass = (TextView)findViewById(R.id.checkPass);  // 비밀번호 경고창
        checkPassC = (TextView)findViewById(R.id.checkPassC); // 비밀번호 확인 경고창

        toolbarTitle.setText("비밀번호 수정");
        checkEmail.setVisibility(View.INVISIBLE);
        checkName.setVisibility(View.INVISIBLE);
        checkPass.setVisibility(View.INVISIBLE);
        checkPassC.setVisibility(View.INVISIBLE);
    }
}
