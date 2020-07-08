package gui;

import CLI.Main;
import config.Constants;
import data.Data;
import data.Log;
import gui.collectionPanel.CollectionMainPanel;
import gui.imageButton.ShiftingImageButton;
import gui.playPanel.PassivePanel;
import gui.playPanel.PlayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static gui.MainFrame.panelCont;

public class MenuPanel extends JPanel {
    private ShiftingImageButton startButton;
    private ShiftingImageButton collectionsButton;
    private ShiftingImageButton storeButton;
    private ShiftingImageButton statusButton;
    private ShiftingImageButton exitButton;


    public MenuPanel() {
        setSize(1200, 700);
        setPreferredSize(new Dimension(1200, 700));
        setBounds(0, 0, 1200, 700);
        setLayout(null);
        initCollectionButton();
        initStartButton();
        initStatusButton();
        initStoreButton();
        initExitButton();

        add(startButton);
        add(collectionsButton);
        add(storeButton);
        add(statusButton);
        add(exitButton);


    }

    public void initStartButton(){
        startButton = new ShiftingImageButton("START");
        startButton.setBounds(100, 100, 300,30);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Main.currentUser.getAllDecks().get(Main.currentUser.getCurDeck()).getCards().size() == 30) {
                    Log.bodyLogger("navigate", "passive page");
                    PassivePanel.getInstance().setPassivePage();
                    MainFrame.cl.show(panelCont, "7");
                }else{
                    Log.bodyLogger("navigate", "collection");
                    JOptionPane.showMessageDialog(null, "your current deck is not complete \n" +
                            "go to collection", "error", JOptionPane.ERROR_MESSAGE);
                    CollectionMainPanel.getInstance().StartCollection();
                    MainFrame.cl.show(panelCont, "5");
                }
            }
        });
    }


    public void initCollectionButton(){
        collectionsButton = new ShiftingImageButton("COLLECTION");
        collectionsButton.setBounds(100, 180, 300,30);
        collectionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("navigate", "collections");
                CollectionMainPanel.getInstance().StartCollection();
                MainFrame.cl.show(panelCont, "5");
            }
        });
    }

    public void initStoreButton(){
        storeButton = new ShiftingImageButton("STORE");
        storeButton.setBounds(100, 220, 300,30);
        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.bodyLogger("navigate", "store");
                CollectionMainPanel.getInstance().StartCollection();
                MainFrame.cl.show(panelCont, "4");
            }
        });
    }

    public void initStatusButton(){
        statusButton = new ShiftingImageButton("STATUS");
        statusButton.setBounds(100, 260, 300,30);
        statusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.cl.show(panelCont, "8");
                Log.bodyLogger("navigate", "status");
            }
        });
    }

    public void initExitButton(){
        exitButton = new ShiftingImageButton("EXIT");
        exitButton.setBounds(100, 300, 300,30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("exit game", "sign_out");
                Data.updateAllData();
                System.exit(0);
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("menuImage"), 0, 0, 1200, 700, null);
    }
}
