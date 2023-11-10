package com.example.sudoku;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SudokuView extends View {

    final int board;

    final int cellFill, cellHigh, cellError;
    final Paint painted = new Paint();
    final Paint cellFilling = new Paint();
    final Paint cellHighpoint = new Paint();
    int cell;
    static int countErrors = 0;
    final int letter, letterSolve;
    final Paint cellErrorPaint = new Paint();
    final Paint letterSolvePaint = new Paint();
    final Paint letterPaint = new Paint();
    final Rect letterPaintSize = new Rect();


    private final Generatore generatore = new Generatore();


    public SudokuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SudokuView, 0, 0);

        try {
            board = a.getInteger(R.styleable.SudokuView_board, 0);
            cellFill = a.getInteger(R.styleable.SudokuView_cellFill, 0);
            cellHigh = a.getInteger(R.styleable.SudokuView_cellHigh, 0);
            letter = a.getInteger(R.styleable.SudokuView_letter, 0);
            cellError = a.getInteger(R.styleable.SudokuView_cellError, 0);
            letterSolve = a.getInteger(R.styleable.SudokuView_letterSolve, 0);

        } finally {
            a.recycle();
        }
    }


    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int dim = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        cell = dim / 9;
        setMeasuredDimension(dim, dim);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        painted.setStyle(Paint.Style.STROKE);
        painted.setStrokeWidth(16);
        painted.setColor(board);
        painted.setAntiAlias(true);

        cellFilling.setStyle(Paint.Style.FILL_AND_STROKE);
        cellFilling.setAntiAlias(true);
        cellFilling.setColor(cellFill);

        cellHighpoint.setStyle(Paint.Style.FILL_AND_STROKE);
        cellHighpoint.setAntiAlias(true);
        cellHighpoint.setColor(cellHigh);


        letterPaint.setStyle(Paint.Style.FILL);
        letterPaint.setAntiAlias(true);
        letterPaint.setColor(letter);

        cellErrorPaint.setStyle(Paint.Style.FILL);
        cellErrorPaint.setAntiAlias(true);
        cellErrorPaint.setColor(cellError);

        letterSolvePaint.setStyle(Paint.Style.FILL);
        letterSolvePaint.setAntiAlias(true);
        letterSolvePaint.setColor(letterSolve);


        cellacolore(canvas, generatore.getRow(), generatore.getColumn());
        canvas.drawRect(0, 0, getWidth(), getHeight(), painted);
        drawB(canvas);
        drawN(canvas);


    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean v;

        float x = event.getX();
        float y = event.getY();

        int act = event.getAction();

        if (act == MotionEvent.ACTION_DOWN) {
            Generatore.setRow((int) Math.ceil(y / cell));
            Generatore.setColumn((int) Math.ceil(x / cell));
            v = true;

        } else {
            v = false;
        }

        return v;
    }


    void drawN(Canvas canvas) {


        letterPaint.setTextSize(cell);
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (generatore.getPanel()[r][c] != 0) {
                    String text;
                    text = Integer.toString(generatore.getPanel()[r][c]);
                    if (generatore.getPanel()[r][c] != generatore.getBase()[r][c]) {

                        if (generatore.getPanel()[r][c] < 10 && generatore.getPanel()[r][c] > 0) {
                            countErrors++;

                        }

                        letterPaint.setColor(cellError);

                    } else if (generatore.getPanel()[r][c] != generatore.getClear1()[r][c]) {

                        letterPaint.setColor(letter);
                    } else {
                        letterPaint.setColor(letterSolve);

                    }
                    float width, height;

                    letterPaint.getTextBounds(text, 0, text.length(), letterPaintSize);
                    width = letterPaint.measureText(text);
                    height = letterPaintSize.height();

                    canvas.drawText(text, (c * cell) + (cell - width) / 2,
                            (r * cell + cell) - (cell - height) / 2, letterPaint);
                }

            }
        }


        letterPaint.setColor(letterSolve);
        for (ArrayList<Object> letter : generatore.getEmptyBoxIndex()) {


            int r = (int) letter.get(0);
            int c = (int) letter.get(1);

            String text = Integer.toString((generatore.getPanel()[r][c]));
            float width, height;

            letterPaint.getTextBounds(text, 0, text.length(), letterPaintSize);
            width = letterPaint.measureText(text);
            height = letterPaintSize.height();

            canvas.drawText(text, (c * cell) + (cell - width) / 2, (r * cell + cell) - (cell - height) / 2, letterPaint);


        }
    }


    private void cellacolore(Canvas canvas, int r, int c) {
        if (generatore.getColumn() != -1 && generatore.getRow() != -1) {
            canvas.drawRect(cell * (c - 1), 0, c * cell, cell * 9, cellHighpoint);

            canvas.drawRect(0, cell * (r - 1), cell * 9, r * cell, cellHighpoint);

            canvas.drawRect(cell * (c - 1), cell * (r - 1), c * cell, r * cell, cellHighpoint);

        }
        invalidate();
    }


    private void drawLc() {
        painted.setStyle(Paint.Style.STROKE);
        painted.setStrokeWidth(16);
        painted.setColor(board);

    }

    private void drawLn() {
        painted.setStyle(Paint.Style.STROKE);
        painted.setStrokeWidth(4);
        painted.setColor(board);
    }

    private void drawB(Canvas canvas) {

        for (int c = 0; c < 10; c++) {

            if (c % 3 == 0) {
                drawLc();
            } else {
                drawLn();
            }
            canvas.drawLine(cell * c, 0, cell * c, getWidth(), painted);
        }
        for (int r = 0; r < 10; r++) {
            if (r % 3 == 0) {
                drawLc();
            } else {
                drawLn();
            }
            canvas.drawLine(0,
                    cell * r, getWidth(), cell * r, painted);
        }
    }

    public Generatore getGen() {
        return this.generatore;
    }


}
