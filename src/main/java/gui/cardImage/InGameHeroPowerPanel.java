package gui.cardImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class InGameHeroPowerPanel extends ImageHeroPowerPanel{

    private JLabel manaLabel;
    private String heroName;


    public InGameHeroPowerPanel(String heroName, MouseListener mouseListener) {
        super(heroName, mouseListener);
        this.heroName = heroName;
        this.setPreferredSize(new Dimension(60,85));
        this.setSize(60,85);
        this.setLayout(null);

        if(heroName.equalsIgnoreCase("Mage")){
            manaLabel = new JLabel();
            manaLabel.setText("2");
            manaLabel.setBounds(29,3,17,17);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
        if(heroName.equalsIgnoreCase("Rouge")){
            manaLabel = new JLabel();
            manaLabel.setText("3");
            manaLabel.setBounds(29,3,17,17);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
        if(heroName.equalsIgnoreCase("Warlock")){
            manaLabel = new JLabel();
            manaLabel.setText("2");
            manaLabel.setBounds(29,3,17,17);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
        if(heroName.equalsIgnoreCase("Priest")){
            manaLabel = new JLabel();
            manaLabel.setText("2");
            manaLabel.setBounds(29,3,17,17);
            manaLabel.setForeground(Color.WHITE);
            add(manaLabel);
        }
    }


}
