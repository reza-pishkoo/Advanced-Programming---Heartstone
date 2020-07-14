package gui.cardImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHeroPowerPanel extends JPanel {

    BufferedImage image;
    public String heroName;
    public ImageHeroPowerPanel(String heroName, MouseListener mouseListener){
        this.heroName = heroName;

        addMouseListener(mouseListener);

        try {
            image = ImageIO.read(new File("images/PassiveImages/" + heroName + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHeroName() {
        return heroName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(image.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0,getWidth(),getHeight(),null);
    }
}
