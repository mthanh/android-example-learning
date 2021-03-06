package com.example.androidvideoview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView videoview;
    private int position = 0;
    private MediaController mediaController;
    private Button buttonRaw;
    private Button buttonLocal;
    private Button buttonURL;


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find
        this.videoview = (VideoView)findViewById(R.id.videoView);
        this.buttonLocal = (Button)findViewById(R.id.button_local);
        this.buttonRaw = (Button) findViewById(R.id.button_raw);
        this.buttonURL = (Button) findViewById(R.id.button_url);

        this.textView = (TextView) findViewById(R.id.textView);



        //set the media control button
        if(this.mediaController==null){
            this.mediaController = new MediaController(MainActivity.this);

            //set videoView as the anchor for MediaController
            this.mediaController.setAnchorView(videoview);

            //Set MediaControlled for VideoView
            this.videoview.setMediaController(mediaController);

            textView.setText("HAHAHA");


            String vid_url2 = "http://www.youtube.com/watch?v=QnOcXQL2wDA";
            String vid_url = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"; //
            Uri video = Uri.parse(vid_url2);
            videoview.setVideoURI(video);


//            try {
////                MediaController mediacontroller = new android.widget.MediaController(this);
//                this.mediaController.setAnchorView(videoview);
//                Uri video = Uri.parse(vid_url);
//                videoview.setMediaController(this.mediaController);
//                videoview.setVideoURI(video);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
        }

        //when video screen change size
        this.videoview.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoview.seekTo(position);
                if(position==0){
                    videoview.start();
                }

                //when the screen change size
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        //re-set the video View
                        mediaController.setAnchorView(videoview);
                    }
                });
            }
        });

        //set cac button
        this.buttonRaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resName = VideoViewUtils.RAW_VIDEO_SAMPLE;
                VideoViewUtils.playRawVideo(MainActivity.this, videoview, resName);
            }
        });

        this.buttonLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String localPath = VideoViewUtils.LOCAL_VIDEO_SAMPLE;
                VideoViewUtils.playRawVideo(MainActivity.this, videoview, localPath);
            }
        });

        this.buttonURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoURL = VideoViewUtils.URL_VIDEO_SAMPLE;
                VideoViewUtils.playURLVideo(MainActivity.this, videoview, textView, videoURL);
            }
        });



    }


    // When you change direction of phone, this method will be called.
    // It store the state of video (Current position)
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Store current position.
        savedInstanceState.putInt("CurrentPosition", videoview.getCurrentPosition());
        videoview.pause();
    }


    // After rotating the phone. This method is called.
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Get saved position.
        position = savedInstanceState.getInt("CurrentPosition");
        videoview.seekTo(position);
    }



}