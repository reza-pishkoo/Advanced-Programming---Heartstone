package CLI;

import data.Data;
import data.Log;
import user.User;

import java.io.IOException;
import java.util.Scanner;

public abstract class Menu {
    static Scanner mySca = new Scanner(System.in);

    public Menu() {

    }

    public void mainPerform() {
        while(true) {
            System.out.println("\u001b[32m" + this.getClass().getSimpleName() + ")) \u001b[0m");
            System.out.println("\u001b[31mPlease enter a command:  \u001b[0m");
            String command = mySca.nextLine();
            System.out.println("");
            //Data.save();
            if(command.equals("exit")){
                Log.bodyLogger("exit game", "sign_out");
                Data.updateAllData();
                //    Log.closeFileWriter();
                new EnterPage().mainPerform();
            }
            if(command.equals("back")) {
                Log.bodyLogger("navigate", "main menu");
                Data.updateAllData();
                return;
            }
            if(command.equals("exit -a")) {
                Log.bodyLogger("exit game", "sign_out");
                Data.updateAllData();
                //    Log.closeFileWriter();
                System.exit(0);
            }
            String err = perform(command);
            System.out.println(err);

        }
    }

    protected abstract String perform(String command);

}
