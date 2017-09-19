package com.kkard.seoulroad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SuGeun on 2017-08-08.
 */

public class LoginActivity extends Activity {
    /////////////// 뷰 객체 선언 /////////////
    EditText email, pass;
    Button loginBtn;
    TextView joinBtn;
    ConstraintLayout backLayout;
    CheckBox autoLogin;
    //////////////////////////////////////////

    /////////////////////// 변수 선언 //////////////////////
    boolean emailFlag= false; // 처음으로 이메일 입력창 누르는지
    boolean passFlag= false; // 처음으로 비번 입력창 누르는지
    boolean anyPlay=false; // 이메일이나 비번을 입력중인지
    //////////////////////////////////////////

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
                //if ((email.getText().toString().equals("tnrms")) && (pass.getText().toString().equals("123"))) {
                    startActivity(new Intent(LoginActivity.this, FragmentActivity.class));
                    finish();
               // } else {
                    Toast.makeText(getApplicationContext(), "다시 입력하시기 바랍니다.", Toast.LENGTH_SHORT).show();
             //   }
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
        super.onBackPressed();
            AlertDialog.Builder aDB = new AlertDialog.Builder(this);
            aDB.setTitle("서울로드 종료").setMessage("종료하시겠습니까?").setCancelable(false);
            aDB.setPositiveButton("종료",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.this.finish();
                        }
                    })
                    .setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                    .setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if(keyCode == KeyEvent.KEYCODE_BACK){
                                finish();
                                dialog.dismiss();
                            }
                            return false;
                        }
                    });
       // if(!anyPlay){// 아무것도 안 켜져 있으면
            //어플리케이션 종료 다이얼로그 띄우기
            AlertDialog aD = aDB.create();
            aD.show();
       // }
    }
}
