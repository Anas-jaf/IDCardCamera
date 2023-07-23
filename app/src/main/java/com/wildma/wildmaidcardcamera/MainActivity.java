package com.wildma.wildmaidcardcamera;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.wildma.idcardcamera.camera.IDCardCamera;

/**
 * Author   wildma
 * Github   https://github.com/wildma
 * Date     2018/6/24
 * Desc     ${身份证相机使用例子}
 */
public class MainActivity extends AppCompatActivity {
    private ImageView mIvFront;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvFront = (ImageView) findViewById(R.id.iv_front);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
    }

    /**
     * ID card front
     */
    public void front(View view) {
        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_FRONT);
    }

    /**
     * reverse side of ID card
     */
    public void back(View view) {
        IDCardCamera.create(this).openCamera(IDCardCamera.TYPE_IDCARD_BACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == IDCardCamera.RESULT_CODE) {
            //Get the image path and display the image
            final String path = IDCardCamera.getImagePath(data);
            if (!TextUtils.isEmpty(path)) {
                if (requestCode == IDCardCamera.TYPE_IDCARD_FRONT) { //ID card front
                    mIvFront.setImageBitmap(BitmapFactory.decodeFile(path));
                } else if (requestCode == IDCardCamera.TYPE_IDCARD_BACK) {  //reverse side of ID card
                    mIvBack.setImageBitmap(BitmapFactory.decodeFile(path));
                }

                //In actual development, after uploading pictures to the server successfully, all cached pictures need to be deleted
//                FileUtils.clearCache(this);
            }
        }
    }
}
