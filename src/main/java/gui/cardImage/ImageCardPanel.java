package gui.cardImage;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ImageCardPanel extends JPanel {

    BufferedImage image1;
    BufferedImage image2;
    public String cardName;
    private boolean have;

    public ImageCardPanel(String cardName, MouseListener mouseListener, boolean have){
        this.have = have;
        this.cardName = cardName;

        addMouseListener(mouseListener);

        try {
            image1 = ImageIO.read(new File("images/Card Images/have/" + cardName + ".png"));
            image2 = ImageIO.read(new File("images/Card Images/notHave/" + cardName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ImageCardPanel(String cardName, boolean have){
        this.cardName = cardName;
        this.have = have;
        try {
            image1 = ImageIO.read(new File("images/Card Images/have/" + cardName + ".png"));
            image2 = ImageIO.read(new File("images/Card Images/have/" + cardName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(have)
            g2.drawImage(image1.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0,getWidth(),getHeight(),null);
        else
            g2.drawImage(image2.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH),0,0,getWidth(),getHeight(),null);
    }
}
