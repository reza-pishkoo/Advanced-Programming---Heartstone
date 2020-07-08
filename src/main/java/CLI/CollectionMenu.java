package CLI;

import data.Log;
import heroes.Hero;

import java.io.IOException;
import java.util.ArrayList;

import static CLI.Main.currentUser;

public class CollectionMenu extends Menu {
    public CollectionMenu() {

    }


    @Override
    protected String perform(String command) {
        boolean heroChooser = false;
        String[] input = command.split(" ");

        if(command.trim().equals("hearthstone --help")){
            System.out.println("\u001b[33mselect heroName      >> to choose your current hero");
            System.out.println("ls -a -heroes        >> to show your heroes");
            System.out.println("ls -m -heroes        >> to show your current hero");
            System.out.println("ls -a -cards         >> to show your current hero's cards");
            System.out.println("ls -m -cards         >> to show your current hero's deck");
            System.out.println("ls -n -cards         >>  to show the cards you can add to your deck");
            System.out.println("add cardName         >>  to add card to your desk");
            System.out.println("remove cardName      >>  to remove card from your deck");
            System.out.println("back                 >> to go back");
            System.out.println("exit                 >> to go to the Enter page");
            System.out.println("exit -a              >> to close the game\u001b[0m");
            return "";
        }

        if(input[0].equals("select")){
            String heroName = command.substring(6).trim();
            for(Hero moveOnHeroes : currentUser.getAllHeroes()){
                if(moveOnHeroes.getName().equals(heroName)){
                    heroChooser = true;
                    currentUser.setCurHero(currentUser.getAllHeroes().indexOf(moveOnHeroes));
                    Log.bodyLogger("select", "hero:"+heroName);
                    System.out.println("hero selected");
                    return "";
                }
            }
            if(!heroChooser){
                return "nonexistent hero !";
            }
        }

        if(command.trim().equals("ls -a -heroes")){
            System.out.println("your heroes :");
            Log.bodyLogger("list", "heroes:user_heroes");
            System.out.println(currentUser.getAllHeroes().toString());
            return "";
        }

        if(command.trim().equals("ls -m -heroes")){
            System.out.println("your current Hero :");
            Log.bodyLogger("list", "heroes:user_current_hero");
            System.out.println(currentUser.getAllHeroes().get(currentUser.getCurHero()));
            return "";
        }

        if(command.trim().equals("ls -a -cards")){
            System.out.println("your hero's cards :");
            Log.bodyLogger("list", "cards:user_current_hero_cards");
            System.out.println(currentUser.getAllCards());
            return "";
        }

        if(command.trim().equals("ls -m -cards")){
            System.out.println("cards in your current deck :");
            Log.bodyLogger("list", "cards:user_current_deck_desk");
            System.out.println(currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards());
            return "";
        }

        if(command.trim().equals("ls -n -cards")){
            ArrayList<String> canAddToDeck = new ArrayList<>();
            for(String allCard : currentUser.getAllCards()){
                int canAddCard = 0;
                for(String deckCard : currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards()){
                    if(allCard.equals(deckCard))
                        canAddCard++;
                }
                if(canAddCard < 2)
                    canAddToDeck.add(allCard);
            }
            Log.bodyLogger("list", "cards:user_cards_not_in_the_desk");
            System.out.println("the cards which you can add to your deck :");
            System.out.println(canAddToDeck.toString());
            return "";
        }

        if(input[0].equals("add")){
            String cardName = command.substring(3).trim();
            if(currentUser.getAllCards().contains(cardName)){
                if(currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().size()<=30) {
                    int cardNum = 0;
                    for (String heroDeck : currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards()) {
                        if (heroDeck.equals(cardName))
                            cardNum++;
                    }
                    if (cardNum < 2) {
                        currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().add(cardName);
                        Log.bodyLogger("add", "card:[" + cardName + "]:[" + currentUser.getAllHeroes().get(currentUser.getCurHero()).getName() + "]");
                        System.out.println("card added");
                        return "";
                    } else {
                        System.out.println("you have this card in your deck twice");
                        return "";
                    }
                }else{
                    System.out.println("your deck is full");
                    return "";
                }
            }else{
                System.out.println("nonexistent card");
                return "";
            }
        }

        if(input[0].equals("remove")){
            String cardName = command.substring(6).trim();
            if(currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().contains(cardName)){
                currentUser.getAllDecks().get(currentUser.getCurDeck()).getCards().remove(cardName);
                Log.bodyLogger("remove", "card:[" + cardName + "]:[" + currentUser.getAllHeroes().get(currentUser.getCurHero()).getName() + "]");
                System.out.println("card removed");
                return "";
            }else{
                System.out.println("this card isn't in your deck");
                return "";
            }
        }

        return "invalid input";
    }
}
