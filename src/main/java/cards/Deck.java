package cards;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String hero;
    @Column
    private int use;
    @Column
    private int win;
    @Column
    private String deckName;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> cards;
    @ElementCollection
    @Cascade(CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Map<String, CardDetails> cardsDetails;

    public Deck(){
        cards = new ArrayList<>();
        cardsDetails = new HashMap<>();
    }

    public Deck(String hero, String deckName){
        cards = new ArrayList<>();
        this.hero = hero;
        this.deckName = deckName;
        this.use = 0;
        this.win = 0;
        cardsDetails = new HashMap<>();
    }

    public void initCardMap(){
        for(String card : cards){
            cardsDetails.put(card, new CardDetails());
        }
    }
    public void cardPlayed(String cardName){
        getCardsDetails().get(cardName).setUse(getCardsDetails().get(cardName).getUse() + 1);
    }

    public void removeCardFromMap(String cardName){
        if(cards.contains(cardName)) {
            int x = getCardsDetails().get(cardName).getCounts();
            if (x == 1) {
                setWin(0);
                setUse(0);
                getCardsDetails().remove(cardName);
            }
            if (x == 2) {
                setWin(0);
                setUse(0);
                getCardsDetails().get(cardName).setCounts(1);
            }
        }
    }
    public void addCardToMap(String cardName, int count){

        if(count == 0){
            setWin(0);
            setUse(0);
            cardsDetails.put(cardName, new CardDetails());
        }
        if(count == 1){
            setWin(0);
            setUse(0);
            getCardsDetails().get(cardName).setCounts(2);
        }
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public Map<String, CardDetails> getCardsDetails() {
        return cardsDetails;
    }

    public void setCardsDetails(Map<String, CardDetails> cardsDetails) {
        this.cardsDetails = cardsDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public List<String> getCards() {
        return cards;
    }

    public void setCards(List<String> cards) {
        this.cards = cards;
    }

}
