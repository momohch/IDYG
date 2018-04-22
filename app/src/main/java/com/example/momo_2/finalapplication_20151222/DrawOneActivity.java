package com.example.momo_2.finalapplication_20151222;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DrawOneActivity extends Activity{
    int n = 0;
    int m = 0;
    TextView ctime;
    TextView question;
    MediaPlayer count;
    MediaPlayer click;
    SimpleDrawingView SDV;
    static Random rnd = new Random();
    static String[] paths = { "no", "no", "no", "no" };
    int rq = rnd.nextInt(50) + 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_one);
        ctime = (TextView)findViewById(R.id.ctime);
        question = (TextView)findViewById(R.id.Q);
        SDV = (SimpleDrawingView)findViewById(R.id.simpleDrawingView1);
        count = MediaPlayer.create(this, R.raw.count);
        click = MediaPlayer.create(this, R.raw.click);
        String[] QQ = getResources().getStringArray(R.array.questions);
        String s = QQ[rq] ;
        question.setText(s);
        /*//橡皮擦功能
        Switch eraser = (Switch)findViewById(R.id.eraser);

        eraser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SDV.eraser();
                }else{
                    SDV.pen();
                }
            }
        });*/
        //計時開始
        CDTimer.start();
        //倒數聲音
        count.start();
        count.setLooping(true);
        //接收資料
        Intent it = getIntent();
        m = it.getIntExtra("資料", n);
        m = m-1;
    }
    //倒數計時器
    CountDownTimer CDTimer = new CountDownTimer(30000,1000){

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
    /*public void eraser(View v){
        SDV.eraser();
    }
    public void pen(View v){
        SDV.pen();
    }*/

    public void ok(View v){
        count.stop();
        CDTimer.cancel();
        click.start();
        go();
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
        Intent it = new Intent(this, ReadyActivity.class);
        paths[m] = path;
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
