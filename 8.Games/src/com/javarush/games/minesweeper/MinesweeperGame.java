package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private boolean isGameStopped;
    private static final int SIDE = 16;
    private int countClosedTiles = SIDE * SIDE;
    private int score, countFlags, countMinesOnField;
    private static final String MINE = "\uD83D\uDCA3";
    private static  final String FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];


    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                countMinesOnField += isMine? 1 : 0;
                gameField[x][y] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.DARKBLUE);
                setCellValue(x,y,"");
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;

    }
    public void onMouseLeftClick(int x, int y) {
        if(isGameStopped){
            restart();
            return;
        }
        openTile(x,y);
    }
    public void onMouseRightClick(int x, int y) { markTile(x,y); }
    private void countMineNeighbors() {
        for (GameObject[] row : gameField) {
            for (GameObject cell : row) {
                if(!cell.isMine) {
                    List<GameObject> neighbors = getNeighbors(cell);
                    for (GameObject neighbor: neighbors) {
                        if (neighbor.isMine) {
                            cell.countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[x][y] == gameObject) {
                    continue;
                }
                result.add(gameField[x][y]);
            }
        }
        return result;
    }
    private void markTile(int x, int y){
        if(isGameStopped) {return;}
        GameObject currentCell = gameField[x][y];
        if(!currentCell.isOpen &&
                (currentCell.isFlag || countFlags !=0)){
            currentCell.isFlag = !currentCell.isFlag;
            countFlags += currentCell.isFlag ? -1: 1;
            setCellColor(x, y, currentCell.isFlag? Color.YELLOW : Color.DARKBLUE);
            setCellValue(x, y, currentCell.isFlag? FLAG : "");
        }
    }
    private void openTile(int x, int y){
        if(isGameStopped) {return;}
        GameObject currentCell = gameField[x][y];
        if(!currentCell.isOpen && !currentCell.isFlag){
            currentCell.isOpen =true;
            setCellColor(x,y, Color.DEEPSKYBLUE);
            countClosedTiles--;
            if(!currentCell.isMine) {
                if(currentCell.countMineNeighbors == 0){
                    for(GameObject neighbor: getNeighbors(currentCell)){
                        if (!neighbor.isOpen) {
                            openTile(neighbor.x, neighbor.y);
                        }
                    }
                    setCellValue(x,y,"");
                }else{

                    setCellNumber(x,y, currentCell.countMineNeighbors);
                }
                score+=5;
                setScore(score);
            }else{
                setCellValueEx(currentCell.x, currentCell.y, Color.PINK, MINE);
                gameOver();
            }
        }
        if(countClosedTiles == countMinesOnField && !currentCell.isMine) {win();}
    }
    private void restart(){
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
    }
    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.YELLOWGREEN, "YOU ARE WIN",Color.DARKBLUE, 92);
    }
    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.DARKBLUE, "GAME OVER",Color.YELLOWGREEN, 92);
    }
}