package gui.playPanel;

import cards.Card;
import cards.Minion;
import cards.Weapon;
import config.Constants;
import gui.cardImage.ImageCardDetailPanel;
import gui.cardImage.InGameHeroPowerPanel;
import gui.cardImage.InGameImageHeroPanel;
import guiController.GuiController;
import heroes.Hero;
import logicController.*;
import data.Data;
import data.Log;
import gui.MainFrame;
import gui.cardImage.InGameImageCardPanel;
import model.GameModel;
import model.PlayerModel;
import model.UpdateGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayPanel extends JPanel {


    private GuiController guiController;

    private JButton backButton;
    private JButton exitButton;
    private List<UpdateGame> updatable;
    private GameModel gameModel;
    private JButton endTurn;


    public GameLog gameLogPanel;

    private HandPanel FirstPlayerHandPanel;
    private BattleGroundPanel FirstPlayerBattleGroundPanel;
    private HeroPanel FirstPlayerHeroPanel;
    private WeaponPanel FirstPlayerWeaponPanel;
    private JLabel FirstPlayerCardsRemained;
    private ManaPanel FirstPlayerManaPanel;
    private HeroPowerPanel FirstPower;

    private HandPanel SecondPlayerHandPanel;
    private BattleGroundPanel SecondPlayerBattleGroundPanel;
    private HeroPanel SecondPlayerHeroPanel;
    private WeaponPanel SecondPlayerWeaponPanel;
    private JLabel SecondPlayerCardsRemained;
    private ManaPanel SecondPlayerManaPanel;
    private HeroPowerPanel SecondPower;

    private CardDetails cardDetails;

    private JProgressBar progressBar;
    private Time time;
    private boolean isDeckReader;

    private PlayPanel(){
        initPlayPanel();
        updatable = new ArrayList<>();
        initEndTurnButton();
    }

    private void initPlayPanel(){
        setSize(1200,700);
        setPreferredSize(new Dimension(1200,700));
        setBounds(0,0,1200,700);
        setLayout(null);
    }

    private static PlayPanel instance;
    public static PlayPanel getInstance(){
        if(instance == null)
            instance = new PlayPanel();
        return instance;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public static void startGame(String FirstPassive, String SecondPassive, boolean isDeckReader){
        getInstance().isDeckReader = isDeckReader;
        LogicController logicController;
        logicController = new LogicController(isDeckReader);
        getInstance().guiController = new GuiController(logicController.getResponses(), logicController.getRequests());
        getInstance().guiController.setPlayPanel(getInstance());
        logicController.start();
        getInstance().guiController.start();
        getInstance().updateByModel(logicController.getGameModel());
        getInstance().guiController.sendRequest(new SetPassiveRequest(getInstance().getGameModel().getFirstPlayer(), FirstPassive));
        getInstance().guiController.sendRequest(new SetPassiveRequest(getInstance().getGameModel().getSecondPlayer(), SecondPassive));
        getInstance().guiController.sendRequest(new SetHeroRequest(getInstance().getGameModel().getFirstPlayer()));
        getInstance().guiController.sendRequest(new SetHeroRequest(getInstance().getGameModel().getSecondPlayer()));

        getInstance().initBackButton();
        getInstance().initExitButton();

        getInstance().initFirstPlayerHandPanel();
        getInstance().initFirstBattleGroundPanel();
        getInstance().initFirstHeroPanel();
        getInstance().initFirstManaPanel();
        getInstance().initFirstWeaponPanel();
        getInstance().initFirstCardsRemained();
        getInstance().initFirstPlayerStartingHandCards();
        getInstance().FirstPlayerHandPanel.drawHandCards();
        getInstance().initFirstPowerPanel();


        getInstance().initSecondPlayerHandPanel();
        getInstance().initSecondBattleGroundPanel();
        getInstance().initSecondHeroPanel();
        getInstance().initSecondManaPanel();
        getInstance().initSecondWeaponPanel();
        getInstance().initSecondCardsRemained();
        getInstance().initSecondPlayerStartingHandCards();
        getInstance().SecondPlayerHandPanel.drawHandCards();
        getInstance().initSecondPowerPanel();

        getInstance().initCardDetails();

        getInstance().initGameLogPanel();
        getInstance().initProgressBard();

    }

    private void initProgressBard(){
        progressBar = new JProgressBar(0, 60);
        progressBar.setBounds(150, 335, 80, 30);
        progressBar.setStringPainted(true);
        progressBar.setString("0");
        progressBar.setValue(0);
        add(progressBar);
        time = new Time(progressBar);
        time.start();
    }

    private void initCardDetails(){
        getInstance().cardDetails = new CardDetails();
        getInstance().setBounds(1050, 225, 150, 250);
        add(cardDetails);
    }

    private void initFirstPlayerHandPanel(){
        getInstance().FirstPlayerHandPanel = new HandPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPlayerHandPanel.setBounds(170,580,600,120);
        getInstance().add(getInstance().FirstPlayerHandPanel);
    }
    private void initSecondPlayerHandPanel(){
        getInstance().SecondPlayerHandPanel = new HandPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPlayerHandPanel.setBounds(170,0,600,120);
        getInstance().add(getInstance().SecondPlayerHandPanel);
    }


    private void initFirstBattleGroundPanel(){
        getInstance().FirstPlayerBattleGroundPanel = new BattleGroundPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPlayerBattleGroundPanel.setBounds(240,340, 740, 130);
        getInstance().add(getInstance().FirstPlayerBattleGroundPanel);
    }
    private void initSecondBattleGroundPanel(){
        getInstance().SecondPlayerBattleGroundPanel = new BattleGroundPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPlayerBattleGroundPanel.setBounds(240,160, 740, 130);
        getInstance().add(getInstance().SecondPlayerBattleGroundPanel);
    }


    private void initFirstHeroPanel(){
        getInstance().FirstPlayerHeroPanel = new HeroPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPlayerHeroPanel.setBounds(555,475,103,117);
        getInstance().add(getInstance().FirstPlayerHeroPanel);
    }
    private void initSecondHeroPanel(){
        getInstance().SecondPlayerHeroPanel = new HeroPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPlayerHeroPanel.setBounds(555,70,103,117);
        getInstance().add(getInstance().SecondPlayerHeroPanel);
    }


    private void initFirstManaPanel(){
        getInstance().FirstPlayerManaPanel = new ManaPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPlayerManaPanel.setBounds(835, 635, 210,30);
        getInstance().add(getInstance().FirstPlayerManaPanel);
    }
    private void initSecondManaPanel(){
        getInstance().SecondPlayerManaPanel = new ManaPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPlayerManaPanel.setBounds(835, 50, 210,30);
        getInstance().add(getInstance().SecondPlayerManaPanel);
    }

    private void initFirstWeaponPanel(){
        getInstance().FirstPlayerWeaponPanel = new WeaponPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPlayerWeaponPanel.setBounds(450,480,70,100);
        getInstance().add(getInstance().FirstPlayerWeaponPanel);
    }
    private void initSecondWeaponPanel(){
        getInstance().SecondPlayerWeaponPanel = new WeaponPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPlayerWeaponPanel.setBounds(690,110,70,100);
        getInstance().add(getInstance().SecondPlayerWeaponPanel);
    }

    private void initFirstPowerPanel(){
        getInstance().FirstPower = new HeroPowerPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPower.setBounds(690,480,70,100);
        getInstance().add(getInstance().FirstPower);
    }
    private void initSecondPowerPanel(){
        getInstance().SecondPower = new HeroPowerPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPower.setBounds(450,110,70,100);
        getInstance().add(getInstance().SecondPower);
    }

    private void initGameLogPanel(){
        getInstance().gameLogPanel = new GameLog();
        getInstance().gameLogPanel.setBounds(0,0,150,700);
        getInstance().add(getInstance().gameLogPanel);
    }

    private void initFirstCardsRemained(){
        getInstance().FirstPlayerCardsRemained = new JLabel();
        getInstance().FirstPlayerCardsRemained.setText(27 + "");
        getInstance().FirstPlayerCardsRemained.setBounds(1010,470,50,50);
        getInstance().FirstPlayerCardsRemained.setFont(new Font("Tahoma", Font.BOLD, 30));
        getInstance().FirstPlayerCardsRemained.setForeground(Color.RED);
        getInstance().add(getInstance().FirstPlayerCardsRemained);
    }
    private void initSecondCardsRemained(){
        getInstance().SecondPlayerCardsRemained = new JLabel();
        getInstance().SecondPlayerCardsRemained.setText(27 + "");
        getInstance().SecondPlayerCardsRemained.setBounds(1010,80,50,50);
        getInstance().SecondPlayerCardsRemained.setFont(new Font("Tahoma", Font.BOLD, 30));
        getInstance().SecondPlayerCardsRemained.setForeground(Color.RED);
        getInstance().add(getInstance().SecondPlayerCardsRemained);
    }
    private void finishGame(){
        getInstance().remove(getInstance().SecondPlayerCardsRemained);
        getInstance().remove(getInstance().FirstPlayerCardsRemained);
        getInstance().gameModel = gameModel;
        getInstance().remove(getInstance().FirstPlayerManaPanel);
        getInstance().remove(getInstance().gameLogPanel);
    }

    private void initFirstPlayerStartingHandCards(){
        if(!isDeckReader) {
            Random random = new Random();
            int cardIndex = 0;
            cardIndex = random.nextInt(30);
            //TODO
            getInstance().gameModel.getFirstPlayer().getHandCards().add
                    (getInstance().getGameModel().getFirstPlayer().getCurrentDeck().get(cardIndex));
            getInstance().gameModel.getFirstPlayer().getCurrentDeck().remove(cardIndex);

            cardIndex = random.nextInt(29);
            getInstance().gameModel.getFirstPlayer().getHandCards().add
                    (getInstance().getGameModel().getFirstPlayer().getCurrentDeck().get(cardIndex));
            getInstance().gameModel.getFirstPlayer().getCurrentDeck().remove(cardIndex);


            cardIndex = random.nextInt(28);
            getInstance().gameModel.getFirstPlayer().getHandCards().add
                    (getInstance().getGameModel().getFirstPlayer().getCurrentDeck().get(cardIndex));
            getInstance().gameModel.getFirstPlayer().getCurrentDeck().remove(cardIndex);
        }else{
            for (int i = 0; i < 3; i++) {
                getInstance().gameModel.getFirstPlayer().getHandCards().add
                        (getInstance().getGameModel().getFirstPlayer().getCurrentDeck().get(0));
                getInstance().gameModel.getFirstPlayer().getCurrentDeck().remove(0);
            }
        }

    }
    private void initSecondPlayerStartingHandCards(){
        if(!isDeckReader) {
            Random random = new Random();
            int cardIndex = 0;
            cardIndex = random.nextInt(30);
            getInstance().gameModel.getSecondPlayer().getHandCards().add
                    (getInstance().getGameModel().getSecondPlayer().getCurrentDeck().get(cardIndex));
            getInstance().gameModel.getSecondPlayer().getCurrentDeck().remove(cardIndex);


            cardIndex = random.nextInt(29);
            getInstance().gameModel.getSecondPlayer().getHandCards().add
                    (getInstance().getGameModel().getSecondPlayer().getCurrentDeck().get(cardIndex));
            getInstance().gameModel.getSecondPlayer().getCurrentDeck().remove(cardIndex);

            cardIndex = random.nextInt(28);
            getInstance().gameModel.getSecondPlayer().getHandCards().add
                    (getInstance().getGameModel().getSecondPlayer().getCurrentDeck().get(cardIndex));
            getInstance().gameModel.getSecondPlayer().getCurrentDeck().remove(cardIndex);
        }else{
            for (int i = 0; i < 3; i++) {
                getInstance().gameModel.getSecondPlayer().getHandCards().add
                        (getInstance().getGameModel().getSecondPlayer().getCurrentDeck().get(0));
                getInstance().gameModel.getSecondPlayer().getCurrentDeck().remove(0);
            }
        }

    }


    public static class GameLog extends JPanel{
        public TextArea gameLog;
       private GameLog(){
           setPreferredSize(new Dimension(150,700));
           initGameLog();
       }
        private void initGameLog(){
            gameLog = new TextArea();
            gameLog.setBounds(0,0,150, 700);
            gameLog = new TextArea();
            gameLog.setPreferredSize(new Dimension(150,700));
            gameLog.setBackground(Color.LIGHT_GRAY);
            gameLog.setForeground(Color.BLACK);
            gameLog.setFont(new Font("Tahoma", Font.BOLD, 12));
            add(gameLog);
        }
        public void appendText(String text){
            getInstance().gameLogPanel.gameLog.append(text + "\n");
        }
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
        getInstance().add(exitButton);
    }

    private void initBackButton(){
        backButton = new JButton("BACK");
        backButton.setBounds(1120,60,70,30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Log.bodyLogger("navigate", "main menu");
                Data.updateAllData();
                finishGame();
                MainFrame.cl.show(MainFrame.panelCont, "3");
            }
        });
        getInstance().add(backButton);
    }

    private static class BattleGroundPanel extends JPanel implements UpdateGame{

        private List<Card> cards;
        private PlayerModel playerModel;
        private BattleGroundPanel(PlayerModel playerModel){
            this.playerModel = playerModel;
            getInstance().updatable.add(this);
            cards = new ArrayList<>();
            initBattleGround();

        }
        private void initBattleGround(){
            setLayout(null);
            setBackground(new Color(0,0,0,5));
        }

        @Override
        public void update() {
            removeAll();
            drawBattleGroundCards();
            setBackground(new Color(0,0,0,5));
        }
        private void drawBattleGroundCards(){
            cards = playerModel.getBattleGroundCards();
            int i = 0;
            for(Card card : cards){
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {

                        if(getInstance().guiController.isNeedTarget()) {
                            getInstance().guiController.sendRequest(new MyTargetRequest(playerModel, (Object)card));
                        }else{
                            if (card instanceof Minion) {
                                getInstance().guiController.sendRequest(new AttackRequest(playerModel, (Minion) card));
                            }
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                        getInstance().cardDetails.setCard(card);
                        getInstance().cardDetails.drawCard();
                        getInstance().revalidate();
                        getInstance().repaint();
                    }
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                        getInstance().cardDetails.setCard(null);
                        getInstance().cardDetails.drawCard();
                        getInstance().revalidate();
                        getInstance().repaint();
                    }
                };
                InGameImageCardPanel cardImage = new InGameImageCardPanel(card.getName(), mouseListener, card);
                cardImage.setBounds((i*60), 30, 60, 85);
                add(cardImage);
                i++;
            }
            setBackground(new Color(0,0,0,5));
//            getInstance().revalidate();
//            getInstance().repaint();
        }
    }
    private static class HandPanel extends JPanel implements UpdateGame{
        private List<Card> cards;
        private PlayerModel playerModel;
        private HandPanel(PlayerModel playerModel){
            this.playerModel = playerModel;
            getInstance().updatable.add(this);
            cards = playerModel.getHandCards();
            setLayout(null);
            setBackground(new Color(0,0,0,5));

        }

        @Override
        public void update() {
            removeAll();
            drawHandCards();
            setBackground(new Color(0,0,0,5));
        }
        private void drawHandCards(){
            int i = 0;
            for(int j = 0; j < cards.size(); j++){
                i++;
                int finalJ = j;
                MouseListener mouseListener = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if(!getInstance().guiController.isNeedTarget())
                            getInstance().guiController.sendRequest(new AddToBattleGroundRequest(playerModel, cards.get(finalJ)));

//                        getInstance().updatePanel();
//                        getInstance().revalidate();
//                        getInstance().repaint();
//                        updateUI();
                    }
                };
                InGameImageCardPanel cardImage = new InGameImageCardPanel(cards.get(finalJ).getName(), mouseListener, cards.get(finalJ));
                cardImage.setBounds(600 - (i*60), 10, 60, 85);
                add(cardImage);
            }
            setBackground(new Color(0,0,0,5));
//            getInstance().revalidate();
//            getInstance().repaint();
        }
    }


    private void initEndTurnButton(){
        endTurn = new JButton();
        endTurn.setBounds(940,290,110,50);
        endTurn.setContentAreaFilled(false);
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                guiController.sendRequest(new endTurn(null, isDeckReader));

//                getInstance().updatePanel();

                getInstance().FirstPlayerCardsRemained.setText(getInstance().gameModel.getFirstPlayer().getCurrentDeck().size() + "");
                getInstance().FirstPlayerCardsRemained.revalidate();
                getInstance().FirstPlayerCardsRemained.repaint();

                getInstance().SecondPlayerCardsRemained.setText(getInstance().gameModel.getSecondPlayer().getCurrentDeck().size() + "");
                getInstance().SecondPlayerCardsRemained.revalidate();
                getInstance().SecondPlayerCardsRemained.repaint();

//                getInstance().updatePanel();
//                getInstance().revalidate();
//                getInstance().repaint();
            }
        });
        add(endTurn);
    }

    private static class HeroPowerPanel extends JPanel{
        private PlayerModel playerModel;

        private HeroPowerPanel(PlayerModel playerModel){
            this.playerModel = playerModel;
            setLayout(null);
            setPreferredSize(new Dimension(70,100));
            setBackground(new Color(0,0,0,5));
            drawPower();
        }
        private void drawPower(){
            MouseListener mouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!playerModel.getHero().getName().equalsIgnoreCase("Hunter"))
                        if(!getInstance().guiController.isNeedTarget()) {
                            if(playerModel.getHero().isCanAttack())
                                getInstance().guiController.sendRequest(new PowerRequest(playerModel));
                        }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            };
            InGameHeroPowerPanel powerImage = new InGameHeroPowerPanel(playerModel.getHero().getName(), mouseListener);
            powerImage.setBounds(0, 0, 70, 100);
            add(powerImage);
            setBackground(new Color(0,0,0,5));
        }
    }

    private static class CardDetails extends JPanel implements UpdateGame{

        private Card card = null;

        @Override
        public void update() {
            drawCard();
        }

        public void setCard(Card card) {
            this.card = card;
        }

        private CardDetails(){
            setLayout(null);
            getInstance().updatable.add(this);
            setPreferredSize(new Dimension(150,250));
            setBounds(1050, 225, 150, 250);
            setBackground(new Color(0,0,0,5));
        }
        private void drawCard(){
            removeAll();
            if(card != null){
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                };
                ImageCardDetailPanel cardImage = new ImageCardDetailPanel(card.getName(), mouseListener, card);
                cardImage.setBounds(0,0,150,250);
                add(cardImage);
                this.revalidate();
                this.repaint();
            }
            setBackground(Color.WHITE);
        }
    }

    private static class WeaponPanel extends JPanel implements UpdateGame{

        private PlayerModel playerModel;

        private WeaponPanel(PlayerModel playerModel){
            this.playerModel = playerModel;
            setLayout(null);
            getInstance().updatable.add(this);
            setPreferredSize(new Dimension(70,100));
            drawWeapon();
            setBackground(new Color(0,0,0,5));
        }

        private void drawWeapon(){
            removeAll();
            Weapon weapon = playerModel.getWeapon();
            if(weapon != null) {
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if(!getInstance().guiController.isNeedTarget()){
                            getInstance().guiController.sendRequest(new AttackRequest(playerModel, weapon));
                        }
                    }
                    @Override
                    public void mousePressed(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {

                    }
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) { }
                };
                InGameImageCardPanel cardImage = new InGameImageCardPanel(weapon.getName(), mouseListener, weapon);
                cardImage.setBounds(0, 0, 70, 100);
                add(cardImage);
            }
            setBackground(new Color(0,0,0,5));
//            getInstance().revalidate();
//            getInstance().repaint();
        }

        @Override
        public void update() {
            removeAll();
            drawWeapon();
            setBackground(new Color(0,0,0,5));
        }
    }

    private static class HeroPanel extends JPanel implements UpdateGame{

        private PlayerModel playerModel;



        private HeroPanel(PlayerModel playerModel){

            this.playerModel = playerModel;
            setLayout(null);
            getInstance().updatable.add(this);
            setPreferredSize(new Dimension(103,117));
            drawHero();
            setBackground(new Color(0,0,0,5));

        }
        private void drawHero(){
            removeAll();
            Hero hero = playerModel.getHero();
            String heroName = hero.getName();
            MouseListener mouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    if(getInstance().guiController.isNeedTarget()){
                        getInstance().guiController.sendRequest(new MyTargetRequest(playerModel, hero));
                    }
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
            InGameImageHeroPanel heroImage = new InGameImageHeroPanel(heroName, mouseListener, hero);
            heroImage.setBounds(0, 0, 103, 117);
            add(heroImage);
            setBackground(new Color(0, 0, 0, 5));
//            getInstance().revalidate();
//            getInstance().repaint();
        }


        @Override
        public void update() {
            drawHero();
        }
    }
    private static class ManaPanel extends JPanel implements UpdateGame{
        private PlayerModel playerModel;
        private ManaPanel(PlayerModel playerModel){
            this.playerModel = playerModel;
            getInstance().updatable.add(this);
            initMana();
            drawMana();
        }
        private void initMana(){
            setLayout(null);
            setBackground(new Color(0,0,0,5));
        }
        private void drawMana(){
            removeAll();
            if(playerModel == getInstance().getGameModel().getCurrentPlayer()) {
                for (int i = 0; i < playerModel.getCurrentMana(); i++) {
                    final JLabel mana = new JLabel(new ImageIcon("images/gem Images/gem" + (i + 1) + ".jpg"));
                    mana.setBounds(5 + 20 * i, 5, 20, 20);
                    add(mana);
                }
            }
            setBackground(new Color(0,0,0,5));
//            getInstance().revalidate();
//            getInstance().repaint();
        }

        @Override
        public void update() {
            drawMana();
            setBackground(new Color(0,0,0,5));
        }
    }



    public void updateByModel(GameModel gameModel) {
        this.gameModel = gameModel;
        updatePanel();
    }

    private synchronized void updatePanel(){
        for(int i = 0; i < updatable.size() - 1; i++){
            updatable.get(i).update();
            getInstance().revalidate();
            getInstance().repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("GameBoardImage"), 0, 0, 1200, 700, null);
    }

    public static int sec = 0;

    public class Time extends Thread{

        private JLabel hurry;
        private JProgressBar progressBar;

        public Time(JProgressBar progressBar){
            this. progressBar = progressBar;
            initHurry();
        }
        public void initHurry(){
            hurry = new JLabel();
            hurry.setFont(new Font("Tahoma", Font.BOLD, 30));
            hurry.setForeground(Color.RED);
            hurry.setBackground(Color.DARK_GRAY);
            hurry.setBounds(500, 350, 200, 50);
            getInstance().add(hurry);
            hurry.setVisible(false);
        }

        @Override
        public void run() {
            while (true){
                sec += 1;
                progressBar.setString(sec + "");
                progressBar.setValue(sec);
                if(sec >= 40 && sec <= 47){
                    progressBar.setString("hurry up");
                }
                try {
                    this.sleep(500);
                    progressBar.setString(sec + "");
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(sec == 60){
                    sec = 0;
                    getInstance().guiController.sendRequest(new endTurn(getInstance().getGameModel().getCurrentPlayer(), isDeckReader));
                }
            }
        }
    }
}
