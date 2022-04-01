package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[][] field;
    public static final char DOT_EMTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static int SIZE;
    public static Scanner reader = new Scanner(System.in);
    static Random random = new Random();
    static String xWin = String.valueOf(DOT_X);
    static String oWin = String.valueOf(DOT_O);

    public static void main(String[] args) {
        initialField();
        printField();

        while (true) {
            humanTurn();
            if (checkWin(xWin)) {
                System.out.println("!!!Кожаный мешок победил!!!");
                break;
            }
            if (nothingMore()) {
                System.out.println("Ничья.");
                break;
            }

            aiTurn();
            if (checkWin(oWin)) {
                System.out.println("!!!Компьютер победил!!!");
                break;
            }
            if (nothingMore()) {
                System.out.println("Ничья.");
                break;
            }
        }
    }

    public static void initialField() {
        do {
            System.out.println("Укажи размер поля от 3 до 5.");
            SIZE = reader.nextInt();
        } while (SIZE < 3 || SIZE > 5);
        field = new char[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                field[y][x] = DOT_EMTY;
            }
        }
        initWinPoints();

    }

    public static void printField() {
        System.out.print("*");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(" " + i);
        }
        for (int y = 0; y < SIZE; y++) {
            System.out.println();
            System.out.print(y + 1);
            for (int x = 0; x < SIZE; x++) {
                System.out.print("|" + field[y][x]);
            }
            System.out.print("|");
        }
        System.out.println("\n");
    }

    public static boolean isHumanValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return true;
    }

    public static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введи координату по горизонтали.");
            x = reader.nextInt() - 1;
            System.out.println("Введи координату по вертикали.");
            y = reader.nextInt() - 1;
            if(!isHumanValid(x, y)) {
                System.out.println("Координаты вне диапазона.");
            } else if (!isEmpty(x, y)) {
                System.out.println("Точка уже занята.");
            }
        } while (!isHumanValid(x, y) || !isEmpty(x, y));

        field[y][x] = DOT_X;
        printField();
    }

    public static boolean isEmpty(int x, int y) {
        if(isHumanValid(x,y)) {
        if (field[y][x] == DOT_EMTY) return true;}
        return false;
    }

    public static void aiTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isEmpty(x, y));

        field[y][x] = DOT_O;
        System.out.println("Соперник завершил ход.");
        printField();
    }

    public static void initWinPoints() {
        for (int i = 1; i < SIZE; i++) {
            xWin = xWin.concat(String.valueOf(DOT_X));
            oWin = oWin.concat(String.valueOf(DOT_O));
        }

    }

    public static boolean checkWin(String symbol) {
        String arraySymbol = "";
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                arraySymbol = arraySymbol.concat(String.valueOf(field[y][x]));
            }
            if (arraySymbol.equals(String.valueOf(symbol))) return true;
            arraySymbol = "";
        }

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                arraySymbol = arraySymbol.concat(String.valueOf(field[y][x]));
            }
            if (arraySymbol.equals(String.valueOf(symbol))) return true;
            arraySymbol = "";
        }

        for (int i = 0; i < SIZE; i++) {
            arraySymbol = arraySymbol.concat(String.valueOf(field[i][i]));
        }
        if (arraySymbol.equals(String.valueOf(symbol))) return true;
        arraySymbol = "";

         for (int y = SIZE -1; y >=0; y--) {
             arraySymbol = arraySymbol.concat(String.valueOf(field[y][SIZE - y - 1]));
             }
        if (arraySymbol.equals(String.valueOf(symbol))) return true;

        return false;

        }


    public static boolean nothingMore() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (field[y][x] == DOT_EMTY) return false;
            }
        }
        return true;
    }

    public static boolean fiveVSfive () {
        return false;
    }

}
