package logicController;

import CLI.Main;
import cards.Card;
import cards.Minion;
import cards.Spell;
import cards.Weapon;
import data.Log;
import gui.MainFrame;
import gui.playPanel.PlayPanel;
import guiController.GuiRequest;
import guiController.UpdateRequest;
import logicController.actions.Observer;
import logicController.actions.WeaponObserver;
import model.GameModel;

import javax.swing.*;
import java.util.*;

public class LogicController extends Thread{

    private Deque<LogicRequest> requests;
    private GameModel gameModel;
    private List<logicController.actions.Observer> observers;
    private Deque<GuiRequest> responses;

    public LogicController(boolean isDeckReader){
        requests = new LinkedList<>();
        observers = new ArrayList<>();
        responses = new LinkedList<>();
        gameModel = new GameModel(isDeckReader);
    }

    public Deque<GuiRequest> getResponses() {
        return responses;
    }

    public Deque<LogicRequest> getRequests() {
        return requests;
    }

    public void register(logicController.actions.Observer observer) {
        observers.add(observer);
    }
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    public void updateBefore(LogicRequest request) throws Exception{
        Exception exception = null;
        for (int i = observers.size() - 1; i >= 0; i--) {
            try {
                observers.get(i).observeBeforeRequest(request, this);
            }catch (Exception e){
                exception = e;
            }
        }
        if (exception != null)
            throw exception;
    }

    public void updateAfter(LogicRequest request) throws Exception{
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).observeAfterRequest(request, this);
        }
    }


    public void run(){
        while(true){
            while (requests.isEmpty()) {
                sleep();
            }
            executeAll();
            //TODO

        }
    }
    public void sleep(){
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeAll(){
        LogicRequest request = requests.pollFirst();

        try {
            updateBefore(request);
        } catch (Exception e) {
            //
            return;
        }
        try {
            request.execute(this);
            sendResponse(new UpdateRequest(request.getPlayer(), gameModel));
        } catch (Exception e) {

        }
        try {
            updateAfter(request);
        } catch (Exception e) {
            //
            return;
        }

    }


    public void sendResponse(GuiRequest request) {
        responses.add(request);
    }


    public void endTurn(boolean isDeckReader){
        PlayPanel.sec = 0;
        gameModel.setRound(gameModel.getRound()+1);
        if(finishedOrNot()){
            finishGame();
            return;
        }
        Log.bodyLogger("game", "end turn");
        PlayPanel.getInstance().gameLogPanel.appendText("clicked end turn");

        if(gameModel.getRound() <= 20) {
            if(gameModel.getRound() % 2 == 1)
                gameModel.getCurrentPlayer().setCurrentMana((gameModel.getRound() / 2) + 1);
            else
                gameModel.getCurrentPlayer().setCurrentMana(gameModel.getRound() / 2);
        }
        else
            gameModel.getCurrentPlayer().setCurrentMana(10);
        if(gameModel.getRound() != 2){
            if(!isDeckReader)
                addToHand();
            else
                addForDeckReader();
        }

        for (Card card : gameModel.getCurrentPlayer().getBattleGroundCards()) {
            card.setCanUse(true);
        }
        gameModel.getCurrentPlayer().getHero().setCanAttack(true);
        gameModel.getNextPlayer().getHero().setCanAttack(true);

        if(gameModel.getCurrentPlayer().getWeapon() != null)
            gameModel.getCurrentPlayer().getWeapon().setCanUse(true);
        if(gameModel.getNextPlayer().getWeapon() != null)
            gameModel.getNextPlayer().getWeapon().setCanUse(true);

    }

    public void addToHand(){
        Random random = new Random();
        if(gameModel.getCurrentPlayer().getCurrentDeck().size() > 0) {
            int cardIndex = random.nextInt(gameModel.getCurrentPlayer().getCurrentDeck().size());
            if (gameModel.getCurrentPlayer().getHandCards().size() < 10) {
                gameModel.getCurrentPlayer().getHandCards().add
                        (gameModel.getCurrentPlayer().getCurrentDeck().get(cardIndex));
            }
            Log.bodyLogger("game", "draw " + gameModel.getCurrentPlayer().getCurrentDeck().get(cardIndex).toString());
            PlayPanel.getInstance().gameLogPanel.appendText("draw  " + gameModel.getCurrentPlayer().getCurrentDeck().get(cardIndex).toString());
            gameModel.getCurrentPlayer().getCurrentDeck().remove(cardIndex);
        }
    }
    private void addForDeckReader(){
        System.out.println(gameModel.getCurrentPlayer() +"   " +gameModel.getCurrentPlayer().getCurrentDeck().size() + "");
        if(gameModel.getCurrentPlayer().getCurrentDeck().size() > 0) {
            gameModel.getCurrentPlayer().getHandCards().add(gameModel.getCurrentPlayer().getCurrentDeck().get(0));
            Log.bodyLogger("game", "draw " + gameModel.getCurrentPlayer().getCurrentDeck().get(0).toString());
            PlayPanel.getInstance().gameLogPanel.appendText("draw  " + gameModel.getCurrentPlayer().getCurrentDeck().get(0).toString());
            gameModel.getCurrentPlayer().getCurrentDeck().remove(0);
        }
    }

    public void addCardToBattleground(Card card, AddToBattleGroundRequest  request){

        if(gameModel.getCurrentPlayer().getCurrentMana() >= card.getManaCost()) {
            if (card instanceof Minion) {
                if (gameModel.getCurrentPlayer().getBattleGroundCards().size() < 7) {
                    register(((Minion)card).getMinionObserver());
                    ((Minion) card).getMinionObserver().play(request, this);
                    PlayPanel.getInstance().gameLogPanel.appendText("play" + card.getName());
                    Log.bodyLogger("game", "play "+ card.getName());
                    gameModel.getCurrentPlayer().cardPlayed(card.getName());
                }
            } else if (card instanceof Weapon) {
                for (Observer observer : observers){
                    if(observer instanceof WeaponObserver)
                        unregister(observer);
                }
                ((Weapon)card).getWeaponObserver().play(request);
                PlayPanel.getInstance().gameLogPanel.appendText("play" + card.getName());
                Log.bodyLogger("game", "play "+ card.getName());
                gameModel.getCurrentPlayer().cardPlayed(card.getName());
            } else if(card instanceof Spell){
                ((Spell)card).getSpellObserver().play(request, this);
                PlayPanel.getInstance().gameLogPanel.appendText("play" + card.getName());
                Log.bodyLogger("game", "play "+ card.getName());
                gameModel.getCurrentPlayer().cardPlayed(card.getName());
            }
            gameModel.getCurrentPlayer().setCurrentMana(gameModel.getCurrentPlayer().getCurrentMana() - card.getManaCost());

        }else{
            JOptionPane.showMessageDialog(null, "not enought mana", "mana", JOptionPane.ERROR_MESSAGE);
        }
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

    public GameModel getGameModel() {
        return gameModel;
    }
}
