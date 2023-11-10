package com.example.sudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        android.widget.ImageButton Play = findViewById(R.id.imageButton);
        android.widget.ImageButton Info = findViewById(R.id.imageButton6);
        android.widget.ImageButton Help = findViewById(R.id.imageButton5);

        MediaPlayer sound = MediaPlayer.create(this,
                R.raw.suono);


        Play.setOnClickListener(view -> sound.start());
        Info.setOnClickListener(view -> sound.start());
        Help.setOnClickListener(view -> sound.start());


        Help.setOnClickListener(view -> {
            Intent openPage1 = new Intent(MainActivity.this, com.example.sudoku.Help.class);
            startActivity(openPage1);
        });


        Info.setOnClickListener(view -> {
            Intent openPage2 = new Intent(MainActivity.this, com.example.sudoku.Info.class);
            startActivity(openPage2);
        });

        Play.setOnClickListener(view -> {
            Intent openPageplay = new Intent(MainActivity.this, Pageplay.class);
            startActivity(openPageplay);
        });

    }
}
