package com.essdev.russ.mathgame;

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

    public GameView(Context context) {

        super(context);

        getHolder().addCallback(this);


     /*   EditText editText = (EditText) findViewById(R.id.editText);
        GameKeyboard keyboard = (GameKeyboard) findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);


        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);*/

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public GameView(Context context, AttributeSet attributeSet) {

        super(context);

        getHolder().addCallback(this);


     /*   EditText editText = (EditText) findViewById(R.id.editText);
        GameKeyboard keyboard = (GameKeyboard) findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);


        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);*/

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

    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawRect(100, 100, 200, 200, paint);
        }
    }
}
