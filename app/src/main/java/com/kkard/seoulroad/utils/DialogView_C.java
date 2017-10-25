package com.kkard.seoulroad.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kkard.seoulroad.R;

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

    private Button mLikeButton;
    private Button mRightButton;
    private Button mMiddleButton;

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

    private boolean dialogTypeFlag = true;
    private boolean isClickLike = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        if(dialogTypeFlag) { // 이미지 클릭 다이얼로그
            setContentView(R.layout.activity_c_dialogview);
            setLayout();
            setTitle(mTitle);
            setContent(mContent);
            setId(mId);
            setCount(mCount);
            setImage(mImage);

            mLikeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int mcnt;
                    String cnt = mLikeCountView.getText().toString();
                    cnt = cnt.substring(0,cnt.length()-1);
                    mcnt = Integer.parseInt(cnt);
                    if(isClickLike) { // 좋아요가 눌려있으면
                        mLikeButton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.love_btn_black));
                        isClickLike = false;
                        mcnt--; mLikeCountView.setText(Integer.toString(mcnt)+"명");
                    }else{
                        mLikeButton.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.love_btn));
                        isClickLike = true;
                        mcnt++; mLikeCountView.setText(Integer.toString(mcnt)+"명");}
                }
            });
        }
        else { // 버튼 3개 다이얼로그
            setContentView(R.layout.activity_regit_dialog);
            setLayout(dialogTypeFlag);
            setTitle(mTitle);
            setClickListener(mLeftClickListener ,mMiddleClickListener ,mRightClickListener);
        }

    }
// 이미지 클릭시 생성자
    public DialogView_C(Context context,int image, String title, String id,String count,String content) {
        // Dialog 배경을 투명 처리 해준다.
        super(context,android.R.style.Theme_Translucent_NoTitleBar);
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
        dialogTypeFlag = false;
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

    private void setTitle(String title){mTitleView.setText(title);}
    private void setContent(String content){
        mContentView.setText(content);
    }
    private void setCount(String count){mContentView.setText(count);}
    private void setId(String id){mIdView.setText(id);}
    private void setImage(int id){if(id != -1)mImageView.setImageResource(id);}

    private void setClickListener(View.OnClickListener left , View.OnClickListener right){
        if(left!=null && right!=null){
            mLikeButton.setOnClickListener(left);
            mRightButton.setOnClickListener(right);
        }else if(left!=null && right==null){
            mLikeButton.setOnClickListener(left);
        }else {

        }
    }
    private void setClickListener(View.OnClickListener left , View.OnClickListener middle ,View.OnClickListener right){
        mLikeButton.setOnClickListener(left);
        mMiddleButton.setOnClickListener(middle);
        mRightButton.setOnClickListener(right);
    }

    /*
     * Layout
     */
    // 이미지 클릭 초기화 함수
    private void setLayout(){
        mTitleView = (TextView) findViewById(R.id.dia_title);
        mContentView = (TextView) findViewById(R.id.dia_content);
        mLikeCountView = (TextView) findViewById(R.id.cnt_like);
        mIdView = (TextView)findViewById(R.id.dia_id);
        mImageView = (ImageView)findViewById(R.id.image_dialog);
        mLikeButton = (Button) findViewById(R.id.btn_heart);
        mXBtn = (ImageView)findViewById(R.id.dia_x_btn);
        back = (RelativeLayout)findViewById(R.id.dialog_back);
        inside = (LinearLayout)findViewById(R.id.dialog_inside);
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
    }
    // 버튼 세개 초기화 함수
    private void setLayout(boolean flag){
        mTitleView = (TextView) findViewById(R.id.up_title);
        mLikeButton = (Button) findViewById(R.id.takepicture);
        mMiddleButton = (Button) findViewById(R.id.fromgallery);
        mRightButton = (Button) findViewById(R.id.upcancel);
    }


}
