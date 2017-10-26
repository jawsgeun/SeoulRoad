package com.kkard.seoulroad;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by SuGeun on 2017-10-26.
 */

public class ApplicationBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .add("Regular",Typekit.createFromAsset(this,"NotoSansCJKkr-Regular.otf"))
                .addBold(Typekit.createFromAsset(this,"NotoSansCJKkr-Bold.otf"))
                .add("DemiLight",Typekit.createFromAsset(this,"NotoSansCJKkr-DemiLight.otf"))
                .add("Light",Typekit.createFromAsset(this,"NotoSansCJKkr-Light.otf"))
                .add("Medium",Typekit.createFromAsset(this,"NotoSansCJKkr-Medium.otf"));
    }
}
