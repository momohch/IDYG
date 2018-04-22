package com.example.momo_2.finalapplication_20151222;

/**
 * Created by momo_2 on 2015/12/29.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by momo_2 on 2015/12/28.
 */
public class SimpleDrawingView extends View {
    // setup initial color
    private final int paintColor = Color.BLACK;
    // defines paint and canvas
    private Paint drawPaint;
    // stores next circle
    private Path path = new Path();

    static Bitmap vBitmap = Bitmap.createBitmap(850, 850, Bitmap.Config.ARGB_8888);

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
    }

    private void setupPaint() {
        // Setup paint with color and stroke styles
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        vBitmap.eraseColor(Color.WHITE);
        canvas.drawPath(path, drawPaint);
        canvas = new Canvas(vBitmap);

        canvas.drawPath(path, drawPaint);
        canvas.drawBitmap(vBitmap, 0, 0, drawPaint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        // Force a view to draw again
        postInvalidate();
        return true;
    }
    //eraser
    /*void eraser(){
        drawPaint.setColor(Color.WHITE);
        drawPaint.setStrokeWidth(10);
    }

    void pen(){
        drawPaint.setColor(paintColor);
        drawPaint.setStrokeWidth(5);
    }*/

    public static Bitmap save() {
        return vBitmap;
    }
}