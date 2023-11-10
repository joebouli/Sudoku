package com.example.sudoku;

import android.content.Intent;
import android.os.Bundle;

import android.os.SystemClock;

import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class Easy extends AppCompatActivity {

    private SudokuView gamePanel;
    Generatore gameGeneratore;
    static long bas = 0;
    private static int countHints = 0;


    private Button SolveBut, ClearBut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fast);

        ImageButton Home1 = findViewById(R.id.Home1);

        Home1.setOnClickListener(view -> {
            Intent openPage = new Intent(Easy.this, MainActivity.class);
            startActivity(openPage);
        });

        LabelThread labelThread = new LabelThread();
        new Thread(labelThread).start();


        gamePanel = findViewById(R.id.SudokuView);
        gameGeneratore = gamePanel.getGen();

        SolveBut = findViewById(R.id.button);
        ClearBut = findViewById(R.id.buttonR);


    }

    public void OnePress(View view) {
        gameGeneratore.setPos(1);
        gamePanel.invalidate();
    }

    public void TwoPress(View view) {
        gameGeneratore.setPos(2);
        gamePanel.invalidate();
    }

    public void TreePress(View view) {
        gameGeneratore.setPos(3);
        gamePanel.invalidate();
    }

    public void FourPress(View view) {
        gameGeneratore.setPos(4);
        gamePanel.invalidate();
    }

    public void FivePress(View view) {
        gameGeneratore.setPos(5);
        gamePanel.invalidate();
    }

    public void SixPress(View view) {
        gameGeneratore.setPos(6);
        gamePanel.invalidate();
    }

    public void SevenPress(View view) {
        gameGeneratore.setPos(7);
        gamePanel.invalidate();
    }

    public void EightPress(View view) {
        gameGeneratore.setPos(8);
        gamePanel.invalidate();
    }

    public void NinePress(View view) {
        gameGeneratore.setPos(9);
        gamePanel.invalidate();
    }

    public class LabelThread implements Runnable {

        @Override

        public void run() {
            int[][] base = new int[9][9];

            Chronometer simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);

            simpleChronometer.start();


            gameGeneratore.breeder(gamePanel, base);
            gameGeneratore.Copy(base, 38);

            SolveBut.setOnClickListener(view -> countHints = gameGeneratore.Copy(base, 1) - 45);
            ClearBut.setOnClickListener(view -> gameGeneratore.Clear());


            while (true) {

                if (gameGeneratore.End(base)) {
                    simpleChronometer.stop();


                    bas = SystemClock.elapsedRealtime() - simpleChronometer.getBase();

                    Intent openPage = new Intent(Easy.this, WinEasy.class);
                    startActivity(openPage);
                    break;
                }

            }


        }


    }

    public static long Print() {
        return bas;
    }

    public static int countHints() {
        return (countHints);
    }


}
