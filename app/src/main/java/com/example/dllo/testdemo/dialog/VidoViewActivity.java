package com.example.dllo.testdemo.dialog;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

/**
 * Created by dllo on 16/10/10.
 */
public class VidoViewActivity extends BaseAty implements UniversalVideoView.VideoViewCallback{
    private View mVideoLyout , mBtnLayout;
    private UniversalMediaController mMediaController;
    private UniversalVideoView mVideoView;
    private TextView mStart;
    private Button mClose;

    private static final String TAG = "VidoViewActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://www.yinyuetai.com/mv/video-url/2685828";

    private int mSeekpostion;
    private int cacheHeight;
    private boolean isFullscreen;

    @Override
    protected int setLayout() {
        return R.layout.vido_view_activity;
    }

    @Override
    protected void initView() {
        mVideoLyout = bindView(R.id.video_layout);
        mMediaController = bindView(R.id.media_controllar);
        mVideoView = bindView(R.id.video_View);
        mBtnLayout = bindView(R.id.btn_layout);
        mStart = bindView(R.id.video_start);
        mClose = bindView(R.id.video_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSeekpostion > 0) {
                    mVideoView.seekTo(mSeekpostion);
                }
                mVideoView.start();
                mMediaController.setTitle("小视频");
            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

    }
    private void setVideoAreaSize() {
        mVideoLyout.post(new Runnable() {

            @Override
            public void run() {
                int width = mVideoLyout.getWidth();
                cacheHeight = (int) (width * 405f / 720f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLyout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cacheHeight;
                mVideoLyout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(VIDEO_URL);
                mVideoView.requestFocus();
            }
        });


    }

    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mMediaController.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoView.setLayoutParams(layoutParams);
            mBtnLayout.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLyout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cacheHeight;
            mVideoView.setLayoutParams(layoutParams);
            mBtnLayout.setVisibility(View.VISIBLE);
        }
        switchTitileBar(isFullscreen);

    }
    private void switchTitileBar(boolean isFullscreen) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (isFullscreen) {
            actionBar.show();
        } else {
            actionBar.hide();
        }

    }

    @Override
    public void onBackPressed() {
        if (isFullscreen) {
            mVideoView.setFullscreen(false);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SEEK_POSITION_KEY , mSeekpostion);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mSeekpostion = savedInstanceState.getInt(SEEK_POSITION_KEY);
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {

    }
}
