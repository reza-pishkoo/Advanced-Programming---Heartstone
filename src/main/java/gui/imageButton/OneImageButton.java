package gui.imageButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OneImageButton extends JButton {

    BufferedImage image;

    public OneImageButton(String buttonName){
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        try {
            image = ImageIO.read(new File("images/Button Images/" + buttonName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, getWidth(), getHeight(), null);
    }
}
