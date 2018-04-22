package com.example.momo_2.finalapplication_20151222;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {
    MediaPlayer mpLogo;
    MediaPlayer click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mpLogo = MediaPlayer.create(this, R.raw.logo_sound);
        click = MediaPlayer.create(this, R.raw.click);
        mpLogo.start();
        /*new CountDownTimer(2000,1000){

            @Override
            public void onFinish() {
                mpLogo.stop();
                go_numpeo();
               //go();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                //ctime.setText(""+millisUntilFinished/1000);
            }

        }.start();*/
        //Sound

    }


    //遊戲開始按鈕
    public void go_numpeo(View v) {
        Intent it = new Intent(this, NumPeoActivity.class);
        startActivity(it);
        click.start();
    }


}
