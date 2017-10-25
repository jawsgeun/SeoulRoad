package com.kkard.seoulroad.Circleindicator_C;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kkard.seoulroad.R;
import com.kkard.seoulroad.utils.DialogView_C;

/**
 * Created by KyungHWan on 2017-09-20.
 */

public class IndicatorAdapter extends PagerAdapter {
    private int mSize;
    private DialogView_C mDialog;
    private Context mcontext;

    public IndicatorAdapter(Context context) {
        mSize = 3;
        mcontext = context;
    }

    @Override public int getCount() {
        return mSize;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override public Object instantiateItem(ViewGroup view, int position) {
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageResource(R.drawable.test_pager);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(mPagerListener);
        view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        return imageView;
    }
    private View.OnClickListener mPagerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDialog = new DialogView_C(v.getContext(),-1,"몇번째 방문자입니다.","아이디@이메일.com","xxx개","주저리주저리"); // 페이저 눌렀을때 다이얼로그 전달
            mDialog.show();
        }
    };
}
