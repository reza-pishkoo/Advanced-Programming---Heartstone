package gui.cardImage;

import cards.Card;
import cards.Minion;
import cards.Spell;
import cards.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ImageCardDetailPanel extends SecImageCardPanel{

    private Card card;
    private JLabel manaLabel;
    private JLabel attackLabel;
    private JLabel healthLabel;

    public ImageCardDetailPanel(String cardName, MouseListener mouseListener, Card card) {
        super(cardName, mouseListener);
        this.setPreferredSize(new Dimension(150,250));
        this.setSize(150,250);
        this.setLayout(null);
        this.card = card;
        if(card instanceof Minion){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            manaLabel.setBounds(10,7,30,30);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);

            attackLabel = new JLabel();
            attackLabel.setText(((Minion) card).getAttack() + "");
            attackLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            attackLabel.setBounds(10,210,30,30);
            attackLabel.setForeground(Color.WHITE);
            add(attackLabel);

            healthLabel = new JLabel();
            healthLabel.setText(((Minion) card).getHP() + "");
            healthLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            healthLabel.setBounds(117,210,30,30);
            healthLabel.setForeground(Color.WHITE);
            add(healthLabel);
        }
        if(card instanceof Spell){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            manaLabel.setBounds(10,7,30,30);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
        if(card instanceof Weapon){
            manaLabel = new JLabel();
            manaLabel.setText(card.getManaCost() + "");
            manaLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            manaLabel.setBounds(10,7,30,30);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);

            attackLabel = new JLabel();
            attackLabel.setText(((Weapon) card).getAttack() + "");
            attackLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            attackLabel.setBounds(10,210,30,30);
            attackLabel.setForeground(Color.WHITE);
            add(attackLabel);

            healthLabel = new JLabel();
            healthLabel.setText(((Weapon) card).getDurability() + "");
            healthLabel.setFont(new Font("Tahoma",Font.BOLD, 25));
            healthLabel.setBounds(117,210,30,30);
            healthLabel.setForeground(Color.WHITE);
            add(healthLabel);
        }
    }
}
