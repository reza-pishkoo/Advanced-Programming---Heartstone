package CLI;

import cards.Deck;

import java.util.ArrayList;

import static CLI.Main.currentUser;
import static CLI.Main.market;

public class StoreMenu extends Menu {



    public StoreMenu() {

    }

    @Override
    protected String perform(String command) {
        String[] input = command.split(" ");

        if(command.trim().equals("hearthstone --help")){
            System.out.println("\u001b[33mbuy cardName     >> to buy a card");
            System.out.println("sell cardName    >> to sell a card");
            System.out.println("ls -s            >> to show the cards you can sell");
            System.out.println("ls -b            >> to show the cards for sell in store");
            System.out.println("wallet           >> to show the number of your coins");
            System.out.println("back             >> to go back");
            System.out.println("exit             >> to go to the Enter page");
            System.out.println("exit -a          >> to close the game\u001b[0m");
            return "";
        }

        if(input[0].equals("buy")){
            String cardName = command.substring(3).trim();
            System.out.println(market.buy(cardName));
            return "";
        }

        if(input[0].equals("sell")){
            String cardName = command.substring(4).trim();
            System.out.println(market.sell(cardName));
            return "";
        }

        if(command.trim().equals("ls -s")){
            ArrayList<String> cardsForSell = new ArrayList<>();
            for (String card : currentUser.getAllCards()) {
                boolean inContained = false;
                for (Deck deck: currentUser.getAllDecks())
                    if (deck.getCards().contains(card))
                        inContained = true;
                if (!inContained)
                    cardsForSell.add(card);
            }
            System.out.println("your cards for sell:");
            System.out.println(cardsForSell.toString());
            return "";
        }

        if(command.trim().equals("ls -b")){
            System.out.println("cards for sell in shop: ");
            System.out.println(market.allCards.toString());
            return "";
        }

        if(command.trim().equals("wallet")){
            System.out.println("number of your coins: " + currentUser.getNumberOfCoins());
            return "";
        }
        return "invalid command !";
    }
}
