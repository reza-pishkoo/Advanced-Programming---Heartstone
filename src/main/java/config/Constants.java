package config;

import cards.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static Map<String, BufferedImage> backgroundImages;
    public static Map<String, BufferedImage> cardImages;

    static BufferedImage loginImage;
    static BufferedImage menuImage;
    static BufferedImage storeImage;
    static BufferedImage LowerTape;
    static BufferedImage UpperTape;
    static BufferedImage CardBack;
    static BufferedImage PlayImage;
    static BufferedImage passiveImage;
    static BufferedImage statusImage;


    public static void imageLoader(){
        backgroundImages = new HashMap<>();
        try {
            loginImage = ImageIO.read(new File("images/backgroundImages/login.jpg"));
            backgroundImages.put("loginImage", loginImage);
            menuImage = ImageIO.read(new File("images/backgroundImages/menu.jpg"));
            backgroundImages.put("menuImage", menuImage);
            CardBack = ImageIO.read(new File("images/backgroundImages/CardBack.jpg"));
            backgroundImages.put("CardBack", CardBack);
//            storeImage = ImageIO.read(new File("images/store.jpg"));
//            backgroundImages.put("storeImage", storeImage);
            LowerTape = ImageIO.read(new File("images/LowerTape.jpg"));
            backgroundImages.put("LowerTape", LowerTape);
            UpperTape = ImageIO.read(new File("images/UpperTape.jpg"));
            backgroundImages.put("UpperTape", UpperTape);
            PlayImage = ImageIO.read(new File("images/backgroundImages/GameBoardImage.png"));
            backgroundImages.put("GameBoardImage", PlayImage);
            passiveImage = ImageIO.read(new File("images/backgroundImages/passive.jpg"));
            backgroundImages.put("passiveImage", passiveImage);
            statusImage = ImageIO.read(new File("images/backgroundImages/status.png"));
            backgroundImages.put("statusImage", statusImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
