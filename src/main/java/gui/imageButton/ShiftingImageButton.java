package gui.imageButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShiftingImageButton extends JButton {
    BufferedImage image1;
    BufferedImage image2;

    public boolean mouseEntered;

    public ShiftingImageButton(String buttonName){

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                mouseEntered = true;
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                mouseEntered = false;
            }
        });

        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        mouseEntered = false;
        try {
            image1 = ImageIO.read(new File("images/Button Images/" + buttonName + "2.png"));
            image2 = ImageIO.read(new File("images/Button Images/" + buttonName + "3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(!mouseEntered)
            g2.drawImage(image1.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0,getWidth(),getHeight(),null);
        if(mouseEntered)
            g2.drawImage(image2.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0,getWidth(),getHeight(),null);
    }
}
