package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.graphics.Paint;
import android.graphics.Color;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public MainThread thread;
    private AsteroidSprite asteroidSprite;
    private EarthSprite bgSprite;
    private Bitmap bg = BitmapFactory.decodeResource(getResources(),R.drawable.earth);
    private int y_loc, numOne, numTwo;
    private Paint paint = new Paint();

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        y_loc = 0;
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        y_loc = 0;
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        thread.start();
        bgSprite = new EarthSprite(this, bg);
        asteroidSprite = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
        numOne = (int)(Math.random()*12) + 1;
        numTwo = (int)(Math.random()*12) + 1;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(){
        y_loc++;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.rgb(55,0,111));
       // bgSprite.draw(canvas);
        canvas.drawText(numOne + " * " + numTwo, 500, y_loc, paint);
        asteroidSprite.draw(canvas);
    }
}
