package data;

import CLI.MainMenu;
import cards.Deck;
import heroes.Hero;
import heroes.HeroType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static CLI.Main.*;
import static user.User.startingDeck;

public class Data {

    private static SessionFactory sessionFactory = buildSessionFactory();

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

    private static <T> T fetch(Class<T> tClass, Serializable id) {
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
        enemyStartingDeck.add("Polymorph");
        enemyStartingDeck.add("Friendly Smith");
        enemyStartingDeck.add("Dreadscale");
        enemyStartingDeck.add("Blade of C'Thun");
        enemyStartingDeck.add("Aranasi Broodmother");
        enemyStartingDeck.add("Cobalt Spellkin");
        enemyStartingDeck.add("Depth Charge");
        enemyStartingDeck.add("Evasive Wyrm");
        enemyStartingDeck.add("Blessing of the Ancients");
        enemyStartingDeck.add("Chaos Nova");
        enemyStartingDeck.add("Starfire");
        enemyStartingDeck.add("Piloted Shredder");
        enemyStartingDeck.add("Hunter's Mark");
        enemyStartingDeck.add("Gnomish Inventor");
        enemyStartingDeck.add("Goldshire Footman");
        enemyStartingDeck.add("Murloc Raider");
        enemyStartingDeck.add("Stonetusk Boar");
        enemyStartingDeck.add("Doomsayer");
        enemyStartingDeck.add("Shieldbearer");
        enemyStartingDeck.add("Sprint");
        enemyStartingDeck.add("Swarm of Locusts");
        enemyStartingDeck.add("Chaos Nova");
        enemyStartingDeck.add("Sprint");
        enemyStartingDeck.add("Doomsayer");
        enemyStartingDeck.add("Stonetusk Boar");
        enemyStartingDeck.add("Starfire");
        enemyStartingDeck.add("Evasive Wyrm");
        enemyStartingDeck.add("Depth Charge");
        enemyStartingDeck.add("Polymorph");
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
        MY_HEROES.add(fetch(Hero.class, "Mage"));
        enemyUser.setAllHeroes(MY_HEROES);
        enemyUser.setCurHero(0);
        List<Deck> enemyDecks = new ArrayList<>();
        enemyDecks.add(enemyStartingDeck());
        enemyUser.setAllDecks(enemyDecks);
        enemyUser.setCurDeck(0);
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
        Data.save(hero);
    }
    public static void savePaladin(){
        Hero hero = new Hero();
        hero.setName("Paladin");
        hero.setAttack(2);
        hero.setCanAttack(false);
        hero.setHealth(30);
        hero.setHeroPower("costs 2 mana and deal 1 damage to any character");
        hero.setSpecialPower("two less mana for playing spells");
        hero.setType(HeroType.PALADIN);
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
        Data.save(hero);
    }
}
