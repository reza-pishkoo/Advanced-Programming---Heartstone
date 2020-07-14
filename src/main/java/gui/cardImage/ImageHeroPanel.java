package gui.cardImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHeroPanel extends JPanel {
    BufferedImage image;
    public String heroName;

    public ImageHeroPanel(String heroName, MouseListener mouseListener){
        this.heroName = heroName;

        addMouseListener(mouseListener);

        try {
            image = ImageIO.read(new File("images/playImages/" + heroName + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0,getWidth(),getHeight(),null);
    }
}
