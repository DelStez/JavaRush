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
               setCellColor(x,y, Color.SANDYBROWN);
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

        return null;
    }
    private void setCellColoredNumber(int x, int y, int value) {
        getColorByValue(value);
        setCellValueEx(x, y, Color.ORCHID, "");
    }

}
