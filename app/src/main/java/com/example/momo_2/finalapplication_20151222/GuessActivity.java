package com.example.momo_2.finalapplication_20151222;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class GuessActivity extends Activity {
    ImageView img;
    TextView ctime;
    String[] paths;
    MediaPlayer click;
    MediaPlayer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        ctime = (TextView)findViewById(R.id.ctime);
        click = MediaPlayer.create(this, R.raw.click);
        count = MediaPlayer.create(this, R.raw.count);
        //倒數聲音
        count.start();
        count.setLooping(true);
        //倒數計時器
        CountDownTimer CDTime = new CountDownTimer(15000,1000){

            @Override
            public void onFinish() {
                ctime.setText("Time's up");
                count.stop();
                coverpic();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                ctime.setText(""+millisUntilFinished/1000);
            }

        };
        CDTime.start();
        //接收資料
        Intent it = getIntent();
        paths = it.getStringArrayExtra("路徑");
        String filepath = paths[1];
        img = (ImageView) findViewById(R.id.imv);
        File file = new File(filepath);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(filepath);
            //將圖片顯示在ImageView中
            img.setImageBitmap(bm);
        }
    }
    public void coverpic(){
           TextView tex = (TextView) findViewById(R.id.pad);
            //float curTranslationX = img.getTranslationX();
            ObjectAnimator animatorA = ObjectAnimator.ofFloat(tex, "alpha", 0f, 1f);
            animatorA.setDuration(1000);
            animatorA.start();
       /* TextView tvx = (TextView) findViewById(R.id.pad);
        tvx.setBackgroundColor(Color.rgb(0, 0, 0));*/
    }

    public void OK(View v){
        count.stop();
        click.start();
        Intent it = new Intent(this, OKActivity.class);
        it.putExtra("路徑",paths);
        startActivity(it);
    }

    public void NO(View v){
        count.stop();
        click.start();
        Intent it = new Intent(this, NOActivity.class);
        it.putExtra("路徑",paths);
        startActivity(it);
    }


    //禁止按返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }
}
