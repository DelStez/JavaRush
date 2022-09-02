package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;
import com.javarush.engine.cell.*;

public class Snake {
    public int x;
    public int y;
    public boolean isAlive = true;
    private final static String HEAD_SIGN = "\uD83D\uDC7E";
    private final static String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    private Snake snake;
    private Direction direction = Direction.LEFT;
    public Snake(int x, int y){
        this.snakeParts = new ArrayList<>();
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }
    public void setDirection(Direction direction) {
        if ( (this.direction == Direction.UP && direction != Direction.DOWN)
                || (this.direction == Direction.DOWN && direction != Direction.UP)
                || (this.direction == Direction.LEFT && direction != Direction.RIGHT)
                || (this.direction == Direction.RIGHT && direction != Direction.LEFT)) {
            this.direction = direction;
        }

    }
    public void move(Apple apple) {
        GameObject head = createNewHead();
        if ((head.x >= SnakeGame.WIDTH || head.x < 0 )
                || (head.y >= SnakeGame.HEIGHT || head.y < 0)) {
            isAlive = false;
        }else {
            if (checkCollision(head)) {
                isAlive = false;
            }else {
                snakeParts.add(0, head);
                if(head.x == apple.x && head.y == apple.y) {
                    apple.isAlive = false;
                }else {
                    removeTail();
                }
            }
        }
    }

    public void draw(Game game) {
        for(int j = 0; j < snakeParts.size(); j++) {
            game.setCellValueEx(snakeParts.get(j).x, snakeParts.get(j).y, Color.NONE,
                    j==0 ? HEAD_SIGN :BODY_SIGN, !isAlive? Color.RED : Color.GREEN, 75);
        }
    }
    public GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        GameObject head = new GameObject(
                direction == Direction.LEFT ? headX - 1:
                        direction == Direction.RIGHT? headX + 1: headX,
                direction == Direction.DOWN? headY + 1:
                        direction == Direction.UP? headY - 1: headY);
        return head;
    }
    public int getLength() {
        return snakeParts.size();
    }
    public void removeTail() {
        snakeParts.remove(snakeParts.size()-1);
    }
    public boolean checkCollision(GameObject object) {
        for (GameObject snakeBody: snakeParts) {
            if (object.y == snakeBody.y && object.x == snakeBody.x ) {
                return true;
            }
        }
        return false;
    }
}
