package com.essdev.russ.mathgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class AsteroidSprite {
    private Bitmap image;
    private GameView gameView;
    private int x,y;

    public AsteroidSprite(GameView gameView, Bitmap bmp) {
        image = bmp;
        this.gameView = gameView;
        x = 100;
        y = 100;
    }

    public void draw(Canvas canvas) {
        update();
        canvas.drawBitmap(image, x, y,null);
    }
    public void update() {
        y++;
    }
}
