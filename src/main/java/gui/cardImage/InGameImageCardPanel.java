package gui.cardImage;

import cards.Card;
import cards.Minion;
import cards.Spell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InGameImageCardPanel extends ImageCardPanel{

    private Card card;
    private JLabel manaLabel;
    private JLabel attackLabel;
    private JLabel healthLabel;

    public InGameImageCardPanel(String cardName, MouseListener mouseListener, boolean have, Card card) {
        super(cardName, mouseListener, have);
        this.setPreferredSize(new Dimension(50,70));
        this.setSize(50,70);
        this.setLayout(null);
        this.card = card;
        if(card instanceof Minion){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setBounds(3,3,10,10);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);

            attackLabel = new JLabel();
            attackLabel.setText(((Minion) card).getAttack() + "");
            attackLabel.setBounds(3,55,10,10);
            attackLabel.setForeground(Color.WHITE);
            add(attackLabel);

            healthLabel = new JLabel();
            healthLabel.setText(((Minion) card).getHP() + "");
            healthLabel.setBounds(39,57,10,10);
            healthLabel.setForeground(Color.WHITE);
            add(healthLabel);
        }
        if(card instanceof Spell){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setBounds(3,3,10,10);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
    }
}
