package com.javarush.games.game2048;
import com.javarush.engine.cell.*;

public class Game2048 extends Game{
    private static  final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];


    public void initialize() {
        setScreenSize(SIDE,SIDE);
        createGame();
        drawScene();
    }
    private  void createGame() {
        for(int i = 0; i < 2; i++) {
            createNewNumber();
        }
    }
    private void drawScene() {
       for (int x = 0;  x < gameField.length; x++) {
           for (int y = 0; y < gameField[x].length; y++) {
               setCellColoredNumber(x,y, gameField[y][x]);
           }
       }
    }
    private  void createNewNumber() {
        int x, y;
        do {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        }while(gameField[y][x] != 0);
        int j = getRandomNumber(10);
        gameField[y][x] = j == 9 ? 4 : 2;
    }
    private Color getColorByValue(int value) {
        switch (value){
            case 0: return Color.GREENYELLOW;
            case 2: return Color.GREEN;
            case 4: return Color.BLUE;
            case 8: return Color.BLANCHEDALMOND;
            case 16: return Color.DARKBLUE;
            case 32: return Color.INDIGO;
            case 64: return Color.VIOLET;
            case 128: return Color.BLUEVIOLET;
            case 256: return Color.AZURE;
            case 512: return Color.AQUAMARINE;
            case 1024: return Color.CYAN;
            case 2048: return Color.LIGHTPINK;
        }
        return Color.WHITE;
    }
    private void setCellColoredNumber(int x, int y, int value) {
        if (value == 0)
            setCellValueEx(x, y, getColorByValue(0), "");
        else
            setCellValueEx(x, y, getColorByValue(value), Integer.toString(value));
    }
    private boolean compressRow(int[] row) {
        boolean flag = false;
        for (int i = 0; i < row.length - 1; i++)
        {
            for (int j = 0; j < row.length - 1; j++)
            {
                int a = row[j];
                int b = row[j + 1];
                if (a == 0 && b > 0)
                {
                    flag = true;
                    row[j] = b;
                    row[j + 1] = a;
                }
            }
        }
        return flag;
    }

}
