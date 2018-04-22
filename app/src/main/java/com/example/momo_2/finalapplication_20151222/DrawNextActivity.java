package com.example.momo_2.finalapplication_20151222;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class DrawNextActivity extends Activity {

    int m;
    ImageView img;
    TextView ctime;
    String[] paths;
    MediaPlayer count;
    MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_next);
        ctime = (TextView)findViewById(R.id.ctime);
        count = MediaPlayer.create(this, R.raw.count);
        click = MediaPlayer.create(this, R.raw.click);
        //計時開始
        CDTimer.start();
        //倒數聲音
        count.start();
        count.setLooping(true);
        //接收資料
        Intent it = getIntent();
        m = it.getIntExtra("資料", m);
        paths = it.getStringArrayExtra("路徑");
        String filepath = paths[m+1];
        img = (ImageView) findViewById(R.id.imv);
        File file = new File(filepath);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(filepath);
            //將圖片顯示在ImageView中
            img.setImageBitmap(bm);
        }
        //顯示圖片動畫
        show();
    }
    //倒數計時器
    CountDownTimer CDTimer = new CountDownTimer(40000,1000){

        @Override
        public void onFinish() {
            ctime.setText("Time's up");
            count.stop();

            go();

        }

        @Override
        public void onTick(long millisUntilFinished) {
            ctime.setText("" + millisUntilFinished/1000);
        }

    };
    public void ok(View v){
        count.stop();
        CDTimer.cancel();
        click.start();
        go();
    }
    public void show() {
        //TextView tex = (TextView) findViewById(R.id.textView2);
        //float curTranslationX = img.getTranslationX();
        /*ObjectAnimator animatorA = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
        animatorA.setDuration(10000);
        animatorA.start();*/
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(img, "scaleX", 1f, 0f);
        animatorX.setDuration(10000);
        animatorX.start();
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(img, "scaleY", 1f, 0f);
        animatorY.setDuration(10000);
        animatorY.start();
    }
    public void go(){
        if (!FileUtil.checkSDCard()) {
            return;
        }
        Bitmap bm = SimpleDrawingView.save();
        final String _folderPath = Environment.getExternalStorageDirectory() + "/draw/";
        final String _fileName = FileUtil.getTimeName() + ".jpg";
        FileUtil.createFolder(_folderPath);
        if (bm != null) {//
            try {
                FileUtil.saveBitmap(_folderPath, bm, _fileName);
                File file = new File(_folderPath + _fileName);
                new SingleMediaScanner(this, file);
                //Toast.makeText(this, "COMPLETE", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e + "", Toast.LENGTH_LONG).show();
            }
        }
        String path = _folderPath + _fileName;
        paths[m] = path;
        Intent it = new Intent(this, ReadyActivity.class);
        it.putExtra("資料", m);
        it.putExtra("路徑",paths);
        startActivity(it);

    }
    public class SingleMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
        private MediaScannerConnection mMs;
        private File mFile;
        public SingleMediaScanner(Context context, File f) {
            mFile = f;
            mMs = new MediaScannerConnection(context, this);
            mMs.connect();
        }
        @Override
        public void onMediaScannerConnected() {
            mMs.scanFile(mFile.getAbsolutePath(), null);
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            mMs.disconnect();
        }

    }

    public static class FileUtil {

        public static boolean checkSDCard() {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return false;
        }

        public static boolean createFolder(String pFolderPath) {
            File _file = new File(pFolderPath);
            if (!_file.exists()) {
                _file.mkdirs();
                return true;
            }
            return false;
        }

        public static void saveBitmap(String pFolderPath, Bitmap pBitmap,
                                      String pFileName) throws IOException {

            String _folderPath = pFolderPath;
            if (_folderPath.lastIndexOf("/") == -1) {
                _folderPath += "/";
            }
            File _file = new File(_folderPath + pFileName);
            OutputStream _outStream  = new FileOutputStream(_file);

            pBitmap.compress(Bitmap.CompressFormat.JPEG, 90, _outStream);

            _outStream.flush();
            _outStream.close();
        }
        public static String getTimeName() {
            Date _date = new Date(System.currentTimeMillis());
            SimpleDateFormat _sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            Random _random = new Random();
            return _sdf.format(_date) + _random.nextInt(999);
        }
    }
    //禁止按返回鍵
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return false;
    }

}
