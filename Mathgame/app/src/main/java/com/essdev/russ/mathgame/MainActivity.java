package com.essdev.russ.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_menu);

        Button play = findViewById(R.id.play);
        Button about = findViewById(R.id.about);
        Button stats = findViewById(R.id.stats);

        play.setOnClickListener(this);
        about.setOnClickListener(this);
        stats.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.play) {
            onPlay();
        }
        else if (v.getId() == R.id.about) {
            // show about page
        }
        else if (v.getId() == R.id.stats) {
            // show statistics
        }
    }

    protected void onPlay() {
        setContentView(R.layout.activity_main);


        GameView game = (GameView) findViewById(R.id.gameView);

        EditText editText = (EditText) findViewById(R.id.editText);
        GameKeyboard keyboard = (GameKeyboard) findViewById(R.id.keyboard);

        // prevent system keyboard from appearing when EditText is tapped
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        // pass the InputConnection from the EditText to the keyboard
        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

//        game.setEditText(editText);
    }
}