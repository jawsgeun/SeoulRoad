package com.kkard.seoulroad.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kkard.seoulroad.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by KyungHWan on 2017-10-05.
 */

public class DialogView_C extends Dialog {

    private TextView mTitleView;
    private TextView mContentView;
    private TextView mIdView;
    private TextView mLikeCountView;
    private ImageView mImageView;
    private ImageView mXBtn;
    private TextView mDateView;

    private ImageButton mLikeButton;
    private ImageButton mMiddleButton;
    private Button confBtn;
    private Button cancleBtn;

    private RelativeLayout back;
    private LinearLayout inside;

    private int mImage;
    private String mTitle;
    private String mContent;
    private String mCount;
    private String mId;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;
    private View.OnClickListener mMiddleClickListener;

    public final static int DIA_TYPE_IMAGE = 90;
    public final static int DIA_TYPE_CAMERA = 91;
    public final static int DIA_TYPE_MOD = 92;
    public final static int DIA_TYPE_MOD_CONF = 93;
    public final static int DIA_TYPE_TEST = 94;

    private int type;
    private boolean isClickLike = false;


    ////
    private String u_index_id;
    private String u_email_id;
    private String heart_toggle;
    private String p_name;
    private String count;
    private String date;
    private String photo_index_id;
    private String content;
    private String imgUrl = "http://stou2.cafe24.com/image/";
    ///

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        switch (type) {
            case DIA_TYPE_IMAGE:
                setContentView(R.layout.activity_c_dialogview);
                setLayout(type);
                setTitle(mTitle);
                setContent(mContent);
                setId(mId);
                setCount(mCount);
                setImage(mImage);
                break;
            case DIA_TYPE_CAMERA:
                setContentView(R.layout.activity_regit_dialog);
                setLayout(type);
                setClickListener(mLeftClickListener, mMiddleClickListener, mRightClickListener);
                break;
            case DIA_TYPE_MOD:
                setContentView(R.layout.layout_dialog_modify);
                setLayout(type);
                break;
            case DIA_TYPE_MOD_CONF:
                setContentView(R.layout.layout_dialog_modify_conf);
                setLayout(type);
                break;
            case DIA_TYPE_TEST:
                setContentView(R.layout.activity_c_dialogview);
                break;
        }
    }

    public DialogView_C(int type , final Context context, final String u_index_id, final String photo_id){
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.type = type;
        this.u_index_id = u_index_id;
        this.photo_index_id = photo_id;

        new AsyncTask<Void,Void,String>(){
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("기달려봐");
                progressDialog.show();
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(Void... voids) {
                RequestHttpConnection rhc = new RequestHttpConnection();
                BufferedReader br = rhc.requestImageInfo("http://stou2.cafe24.com/php/imageinfodown.php", u_index_id, photo_id);//앞숫자가 유저인덱스아이디, 뒷숫자가 포토아이디
                String json;
                StringBuilder sb = new StringBuilder();
                try {
                    while ((json = br.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                }catch(IOException e){}
                String myJson =sb.toString().trim();
                getimageinfo(myJson);
                return sb.toString().trim();
            }

            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    setlayout1(94);
                }
            }

        }.execute();

    }

    // 이미지 클릭시 생성자
    public DialogView_C(int type, Context context, int image, String title, String id, String count, String content) {
        // Dialog 배경을 투명 처리 해준다.
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.type = type;
        mImage = image;
        mTitle = title;
        mId = id;
        mCount = count;
        mContent = content;
    }

    // 버튼 3개 생성자
    public DialogView_C(int type, Context context, View.OnClickListener photoClick, View.OnClickListener galleryClick, View.OnClickListener cancelClick) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.type = type;
        this.mLeftClickListener = photoClick;
        this.mMiddleClickListener = galleryClick;
        this.mRightClickListener = cancelClick;
    }

    // 수정 미완료 생성자, 수정 완료 생성자
    public DialogView_C(int type, Context context, View.OnClickListener confClick) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.type = type;
        this.mLeftClickListener = confClick;
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            // Tapped outside so we finish the activity
            new AsyncTask<Void,Void,Void>(){
                @Override
                protected Void doInBackground(Void... voids) {//좋아요 갯수랑 좋아요 눌렀는지 업데이트
                    RequestHttpConnection rhc = new RequestHttpConnection();
                    rhc.updateLike("http://stou2.cafe24.com/php/likeupdate.php",u_index_id,photo_index_id,heart_toggle,count);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    dismiss();
                }
            }.execute();

        }
        return super.dispatchTouchEvent(ev);
    }

    private void setTitle(String title) {
        mTitleView.setText(title);
    }

    private void setContent(String content) {
        mContentView.setText(content);
    }

    private void setCount(String count) {
        mContentView.setText(count);
    }

    private void setId(String id) {
        mIdView.setText(id);
    }

    private void setImage(int id) {
        if (id != -1) mImageView.setImageResource(id);
    }

    private void setClickListener(View.OnClickListener left, View.OnClickListener middle, View.OnClickListener right) {
                mLikeButton.setOnClickListener(left);
                mMiddleButton.setOnClickListener(middle);
                mXBtn.setOnClickListener(right);
        }

    public void setlayout1(int type){
        mTitleView = (TextView) findViewById(R.id.dia_title);
        mTitleView.setText(photo_index_id+"번째 방문자입니다 !");
        mContentView = (TextView) findViewById(R.id.dia_content);
        mContentView.setText(content);
        mLikeCountView = (TextView) findViewById(R.id.cnt_like);
        mLikeCountView.setText(count+"명");
        mIdView = (TextView) findViewById(R.id.dia_id);
        mIdView.setText(u_email_id);
        mImageView = (ImageView) findViewById(R.id.image_dialog);
        Picasso.with(getContext()).load(imgUrl).into(mImageView);
        mDateView = (TextView)findViewById(R.id.date_tv);
        date = date.substring(0,10);
        mDateView.setText(date);
        mLikeButton = (ImageButton) findViewById(R.id.btn_heart);
        if (heart_toggle.equals("1")){
            mLikeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.love_btn));
        }else{
            mLikeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.love_btn_black));
        }
        mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mcnt;
                String cnt = mLikeCountView.getText().toString();
                cnt = cnt.substring(0, cnt.length() - 1);
                mcnt = Integer.parseInt(cnt);
                if (heart_toggle.equals("1")) { // 좋아요가 눌려있으면
                    mLikeButton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.love_btn_black));
                    heart_toggle = "0";
                    mcnt--;
                    count = Integer.toString(mcnt);
                    mLikeCountView.setText(Integer.toString(mcnt) + "명");
                } else {
                    mLikeButton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.love_btn));
                    heart_toggle = "1";
                    mcnt++;
                    count = Integer.toString(mcnt);
                    mLikeCountView.setText(Integer.toString(mcnt) + "명");
                }
            }
        });
        mXBtn = (ImageView) findViewById(R.id.dia_x_btn);
        back = (RelativeLayout) findViewById(R.id.dialog_back);
        inside = (LinearLayout) findViewById(R.id.dialog_inside);
        inside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아무것도 안함
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Void,Void>(){//좋아요 갯수랑 좋아요 눌렀는지 업데이트
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Log.d("heart",heart_toggle);
                        RequestHttpConnection rhc = new RequestHttpConnection();
                        rhc.updateLike("http://stou2.cafe24.com/php/likeupdate.php",u_index_id,photo_index_id,heart_toggle,count);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        dismiss();
                    }
                }.execute();

            }
        });
        mXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void,Void,Void>(){//좋아요 갯수랑 좋아요 눌렀는지 업데이트
                    @Override
                    protected Void doInBackground(Void... voids) {
                        RequestHttpConnection rhc = new RequestHttpConnection();
                        rhc.updateLike("http://stou2.cafe24.com/php/likeupdate.php",u_index_id,photo_index_id,heart_toggle,count);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        dismiss();
                    }
                }.execute();

            }
        });

    }
    /*
     * Layout
     */
    // 이미지 클릭 초기화 함수
    private void setLayout(int type) {
        switch (type) {
            case DIA_TYPE_IMAGE:
                mTitleView = (TextView) findViewById(R.id.dia_title);
                mContentView = (TextView) findViewById(R.id.dia_content);
                mLikeCountView = (TextView) findViewById(R.id.cnt_like);
                mIdView = (TextView) findViewById(R.id.dia_id);
                mImageView = (ImageView) findViewById(R.id.image_dialog);
                mLikeButton = (ImageButton) findViewById(R.id.btn_heart);
                mLikeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int mcnt;
                        String cnt = mLikeCountView.getText().toString();
                        cnt = cnt.substring(0, cnt.length() - 1);
                        mcnt = Integer.parseInt(cnt);
                        if (isClickLike) { // 좋아요가 눌려있으면
                            mLikeButton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.love_btn_black));
                            isClickLike = false;
                            mcnt--;
                            mLikeCountView.setText(Integer.toString(mcnt) + "명");
                        } else {
                            mLikeButton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.love_btn));
                            isClickLike = true;
                            mcnt++;
                            mLikeCountView.setText(Integer.toString(mcnt) + "명");
                        }
                    }
                });
                mXBtn = (ImageView) findViewById(R.id.dia_x_btn);
                back = (RelativeLayout) findViewById(R.id.dialog_back);
                inside = (LinearLayout) findViewById(R.id.dialog_inside);
                inside.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 아무것도 안함
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                mXBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                break;
            case DIA_TYPE_CAMERA:
                mLikeButton = (ImageButton) findViewById(R.id.takepicture);
                mMiddleButton = (ImageButton) findViewById(R.id.fromgallery);
                mXBtn = (ImageView) findViewById(R.id.camera_x_btn);
                back = (RelativeLayout) findViewById(R.id.camera_back);
                inside = (LinearLayout) findViewById(R.id.layout_camera);
                inside.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 아무것도 안함
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                break;
            case DIA_TYPE_MOD:
                confBtn = (Button)findViewById(R.id.mod_conf);
                confBtn.setOnClickListener(mLeftClickListener);
                cancleBtn = (Button)findViewById(R.id.mod_cancle);
                cancleBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
                break;
            case DIA_TYPE_MOD_CONF:
                confBtn = (Button)findViewById(R.id.modconf_conf);
                confBtn.setOnClickListener(mLeftClickListener);
                break;
        }
    }
    private void getimageinfo(String myJson){
        try{
            JSONObject jsonObject = new JSONObject(myJson);
            JSONArray res = jsonObject.getJSONArray("result");
            for(int i =0 ; i<res.length();i++){
                JSONObject c = res.getJSONObject(i);
                switch (i){
                    case 0:
                        heart_toggle = c.getString("created");
                        break;
                    case 1:
                        u_email_id = c.getString("u_email_id");
                        break;
                    case 2:
                        String p_name = c.getString("photo_name");
                        imgUrl = imgUrl+p_name;
                        break;
                    case 3:
                        count = c.getString("count");
                        break;
                    case 4:
                        date = c.getString("date");
                        break;
                    case 5:
                        content = c.getString("content");
                        break;
                    case 6:
                        photo_index_id = c.getString("photo_id");
                        break;
                }
            }

        }catch (JSONException e ){
            e.printStackTrace();
        }
    }
}
