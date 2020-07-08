package model;

import CLI.Main;
import data.Data;

public class GameModel {


    private PlayerModel firstPlayer;
    private PlayerModel secondPlayer;
    private int round = 1;


    public GameModel(){
        setSecondPlayer();
        firstPlayer = new PlayerModel(Main.currentUser, this, 1);
        secondPlayer = new PlayerModel(Main.enemyUser, this, 2);
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }



}
