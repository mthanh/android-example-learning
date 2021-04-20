package com.example.videoview1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

//import android.media.session.MediaController;

//public class MainActivity extends AppCompatActivity {
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        VideoView videoView = findViewById(R.id.videoView);
//        videoView.setVideoPath("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
//        videoView.start();
//
//    }
//}


public class MainActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    VideoView videoview;

    String vid_url2 = "http://www.youtube.com/watch?v=QnOcXQL2wDA";
    String vid_url = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"; //"https://www.youtube.com/watch?v=QnOcXQL2wDA&t=18s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        // String vid_url = intent.getStringExtra("video_url");
        videoview = (VideoView) findViewById(R.id.videoView);
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Video Stream");
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();


        try {
            MediaController mediacontroller = new android.widget.MediaController(this);
            mediacontroller.setAnchorView(videoview);
            Uri video = Uri.parse(vid_url2);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                MainActivity.this.pDialog.dismiss();
                MainActivity.this.videoview.start();
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });


    }
}