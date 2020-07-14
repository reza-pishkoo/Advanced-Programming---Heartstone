package gui.cardImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SecImageCardPanel extends JPanel {

    BufferedImage image;
    public String cardName;
    public SecImageCardPanel(String cardName, MouseListener mouseListener){
        this.cardName = cardName;
        addMouseListener(mouseListener);

        try {
            image = ImageIO.read(new File("images/Card Images/In Game/" + cardName + ".png"));
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
