package com.example.momo_2.finalapplication_20151222;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class FinalActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Intent it = getIntent();
    }
    public void OK(View v){
        Intent it = new Intent(this, OKActivity.class);
        startActivity(it);
    }

    public void NO(View v){
        Intent it = new Intent(this, NOActivity.class);
        startActivity(it);
    }
    //禁止按返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }
}
