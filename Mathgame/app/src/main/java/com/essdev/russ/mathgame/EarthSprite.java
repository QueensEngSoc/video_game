package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class EarthSprite {
    private Bitmap image;
    private GameView gameView;
    private int x,y;

    public EarthSprite(GameView gameView, Bitmap bmp) {
        image = bmp;
        this.gameView = gameView;
        x = 0;
        y = 600;
    }

    public void draw(Canvas canvas) {
        update();
        canvas.drawBitmap(image, new Rect(0,0,1500,1500), new Rect(200,1000,1000,1800),null);
    }
    public void update() {
    }
}
