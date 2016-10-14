package com.example.dllo.testdemo.base;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by dllo on 16/9/28.
 */
public class CircleDrawable extends Drawable{
    private Bitmap bitmap;
    private Paint paint;
    private int r;

    public CircleDrawable(Bitmap bitmap) {
        this.bitmap = bitmap;
        r = Math.min(bitmap.getWidth(),bitmap.getHeight()) / 2;
        paint = new Paint();
        paint.setAntiAlias(true);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(r,r,r,paint);

    }

    @Override
    public void setAlpha(int i) {
        paint.setAlpha(i);

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);

    }

    @Override
    public int getIntrinsicWidth() {
        return r * 2;
    }

    @Override
    public int getIntrinsicHeight() {
        return r * 2;
    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
