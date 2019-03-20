package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.EditText;
import android.graphics.Color;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public MainThread thread;
    private AsteroidSprite asteroidOne, asteroidTwo;
    private EarthSprite bgSprite;
    private Bitmap bg = BitmapFactory.decodeResource(getResources(),R.drawable.earth);
    private int count;
    private EditText editText;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        thread.start();
        bgSprite = new EarthSprite(this, bg);
        asteroidOne = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
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
        count++;
        if ( count == 300 ) {
            asteroidTwo = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
        }
//        if (Integer.parseInt(editText.getText().toString()) == asteroidOne.soln) asteroidOne.explode();
//        if (Integer.parseInt(editText.getText().toString()) == asteroidTwo.soln) asteroidTwo.explode();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.rgb(55,0,111));
        bgSprite.draw(canvas);
        if (asteroidOne.getSpawn() == 1) asteroidOne.draw(canvas);
        if (asteroidTwo.getSpawn() == 1) asteroidTwo.draw(canvas);
    }

    public void setEditText(EditText edit) {
        this.editText = edit;
    }
}
