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
    List<String> startingPassiveCards;
    private List<String> passiveCards;
    private JButton startGameButton;
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
        PlayPanel.startGame();
    }


    public void startPassivePanel(){
        initBackButton();
        initExitButton();
        initStartGameButton();
        setPassiveCards();
        chooseStartingPassive();
        drawStartingPassive();
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
        passiveCards.add("Band of Scarabs");
        passiveCards.add("Alchemist Stone");
        passiveCards.add("Aprinces Ring");
        passiveCards.add("Band of Ben");
        passiveCards.add("Battle Totem");
    }
    private void chooseStartingPassive(){
        startingPassiveCards = new ArrayList<>();
        List<String> passiveCopies = passiveCards;
        Random random = new Random();
        for(int i = 0; i < 3; i++){
            int x = random.nextInt(5-i);
            startingPassiveCards.add(passiveCopies.get(x));
            passiveCopies.remove(x);
        }
    }
    private void drawStartingPassive(){
        int i = 0;

        for(String cardName : startingPassiveCards){
            i++;
            MouseListener mouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    PlayPanel.getInstance().getGameModel().getFirstPlayer().setPassive(cardName);
                    JOptionPane.showMessageDialog(null, "you choose  " + cardName + "  as your passive",
                            "passive", JOptionPane.ERROR_MESSAGE);
                    Log.bodyLogger("choose passive", cardName);
                }
                @Override
                public void mousePressed(MouseEvent mouseEvent) { }
                @Override
                public void mouseReleased(MouseEvent mouseEvent) { }
                @Override
                public void mouseEntered(MouseEvent mouseEvent) { }
                @Override
                public void mouseExited(MouseEvent mouseEvent) { }
            };
            ImageCardPanel cardImage = new ImageCardPanel(cardName, mouseListener, true);
            cardImage.setBounds(180 + (i*160), 100, 160, 200);
            add(cardImage);
        }
    }
    private void initStartGameButton(){
        startGameButton = new JButton("Start");
        startGameButton.setBounds(500,500,200,50);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("navigate", "start game");
                MainFrame.cl.show(MainFrame.panelCont, "6");
            }
        });
        getInstance().add(startGameButton);
    }



    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("passiveImage"), 0, 0, 1200, 700, null);

    }

}
