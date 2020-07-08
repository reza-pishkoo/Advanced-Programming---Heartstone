package gui;

import config.Constants;
import gui.collectionPanel.CollectionMainPanel;
import gui.playPanel.PassivePanel;
import gui.playPanel.PlayPanel;
import gui.status.StatusPanel;
import gui.storePanel.StoreMainPanel;
import gui.signIn.LoginPanel;
import gui.signIn.RegisterPanel;


import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame{
     LoginPanel loginPanel;
     RegisterPanel registerPanel;
     MenuPanel menuPanel;
     StoreMainPanel storeMainPanel;
     CollectionMainPanel collectionMainPanel;
     PlayPanel playPanel;
     PassivePanel passivePanel;
     StatusPanel statusPanel = new StatusPanel();

    public static JPanel panelCont = new JPanel();
    public static CardLayout cl = new CardLayout();

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setUndecorated(true);
    }

    public MainFrame(){
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        menuPanel = new MenuPanel();
        storeMainPanel = new StoreMainPanel();
        collectionMainPanel = CollectionMainPanel.getInstance();
        playPanel = PlayPanel.getInstance();
        passivePanel = PassivePanel.getInstance();
        statusPanel = new StatusPanel();

        panelCont.setSize(1200, 700);
        panelCont.setPreferredSize(new Dimension(1200, 700));
        panelCont.setLayout(cl);
        panelCont.add(loginPanel, "1");
        panelCont.add(registerPanel, "2");
        panelCont.add(menuPanel, "3");
        panelCont.add(storeMainPanel, "4");
        panelCont.add(collectionMainPanel, "5");
        panelCont.add(playPanel, "6");
        panelCont.add(passivePanel, "7");
        panelCont.add(statusPanel, "8");


        initFrame();
        add(panelCont);
        cl.show(panelCont, "1");
        setVisible(true);
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            revalidate();
            repaint();
        }
    };


    private static MainFrame instance;
    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
        }
        return instance;
    }

    public static void main(String[] args) {
//
//        Timer timer = new Timer();
//        timer.schedule(getInstance().timerTask,0,26);


        Constants.imageLoader();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getInstance();
            }
        });

    }

}
