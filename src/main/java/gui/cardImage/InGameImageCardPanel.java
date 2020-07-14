package gui.cardImage;

import cards.Card;
import cards.Minion;
import cards.Spell;
import cards.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InGameImageCardPanel extends SecImageCardPanel{

    private Card card;
    private JLabel manaLabel;
    private JLabel attackLabel;
    private JLabel healthLabel;

    public InGameImageCardPanel(String cardName, MouseListener mouseListener, Card card) {
        super(cardName, mouseListener);
        this.setPreferredSize(new Dimension(60,85));
        this.setSize(60,85);
        this.setLayout(null);
        this.card = card;
        if(card instanceof Minion){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setBounds(5,3,15,15);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);

            attackLabel = new JLabel();
            attackLabel.setText(((Minion) card).getAttack() + "");
            attackLabel.setBounds(5,69,15,15);
            attackLabel.setForeground(Color.WHITE);
            add(attackLabel);

            healthLabel = new JLabel();
            healthLabel.setText(((Minion) card).getHP() + "");
            healthLabel.setBounds(45,69,15,15);
            healthLabel.setForeground(Color.WHITE);
            add(healthLabel);
        }
        if(card instanceof Spell){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setBounds(5,3,15,15);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
        if(card instanceof Weapon){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setBounds(5,3,15,15);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);

            attackLabel = new JLabel();
            attackLabel.setText(((Weapon) card).getAttack() + "");
            attackLabel.setBounds(5,69,15,15);
            attackLabel.setForeground(Color.WHITE);
            add(attackLabel);

            healthLabel = new JLabel();
            healthLabel.setText(((Weapon) card).getDurability() + "");
            healthLabel.setBounds(45,69,15,15);
            healthLabel.setForeground(Color.WHITE);
            add(healthLabel);
        }
    }
}
