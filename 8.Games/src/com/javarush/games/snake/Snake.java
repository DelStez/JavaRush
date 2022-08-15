package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    public int x;
    public int y;
    private List<GameObject> snakeParts;
    public Snake(int x, int y){
        this.snakeParts = new ArrayList<>();
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }
}
