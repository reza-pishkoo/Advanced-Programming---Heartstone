package model;

import CLI.Main;
import cards.Card;
import cards.CardFactory;
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

    public List<String> getCurrentStringDeck() {
        return currentStringDeck;
    }

    public void setCurrentStringDeck(List<String> currentStringDeck) {
        this.currentStringDeck = currentStringDeck;
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

    public void setBattleGroundCards(List<Card> battleGroundCards) {
        this.battleGroundCards = battleGroundCards;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    private void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }



    public void endTurn(){
        System.out.println("check commit");
        getGameModel().setRound(getGameModel().getRound()+1);
        if(finishedOrNot()){
            finishGame();
            return;
        }
        Log.bodyLogger("game", "end turn");
        PlayPanel.getInstance().gameLogPanel.appendText("clicked end turn");

        if(getGameModel().getRound() <= 10)
            setCurrentMana(getGameModel().getRound());
        else
            setCurrentMana(10);
        Random random = new Random();
        if(getCurrentStringDeck().size() > 0) {
            int cardIndex = random.nextInt(getCurrentStringDeck().size());
            if (getHandCards().size() < 12) {
                getHandCards().add(CardFactory.build(getCurrentStringDeck().get(cardIndex)));
            }
            Log.bodyLogger("game", "draw " + getCurrentStringDeck().get(cardIndex).toString());
            PlayPanel.getInstance().gameLogPanel.appendText("draw " + getCurrentStringDeck().get(cardIndex).toString());
            getCurrentStringDeck().remove(cardIndex);
        }
    }

    public void addCardToBattleground(Card card){
        if(getCurrentMana() >= card.getManaCost()) {
                if (card.getType().type().equalsIgnoreCase("MINION")) {
                    if (getBattleGroundCards().size() < 7) {
                        getBattleGroundCards().add(card);
                        getHandCards().remove(card);
                        PlayPanel.getInstance().gameLogPanel.appendText("play" + card.getName());
                        Log.bodyLogger("game", "play "+ card.getName());
                        cardPlayed(card.getName());
                    }
                } else if (card.getType().type().equalsIgnoreCase("WEAPON")) {
                    setWeapon((Weapon) card);
                    getHandCards().remove(card);
                    PlayPanel.getInstance().gameLogPanel.appendText("play" + card.getName());
                    Log.bodyLogger("game", "play "+ card.getName());
                    cardPlayed(card.getName());
                } else {
                    getHandCards().remove(card);
                    PlayPanel.getInstance().gameLogPanel.appendText("play" + card.getName());
                    Log.bodyLogger("game", "play "+ card.getName());
                    cardPlayed(card.getName());
                }
                setCurrentMana(getCurrentMana() - card.getManaCost());

        }else{
            JOptionPane.showMessageDialog(null, "not enought mana", "mana", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setPassive(String passive){
        this.passive = passive;
    }

    private void cardPlayed(String cardName){
        Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).cardPlayed(cardName);
    }

    private void finishGame(){

        Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).setUse(Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).getUse()+1);
        Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).setWin(Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).getWin()+1);
        PlayPanel.getInstance().gameLogPanel.appendText("game finished");
        Log.bodyLogger("game", "game finished");
        JOptionPane.showMessageDialog(null,"game over", "Game", JOptionPane.OK_OPTION);
        MainFrame.cl.show(MainFrame.panelCont, "3");
    }
    private boolean finishedOrNot(){
        if(gameModel.getRound() == 61)
            return true;
        return false;
    }
}
