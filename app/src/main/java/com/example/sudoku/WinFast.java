package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WinFast extends MainActivity {

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win);

        ImageButton Home = findViewById(R.id.Home);
        Home.setOnClickListener(view -> {
            Intent openPage = new Intent(WinFast.this, MainActivity.class);
            startActivity(openPage);
        });


        long bas;
        int countHints = Fast.countHints();
        double score = 0;

        bas = Fast.Printbas();
        bas = bas / 1000;
        long min = bas / 60;

        bas = bas - (min * 60);


        TextView timer = (TextView) findViewById(R.id.time);
        timer.setText("\uD83C\uDD83\uD83C\uDD78\uD83C\uDD7C\uD83C\uDD74:  " + String.format("%02d", min) + ":" + String.format("%02d", bas));

        TextView Hints = (TextView) findViewById(R.id.hints);
        Hints.setText("\uD83C\uDD77\uD83C\uDD78\uD83C\uDD7D\uD83C\uDD83\uD83C\uDD82:  " + countHints);

        if ((.76 * countHints + .1 * (bas)) < 30) {
            score = 30 - (.76 * countHints) - (.05 * (bas));
        }

        TextView Score = (TextView) findViewById(R.id.score);

        Score.setText("" + String.format("%.2f", score));


        Button Button = findViewById(R.id.buttonagain);
        MediaPlayer sound = MediaPlayer.create(this,
                R.raw.suono);
        Button.setOnClickListener(view -> sound.start());


        Button.setOnClickListener(view -> {
            Intent openPage = new Intent(WinFast.this, Pageplay.class);
            startActivity(openPage);
        });


    }
}
