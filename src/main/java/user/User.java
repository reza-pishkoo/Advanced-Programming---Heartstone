package user;

import cards.Deck;
import heroes.Hero;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String username;
    @Column
    private String password;
    @Column
    private int numberOfCoins;
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> allCards;
    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Hero> allHeroes;
    @Column
    private int curHero;
    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Deck> allDecks;
    @Column
    private int curDeck;

    public User(){

    }

    public List<Deck> getAllDecks() {
        return allDecks;
    }

    public void setAllDecks(List<Deck> allDecks) {
        this.allDecks = allDecks;
    }

    public int getCurDeck() {
        return curDeck;
    }

    public void setCurDeck(int curDeck) {
        this.curDeck = curDeck;
    }

    public List<String> getAllCards() {
        return allCards;
    }

    public List<Hero> getAllHeroes() {
        return allHeroes;
    }

    public int getCurHero() {
        return curHero;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getNumberOfCoins() {
        return numberOfCoins;
    }

    public void setAllCards(List<String> allCards) {
        this.allCards = allCards;
    }

    public void setAllHeroes(List<Hero> allHeroes) {
        this.allHeroes = allHeroes;
    }

    public void setCurHero(int curHero) {
        this.curHero = curHero;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumberOfCoins(int numberOfCoins) {
        this.numberOfCoins = numberOfCoins;
    }

    public static List<String> startingCards(){
        List<String> ans = new ArrayList<>();
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
        return ans;
    }

    public static Deck startingDeck(){
        Deck ans = new Deck();
        ans.setDeckName("1");
        ans.setHero("Mage");
        ans.setCards(startingCards());
        ans.initCardMap();
        return ans;
    }
}
