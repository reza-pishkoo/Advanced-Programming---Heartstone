package gui.playPanel;

import cards.Card;
import cards.Minion;
import cards.Weapon;
import config.Constants;
import guiController.GuiController;
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

    private HandPanel SecondPlayerHandPanel;
    private BattleGroundPanel SecondPlayerBattleGroundPanel;
    private HeroPanel SecondPlayerHeroPanel;
    private WeaponPanel SecondPlayerWeaponPanel;
    private JLabel SecondPlayerCardsRemained;
    private ManaPanel SecondPlayerManaPanel;

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

    public static void startGame(){
        LogicController logicController;
        logicController = new LogicController();
        getInstance().guiController = new GuiController(logicController.getResponses(), logicController.getRequests());
        getInstance().guiController.setPlayPanel(getInstance());
        logicController.start();
        getInstance().guiController.start();
        getInstance().updateByModel(logicController.getGameModel());

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


        getInstance().initSecondPlayerHandPanel();
        getInstance().initSecondBattleGroundPanel();
        getInstance().initSecondHeroPanel();
        getInstance().initSecondManaPanel();
        getInstance().initSecondWeaponPanel();
//        getInstance().initSecondCardsRemained();
        getInstance().initSecondPlayerStartingHandCards();
        getInstance().SecondPlayerHandPanel.drawHandCards();


        getInstance().initGameLogPanel();
        getInstance().updateAll();
    }

    private void updateAll(){
        getInstance().updatePanel();
        getInstance().revalidate();
        getInstance().repaint();
    }

    private void initFirstPlayerHandPanel(){
        getInstance().FirstPlayerHandPanel = new HandPanel(getInstance().getGameModel().getFirstPlayer());
        getInstance().FirstPlayerHandPanel.setBounds(170,580,600,120);
        getInstance().add(getInstance().FirstPlayerHandPanel);
    }
    private void initSecondPlayerHandPanel(){
        getInstance().SecondPlayerHandPanel = new HandPanel(getInstance().getGameModel().getSecondPlayer());
        getInstance().SecondPlayerHandPanel.setBounds(170,1,600,120);
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
        getInstance().SecondPlayerWeaponPanel.setBounds(600,50,70,100);
        getInstance().add(getInstance().SecondPlayerWeaponPanel);
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
    private void finishGame(){
        getInstance().remove(getInstance().FirstPlayerCardsRemained);
        getInstance().gameModel = gameModel;
        getInstance().remove(getInstance().FirstPlayerManaPanel);
        getInstance().remove(getInstance().gameLogPanel);
    }

    private void initFirstPlayerStartingHandCards(){
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

    }
    private void initSecondPlayerStartingHandCards(){
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
            revalidate();
            repaint();
            setBackground(new Color(0,0,0,5));
        }
        private void drawBattleGroundCards(){
            cards = playerModel.getBattleGroundCards();
            int i = 0;
            for(Card card : cards){
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        System.out.println(getInstance().guiController.isNeedTarget());
                        if(getInstance().guiController.isNeedTarget()) {
                            System.out.println("toosh");
                            JOptionPane.showMessageDialog(null, "choose target", "target", JOptionPane.ERROR_MESSAGE);
                            getInstance().guiController.sendRequest(new MinionTargetRequest(playerModel, (Object)card));
                        }else{
                            System.out.println("a");
                            if (card instanceof Minion) {
                                System.out.println("b");
                                getInstance().guiController.sendRequest(new MinionAttackRequest(playerModel, (Minion) card));
                            }
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
                InGameImageCardPanel cardImage = new InGameImageCardPanel(card.getName(), mouseListener, true, card);
                cardImage.setBounds((i*50), 30, 50, 70);
                add(cardImage);
                i++;
            }
            setBackground(new Color(0,0,0,5));
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
            revalidate();
            repaint();
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

                        getInstance().guiController.sendRequest(new AddToBattleGroundRequest(playerModel, cards.get(finalJ)));


                        getInstance().updatePanel();
                        getInstance().revalidate();
                        getInstance().repaint();
                        updateUI();
                    }
                };
                InGameImageCardPanel cardImage = new InGameImageCardPanel(cards.get(finalJ).getName(), mouseListener, true, cards.get(finalJ));
                cardImage.setBounds(600 - (i*50), 10, 50, 70);
                add(cardImage);
            }
            setBackground(new Color(0,0,0,5));
        }
    }


    private void initEndTurnButton(){
        endTurn = new JButton();
        endTurn.setBounds(940,290,110,50);
        endTurn.setContentAreaFilled(false);
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                guiController.sendRequest(new endTurn(null));

                getInstance().updatePanel();
                getInstance().FirstPlayerCardsRemained.setText(getInstance().gameModel.getFirstPlayer().getCurrentDeck().size() + "");
                getInstance().FirstPlayerCardsRemained.revalidate();
                getInstance().FirstPlayerCardsRemained.repaint();

                getInstance().updatePanel();
                getInstance().revalidate();
                getInstance().repaint();
            }
        });
        add(endTurn);
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

            Weapon weapon = playerModel.getWeapon();
            if(weapon != null) {
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) { }
                    @Override
                    public void mousePressed(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) { }
                    @Override
                    public void mouseExited(MouseEvent mouseEvent) { }
                };
                InGameImageCardPanel cardImage = new InGameImageCardPanel(weapon.getName(), mouseListener, true, weapon);
                cardImage.setBounds(0, 0, 70, 100);
                add(cardImage);
            }
            setBackground(new Color(0,0,0,5));
        }

        @Override
        public void update() {
            removeAll();
            drawWeapon();
            setBackground(new Color(0,0,0,5));
        }
    }

    private static class HeroPanel extends JPanel{

        private PlayerModel playerModel;

        private HeroPanel(PlayerModel playerModel){
            setBackground(new Color(0,0,0,5));
            this.playerModel = playerModel;
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(new ImageIcon("images/playImages/" +
                    playerModel.getHero() + ".jpg").getImage() , 0, 0, null);
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

            for (int i = 0; i < playerModel.getCurrentMana(); i++) {
                final JLabel mana = new JLabel(new ImageIcon("images/gem Images/gem" + (i + 1) + ".jpg"));
                mana.setBounds(5 + 20 * i, 5, 20, 20);
                add(mana);
            }
            setBackground(new Color(0,0,0,5));
        }

        @Override
        public void update() {
            drawMana();
            revalidate();
            repaint();
            setBackground(new Color(0,0,0,5));
        }
    }



    public void updateByModel(GameModel gameModel) {
        this.gameModel = gameModel;
        updatePanel();
    }

    private void updatePanel(){
        for(UpdateGame item : updatable){
            item.update();//remeber to delete
            revalidate();
            repaint();
//            updateUI();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.backgroundImages.get("GameBoardImage"), 0, 0, 1200, 700, null);
    }
}
