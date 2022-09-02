package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game{
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;

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
        if (_key == Key.SPACE)  {
            if (isGameStopped) {
                createGame();
            }
            return;
        }
            snake.setDirection( _key == Key.UP? Direction.UP :
                    _key == Key.DOWN? Direction.DOWN :
                            _key == Key.LEFT? Direction.LEFT: Direction.RIGHT);
    }
    private void createGame(){
        isGameStopped = false;
        snake = new Snake(WIDTH/2, HEIGHT/2);
        //Apple apple = new Apple(5,5);
        createNewApple();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        //apple.draw(this);
    }
    private void createNewApple() {
        do {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        }while (snake.checkCollision(apple) != false);

    }
    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER",Color.WHITE, 16);

    }
    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "YOU WIN",Color.WHITE, 16);
    }
   public void onTurn(int step) {
        if (!apple.isAlive) {
            createNewApple();
        }
        snake.move(apple);
        if (!snake.isAlive) {
            gameOver();
        }
        if (GOAL < snake.getLength()) {
            win();
        }
        drawScene();
   }
}
