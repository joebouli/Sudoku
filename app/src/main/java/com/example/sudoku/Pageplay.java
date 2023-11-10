package com.example.sudoku;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageButton;


public class Pageplay extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pageplay);

        ImageButton Fast = findViewById(R.id.Sceltauno);
        ImageButton Easy = findViewById(R.id.Sceltadue);
        ImageButton Medium = findViewById(R.id.Sceltatre);
        ImageButton Hard = findViewById(R.id.Sceltaquattro);
        MediaPlayer sound = MediaPlayer.create(this,
                R.raw.suono);

        ImageButton Home1 = findViewById(R.id.Home2);

        Home1.setOnClickListener(view -> {
            Intent openPage = new Intent(Pageplay.this, MainActivity.class);
            startActivity(openPage);
        });
        Fast.setOnClickListener(view -> sound.start());
        Easy.setOnClickListener(view -> sound.start());
        Medium.setOnClickListener(view -> sound.start());
        Hard.setOnClickListener(view -> sound.start());


        Fast.setOnClickListener(view -> {
            Intent openPage = new Intent(Pageplay.this, Fast.class);
            startActivity(openPage);
        });

        Easy.setOnClickListener(view -> {
            Intent openPage = new Intent(Pageplay.this, Easy.class);
            startActivity(openPage);
        });

        Medium.setOnClickListener(view -> {
            Intent openPage = new Intent(Pageplay.this, Medium.class);
            startActivity(openPage);
        });

        Hard.setOnClickListener(view -> {
            Intent openPage = new Intent(Pageplay.this, Hard.class);
            startActivity(openPage);
        });
    }
}
