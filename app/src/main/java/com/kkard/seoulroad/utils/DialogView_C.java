package com.kkard.seoulroad.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkard.seoulroad.R;

/**
 * Created by KyungHWan on 2017-10-05.
 */

public class DialogView_C extends Dialog {

    private TextView mTitleView;
    private TextView mContentView;
    private TextView mIdView;
    private TextView mCountView;
    private ImageView mImageView;

    private Button mLeftButton;
    private Button mRightButton;
    private Button mMiddleButton;

    private int mImage;
    private String mTitle;
    private String mContent;
    private String mCount;
    private String mId;

    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;
    private View.OnClickListener mMiddleClickListener;

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        if(flag) { // 이미지 클릭 다이얼로그
            setContentView(R.layout.activity_c_dialogview);
            setLayout();
            setTitle(mTitle);
            setContent(mContent);
            setId(mId);
            setCount(mCount);
            setImage(mImage);
            //setClickListener(mLeftClickListener , mRightClickListener);
        }
        else { // 버튼 3개 다이얼로그
            setContentView(R.layout.activity_regit_dialog);
            setLayout(flag);
            setTitle(mTitle);
            setClickListener(mLeftClickListener ,mMiddleClickListener ,mRightClickListener);
        }

    }
// 이미지 클릭시 생성자
    public DialogView_C(Context context,int image, String title, String id,String count,String content) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mImage = image;
        mTitle = title;
        mId = id;
        mCount = count;
        mContent = content;
    }

    public DialogView_C(Context context , String title ,
                        View.OnClickListener singleListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = singleListener;
    }

    public DialogView_C(Context context , String title , String content ,
                        View.OnClickListener leftListener , View.OnClickListener rightListener) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);

        this.mTitle = title;
        this.mContent = content;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;
    }
    // 버튼 3개 생성자
    public DialogView_C (Context context, String title, View.OnClickListener photoClick , View.OnClickListener galleryClick, View.OnClickListener cancelClick){
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
        this.mLeftClickListener = photoClick;
        this.mMiddleClickListener = galleryClick;
        this.mRightClickListener = cancelClick;
        flag = false;
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            // Tapped outside so we finish the activity
            this.dismiss();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void setTitle(String title){
        mTitleView.setText(title);
    }
    private void setContent(String content){
        mContentView.setText(content);
    }
    private void setCount(String count){mContentView.setText(count);}
    private void setId(String id){mIdView.setText(id);}
    private void setImage(int id){if(id != -1)mImageView.setImageResource(id);}

    private void setClickListener(View.OnClickListener left , View.OnClickListener right){
        if(left!=null && right!=null){
            mLeftButton.setOnClickListener(left);
            mRightButton.setOnClickListener(right);
        }else if(left!=null && right==null){
            mLeftButton.setOnClickListener(left);
        }else {

        }
    }
    private void setClickListener(View.OnClickListener left , View.OnClickListener middle ,View.OnClickListener right){
        mLeftButton.setOnClickListener(left);
        mMiddleButton.setOnClickListener(middle);
        mRightButton.setOnClickListener(right);
    }

    /*
     * Layout
     */
    // 이미지 클릭 초기화 함수
    private void setLayout(){
        mTitleView = (TextView) findViewById(R.id.tv_title);
        mContentView = (TextView) findViewById(R.id.tv_content);
        mCountView = (TextView) findViewById(R.id.tv_heart_cnt);
        mIdView = (TextView)findViewById(R.id.tv_id_dia);
        mImageView = (ImageView)findViewById(R.id.image_dialog);
        mLeftButton = (Button) findViewById(R.id.btn_heart);
    }
    // 버튼 세개 초기화 함수
    private void setLayout(boolean flag){
        mTitleView = (TextView) findViewById(R.id.up_title);
        mLeftButton = (Button) findViewById(R.id.takepicture);
        mMiddleButton = (Button) findViewById(R.id.fromgallery);
        mRightButton = (Button) findViewById(R.id.upcancel);
    }


}
