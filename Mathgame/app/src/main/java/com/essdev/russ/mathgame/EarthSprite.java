package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class EarthSprite {
    private Bitmap image;
    private GameView gameView;
    private int x,y;

    public EarthSprite(GameView gameView, Bitmap bmp) {
        image = bmp;
        this.gameView = gameView;
        x = 0;
        y = 0;
    }

    public void draw(Canvas canvas) {
        update();
        canvas.drawBitmap(image, x, y,null);
    }
    public void update() {
    }
}
