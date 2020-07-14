package data;

import cards.Deck;
import com.google.gson.Gson;
import heroes.Hero;
import heroes.HeroType;
import model.DeckReaderInput;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static CLI.Main.*;
import static user.User.startingDeck;

public class Data {

    private static SessionFactory sessionFactory = buildSessionFactory();
    private static Gson gson = new Gson();
    private static DeckReaderInput mine;

    private static SessionFactory buildSessionFactory() {
//        PrintStream err = System.err;
//        try {
//            PrintStream printStream = new PrintStream(new File("./l.txt"));
//            System.setErr(printStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        System.setErr(err);
        return sessionFactory;
    }

    private static void save(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(o);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> T fetch(Class<T> tClass, Serializable id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tClass,id);
        session.close();
        return t;
    }

    private static void delete(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }

    private static void update(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }

    public static void updateAllData(){
        update(currentUser);
        update(market);
    }
    public static List<String> enemyStartingCards(){
        List<String> enemyStartingDeck = new ArrayList<>();
        enemyStartingDeck.add("Mana Wyrm");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("High Priest Amet");
        enemyStartingDeck.add("Blessing of the Ancients");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Strength in Numbers");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Chaos Nova");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Learn Draconic");
        enemyStartingDeck.add("Swarm of Locusts");
        enemyStartingDeck.add("Depth Charge");
        enemyStartingDeck.add("Swarm of Locusts");
        return enemyStartingDeck;
    }
    public static Deck enemyStartingDeck(){
        Deck ans = new Deck();
        ans.setDeckName("enemy deck");
        ans.setHero("Mage");
        ans.setCards(enemyStartingCards());
        ans.initCardMap();
        return ans;
    }

    public static void setEnemyUser(){
        enemyUser.setUsername("enemy");
        List<Hero> MY_HEROES = new ArrayList<Hero>();
        MY_HEROES.add(fetch(Hero.class, "Warlock"));
        enemyUser.setAllHeroes(MY_HEROES);
        enemyUser.setCurHero(0);
        List<Deck> enemyDecks = new ArrayList<>();
        enemyDecks.add(enemyStartingDeck());
        enemyUser.setAllDecks(enemyDecks);
        enemyUser.setCurDeck(0);
    }



    public static DeckReaderInput setDecks() {
        File file = new File(System.getProperty("user.dir" ) + "\\deckreader\\deckreader.json");
        String st = "";
        try {
            Scanner sca = new Scanner(file);
            while (sca.hasNextLine()){
                st += sca.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mine = gson.fromJson(st, DeckReaderInput.class);
        return mine;
    }

    public static User DeckReaderFirstUser;
    public static User DeckReaderSecondUser;

    public static Deck DeckReaderFirstStartingDeck(){
        Deck ans = new Deck();
        ans.setDeckName("first deck");
        ans.setHero("Mage");
        ans.setCards(setDecks().getFriend());
        ans.initCardMap();
        return ans;
    }

    public static void setDeckReaderFirstUser(){
        DeckReaderFirstUser = new User();
        DeckReaderFirstUser.setUsername("Deck first PLayer");
        List<Hero> MY_HEROES = new ArrayList<Hero>();
        MY_HEROES.add(fetch(Hero.class, "Mage"));
        DeckReaderFirstUser.setAllHeroes(MY_HEROES);
        DeckReaderFirstUser.setCurHero(0);
        List<Deck> enemyDecks = new ArrayList<>();
        enemyDecks.add(DeckReaderFirstStartingDeck());
        DeckReaderFirstUser.setAllDecks(enemyDecks);
        DeckReaderFirstUser.setCurDeck(0);
    }

    public static Deck DeckReaderSecondStartingDeck(){
        Deck ans = new Deck();
        ans.setDeckName("second deck");
        ans.setHero("Mage");
        ans.setCards(setDecks().getEnemy());
        ans.initCardMap();
        return ans;
    }

    public static void setDeckReaderSecondUser(){
        DeckReaderSecondUser = new User();
        DeckReaderSecondUser.setUsername("Deck second PLayer");
        List<Hero> MY_HEROES = new ArrayList<Hero>();
        MY_HEROES.add(fetch(Hero.class, "Mage"));
        DeckReaderSecondUser.setAllHeroes(MY_HEROES);
        DeckReaderSecondUser.setCurHero(0);
        List<Deck> enemyDecks = new ArrayList<>();
        enemyDecks.add(DeckReaderSecondStartingDeck());
        DeckReaderSecondUser.setAllDecks(enemyDecks);
        DeckReaderSecondUser.setCurDeck(0);
    }


    public static String register(String username, String password){
        User user = fetch(User.class, username);
        if(user != null){
            return "this username used before";
        }
        currentUser.setUsername(username);
        currentUser.setPassword(password);
        currentUser.setNumberOfCoins(600);
        List<Hero> MY_HEROES = new ArrayList<Hero>();
        MY_HEROES.add(fetch(Hero.class, "Mage"));
        MY_HEROES.add(fetch(Hero.class, "Rouge"));
        MY_HEROES.add(fetch(Hero.class, "Warlock"));
        MY_HEROES.add(fetch(Hero.class, "Hunter"));
        MY_HEROES.add(fetch(Hero.class, "Priest"));
        currentUser.setAllHeroes(MY_HEROES);
        currentUser.setCurHero(0);
        currentUser.setAllCards(User.startingCards());
        List<Deck> myDecks = new ArrayList<>();
        myDecks.add(startingDeck());
        currentUser.setAllDecks(myDecks);
        currentUser.setCurDeck(0);
        save(currentUser);
//        new MainMenu().mainPerform();
        return "";
    }

    public static String login(String username, String password){
        User user = fetch(User.class, username);
        if(user == null){
            return "username not valid";
        }
        else{
            if(!user.getPassword().equals(password)){
                return "wrong password";
            }
            currentUser = fetch(User.class, username);
//            new MainMenu().mainPerform();
        }
        return "";
    }

    public static void deletePlayer(String password) {

    }

    public static void saveMage(){
        Hero hero = new Hero();
        hero.setName("Mage");
        hero.setAttack(2);
        hero.setCanAttack(false);
        hero.setHealth(30);
        hero.setHeroPower("costs 2 mana and deal 1 damage to any character");
        hero.setSpecialPower("two less mana for playing spells");
        hero.setType(HeroType.MAGE);
        hero.setDefenceShield(0);
        Data.save(hero);
    }
    public static void saveHunter(){
        Hero hero = new Hero();
        hero.setName("Hunter");
        hero.setAttack(2);
        hero.setCanAttack(false);
        hero.setHealth(30);
        hero.setHeroPower("costs 2 mana and deal 1 damage to any character");
        hero.setSpecialPower("two less mana for playing spells");
        hero.setType(HeroType.HUNTER);
        hero.setDefenceShield(0);
        Data.save(hero);
    }
    public static void savePriest(){
        Hero hero = new Hero();
        hero.setName("Priest");
        hero.setAttack(2);
        hero.setCanAttack(false);
        hero.setHealth(30);
        hero.setHeroPower("costs 2 mana and deal 1 damage to any character");
        hero.setSpecialPower("two less mana for playing spells");
        hero.setType(HeroType.PRIEST);
        hero.setDefenceShield(0);
        Data.save(hero);
    }
    public static void saveWarlock(){
        Hero hero = new Hero();
        hero.setName("Warlock");
        hero.setAttack(2);
        hero.setCanAttack(false);
        hero.setHealth(30);
        hero.setHeroPower("costs 2 mana and deal 1 damage to any character");
        hero.setSpecialPower("two less mana for playing spells");
        hero.setType(HeroType.WARLOCK);
        hero.setDefenceShield(0);
        Data.save(hero);
    }
    public static void saveRouge(){
        Hero hero = new Hero();
        hero.setName("Rouge");
        hero.setAttack(2);
        hero.setCanAttack(false);
        hero.setHealth(30);
        hero.setHeroPower("costs 2 mana and deal 1 damage to any character");
        hero.setSpecialPower("two less mana for playing spells");
        hero.setType(HeroType.ROUGE);
        hero.setDefenceShield(0);
        Data.save(hero);
    }
}
