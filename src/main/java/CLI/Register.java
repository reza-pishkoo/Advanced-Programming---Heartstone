package CLI;

import data.Data;
import heroes.Hero;

import java.util.ArrayList;

import static CLI.Main.currentUser;

public class Register extends Menu {
    public Register() {
    }

    @Override
    public void mainPerform(){
        while(true) {
            System.out.println("\u001b[32m" + this.getClass().getSimpleName() + ")) \u001b[0m");
            System.out.println("Username:  ");
            String username = mySca.nextLine();
            System.out.println("Password:  ");
            String err = perform(username);
            System.out.println(err);
        }
    }

    @Override
    protected String perform(String username) {
        String password = mySca.nextLine();
        System.out.println("");
        currentUser.setUsername(username);
        currentUser.setPassword(password);
        Data.register(username, password);
        return "";
    }
}
