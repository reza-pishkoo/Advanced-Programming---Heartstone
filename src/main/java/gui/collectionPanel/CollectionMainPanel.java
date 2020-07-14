package gui.collectionPanel;

import cards.CardClass;
import cards.CardFactory;
import cards.Deck;
import collection.Collection;
import config.Constants;
import data.Data;
import data.Log;
import gui.MainFrame;
import gui.cardImage.ImageCardPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static CLI.Main.currentUser;
import static CLI.Main.market;

public class CollectionMainPanel extends JPanel{

    private static CollectionUpTapePanel collectionUpTapePanel;
    private static CollectionDetailsPanel collectionDetailsPanel;
    private static CardsPanel cardsPanel;


    public CollectionMainPanel(){
        setSize(1200,700);
        setPreferredSize(new Dimension(1200,700));
        setBounds(0,0,1200,700);
        setLayout(new BorderLayout());

    }
    private static CollectionMainPanel instance;
    public static CollectionMainPanel getInstance(){
        if(instance == null)
            instance = new CollectionMainPanel();
        return instance;
    }
    public void StartCollection(){
        collectionUpTapePanel = new CollectionUpTapePanel();
        getInstance().add(collectionUpTapePanel, BorderLayout.NORTH);

        collectionDetailsPanel = new CollectionDetailsPanel();
        getInstance().add(collectionDetailsPanel, BorderLayout.EAST);

        cardsPanel = CardsPanel.getInstance();
        getInstance().add(cardsPanel, BorderLayout.CENTER);
    }

    public void  updateCardsPanel(){
        cardsPanel.mainFilterPage.warlockPanel.drawHeroTypeCards();
        cardsPanel.mainFilterPage.magePanel.drawHeroTypeCards();
        cardsPanel.mainFilterPage.hunterPanel.drawHeroTypeCards();
        cardsPanel.mainFilterPage.priestPanel.drawHeroTypeCards();
        cardsPanel.mainFilterPage.rougePanel.drawHeroTypeCards();
        cardsPanel.mainFilterPage.neutralPanel.drawHeroTypeCards();
        cardsPanel.mainFilterPage.collectionSearchPanel.search("");
    }


    public static class CollectionUpTapePanel extends JPanel{
        private JButton exitButton;
        private JButton minusButton;
        private JButton backButton;

        private CollectionUpTapePanel(){
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

        private void initExitButton(){
            exitButton = new JButton("Exit");
            exitButton.setBounds(1110,2,80,25);
            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Log.bodyLogger("exit game", "sign_out");
                    Data.updateAllData();
                    System.exit(0);
                }
            });
        }
        private void initMinusButton9(){
            minusButton = new JButton("Minus");
            minusButton.setBounds(1000,2,80,25);
            minusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    MainFrame.getInstance().setState(Frame.ICONIFIED);
                }
            });
        }
        private void initBackButton(){
            backButton = new JButton("Back");
            backButton.setBounds(50,2,80,25);
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




    public static class CollectionDetailsPanel extends JPanel{

        private JLabel title;
        private JButton addDeck;
        private JButton editNameButton;
        private JButton editHeroName;
    private  JButton editheroButton;
        private CollectionDetailsPanel(){
            setPreferredSize(new Dimension(300, 700));
            setLayout(null);
            initButton();
            for(int i = 1; i <= currentUser.getAllDecks().size(); i++)
                drawDeckButton(currentUser.getAllDecks().get(i-1).getDeckName(), currentUser.getAllDecks().get(i-1).getHero(), i);

        }

        private void drawDeckButton(String deckName, String heroName, int i){
            JButton button = new JButton(deckName + "        " + heroName);
            button.setBounds(10, i*45 + 5, 280, 40);
            button.setBackground(Color.LIGHT_GRAY);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    for(int i = 0; i < currentUser.getAllDecks().size(); i++){
                        if(currentUser.getAllDecks().get(i).getDeckName().equalsIgnoreCase(deckName)) {
                            currentUser.setCurDeck(i);
                            cardsPanel.mainFilterPage.deckPanel.drawDeck();
                            cardsPanel.mainFilterPage.deckPanel.repaint();
                            cardsPanel.mainFilterPage.deckPanel.revalidate();
                            repaint();
                            revalidate();
                            Log.bodyLogger("choose deck ", deckName);
                        }
                    }
                }
            });
            add(button);
        }
        private void initButton(){
            title = new JLabel("DECKS");
            title.setFont(new Font("Tahoma", Font.BOLD, 30));
            title.setForeground(Color.DARK_GRAY);
            title.setBounds(100, 0, 170, 45);
            add(title);


            addDeck = new JButton("ADD DECK");
            addDeck.setBounds(100, 600, 100,30);
            addDeck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    newDeck();
                }
            });
            add(addDeck);

            editNameButton = new JButton("EDIT NAME");
            editNameButton.setBounds(10, 635, 100, 30);
            editNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String name = "";
                    while (true) {
                        name = JOptionPane.showInputDialog("Enter your name");
                        boolean isValidName = true;
                        for (Deck deck : currentUser.getAllDecks()) {
                            if (deck.getDeckName().equalsIgnoreCase(name))
                                isValidName = false;
                            if(name.length() == 0)
                                isValidName = false;
                        }
                        if (isValidName) {
                            Log.bodyLogger("change deck name", name);
                            break;
                        }
                    }
                    currentUser.getAllDecks().get(currentUser.getCurDeck()).setDeckName(name);
                    removeAll();
                    for(int i = 1; i <= currentUser.getAllDecks().size(); i++)
                        drawDeckButton(currentUser.getAllDecks().get(i-1).getDeckName(), currentUser.getAllDecks().get(i-1).getHero(), i);
                    initButton();
                    repaint();
                    revalidate();
                }
            });
            add(editNameButton);

            editheroButton = new JButton("EDIT Hero");
            editheroButton.setBounds(190, 635, 100, 30);
            editheroButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String [] heroNames = new String[currentUser.getAllHeroes().size()];
                    for(int i = 0; i < currentUser.getAllHeroes().size(); i++){
                        heroNames[i] = currentUser.getAllHeroes().get(i).getName();
                    }
                    String HName = (String) JOptionPane.showInputDialog(null, "Choose your hero.", "hero name",
                            JOptionPane.QUESTION_MESSAGE, null, heroNames, heroNames[0]);

                    boolean canChangeHero = true;
                    for(String card : currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards()){
                        if(!HName.equalsIgnoreCase(CardFactory.build(card, null).getCardClass().cardClass()) &&
                                !CardFactory.build(card, null).getCardClass().cardClass().equalsIgnoreCase("neutral"))
                            canChangeHero = false;
                    }
                    if(canChangeHero) {
                        currentUser.getAllDecks().get(currentUser.getCurDeck()).setHero(HName);
                        currentUser.getAllDecks().get(currentUser.getCurDeck()).setUse(0);
                        currentUser.getAllDecks().get(currentUser.getCurDeck()).setWin(0);
                        Log.bodyLogger("change deck's hero ", HName);
                        removeAll();
                        for (int i = 1; i <= currentUser.getAllDecks().size(); i++)
                            drawDeckButton(currentUser.getAllDecks().get(i - 1).getDeckName(), currentUser.getAllDecks().get(i - 1).getHero(), i);
                        initButton();
                        repaint();
                        revalidate();
                    }else{
                        JOptionPane.showMessageDialog(null, "you cant change your hero \n" +
                                " you have cards from another class in your deck", "error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            add(editheroButton);



        }


        private void newDeck(){
            if(currentUser.getAllDecks().size() > 11){
                JOptionPane.showMessageDialog(null, "you cant add new deck", "error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String name = "";
            while (true) {
                name = JOptionPane.showInputDialog("Enter your name");
                boolean isValidName = true;
                for (Deck deck : currentUser.getAllDecks()) {
                    if (deck.getDeckName().equalsIgnoreCase(name))
                        isValidName = false;
                    if(name.length() == 0)
                        isValidName = false;
                }
                if (isValidName) {
                    break;
                }
            }
            String [] heroNames = new String[currentUser.getAllHeroes().size()];
            for(int i = 0; i < currentUser.getAllHeroes().size(); i++){
                heroNames[i] = currentUser.getAllHeroes().get(i).getName();
            }
            String HName = (String) JOptionPane.showInputDialog(null, "Choose your hero.", "hero name",
                    JOptionPane.QUESTION_MESSAGE, null, heroNames, heroNames[0]);
            Deck deck = new Deck(HName, name);
            currentUser.getAllDecks().add(deck);
            Log.bodyLogger("new deck", name);
            currentUser.setCurDeck(currentUser.getAllDecks().size()-1);
            drawDeckButton(name, HName, currentUser.getAllDecks().size());
            repaint();
            revalidate();
        }
    }





    public static class CardsPanel extends JPanel{

        private UpperFilterTape upperFilterTape;
        private LowerFilterTape lowerFilterTape;
        private MainFilterPage mainFilterPage;

        private CardsPanel(){
            setLayout(new BorderLayout());

            setSize(900, 670);
            setPreferredSize(new Dimension(900, 670));

            upperFilterTape = new UpperFilterTape();
            add(upperFilterTape, BorderLayout.NORTH);

            lowerFilterTape = new LowerFilterTape();
            add(lowerFilterTape, BorderLayout.SOUTH);

            mainFilterPage = new MainFilterPage();
            add(mainFilterPage, BorderLayout.CENTER);

        }
        private static CardsPanel instance;
        public static CardsPanel getInstance(){
            if(instance == null)
                instance = new CardsPanel();
            return instance;
        }


        public class UpperFilterTape extends JPanel{
            private JTextField searchField;


            public UpperFilterTape(){
                setLayout(null);
                setPreferredSize(new Dimension(900, 50));
                setBackground(Color.darkGray);
                initSearchField();
                add(searchField);
            }

            public void initSearchField(){
                searchField = new JTextField();
                searchField.setBounds(20,5,250,35);
                searchField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent documentEvent) {
                        String searchedWord = searchField.getText();
                        CollectionSearchPanel.getInstance().search(searchedWord);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent documentEvent) {
                        String searchedWord = searchField.getText();
                        CollectionSearchPanel.getInstance().search(searchedWord);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent documentEvent) {
                        String searchedWord = searchField.getText();
                        CollectionSearchPanel.getInstance().search(searchedWord);
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Constants.backgroundImages.get("UpperTape"), 0, 0, 900, 60, null);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("COLLECTION MENU", 350, 30);
            }
        }


        public class LowerFilterTape extends JPanel{
            private JButton mana1;
            private JButton mana2;
            private JButton mana3;
            private JButton mana4;
            private JButton mana5;
            private JButton mana6;
            private JButton mana7;
            private JButton mana8;
            private JButton mana9;
            private JButton mana10;

            public LowerFilterTape(){
                setPreferredSize(new Dimension(900, 60));
                setBackground(Color.darkGray);
                setLayout(null);
                addManaButtons();
            }

            private void initButtons(){
                mana1 = new JButton("1");
                mana1.setBounds(10,15,50,30);
                mana1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(1);
                    }
                });
                mana2 = new JButton("2");
                mana2.setBounds(70,15,50,30);
                mana2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(2);
                    }
                });
                mana3 = new JButton("3");
                mana3.setBounds(130,15,50,30);
                mana3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(3);
                    }
                });
                mana4 = new JButton("4");
                mana4.setBounds(190,15,50,30);
                mana4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(4);
                    }
                });
                mana5 = new JButton("5");
                mana5.setBounds(250,15,50,30);
                mana5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(5);
                    }
                });
                mana6 = new JButton("6");
                mana6.setBounds(310,15,50,30);
                mana6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(6);
                    }
                });
                mana7 = new JButton("7");
                mana7.setBounds(370,15,50,30);
                mana7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                       CollectionSearchPanel.getInstance().FilterByMana(7);
                    }
                });
                mana8 = new JButton("8");
                mana8.setBounds(430,15,50,30);
                mana8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                       CollectionSearchPanel.getInstance().FilterByMana(8);
                    }
                });
                mana9 = new JButton("9");
                mana9.setBounds(490,15,50,30);
                mana9.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(9);
                    }
                });
                mana10 = new JButton("10");
                mana10.setBounds(550,15,50,30);
                mana10.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        CollectionSearchPanel.getInstance().FilterByMana(10);
                    }
                });


            }
            private void addManaButtons(){
                initButtons();
                add(mana1);
                add(mana2);
                add(mana3);
                add(mana4);
                add(mana5);
                add(mana6);
                add(mana7);
                add(mana8);
                add(mana9);
                add(mana10);
            }
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Constants.backgroundImages.get("LowerTape"), 0, 0, 900, 60, null);
            }
        }


        private class MainFilterPage extends JTabbedPane{
            private CollectionSearchPanel collectionSearchPanel;
            private DeckPanel deckPanel;
            private CollectionHeroPanel neutralPanel;
            private CollectionHeroPanel magePanel;
            private CollectionHeroPanel warlockPanel;
            private CollectionHeroPanel hunterPanel;
            private CollectionHeroPanel priestPanel;
            private CollectionHeroPanel rougePanel;
            private JScrollPane scrollPane;
            private JScrollPane scrollPane1;
            private JScrollPane scrollPane2;
            private JScrollPane scrollPane3;
            private JScrollPane scrollPane4;
            private JScrollPane scrollPane5;
            private JScrollPane scrollPane6;
            private JScrollPane scrollPane7;

            public MainFilterPage(){

                deckPanel = new DeckPanel();
                scrollPane3 = new JScrollPane(deckPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane3.setPreferredSize(new Dimension(900, 560));
                addTab("DECK", scrollPane3);
                setMnemonicAt(0, KeyEvent.VK_4);


                collectionSearchPanel = CollectionSearchPanel.getInstance();
                scrollPane = new JScrollPane(collectionSearchPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setPreferredSize(new Dimension(900, 560));
                addTab("Search", scrollPane);
                setMnemonicAt(1, KeyEvent.VK_1);

                neutralPanel = new CollectionHeroPanel(CardClass.NEUTRAL, deckPanel);
                scrollPane1 = new JScrollPane(neutralPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane1.setPreferredSize(new Dimension(900, 560));
                addTab("NEUTRAL", scrollPane1);
                setMnemonicAt(2, KeyEvent.VK_2);

                magePanel = new CollectionHeroPanel(CardClass.MAGE, deckPanel);
                scrollPane2 = new JScrollPane(magePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane2.setPreferredSize(new Dimension(900, 560));
                addTab("MAGE", scrollPane2);
                setMnemonicAt(3, KeyEvent.VK_3);

                rougePanel = new CollectionHeroPanel(CardClass.ROUGE, deckPanel);
                scrollPane3 = new JScrollPane(rougePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane3.setPreferredSize(new Dimension(900, 560));
                addTab("ROUGE", scrollPane3);
                setMnemonicAt(4, KeyEvent.VK_4);

                hunterPanel = new CollectionHeroPanel(CardClass.HUNTER, deckPanel);
                scrollPane4 = new JScrollPane(hunterPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane4.setPreferredSize(new Dimension(900, 560));
                addTab("PALADIN", scrollPane4);
                setMnemonicAt(5, KeyEvent.VK_5);

                priestPanel = new CollectionHeroPanel(CardClass.PRIEST, deckPanel);
                scrollPane5 = new JScrollPane(priestPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane5.setPreferredSize(new Dimension(900, 560));
                addTab("PRIEST", scrollPane5);
                setMnemonicAt(6, KeyEvent.VK_6);

                warlockPanel = new CollectionHeroPanel(CardClass.WARLOCK, deckPanel);
                scrollPane6 = new JScrollPane(warlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane6.setPreferredSize(new Dimension(900, 560));
                addTab("WARLOCK", scrollPane6);
                setMnemonicAt(7, KeyEvent.VK_7);
                }
        }
    }
    public static class CollectionHeroPanel extends JPanel{
        private CardClass cardClass;
        private List<String> heroCards;

        private DeckPanel deckPanel;
        private CollectionHeroPanel(CardClass cardClass, DeckPanel deckPanel){
            this.cardClass = cardClass;
            this.deckPanel = deckPanel;
            setLayout(new FlowLayout());
            heroCards = new ArrayList<>();
            setHeroCards();
        }


        public void drawHeroTypeCards(){
            removeAll();
            setPreferredSize(new Dimension(900, Math.max(((heroCards.size()/5)+1)*225, 550)));
            for(String cardName : heroCards){
                boolean have = currentUser.getAllCards().contains(cardName);
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                       if(have){
                           if(JOptionPane.showConfirmDialog(null, "do you wanna add this card to your deck?",
                                   "remove", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                               JOptionPane.showMessageDialog(null, Collection.addToDeck(cardName), "add", JOptionPane.OK_OPTION);
                               deckPanel.drawDeck();
                               deckPanel.repaint();
                               deckPanel.revalidate();
                           }
                       }else{
                           if(JOptionPane.showConfirmDialog(null, "you dont have this card \n do you wanna buy it?",
                                   "Go to store", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                               MainFrame.cl.show(MainFrame.panelCont, "4");
                               Log.bodyLogger("navigate", "store menu");
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
                ImageCardPanel card;
                if(have)
                    card = new ImageCardPanel(cardName, mouseListener, true);
                else
                    card = new ImageCardPanel(cardName, mouseListener, false);
                card.setPreferredSize(new Dimension(160,200));
                add(card);
            }
        }
        public void setHeroCards(){
            for(String cardName : market.getAllCards()) {
                if (CardFactory.build(cardName, null).getCardClass() == cardClass) {
                    heroCards.add(cardName);
                }
            }
            drawHeroTypeCards();
        }
        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(Constants.backgroundImages.get("CardBack"), 0, 0, 900, Math.max(((heroCards.size()/5)+1)*225, 550), null);
        }
    }

    public static class CollectionSearchPanel extends JPanel{

        private List<String> searchedCards;
        private static DeckPanel deckPanel;
        private CollectionSearchPanel(DeckPanel deckPanel){
            setLayout(new FlowLayout());
            searchedCards = new ArrayList<>();
        this.deckPanel =deckPanel;
        }
        private static CollectionSearchPanel instance;
        public static CollectionSearchPanel getInstance(){
            if(instance == null){
                instance = new CollectionSearchPanel(deckPanel);
            }
            return instance;
        }


        public void drawSearchedCards(){
            setPreferredSize(new Dimension(900, Math.max(((searchedCards.size()/5)+1)*225, 550)));
            for(String cardName : searchedCards){
                boolean have = currentUser.getAllCards().contains(cardName);
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if(have){
                            if(JOptionPane.showConfirmDialog(null, "do you wanna add this card to your deck?",
                                    "remove", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                                JOptionPane.showMessageDialog(null, Collection.addToDeck(cardName), "add", JOptionPane.OK_OPTION);
                                deckPanel.drawDeck();
                                deckPanel.repaint();
                                deckPanel.revalidate();
                            }
                        }else{
                            if(JOptionPane.showConfirmDialog(null, "you dont have this card \n do you wanna buy it?",
                                    "Go to store", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                                MainFrame.cl.show(MainFrame.panelCont, "4");
                                Log.bodyLogger("navigate", "store manu");
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
                ImageCardPanel card;
                if(have)
                    card = new ImageCardPanel(cardName, mouseListener, true);
                else
                    card = new ImageCardPanel(cardName, mouseListener, false);
                card.setPreferredSize(new Dimension(160,200));
                add(card);
                repaint();
                revalidate();
            }
        }
        public void search(String text){
            removeAll();
            searchedCards.clear();
            for(String cardName : market.getAllCards()){
                if(cardName.contains(text)){
                    searchedCards.add(cardName);
                }
            }
            Log.bodyLogger("serch by name", text);
            drawSearchedCards();
        }

        public void FilterByMana(int mana){
            removeAll();
            searchedCards.clear();
            for(String cardName : market.getAllCards()) {
                if(CardFactory.build(cardName, null).getManaCost() == mana){
                    searchedCards.add(cardName);
                }
            }
            Log.bodyLogger("serch by mana", "mana cost :" + mana);
            drawSearchedCards();
        }
        protected void paintComponent(Graphics g) {
            g.drawImage(Constants.backgroundImages.get("CardBack"), 0, 0, 900, Math.max(((searchedCards.size()/5)+1)*225, 550), null);
        }
    }
    public static class DeckPanel extends JPanel{

        public DeckPanel(){
            setPreferredSize(new Dimension(900, 6*225));
            setLayout(new FlowLayout());
            drawDeck();
        }
        public void drawDeck(){
            removeAll();
            for(String cardName : currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards()){
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        if(JOptionPane.showConfirmDialog(null, "do you wanna remove this card from your deck?",
                                "remove", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                            JOptionPane.showMessageDialog(null, Collection.removeFromDeck(cardName), "remove", JOptionPane.OK_OPTION);
                        removeAll();
                        drawDeck();
                        repaint();
                        revalidate();
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
                ImageCardPanel card = new ImageCardPanel(cardName, mouseListener, true);
                card.setPreferredSize(new Dimension(160,200));
                add(card);
            }
        }
        protected void paintComponent(Graphics g) {
            g.drawImage(Constants.backgroundImages.get("CardBack"), 0, 0, 900, 6*225, null);
        }
    }
}





