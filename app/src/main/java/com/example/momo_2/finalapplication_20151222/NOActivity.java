package com.example.momo_2.finalapplication_20151222;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class NOActivity extends Activity {

    ImageView img1;
    ImageView img2;
    ImageView img3;
    String pic1;
    String pic2;
    String pic3;
    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);
        //接收資料
        Intent it = getIntent();
        String[] paths = it.getStringArrayExtra("路徑");
        pic1 = paths[1];
        pic2 = paths[2];
        pic3 = paths[3];
        click = MediaPlayer.create(this, R.raw.click);
        img1 = (ImageView) findViewById(R.id.imv3);
        File file3 = new File(pic1);
        if (file3.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(pic1);
            //縮小
            Bitmap smbm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, true);
            //將圖片顯示在ImageView中
            img1.setImageBitmap(smbm);
        }
        img2 = (ImageView) findViewById(R.id.imv2);
        File file2 = new File(pic2);
        if (file2.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(pic2);
            //縮小
            Bitmap smbm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, true);
            //將圖片顯示在ImageView中
            img2.setImageBitmap(smbm);
        }
        img3 = (ImageView) findViewById(R.id.imv1);
        File file1 = new File(pic3);
        if (file1.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(pic3);
            //縮小
            Bitmap smbm = Bitmap.createScaledBitmap(bm, bm.getWidth() / 2, bm.getHeight() / 2, true);
            //將圖片顯示在ImageView中
            img3.setImageBitmap(smbm);
        }
    }

    //再玩一次
    public void again(View v){
        click.start();
        Intent it = new Intent(this, NumPeoActivity.class);
        startActivity(it);
    }

    //回主畫面
    public void backhome(View v){
        click.start();
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
    //禁止按返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }

}
