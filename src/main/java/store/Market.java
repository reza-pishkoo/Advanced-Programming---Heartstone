package store;

import cards.CardFactory;
import cards.CardClass;
import cards.Deck;
import data.Log;
import heroes.Hero;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static CLI.Main.currentUser;

@Entity
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<String> allCards;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAllCards(List<String> allCards) {
        this.allCards = allCards;
    }

    public List<String> getAllCards() {
        return allCards;
    }

    public Market(){
        allCards = new ArrayList<>();
        setAllCards(storeCards());
    }




    public String buy(String cardName){
        if (currentUser.getAllCards().contains(cardName))
            return "you have this card!";
        boolean existForBuy = false;

        for(String moveOnCards : allCards){
            if(cardName.equals(moveOnCards)){
                if(currentUser.getNumberOfCoins() >= CardFactory.build(moveOnCards, null).getValue()) {
                    boolean isHero = CardFactory.build(cardName, null).getCardClass().equals(CardClass.NEUTRAL);
                    for (Hero  hero : currentUser.getAllHeroes())
                        if (hero.getName().equalsIgnoreCase(CardFactory.build(cardName, null).getCardClass().cardClass()) ||
                                CardFactory.build(cardName, null).getCardClass().equals(CardClass.NEUTRAL))
                            isHero = true;
                    if (isHero) {
                        currentUser.getAllCards().add(moveOnCards);
                        currentUser.setNumberOfCoins(currentUser.getNumberOfCoins() - CardFactory.build(cardName, null).getValue());
                    }
                    else
                        return "you dont have that hero";

                    Log.bodyLogger("buy", cardName);
                    return "card buyed !";
                }
                else{
                    return "not enough coin !";
                }
            }
        }
        return "nonexistent card !";
    }

    public String sell(String cardName){
        boolean existForSell = false;
        boolean existInDeck = false;

        for(String moveOnCards : currentUser.getAllCards())
            if(cardName.equals(moveOnCards))
                existForSell = true;

        for(Deck deck : currentUser.getAllDecks()) {
            for (String moveOnDeckCard : deck.getCards()) {
                if (cardName.equals(moveOnDeckCard))
                    return "this card is in your deck !";
            }
        }
        if (existForSell) {
            currentUser.getAllCards().remove(cardName);
            currentUser.setNumberOfCoins(currentUser.getNumberOfCoins() + CardFactory.build(cardName, null).getValue());

            Log.bodyLogger("sell", cardName);
            return "card sold !";
        }

        return "nonexistent card !";

    }

    private ArrayList<String> storeCards(){
        ArrayList<String> ans = new ArrayList<>();
        ans.add("Mana Wyrm");
        ans.add("Cobalt Spellkin");
        ans.add("Depth Charge");
        ans.add("Evasive Wyrm");
        ans.add("Blessing of the Ancients");
        ans.add("Piloted Shredder");
        ans.add("Goldshire Footman");
        ans.add("Murloc Raider");
        ans.add("Stonetusk Boar");
        ans.add("Shieldbearer");
        ans.add("Polymorph");
        ans.add("Friendly Smith");
        ans.add("Dreadscale");
        ans.add("Blade of C'Thun");
        ans.add("Aranasi Broodmother");
        ans.add("Chaos Nova");
        ans.add("Hunter's Mark");
        ans.add("Doomsayer");
        ans.add("Gnomish Inventor");
        ans.add("Sprint");
        ans.add("Swarm of Locusts");
        ans.add("Pharaoh's Blessing");
        ans.add("Book of Specters");
        ans.add("Sathrovarr");
        ans.add("Tomb Warden");
        ans.add("Curio Collector");
        ans.add("Strength in Numbers");
        ans.add("Learn Draconic");
        ans.add("Swamp King Dred");
        ans.add("Meanstreet Marshal");
        ans.add("High Priest Amet");
        ans.add("Demonheart");
        ans.add("Battle Axe");
        ans.add("Heavy Axe");
        ans.add("Blood Fury");
        ans.add("Locust");
        ans.add("Sheep");

        return ans;
    }

}

