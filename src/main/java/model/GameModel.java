package model;

import CLI.Main;
import com.google.gson.Gson;
import data.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameModel {


    private PlayerModel firstPlayer;
    private PlayerModel secondPlayer;
    private int round = 1;


    public GameModel(boolean isDeckReader){
        if(!isDeckReader) {
            setSecondPlayer();
            firstPlayer = new PlayerModel(Main.currentUser, this, 1);
            secondPlayer = new PlayerModel(Main.enemyUser, this, 2);
        }
        else{
            setFirstDeckReaderPlayer();
            setSecondDeckReaderPlayer();
            firstPlayer = new PlayerModel(Data.DeckReaderFirstUser, this, 1);
            secondPlayer = new PlayerModel(Data.DeckReaderSecondUser, this, 2);
        }
    }

    public PlayerModel getNextPlayer(){
        if(getRound() % 2 == 0)
            return getFirstPlayer();
        return getSecondPlayer();
    }
    public PlayerModel getCurrentPlayer(){
        if(getRound() % 2 == 1)
            return getFirstPlayer();
        return getSecondPlayer();
    }

    public PlayerModel getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(PlayerModel firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public PlayerModel getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer() {
        Data.setEnemyUser();
    }

    public void setFirstDeckReaderPlayer(){
        Data.setDeckReaderFirstUser();
    }
    public void setSecondDeckReaderPlayer(){
        Data.setDeckReaderSecondUser();
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }





}
