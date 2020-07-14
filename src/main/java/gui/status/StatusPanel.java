package gui.status;

import CLI.Main;
import cards.CardFactory;
import cards.Deck;
import config.Constants;
import data.Data;
import data.Log;
import gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static CLI.Main.currentUser;

public class StatusPanel extends JPanel {

    private Deck[] sortedDecks;
    private StatusUpTapePanel statusUpTapePanel;
    public StatusPanel(){
        setSize(1200, 700);
        setPreferredSize(new Dimension(1200, 700));
        setBounds(0, 0, 1200, 700);
        setLayout(new BorderLayout());

        statusUpTapePanel = new StatusUpTapePanel();
        add(statusUpTapePanel, BorderLayout.NORTH);
    }

    public class StatusUpTapePanel extends JPanel {
        private JButton exitButton;
        private JButton minusButton;
        private JButton backButton;

        public StatusUpTapePanel() {
            setPreferredSize(new Dimension(1200, 30));
            setBackground(Color.RED);
            setLayout(null);
            initBackButton();
            initExitButton();
            initMinusButton9();
            add(exitButton);
            add(minusButton);
            add(backButton);
        }

        private void initExitButton() {
            exitButton = new JButton("Exit");
            exitButton.setBounds(1110, 2, 80, 25);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Log.bodyLogger("exit game", "sign_out");
                    Data.updateAllData();
                    System.exit(0);
                }
            });

        }

        private void initMinusButton9() {
            minusButton = new JButton("Minus");
            minusButton.setBounds(1000, 2, 80, 25);
            minusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    MainFrame.getInstance().setState(Frame.ICONIFIED);
                }
            });
        }

        private void initBackButton() {
            backButton = new JButton("Back");
            backButton.setBounds(50, 2, 80, 25);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Log.bodyLogger("navigate", "main menu");
                    Data.updateAllData();
                    MainFrame.cl.show(MainFrame.panelCont, "3");
                }
            });
        }
    }
    private void initSortedDecksArray(){
        sortedDecks = new Deck[currentUser.getAllDecks().size()];
    }

    double gamePlayedNumbers = 0;
    private void sortDecks(){
        for(Deck deck : currentUser.getAllDecks()){
            gamePlayedNumbers += deck.getUse();
        }
        for(int i = 0; i < currentUser.getAllDecks().size(); i++){
            sortedDecks[i] = Main.currentUser.getAllDecks().get(i);
        }
        for(int i = 0; i < currentUser.getAllDecks().size(); i++){
            for(int j = currentUser.getAllDecks().size()-i-1; j > 0; j--){
                double percent1 = ((double) Main.currentUser.getAllDecks().get(j).getWin())/ gamePlayedNumbers;
                double percent2 = ((double) Main.currentUser.getAllDecks().get(j-1).getWin())/ gamePlayedNumbers;
                if(percent1 > percent2){
                    Deck deck = sortedDecks[j];
                    sortedDecks[j] = sortedDecks[j-1];
                    sortedDecks[j-1] = deck;
                }
            }
        }
    }

    private String getBestCard(Deck deck){
        ArrayList<String> bestCardsByUse = new ArrayList();
        for(String cardName : deck.getCards()){
            if(bestCardsByUse.size() == 0)
                bestCardsByUse.add(cardName);
            else{
                if(deck.getCardsDetails().get(cardName).getUse() > deck.getCardsDetails().get(bestCardsByUse.get(0)).getUse()){
                    bestCardsByUse.clear();
                    bestCardsByUse.add(cardName);
                }else if(deck.getCardsDetails().get(cardName).getUse() == deck.getCardsDetails().get(bestCardsByUse.get(0)).getUse()){
                    if(!bestCardsByUse.contains(cardName))
                        bestCardsByUse.add(cardName);
                }
            }
        }
        ArrayList<String> bestCardsByRarity = new ArrayList();
        for(int i = 0; i < bestCardsByUse.size(); i++){
            if(bestCardsByRarity.size() == 0)
                bestCardsByRarity.add(bestCardsByUse.get(i));
            else{
                if(CardFactory.build(bestCardsByUse.get(i), null).getRarity().rarityValue() >
                        CardFactory.build(bestCardsByRarity.get(0), null).getRarity().rarityValue()){
                    bestCardsByRarity.clear();
                    bestCardsByRarity.add(bestCardsByUse.get(i));
                }else if(CardFactory.build(bestCardsByUse.get(i), null).getRarity().rarityValue() >
                        CardFactory.build(bestCardsByRarity.get(0), null).getRarity().rarityValue()){
                    if(!bestCardsByRarity.contains(bestCardsByUse.get(i)))
                        bestCardsByRarity.add(bestCardsByUse.get(i));
                }
            }
        }
        ArrayList<String> bestCardsByMana = new ArrayList<>();
        for(int i = 0; i < bestCardsByRarity.size(); i++){
            if(bestCardsByMana.size() == 0)
                bestCardsByMana.add(bestCardsByRarity.get(i));
            else{
                if(CardFactory.build(bestCardsByRarity.get(i), null).getManaCost() > CardFactory.build(bestCardsByMana.get(0), null).getManaCost()){
                    bestCardsByMana.clear();
                    bestCardsByMana.add(bestCardsByRarity.get(i));
                }else if(CardFactory.build(bestCardsByRarity.get(i), null).getManaCost() == CardFactory.build(bestCardsByMana.get(0), null).getManaCost()){
                    if(!bestCardsByMana.contains(bestCardsByRarity.get(i)))
                        bestCardsByMana.add(bestCardsByRarity.get(i));
                }
            }
        }
        String bestCard = "";
        boolean haveMinion = false;
        for(String cardName : bestCardsByMana){
            if(CardFactory.build(cardName, null).getType().type().equalsIgnoreCase("minion")){
                haveMinion = true;
                bestCard = cardName;
                break;
            }
        }
        if(!haveMinion){
            bestCard = bestCardsByMana.get(0);
        }
        return bestCard;
    }
    private double getAverageMana(Deck deck){
        double sum = 0;
        for(String card : deck.getCards()){
            sum += CardFactory.build(card, null).getManaCost();
        }
        return sum/(double)deck.getCards().size();
    }


    @Override
    protected void paintComponent(Graphics g) {
        initSortedDecksArray();
        sortDecks();
        int x=60;
        g.setColor(Color.RED);
        g.drawImage(Constants.backgroundImages.get("statusImage"), 0, 0, 1200, 700, null);
        for (int i = 0; i < 5; i++) {
            g.drawRoundRect(50, 45 + i*130,525, 120, 15, 15 );
        }
        for (int i = 0; i < 5; i++) {
            g.drawRoundRect(625, 45 + i*130,525, 120, 15, 15 );
        }
        g.setFont(new Font("Tahoma", Font.BOLD, 15));
        g.setColor(Color.WHITE);
        int first = 70;
        int second = 15;
        int i = 0;
        for(Deck s : sortedDecks){
            g.drawString("Deck Name: " + sortedDecks[i].getDeckName(), x, first);
            first += second;
            if(gamePlayedNumbers==0){
                g.drawString("Win / Total: " + 0, x,first);
            }else{
                g.drawString("Win / Total: " + ((double)sortedDecks[i].getWin()/gamePlayedNumbers), x,first);
            }

            first += second;
            g.setFont(new Font("Tahoma",Font.BOLD,40));
            g.setColor(Color.red);
            g.drawString(i+"",520,first);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.setColor(Color.WHITE);
            g.drawString("Win: " + sortedDecks[i].getWin(), x,first);
            first += second;
            g.drawString("Total:" + sortedDecks[i].getWin(), x,first);
            first += second;
            g.drawString("Hero:  " + sortedDecks[i].getHero(), x,first);
            first += second;
            g.drawString("Average mana: " + getAverageMana(sortedDecks[i]), x,first);
            first += second;
            if (sortedDecks[i].getCards().size()==0){
                g.drawString("Best card: " +  "null" , x,first);
            }else{
                g.drawString("Best card: " + getBestCard(sortedDecks[i]), x,first);
            }

            first += 40;

            if(i == 4)
                break;
            i++;
        }
        i++;
        x=660;
        if(sortedDecks.length>5) {
            for (Deck s : sortedDecks) {
                g.setFont(new Font("Tahoma",Font.BOLD,40));
                g.setColor(Color.red);
                g.drawString(i+"",1100,first);
                g.setFont(new Font("Tahoma", Font.BOLD, 15));
                g.setColor(Color.WHITE);
                g.drawString("Deck Name: " + sortedDecks[i].getDeckName(), x, first);
                first += second;
                g.drawString("Win / Total: " + ((double) sortedDecks[i].getWin() / gamePlayedNumbers), x, first);
                first += second;
                g.drawString("Win: " + sortedDecks[i].getWin(), x, first);
                first += second;
                g.drawString("Total:" + sortedDecks[i].getWin(), x, first);
                first += second;
                g.drawString("Hero:  " + sortedDecks[i].getHero(), x, first);
                first += second;
                g.drawString("Average mana: " + getAverageMana(sortedDecks[i]), x, first);
                first += second;
                g.drawString("Best card: " + getBestCard(sortedDecks[i]), x, first);
                first += 25;

                if(i == sortedDecks.length - 1)
                    break;
                i++;
            }
        }
    }

}
