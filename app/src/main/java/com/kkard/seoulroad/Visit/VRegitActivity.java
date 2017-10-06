package com.kkard.seoulroad.Visit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.kkard.seoulroad.R;
import com.kkard.seoulroad.utils.DialogView_C;

import java.io.File;

/**
 * Created by KyungHWan on 2017-10-06.
 */

public class VRegitActivity extends AppCompatActivity {
    private ImageView imageView;
    private DialogView_C mdialog;
    private Context mcontext;
    private Uri mImageCaptureUri;
    private final int PICK_FROM_CAMERA = 0;
    private final int PICK_FROM_ALBUM = 1;
    private final int CROP_FROM_IMAGE = 2;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vregit);
        imageView = (ImageView)findViewById(R.id.regit_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdialog = new DialogView_C(v.getContext(),"사진선택",leftClickListener,middleClickListener,rightClickListener);
                mdialog.show();
            }
        });

    }
    private View.OnClickListener leftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doTakePhotoAction();
        }
    };
    private View.OnClickListener middleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doTakeAlbumAction();
        }
    };
    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mdialog.dismiss();
        }
    };

    private void doTakePhotoAction() {
        Intent itn = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String url = "tmp_"+String.valueOf(System.currentTimeMillis())+".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));

        itn.putExtra(MediaStore.EXTRA_OUTPUT,mImageCaptureUri);
        startActivityForResult(itn,PICK_FROM_CAMERA);
    }

    private void doTakeAlbumAction() {
        Intent itn = new Intent(Intent.ACTION_PICK);
        itn.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(itn,PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK)
            return;
        switch (requestCode){
            case PICK_FROM_ALBUM:
                mImageCaptureUri = data.getData();
            case PICK_FROM_CAMERA:
                Intent itn = new Intent("com.android.camera.action.CROP");
                itn.setDataAndType(mImageCaptureUri,"image/*");

                //사진크기 300*300으로 자르기
                itn.putExtra("outputX",300);
                itn.putExtra("outputY",300);
                itn.putExtra("aspectX",1);
                itn.putExtra("aspectX",1);
                itn.putExtra("scale",true);
                itn.putExtra("return-data",true);
                startActivityForResult(itn,CROP_FROM_IMAGE);
                break;
            case CROP_FROM_IMAGE:
                if(resultCode != RESULT_OK){
                    return;
                }
                final Bundle extras = data.getExtras();
                String filepath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SmartWheel/"+ System.currentTimeMillis()+".jpg";
                if(extras !=null){
                    Bitmap photo = extras.getParcelable("data");
                    imageView.setImageBitmap(photo);
                    break;
                }
                File f = new File(mImageCaptureUri.getPath());
                if(f.exists())f.delete();
        }
    }

}