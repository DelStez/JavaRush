package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game{
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;

    public void initialize(){
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    private void drawScene(){
        for (int y = 0; y < HEIGHT; y++){
            for (int x = 0; x < WIDTH; x++){
                setCellValueEx(x, y, Color.SANDYBROWN, "");
            }
        }
        snake.draw(SnakeGame.this);
    }
    public void onKeyPress(Key _key) {
        snake.setDirection( _key == Key.UP? Direction.UP :
                _key == Key.DOWN? Direction.DOWN :
                _key == Key.LEFT? Direction.LEFT: Direction.RIGHT);
    }
    private void createGame(){
        snake = new Snake(WIDTH/2, HEIGHT/2);
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        //Apple apple = new Apple(7,7);
        //apple.draw(this);
    }
   public void onTurn(int step) {
        snake.move();
        drawScene();
   }
}
