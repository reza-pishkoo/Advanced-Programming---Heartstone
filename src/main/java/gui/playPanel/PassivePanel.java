package gui.playPanel;

import config.Constants;
import data.Data;
import data.Log;
import gui.MainFrame;
import gui.cardImage.ImageCardPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PassivePanel extends JPanel {

    private JButton backButton;
    private JButton exitButton;

    List<String> FirstPlayerStartingPassiveCards;
    List<String> SecondPlayerStartingPassiveCards;

    private List<String> passiveCards;
    private JButton startGameButton;
    private JButton deckReaderButton;

    private String FirstPlayerPassive = "";
    private String SecondPlayerPassive = "";

    private PlayerPassivePanel firstOne;
    private PlayerPassivePanel secondOne;

    private JLabel firstPlayer;
    private JLabel secondPlayer;

    private PassivePanel(){
        setSize(1200,700);
        setPreferredSize(new Dimension(1200,700));
        setBounds(0,0,1200,700);
        setLayout(null);
    }
    private static PassivePanel instance;
    public static PassivePanel getInstance(){
        if(instance == null)
            instance = new PassivePanel();
        return instance;
    }

    public void setPassivePage(){
        removeAll();
        PassivePanel.getInstance().startPassivePanel();
    }
    private void initLabels(){
        firstPlayer = new JLabel("FIRST PLAYER PASSIVE");
        firstPlayer.setBounds(90, 10, 200, 30);
        firstPlayer.setForeground(Color.WHITE);
        add(firstPlayer);
        secondPlayer = new JLabel("SECOND PLAYER PASSIVE");
        secondPlayer.setBounds(540,  10, 200, 30);
        secondPlayer.setForeground(Color.WHITE);
        add(secondPlayer);
    }


    public void startPassivePanel(){
        FirstPlayerPassive = "";
        SecondPlayerPassive = "";
        initLabels();
        initBackButton();
        initExitButton();
        initStartGameButton();
        initDeckReaderButton();
        setPassiveCards();

        FirstPlayerStartingPassive();
        SecondPlayerStartingPassive();

        initFirstOne();
        initSecondOne();
    }
    private void initExitButton(){
        exitButton = new JButton("EXIT");
        exitButton.setBounds(1120, 20, 70, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("exit game", "sign_out");
                Data.updateAllData();
                System.exit(0);
            }
        });
        add(exitButton);
    }
    private void initBackButton(){
        backButton = new JButton("BACK");
        backButton.setBounds(1120,60,70,30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("navigate", "main menu");
                Data.updateAllData();
                MainFrame.cl.show(MainFrame.panelCont, "3");
            }
        });
        add(backButton);
    }
    private void setPassiveCards(){
        passiveCards = new ArrayList<>();
        passiveCards.add("Off Cards");
        passiveCards.add("Warriors");
        passiveCards.add("Nurse");
        passiveCards.add("Twice draw");
        passiveCards.add("Mana jump");
    }
    private void initFirstOne(){
        firstOne = new PlayerPassivePanel(1);
        firstOne.setBounds(50,50,300,400);
        this.add(firstOne);
    }
    private void initSecondOne(){
        secondOne = new PlayerPassivePanel(2);
        secondOne.setBounds(500,50,300,400);
        this.add(secondOne);
    }
    private void FirstPlayerStartingPassive(){
        FirstPlayerStartingPassiveCards = new ArrayList<>();
        List<String> passiveCopies = new ArrayList<>();
        for(String st : passiveCards){
            passiveCopies.add(st);
        }
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            int x = random.nextInt(5-i);
            FirstPlayerStartingPassiveCards.add(passiveCopies.get(x));
            passiveCopies.remove(x);
        }
    }
    private void SecondPlayerStartingPassive(){
        SecondPlayerStartingPassiveCards = new ArrayList<>();
        List<String> passiveCopies = new ArrayList<>();
        for(String st : passiveCards){
            passiveCopies.add(st);
        }
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            int x = random.nextInt(5-i);
            SecondPlayerStartingPassiveCards.add(passiveCopies.get(x));
            passiveCopies.remove(x);
        }
    }

    private class PlayerPassivePanel extends JPanel{
        public int x;
        public PlayerPassivePanel(int x){
            this.x = x;
            setLayout(null);
            setBackground(new Color(0,0,0,5));
            setSize(300, 400);
            drawPassives();
        }
        private void drawPassives(){
            for (int i = 0; i < 3; i++) {
                if(x == 1){
                    JButton passiveButton = new JButton();
                    passiveButton.setText(FirstPlayerStartingPassiveCards.get(i));
                    passiveButton.setBounds(50,(i+1)*100, 200, 80);
                    passiveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            FirstPlayerPassive= passiveButton.getText();
                        }
                    });
                    add(passiveButton);
                }
                if(x == 2){
                    JButton passiveButton = new JButton();
                    passiveButton.setText(SecondPlayerStartingPassiveCards.get(i));
                    passiveButton.setBounds(50,(i+1)*100, 200, 80);
                    passiveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            SecondPlayerPassive= passiveButton.getText();
                            Log.bodyLogger("choose passive", passiveButton.getText());
                        }
                    });
                    add(passiveButton);
                }
            }
        }

    }
    private void initStartGameButton(){
        startGameButton = new JButton("Start");
        startGameButton.setBounds(600,500,200,50);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("navigate", "start game");
                if (FirstPlayerPassive != "" && SecondPlayerPassive != "") {
                    PlayPanel.startGame(FirstPlayerPassive, SecondPlayerPassive, false);
                    MainFrame.cl.show(MainFrame.panelCont, "6");
                }
                else
                    JOptionPane.showMessageDialog(null, "choose passive", "passive", JOptionPane.ERROR_MESSAGE);
            }
        });
        getInstance().add(startGameButton);
    }
    private void initDeckReaderButton(){
        deckReaderButton = new JButton("Deck Reader");
        deckReaderButton.setBounds(200, 500, 200, 50);
        deckReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.bodyLogger("navigate", "start game");
                if (FirstPlayerPassive != "" && SecondPlayerPassive != "") {
                    PlayPanel.startGame(FirstPlayerPassive, SecondPlayerPassive, true);
                    MainFrame.cl.show(MainFrame.panelCont, "6");
                }
                else
                    JOptionPane.showMessageDialog(null, "choose passive", "passive", JOptionPane.ERROR_MESSAGE);
            }
        });
        getInstance().add(deckReaderButton);
    }



    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("passiveImage"), 0, 0, 1200, 700, null);

    }

}
