package gui.cardImage;

import cards.Card;
import cards.Minion;
import heroes.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InGameImageHeroPanel extends ImageHeroPanel{

    private Hero hero;
    private JLabel healthLabel;
    private JLabel defenceLabel;


    public InGameImageHeroPanel(String heroName, MouseListener mouseListener, Hero hero) {
        super(heroName, mouseListener);
        this.hero = hero;
        this.setPreferredSize(new Dimension(103,117));
        this.setSize(103,117);
        this.setLayout(null);
        healthLabel = new JLabel();
        healthLabel.setText(hero.getHealth() + "");
        healthLabel.setBounds(65,85,35,35);
        healthLabel.setForeground(Color.RED);
        add(healthLabel);
        defenceLabel = new JLabel();
        defenceLabel.setText(hero.getDefenceShield() + "");
        defenceLabel.setBounds(5,85,35,35);
        defenceLabel.setForeground(Color.MAGENTA);
        add(defenceLabel);
    }
}
