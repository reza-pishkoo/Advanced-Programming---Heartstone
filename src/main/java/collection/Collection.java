package collection;

import data.Log;

import static CLI.Main.currentUser;

public class Collection {

    public static String addToDeck(String cardName) {
        if (currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().size() < 30) {
            int cardNum = 0;
            for (String heroDeck : currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards()) {
                if (heroDeck.equals(cardName))
                    cardNum++;
            }
            if (cardNum < 2) {
                currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().add(cardName);
                currentUser.getAllDecks().get(currentUser.getCurDeck()).addCardToMap(cardName, cardNum);
                Log.bodyLogger("add", "card:[" + cardName + "]:[" + currentUser.getAllHeroes().get(currentUser.getCurHero()).getName() + "]");
                return "card added";
            } else {
                return "you have this card in your deck twice";
            }
        } else {
            return "your deck is full";
        }
    }

    public static String removeFromDeck(String cardName) {
        currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().remove(cardName);
        currentUser.getAllDecks().get(currentUser.getCurDeck()).removeCardFromMap(cardName);
        Log.bodyLogger("remove", "card:[" + cardName + "]:[" + currentUser.getAllHeroes().get(currentUser.getCurHero()).getName() + "]");
        return "card removed";
    }
}




