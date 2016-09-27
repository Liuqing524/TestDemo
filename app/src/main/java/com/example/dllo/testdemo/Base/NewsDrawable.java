package com.example.dllo.testdemo.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by dllo on 16/9/23.
 */
public class NewsDrawable extends BitmapDrawable{
    public Bitmap bitmap;

    @Override
    public void draw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap,0,0,getPaint());
        }
    }
}
