package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;
import com.javarush.engine.cell.*;

public class Snake {
    public int x;
    public int y;
    private final static String HEAD_SIGN = "\uD83D\uDC7E";
    private final static String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    private Snake snake;
    public Snake(int x, int y){
        this.snakeParts = new ArrayList<>();
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }
    public void draw(Game game) {
        for(int j = 0; j < snakeParts.size(); j++) {
            game.setCellValue(snakeParts.get(j).x, snakeParts.get(j).y,
                    j==0 ? HEAD_SIGN :BODY_SIGN);
        }
    }
}
