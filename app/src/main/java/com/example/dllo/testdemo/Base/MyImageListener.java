package com.example.dllo.testdemo.base;

import android.graphics.Bitmap;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.testdemo.R;

/**
 * Created by dllo on 16/9/23.
 */
public class MyImageListener implements ImageLoader.ImageListener{
    private ImageView imageView;
    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        Bitmap bitmap = response.getBitmap();
        if (bitmap == null){
            imageView.setImageResource(R.mipmap.ic_launcher);
        }else {
            CircleDrawable circleDrawable =new CircleDrawable(bitmap);
            imageView.setImageBitmap(bitmap);
            AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
            alphaAnimation.setDuration(5000);
            imageView.setAnimation(alphaAnimation);
            alphaAnimation.start();
        }

    }

    public MyImageListener(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        imageView.setImageResource(R.mipmap.ic_launcher);

    }
}