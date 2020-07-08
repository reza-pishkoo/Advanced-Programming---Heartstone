package gui.storePanel;

import cards.CardClass;
import cards.CardFactory;
import config.Constants;
import data.Data;
import data.Log;
import gui.MainFrame;
import gui.cardImage.ImageCardPanel;
import gui.collectionPanel.CollectionMainPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static CLI.Main.currentUser;
import static CLI.Main.market;

public class StoreMainPanel extends JPanel {
    private StoreUpTapePanel storeUpTapePanel;
    private StoreCardDetailsPanel storeCardDetailsPanel;
    private CardsPanel cardsPanel;


    public StoreMainPanel() {
        setSize(1200, 700);
        setPreferredSize(new Dimension(1200, 700));
        setBounds(0, 0, 1200, 700);
        setLayout(new BorderLayout());

        storeUpTapePanel = new StoreUpTapePanel();
        add(storeUpTapePanel, BorderLayout.NORTH);

        storeCardDetailsPanel = StoreCardDetailsPanel.getInstance();
        add(storeCardDetailsPanel, BorderLayout.EAST);

        cardsPanel = CardsPanel.getInstance();
        add(cardsPanel, BorderLayout.CENTER);

    }


    public class StoreUpTapePanel extends JPanel {
        private JButton exitButton;
        private JButton minusButton;
        private JButton backButton;

        public StoreUpTapePanel() {
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


    public static class StoreCardDetailsPanel extends JPanel {
        private JButton button;

        private StoreCardDetailsPanel() {
            setPreferredSize(new Dimension(300, 670));
            setLayout(null);
        }

        private static StoreCardDetailsPanel instance;

        private static StoreCardDetailsPanel getInstance() {
            if (instance == null)
                instance = new StoreCardDetailsPanel();
            return instance;
        }

        private void initShowDetails(String cardName) {
            removeAll();
            initCardPanel(cardName);
            repaint();
            revalidate();
        }

        private void initCardPanel(String cardName) {
            JLabel walletText = new JLabel("WALLET:");
            walletText.setBounds(40, 20, 100, 20);
            add(walletText);
            JLabel walletNum = new JLabel(currentUser.getNumberOfCoins() + "");
            walletNum.setBounds(160, 20, 100, 20);
            add(walletNum);

            ImageCardPanel imageCardPanel = new ImageCardPanel(cardName, true);
            imageCardPanel.setBounds(70, 100, 160, 200);
            add(imageCardPanel);

            JLabel value1 = new JLabel("value: ");
            value1.setBounds(40, 320, 100, 20);
            add(value1);
            JLabel value2 = new JLabel(CardFactory.build(cardName).getValue() + "");
            value2.setBounds(160, 320, 100, 20);
            add(value2);

            JLabel name1 = new JLabel("name: ");
            name1.setBounds(40, 350, 100, 20);
            add(name1);
            JLabel name2 = new JLabel(cardName);
            name2.setBounds(160, 350, 100, 20);
            add(name2);

            JLabel Class1 = new JLabel("class: ");
            Class1.setBounds(40, 380, 100, 20);
            add(Class1);
            JLabel Class2 = new JLabel(CardFactory.build(cardName).getCardClass().toString());
            Class2.setBounds(160, 380, 100, 20);
            add(Class2);

            JLabel type1 = new JLabel("type: ");
            type1.setBounds(40, 410, 100, 20);
            add(type1);
            JLabel type2 = new JLabel(CardFactory.build(cardName).getType().toString());
            type2.setBounds(160, 410, 100, 20);
            add(type2);

            JLabel rarity1 = new JLabel("rarity: ");
            rarity1.setBounds(40, 440, 100, 20);
            add(rarity1);
            JLabel rarity2 = new JLabel(CardFactory.build(cardName).getRarity().toString());
            rarity2.setBounds(160, 440, 100, 20);
            add(rarity2);

            if (!currentUser.getAllCards().contains(cardName)) {
                button = new JButton("Buy card");
                button.setBounds(100, 600, 100, 30);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        if (JOptionPane.showConfirmDialog(null, "do you really want to buy this card???",
                                "buy", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            JOptionPane.showMessageDialog(null, market.buy(cardName), "error", JOptionPane.OK_OPTION);
                            initShowDetails(cardName);
                            StoreSearchPanel.getInstance().showCardsToBuy();
                            CollectionMainPanel.getInstance().updateCardsPanel();
                        }
                    }
                });
                add(button);
            } else {
                button = new JButton("Sell card");
                button.setBounds(100, 500, 100, 30);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (JOptionPane.showConfirmDialog(null, "do you really want to sell this beautiful card???",
                                "sell", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                            JOptionPane.showMessageDialog(null, market.sell(cardName), "error", JOptionPane.ERROR_MESSAGE);
                            initShowDetails(cardName);
                            StoreSearchPanel.getInstance().showCardsToSell();
                            CollectionMainPanel.getInstance().updateCardsPanel();
                        }
                    }
                });
                add(button);
            }

        }

    }

    public static class CardsPanel extends JPanel {

        private UpperFilterTape upperFilterTape;
        private LowerFilterTape lowerFilterTape;
        private MainFilterPage mainFilterPage;

        private CardsPanel() {
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

        public static CardsPanel getInstance() {
            if (instance == null)
                instance = new CardsPanel();
            return instance;
        }


        public class UpperFilterTape extends JPanel {
            private JTextField searchField;


            public UpperFilterTape() {
                setLayout(null);
                setPreferredSize(new Dimension(900, 50));
                setBackground(Color.darkGray);
                initSearchField();
                add(searchField);
            }

            public void initSearchField() {
                searchField = new JTextField();
                searchField.setBounds(20, 5, 250, 35);
                searchField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent documentEvent) {
                        String searchedWord = searchField.getText();
                        StoreSearchPanel.getInstance().search(searchedWord);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent documentEvent) {
                        String searchedWord = searchField.getText();
                        StoreSearchPanel.getInstance().search(searchedWord);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent documentEvent) {
                        String searchedWord = searchField.getText();
                        StoreSearchPanel.getInstance().search(searchedWord);
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Constants.backgroundImages.get("UpperTape"), 0, 0, 900, 60, null);
                g.setFont(new Font("Tahoma", Font.BOLD, 20));
                g.drawString("STORE MENU", 370, 30);
            }
        }


        public class LowerFilterTape extends JPanel {
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


            private JButton buyButton;
            private JButton sellButton;

            public LowerFilterTape() {
                setPreferredSize(new Dimension(900, 60));
                setBackground(Color.darkGray);
                setLayout(null);

                addManaButtons();

                initBuyButton();
                initSellButton();

                add(sellButton);
                add(buyButton);
            }

            private void initButtons() {
                mana1 = new JButton("1");
                mana1.setBounds(10, 15, 50, 30);
                mana1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(1);
                    }
                });
                mana2 = new JButton("2");
                mana2.setBounds(70, 15, 50, 30);
                mana2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(2);
                    }
                });
                mana3 = new JButton("3");
                mana3.setBounds(130, 15, 50, 30);
                mana3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(3);
                    }
                });
                mana4 = new JButton("4");
                mana4.setBounds(190, 15, 50, 30);
                mana4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(4);
                    }
                });
                mana5 = new JButton("5");
                mana5.setBounds(250, 15, 50, 30);
                mana5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(5);
                    }
                });
                mana6 = new JButton("6");
                mana6.setBounds(310, 15, 50, 30);
                mana6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(6);
                    }
                });
                mana7 = new JButton("7");
                mana7.setBounds(370, 15, 50, 30);
                mana7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(7);
                    }
                });
                mana8 = new JButton("8");
                mana8.setBounds(430, 15, 50, 30);
                mana8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(8);
                    }
                });
                mana9 = new JButton("9");
                mana9.setBounds(490, 15, 50, 30);
                mana9.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(9);
                    }
                });
                mana10 = new JButton("10");
                mana10.setBounds(550, 15, 50, 30);
                mana10.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().FilterByMana(10);
                    }
                });
            }

            private void addManaButtons() {
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

            private void initBuyButton() {
                buyButton = new JButton("Buy");
                buyButton.setBounds(650, 15, 100, 30);
                buyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().showCardsToBuy();
                    }
                });
            }

            private void initSellButton() {
                sellButton = new JButton("Sell");
                sellButton.setBounds(780, 15, 100, 30);
                sellButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        StoreSearchPanel.getInstance().showCardsToSell();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(Constants.backgroundImages.get("LowerTape"), 0, 0, 900, 60, null);
            }
        }

        public class MainFilterPage extends JTabbedPane {
            public StoreSearchPanel storeSearchPanel;
            private StoreHeroPanel neutralPanel;
            private StoreHeroPanel magePanel;
            private StoreHeroPanel warlockPanel;
            private StoreHeroPanel paladinPanel;
            private StoreHeroPanel priestPanel;
            private StoreHeroPanel rougePanel;
            private JScrollPane scrollPane;
            private JScrollPane scrollPane1;
            private JScrollPane scrollPane2;
            private JScrollPane scrollPane3;
            private JScrollPane scrollPane4;
            private JScrollPane scrollPane5;
            private JScrollPane scrollPane6;


            public MainFilterPage() {

                storeSearchPanel = StoreSearchPanel.getInstance();
                scrollPane = new JScrollPane(storeSearchPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setPreferredSize(new Dimension(900, 560));
                addTab("Search", scrollPane);
                setMnemonicAt(0, KeyEvent.VK_1);

                neutralPanel = new StoreHeroPanel(CardClass.NEUTRAL);
                scrollPane1 = new JScrollPane(neutralPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane1.setPreferredSize(new Dimension(900, 560));
                addTab("NEUTRAL", scrollPane1);
                setMnemonicAt(1, KeyEvent.VK_2);

                magePanel = new StoreHeroPanel(CardClass.MAGE);
                scrollPane2 = new JScrollPane(magePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane2.setPreferredSize(new Dimension(900, 560));
                addTab("MAGE", scrollPane2);
                setMnemonicAt(2, KeyEvent.VK_3);


                rougePanel = new StoreHeroPanel(CardClass.ROUGE);
                scrollPane3 = new JScrollPane(rougePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane3.setPreferredSize(new Dimension(900, 560));
                addTab("ROUGE", scrollPane3);
                setMnemonicAt(3, KeyEvent.VK_4);

                paladinPanel = new StoreHeroPanel(CardClass.PALADIN);
                scrollPane4 = new JScrollPane(paladinPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane4.setPreferredSize(new Dimension(900, 560));
                addTab("PALADIN", scrollPane4);
                setMnemonicAt(4, KeyEvent.VK_5);

                priestPanel = new StoreHeroPanel(CardClass.PRIEST);
                scrollPane5 = new JScrollPane(priestPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane5.setPreferredSize(new Dimension(900, 560));
                addTab("PRIEST", scrollPane5);
                setMnemonicAt(5, KeyEvent.VK_6);

                warlockPanel = new StoreHeroPanel(CardClass.WARLOCK);
                scrollPane6 = new JScrollPane(warlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane6.setPreferredSize(new Dimension(900, 560));
                addTab("WARLOCK", scrollPane6);
                setMnemonicAt(6, KeyEvent.VK_7);



            }

        }
    }

    public static class StoreHeroPanel extends JPanel {
        private CardClass cardClass;
        private List<String> heroCards;

        private StoreHeroPanel(CardClass cardClass) {
            this.cardClass = cardClass;
            setLayout(new FlowLayout());
            heroCards = new ArrayList<>();
            setHeroCards();
        }


        public void drawHeroTypeCards() {
            removeAll();
            setPreferredSize(new Dimension(900, Math.max(((heroCards.size() / 5) + 1) * 225, 550)));
            for (String cardName : heroCards) {
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        StoreCardDetailsPanel.getInstance().initShowDetails(cardName);
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                    }
                };
                ImageCardPanel card = new ImageCardPanel(cardName, mouseListener, true);
                card.setPreferredSize(new Dimension(160, 200));
                add(card);
            }
        }

        public void setHeroCards() {
            for (String cardName : market.getAllCards()) {
                if (CardFactory.build(cardName).getCardClass() == cardClass) {
                    heroCards.add(cardName);
                }
            }
            drawHeroTypeCards();
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(Constants.backgroundImages.get("CardBack"), 0, 0, 900, Math.max(((heroCards.size() / 5) + 1) * 225, 550), null);
        }
    }

    public static class StoreSearchPanel extends JPanel {

        private List<String> searchedCards;

        private StoreSearchPanel() {
            setLayout(new FlowLayout());
            searchedCards = new ArrayList<>();

        }

        private static StoreSearchPanel instance;

        public static StoreSearchPanel getInstance() {
            if (instance == null) {
                instance = new StoreSearchPanel();
            }
            return instance;
        }


        private void drawSearchedCards() {
            setPreferredSize(new Dimension(900, Math.max(((searchedCards.size() / 5) + 1) * 225, 550)));
            for (String cardName : searchedCards) {
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        StoreCardDetailsPanel.getInstance().initShowDetails(cardName);
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {
                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {
                    }
                };
                ImageCardPanel card = new ImageCardPanel(cardName, mouseListener, true);
                card.setPreferredSize(new Dimension(160, 200));
                add(card);
                updateUI();
            }
        }

        public void search(String text) {
            removeAll();
            searchedCards.clear();
            for (String cardName : market.getAllCards()) {
                if (cardName.contains(text)) {
                    searchedCards.add(cardName);
                }
            }
            Log.bodyLogger("serch by name", text);
            drawSearchedCards();
        }

        public void showCardsToBuy() {
            removeAll();
            searchedCards.clear();
            for (String cardName : market.getAllCards()) {
                if (!currentUser.getAllCards().contains(cardName)) {
                    searchedCards.add(cardName);
                }
            }
            Log.bodyLogger("search", "cards to buy");
            drawSearchedCards();
        }

        public void showCardsToSell() {
            removeAll();
            searchedCards.clear();
            for (String cardName : market.getAllCards()) {
                if (currentUser.getAllCards().contains(cardName)) {
                    searchedCards.add(cardName);
                }
            }
            Log.bodyLogger("search", "cards to sell");
            drawSearchedCards();
        }

        public void FilterByMana(int mana) {
            removeAll();
            searchedCards.clear();
            for (String cardName : market.getAllCards()) {
                if (CardFactory.build(cardName).getManaCost() == mana) {
                    searchedCards.add(cardName);
                }
            }
            Log.bodyLogger("search by mana", "mana cost:" + mana);
            drawSearchedCards();
        }

        protected void paintComponent(Graphics g) {
            g.drawImage(Constants.backgroundImages.get("CardBack"), 0, 0, 900, Math.max(((searchedCards.size() / 5) + 1) * 225, 550), null);
        }
    }


}
