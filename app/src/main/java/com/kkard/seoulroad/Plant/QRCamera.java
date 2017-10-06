package com.kkard.seoulroad.Plant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.kkard.seoulroad.R;

/**
 * Created by KyungHWan on 2017-09-20.
 */

public class QRCamera extends Activity{
    private IntentIntegrator qrscanner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcamera);
        qrscanner = new IntentIntegrator(this);
        qrscanner.setCaptureActivity(CaptureActivityAnyOrientation.class);
        qrscanner.setOrientationLocked(true);
        qrscanner.initiateScan();
    }
}
