package com.example.dllo.testdemo.base;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/9/23.
 */
public class MemoryCache implements ImageLoader.ImageCache{
    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {
        int maxSize = (int) ((Runtime.getRuntime().maxMemory()) / 8);
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}