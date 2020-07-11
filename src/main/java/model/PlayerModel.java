package model;

import CLI.Main;
import cards.Card;
import cards.CardFactory;
import cards.Minion;
import cards.Weapon;
import data.Log;
import gui.MainFrame;
import gui.playPanel.PlayPanel;
import user.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerModel {
    private List<String> currentStringDeck;
    private List<Card> handCards;
    private List<Card> battleGroundCards;
    private int currentMana;
    private GameModel gameModel;
    private Weapon weapon;
    private String passive;
    private String hero;
    private List<Card> currentDeck;
    private int turn;


    public PlayerModel(User user, GameModel gameModel, int turn) {
        this.hero = user.getAllHeroes().get(user.getCurHero()).getName();
        this.currentStringDeck = new ArrayList<>();
        for(String card : user.getAllDecks().get(user.getCurDeck()).getCards())
            this.currentStringDeck.add(card);
        this.handCards = new ArrayList<>();
        this.battleGroundCards = new ArrayList<>();
        this.currentMana = 1;
        this.gameModel = gameModel;
        setCards();
        this.turn = turn;
    }

    private void setCards(){
        currentDeck = new ArrayList<>();
        for (String name: currentStringDeck) {
            Card x = CardFactory.build(name);
            currentDeck.add(x);
        }
    }

    public List<Card> getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(List<Card> currentDeck) {
        this.currentDeck = currentDeck;
    }

    public String getPassive() {
        return passive;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }


    public List<Card> getHandCards() {
        return handCards;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public List<Card> getBattleGroundCards() {
        return battleGroundCards;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }


    public void setPassive(String passive){
        this.passive = passive;
    }

    public void cardPlayed(String cardName){
        Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).cardPlayed(cardName);
    }

}
