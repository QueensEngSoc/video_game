package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

public class AsteroidSprite {
    private Bitmap image;
    private GameView gameView;
    private Paint paint = new Paint();
    private Equation eqn;
    public String soln;
    private int x,y;
    private int spawn;

    public AsteroidSprite(GameView gameView, Bitmap bmp) {
        image = bmp;
        this.gameView = gameView;
        eqn = new Equation();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(60);
        x = (int)(Math.random()*1000) + 1;
        y = -100;
        spawn = 1;
        soln = ""+ (eqn.numOne * eqn.numTwo);
    }

    public void draw(Canvas canvas) {
        update();
        canvas.drawBitmap(image, x, y,null);
        canvas.drawText(eqn.numOne + " * " + eqn.numTwo, x, y-50, paint);
    }
    public void update() {
        y+=2;
        if (x<502) x++;
        if (x>504) x--;
        if (y>= 900) spawn = 0;
    }

    public int getSpawn() {
        return spawn;
    }

    public void setNumOne(int num) {
        eqn.numOne = num;
    }

    public void explode() {
        spawn = 0;
    }
}
