package gui.cardImage;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class OutGameImageCardPanel extends ImageCardPanel{

    private Card card;
    private JLabel manaLabel;

    public OutGameImageCardPanel(String cardName, MouseListener mouseListener, boolean have, Card card) {
        super(cardName, mouseListener, have);
        setPreferredSize(new Dimension(160, 200));
        setSize(160, 200);
        this.card = card;
        if(card.getType().type().equalsIgnoreCase("Minion")){

        }
        if(card.getType().type().equalsIgnoreCase("Spell")){
             manaLabel = new JLabel();
             manaLabel.setText(card.getManaCost() + "");
//             manaLabel.setBounds();
        }
    }
}
