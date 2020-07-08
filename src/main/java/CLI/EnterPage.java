package CLI;

import data.Data;
import data.Log;

import java.io.IOException;

public class EnterPage extends Menu {
    public EnterPage() {

    }

    @Override
    public void mainPerform() {
        while(true){
            System.out.println("welcome to heartStone !!!\n");
            System.out.println("\u001b[32m" + this.getClass().getSimpleName() + ")) \u001b[0m");
            System.out.println("already have an account?(y/n)  ");
            String command = mySca.nextLine();
            System.out.println("");
            String err = perform(command);
            System.out.println(err);
        }
    }

    @Override
    protected String perform(String command){
        if(command.equals("exit -a")){
            Log.bodyLogger("exit game", "sign_out");
            Data.updateAllData();
            System.exit(0);
        }
        if(command.equals("y")) {
            new Login().mainPerform();
            return "";
        }
        if(command.equals("n")){
            new Register().mainPerform();
            return "";
        }
        return "Invalid Command !";
    }
}
