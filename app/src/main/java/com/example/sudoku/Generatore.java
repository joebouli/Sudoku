package com.example.sudoku;

import java.util.Random;

import java.util.ArrayList;


class Generatore {

    static int[][] panel;
    static int[][] base;
    static int[][] clear1;
    ArrayList<ArrayList<Object>> emptyBoxIndex;
    static int row, column;

    Generatore() {
        row = -1;
        column = -1;


        panel = new int[9][9];
        base = new int[9][9];
        clear1 = new int[9][9];


        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                panel[r][c] = 0;

                base[r][c] = 0;

            }

        }
        emptyBoxIndex = new ArrayList<>();


    }


    boolean check(int row, int col, int[][] panel) {
        if (panel[row][col] > 0) {
            for (int i = 0; i < 9; i++) {
                if (panel[i][col] == panel[row][col] && row != i) {
                    return false;
                }

                if (panel[row][i] == panel[row][col] && col != i) {
                    return false;
                }
            }


            for (int r = row / 3 * 3; r < row / 3 * 3 + 3; r++) {
                for (int c = col / 3 * 3; c < col / 3 * 3 + 3; c++) {
                    if (panel[r][c] == panel[row][col] && row != r && col != c) {
                        return false;
                    }
                }
            }
        }

        return true;
    }



    public void breeder(SudokuView screen, int[][] base) {

        int count = 0;
        while (count < 12) {


            Random random = new Random();
            int ri = random.nextInt(9);
            int co = random.nextInt(9);
            base[ri][co] = random.nextInt(10 - 1);
            if (!check(ri, co, base)) {
                while (!check(ri, co, base)) {
                    base[ri][co] = random.nextInt(10 - 1);
                }
            }
            count++;
        }

        breeder0(screen, base);

        Random random = new Random();





        count = 0;


        while (count < 17) {
            int r1 = random.nextInt(9);
            int c1 = random.nextInt(9);
            int r2 = random.nextInt(9);
            int c2 = random.nextInt(9);
            if (Math.abs((r1 / 3 * 3) - (r2 / 3 * 3)) < 3) {


                int[] tmp = base[r1];
                base[r1] = base[r2];
                base[r2] = tmp;

            }


            if (Math.abs((c1 / 3 * 3) - (c2 / 3 * 3)) < 3) {
                for (int i = 0; i < 9; i++) {
                    int tmp = base[i][c1];
                    base[i][c1] = base[i][c2];
                    base[i][c2] = tmp;
                }
            }


            count++;
        }
    }


    public boolean breeder0(SudokuView screen, int[][] base) {


        int row = -1, column = -1;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (base[r][c] == 0) {
                    row = r;
                    column = c;
                    break;
                }
            }

        }

        if (column == -1 /*|| row == -1*/) {
            return true;
        }

        for (int i = 1; i < 10; i++) {


            base[row][column] = i;


            screen.invalidate();


            if (check(row, column, base)) {

                if (breeder0(screen, base)) {

                    return true;
                }
            }


            base[row][column] = 0;
        }
        return false;

    }


    int countHints = 0;

    public int Copy(int[][] base, int difficulty) {


        int count = 0;
        while (count < difficulty) {
            Random random = new Random();
            int ri = random.nextInt(9);
            int co = random.nextInt(9);

            if (panel[ri][co] != base[ri][co]) {
                panel[ri][co] = base[ri][co];
                clear1[ri][co] = base[ri][co];
                count++;
                countHints++;
            }
        }
        for (int r = 0; r < 9; r++) {

            System.arraycopy(base[r], 0, Generatore.base[r], 0, 9);

        }

        return countHints;
    }

    boolean End(int[][] base) {

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (panel[r][c] != base[r][c]) {

                    return false;
                }
            }
        }


        return true;
    }


    void Clear() {
        for (int r = 0; r < 9; r++) {

            System.arraycopy(clear1[r], 0, panel[r], 0, 9);
        }


    }


    void setPos(int num) {
        if (row != -1 && column != -1) {

            if (panel[row - 1][column - 1] == num) {
                panel[row - 1][column - 1] = 0;

            } else {
                panel[row - 1][column - 1] = num;
            }
        }

    }

    public ArrayList<ArrayList<Object>> getEmptyBoxIndex() {
        return this.emptyBoxIndex;
    }

    int[][] getPanel() {
        return panel;
    }

    int[][] getBase() {
        return base;
    }

    int[][] getClear1() {
        return clear1;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static void setRow(int r) {
        row = r;
    }

    public static void setColumn(int c) {
        column = c;
    }


}
