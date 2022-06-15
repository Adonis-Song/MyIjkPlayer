package com.luoye.bzijkplayer;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;


import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IJKPlayerTestActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private static final String TAG = "bz_IJKPlayerTest";
    private SurfaceView texture_view;
    private Surface surface;
    private IjkMediaPlayer ijkMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijkplayer_test);
        texture_view = findViewById(R.id.texture_view);
        texture_view.getHolder().addCallback(this);
//        texture_view.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
//            @Override
//            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
//                IJKPlayerTestActivity.this.surface = new Surface(surfaceTexture);
//            }
//
//            @Override
//            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
//
//            }
//
//            @Override
//            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
//                return false;
//            }
//
//            @Override
//            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//
//            }
//        });
    }

    public void startPlay(View view) {
        if (null == surface) {
            Log.d(TAG, "null == surface");
            return;
        }
        if (null == ijkMediaPlayer) {
            ijkMediaPlayer = new IjkMediaPlayer();
        } else {
            ijkMediaPlayer.reset();
        }
        try {
            ijkMediaPlayer.setSurface(surface);
            ijkMediaPlayer.setDataSource("/sdcard/DCIM/Camera/song.mp4");
            ijkMediaPlayer.setOnPreparedListener(IMediaPlayer::start);
            ijkMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != ijkMediaPlayer) {
            ijkMediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != ijkMediaPlayer) {
            ijkMediaPlayer.stop();
            ijkMediaPlayer.release();
        }
    }

    public void startActivity(View view) {
        startActivity(new Intent(this, IJKPlayerTestActivity.class));
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        IJKPlayerTestActivity.this.surface = holder.getSurface();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        IJKPlayerTestActivity.this.surface = holder.getSurface();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        IJKPlayerTestActivity.this.surface = null;

    }
}
