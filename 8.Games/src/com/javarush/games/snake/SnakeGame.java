package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game{
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStoped;

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
        apple.draw(SnakeGame.this);
    }
    public void onKeyPress(Key _key) {
        snake.setDirection( _key == Key.UP? Direction.UP :
                _key == Key.DOWN? Direction.DOWN :
                _key == Key.LEFT? Direction.LEFT: Direction.RIGHT);
    }
    private void createGame(){
        snake = new Snake(WIDTH/2, HEIGHT/2);
        //Apple apple = new Apple(5,5);
        createNewApple();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        //apple.draw(this);
    }
    private void createNewApple() {
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));

    }
   public void onTurn(int step) {
        if (!apple.isAlive) {
            createNewApple();
        }
        snake.move(apple);
        drawScene();
   }
}
