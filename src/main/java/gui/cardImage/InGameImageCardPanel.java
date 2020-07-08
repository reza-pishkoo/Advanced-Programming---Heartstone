package gui.cardImage;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InGameImageCardPanel extends ImageCardPanel{

    private Card card;
    private JLabel manaLabel;

    public InGameImageCardPanel(String cardName, MouseListener mouseListener, boolean have, Card card) {
        super(cardName, mouseListener, have);
        this.setPreferredSize(new Dimension(50,70));
        this.setSize(50,70);
        this.card = card;
        if(card.getType().type().equalsIgnoreCase("Minion")){

        }
        if(card.getType().type().equalsIgnoreCase("Spell")){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setBounds(5,5,10,10);
        }
    }
}
