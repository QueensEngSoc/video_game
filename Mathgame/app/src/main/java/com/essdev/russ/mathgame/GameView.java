package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.EditText;
import android.graphics.Color;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public MainThread thread;
    private AsteroidSprite asteroidOne, asteroidTwo, asteroidThree, asteroidFour;
    private EarthSprite bgSprite;
    private Bitmap bg = BitmapFactory.decodeResource(getResources(),R.drawable.earth);
    private int count, score;
    private Paint paint = new Paint();

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
        score = 0;
        thread.setRunning(true);
        thread.start();
        bgSprite = new EarthSprite(this, bg);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60);
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
        if ( count == 0 ) {
//            asteroidThree.explode();
            asteroidOne = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
        }
        if ( count == 200 ) {
//            asteroidFour.explode();
            asteroidTwo = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
        }
        if ( count == 400 ) {
            asteroidOne.explode();
            score+=100;
            asteroidThree = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
        }
        if ( count == 600 ) {
            asteroidTwo.explode();
            score+=100;
            asteroidFour = new AsteroidSprite(this, BitmapFactory.decodeResource(getResources(),R.drawable.fire));
        }
        count++;
        count%=800;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.rgb(55,0,111));
        bgSprite.draw(canvas);
        canvas.drawText("Score: " + score, 50, 100, paint);
        if (asteroidOne.getSpawn() == 1) asteroidOne.draw(canvas);
        if (asteroidTwo.getSpawn() == 1) asteroidTwo.draw(canvas);
        if (asteroidThree.getSpawn() == 1) asteroidThree.draw(canvas);
        if (asteroidFour.getSpawn() == 1) asteroidFour.draw(canvas);
    }

    public void checkAnswer (String ans) {
        if (ans.equals(asteroidOne.soln)) {
            asteroidOne.explode();
            score+=100;
        }
        if (ans.equals(asteroidTwo.soln)) {
            asteroidTwo.explode();
            score+=100;
        }
        if (ans.equals(asteroidThree.soln)) {
            asteroidThree.explode();
            score+=100;
        }
        if (ans.equals(asteroidFour.soln)) {
            asteroidFour.explode();
            score+=100;
        }
    }
}
