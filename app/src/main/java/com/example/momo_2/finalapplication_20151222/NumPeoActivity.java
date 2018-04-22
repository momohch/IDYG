package com.example.momo_2.finalapplication_20151222;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class NumPeoActivity extends Activity implements AdapterView.OnItemSelectedListener{
    //static int[] idata = { 2, 3, 4, 5};
    int n;
    MediaPlayer click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_peo);
        click = MediaPlayer.create(this, R.raw.click);
        //Spinner spn = (Spinner)findViewById(R.id.spr);
        // 為 Spinner 指派 OnItemSelectedListener 偵聽器
        //spn.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView adapter, View v, int pos, long id) {
        /*TextView t = (TextView)findViewById(R.id.txv);
        String[] peo = getResources().getStringArray(R.array.peo);
        String s = peo[pos];
        n = Integer.parseInt(s);
        t.setText(s);*/
    }

    @Override
    public void onNothingSelected(AdapterView adapter) {
    }

    public void go_DO(View v) {
        /*if(click.isPlaying()==true){
            click.stop();}*/
        click.start();
        Intent it = new Intent(this, DrawOneActivity.class);
        it.putExtra("資料", n);
        startActivity(it);
    }

    //禁止按返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }

    public void peo2(View view) {
        /*if(click.isPlaying()==true){
            click.stop();}*/
        n = 2;

        click.start();
        TextView t = (TextView)findViewById(R.id.txv);
        String s = "兩人遊戲";
        t.setText(s);
    }
    public void peo3(View view) {
        /*if(click.isPlaying()==true){
            click.stop();}*/
        n = 3;
        click.start();
        TextView t = (TextView)findViewById(R.id.txv);
        String s = "三人遊戲";
        t.setText(s);
    }
    public void peo4(View view) {
        /*if(click.isPlaying()==true){
            click.stop();}*/
        n = 4;
        click.start();
        TextView t = (TextView)findViewById(R.id.txv);
        String s = "四人遊戲";
        t.setText(s);
    }
}
