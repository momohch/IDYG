package com.example.momo_2.finalapplication_20151222;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class ReadyActivity extends Activity {
    int m;
    String[] paths;
    MediaPlayer click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        click = MediaPlayer.create(this, R.raw.click);
        //接收資料
        Intent it = getIntent();
        m = it.getIntExtra("資料", m);
        paths = it.getStringArrayExtra("路徑");
        m = m-1;

    }
    public void already(View v){
        if(m>0){
            click.start();
            Intent it = new Intent(this, DrawNextActivity.class);
            it.putExtra("資料", m);
            it.putExtra("路徑",paths);
            startActivity(it);


    }if(m<=0){
            click.start();
            Intent it = new Intent(this, GuessActivity.class);
            it.putExtra("路徑",paths);
            startActivity(it);
        }
    }


    //禁止按返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }
}
