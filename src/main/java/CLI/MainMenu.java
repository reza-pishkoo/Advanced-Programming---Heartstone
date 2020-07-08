package CLI;

import data.Data;
import data.Log;

import java.io.IOException;

public class MainMenu extends Menu {
    public MainMenu() {
        super();
    }


    @Override
    protected String perform(String command) {

        if(command.trim().equals("hearthstone --help")){
            System.out.println("\u001b[33mcollections       >> to go to collection menu");
            System.out.println("delete-player     >> to delete your account");
            System.out.println("store             >> to go to store menu");
            System.out.println("back              >> to go back");
            System.out.println("exit              >> to go to the Enter page");
            System.out.println("exit -a           >> to close the game\u001b[0m");
            return "";
        }

        if(command.trim().equals("collections")){
            Log.bodyLogger("navigate", "collections");
            new CollectionMenu().mainPerform();
            return "";
        }

        if(command.trim().equals("delete-player")){
            System.out.println("Password: ");
            String password = mySca.nextLine();
            Data.deletePlayer(password);
            return "";
        }

        if(command.trim().equals("store")){
            Log.bodyLogger("navigate", "store");
            new StoreMenu().mainPerform();
            return "";
        }
        return "Invalid Command !";
    }
}
