package CLI;

import config.Constants;
import store.Market;
import user.User;

import java.io.IOException;

public class Main {

    public static User enemyUser = new User();
    public static User currentUser = new User();
    public static Market market = new Market();
    public static void main(String[] args) throws IOException {
        Constants.imageLoader();
        new EnterPage().mainPerform();
    }
}
