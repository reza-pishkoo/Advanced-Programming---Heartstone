package cards;

import logicController.actions.*;
import model.PlayerModel;

public class CardFactory {
    public static Card build(String CardName, PlayerModel player){
        switch (CardName){

            case "Mana Wyrm" :
                Card card1 = new Minion();
                card1.setName("Mana Wyrm");
                card1.setManaCost(2);
                card1.setRarity(Rarity.COMMON);
                card1.setCardClass(CardClass.MAGE);
                card1.setType(Type.MINION);
                card1.setValue(20);
                ((Minion)card1).setAttack(1);
                ((Minion)card1).setHP(3);
                card1.setDescription("Whenever you cast a spell, gain +1 Attack.");
                ((Minion) card1).setMinionObserver(new ManaWyrm(player, (Minion) card1));
                return card1;

            case "Polymorph" :
                Card card2 = new Spell();
                card2.setName("Polymorph");
                card2.setManaCost(4);
                card2.setRarity(Rarity.COMMON);
                card2.setCardClass(CardClass.MAGE);
                card2.setType(Type.SPELL);
                card2.setValue(20);
                ((Spell) card2).setSpellObserver(new Polymorph(player));
                card2.setDescription("Transform a minion\n" + "into a 1/1 Sheep.");
                return card2;

            case "Friendly Smith" :
                Card card3 = new Spell();
                card3.setName("Friendly Smith");
                card3.setManaCost(1);
                card3.setRarity(Rarity.COMMON);
                card3.setCardClass(CardClass.ROUGE);
                card3.setType(Type.SPELL);
                card3.setValue(20);
                ((Spell) card3).setSpellObserver(new FriendlySmith(player));
                card3.setDescription("Discover a weapon \n" +
                        "from any class. Add it \n" +
                        "to your Adventure Deck\n" +
                        " with +2/+2.");
                return card3;

            case "Dreadscale" :
                Card card4 = new Minion();
                card4.setName("Dreadscale");
                card4.setManaCost(3);
                card4.setRarity(Rarity.LEGENDARY);
                card4.setCardClass(CardClass.WARLOCK);
                card4.setType(Type.MINION);
                card4.setValue(20);
                ((Minion)card4).setMinionObserver(new Dreadscale(player, (Minion) card4));
                card4.setDescription("At the end of your turn, deal 1 damage to all other minions.");
                ((Minion)card4).setAttack(4);
                ((Minion)card4).setHP(2);
                return card4;

            case "Blade of C'Thun" :
                Card card5 = new Minion();
                card5.setName("Blade of C'Thun");
                card5.setManaCost(9);
                card5.setRarity(Rarity.EPIC);
                card5.setCardClass(CardClass.ROUGE);
                card5.setType(Type.MINION);
                card5.setValue(20);
                ((Minion)card5).setAttack(4);
                ((Minion)card5).setHP(4);
                ((Minion)card5).setMinionObserver(new BladeOfCThun(player, (Minion) card5));
                card5.setDescription("Destroy a minion.Add its Attack and Health to your C'Thun's(wherever it is).");
                return card5;

            case "Aranasi Broodmother" :
                Card card6 = new Minion();
                card6.setName("Aranasi Broodmother");
                card6.setManaCost(6);
                card6.setRarity(Rarity.COMMON);
                card6.setCardClass(CardClass.WARLOCK);
                card6.setType(Type.MINION);
                card6.setValue(20);
                ((Minion)card6).setAttack(4);
                ((Minion)card6).setHP(6);
                ((Minion)card6).setMinionObserver(new AranasiBroodmother(player, (Minion) card6));
                card6.setDescription("Taunt\n" +
                        "When you draw this, restore\n" +
                        "4 Health to your hero.");
                return card6;


            case "Cobalt Spellkin" :
                Card card7 = new Minion();
                card7.setName("Cobalt Spellkin");
                card7.setManaCost(5);
                card7.setRarity(Rarity.RARE);
                card7.setCardClass(CardClass.NEUTRAL);
                card7.setType(Type.MINION);
                card7.setValue(20);
                ((Minion)card7).setAttack(3);
                ((Minion)card7).setHP(5);
                ((Minion)card7).setMinionObserver(new CobaltSpellkin(player, (Minion) card7));
                card7.setDescription("[b]Battlecry:[/b] Add two 1-Cost spells from your class to your hand.");
                return card7;

            case "Depth Charge" :
                Card card8 = new Minion();
                card8.setName("Depth Charge");
                card8.setManaCost(1);
                card8.setRarity(Rarity.RARE);
                card8.setCardClass(CardClass.NEUTRAL);
                card8.setType(Type.MINION);
                card8.setValue(20);
                ((Minion)card8).setAttack(0);
                ((Minion)card8).setHP(5);
                ((Minion)card8).setMinionObserver(new DepthCharge(player, (Minion) card8));
                card8.setDescription("At the start of your turn, deal 5 damage to ALL minions.");
                return card8;

            case "Evasive Wyrm" :
                Card card9 = new Minion();
                card9.setName("Evasive Wyrm");
                card9.setManaCost(6);
                card9.setRarity(Rarity.COMMON);
                card9.setCardClass(CardClass.NEUTRAL);
                card9.setType(Type.MINION);
                card9.setValue(20);
                ((Minion)card9).setAttack(5);
                ((Minion)card9).setHP(3);
                ((Minion)card9).setMinionObserver(new EvasiveWyrm(player, (Minion) card9));
                card9.setDescription("Divine Shield. Rush.\n" +
                        "Can't be targeted by spells or Hero Powers.");
                return card9;

            case "Blessing of the Ancients" :
                Card card10 = new Spell();
                card10.setName("Blessing of the Ancients");
                card10.setManaCost(3);
                card10.setRarity(Rarity.COMMON);
                card10.setCardClass(CardClass.NEUTRAL);
                card10.setType(Type.SPELL);
                card10.setValue(20);
                ((Spell)card10).setSpellObserver(new BlessingOfTheAncients(player));
                card10.setDescription("Twinspell\n" +
                        "Give your minions +1/+1.");
                return card10;

            case "Chaos Nova" :
                Card card11 = new Spell();
                card11.setName("Chaos Nova");
                card11.setManaCost(5);
                card11.setRarity(Rarity.RARE);
                card11.setCardClass(CardClass.NEUTRAL);
                card11.setType(Type.SPELL);
                card11.setValue(20);
                ((Spell)card11).setSpellObserver(new ChaosNova(player));
                card11.setDescription("Deal 4 damage to all minions");
                return card11;


            case "Piloted Shredder" :
                Card card13 = new Minion();
                card13.setName("Piloted Shredder");
                card13.setManaCost(4);
                card13.setRarity(Rarity.EPIC);
                card13.setCardClass(CardClass.NEUTRAL);
                card13.setType(Type.MINION);
                card13.setValue(20);
                ((Minion)card13).setAttack(4);
                ((Minion)card13).setHP(3);
                ((Minion)card13).setMinionObserver(new PilotedShredder(player, (Minion) card13));
                card13.setDescription("Deathrattle: Summon a random 2-Cost minion.");
                return card13;


            case "Hunter's Mark" :
                Card card14 = new Spell();
                card14.setName("Hunter's Mark");
                card14.setManaCost(2);
                card14.setRarity(Rarity.COMMON);
                card14.setCardClass(CardClass.NEUTRAL);
                card14.setType(Type.SPELL);
                card14.setValue(20);
                ((Spell)card14).setSpellObserver(new HuntersMark(player));
                card14.setDescription("Change a minion's Health to 1");
                return card14;


            case "Gnomish Inventor" :
                Card card15 = new Minion();
                card15.setName("Gnomish Inventor");
                card15.setManaCost(4);
                card15.setRarity(Rarity.COMMON);
                card15.setCardClass(CardClass.NEUTRAL);
                card15.setType(Type.SPELL);
                card15.setValue(20);
                ((Minion)card15).setAttack(4);
                ((Minion)card15).setHP(3);
                ((Minion)card15).setMinionObserver(new GnomishInventor(player, (Minion) card15));
                card15.setDescription("Battlecry: Draw a card.");
                return card15;


            case "Goldshire Footman" :
                Card card16 = new Minion();
                card16.setName("Goldshire Footman");
                card16.setManaCost(1);
                card16.setRarity(Rarity.COMMON);
                card16.setCardClass(CardClass.NEUTRAL);
                card16.setType(Type.MINION);
                card16.setValue(20);
                ((Minion)card16).setAttack(1);
                ((Minion)card16).setHP(2);
                ((Minion)card16).setMinionObserver(new GoldshireFootman(player, (Minion) card16));
                card16.setDescription("Taunt");
                ((Minion)card16).setTaunt(true);
                return card16;


            case "Murloc Raider" :
                Card card17 = new Minion();
                card17.setName("Murloc Raider");
                card17.setManaCost(1);
                card17.setRarity(Rarity.COMMON);
                card17.setCardClass(CardClass.NEUTRAL);
                card17.setType(Type.MINION);
                card17.setValue(20);
                ((Minion)card17).setAttack(2);
                ((Minion)card17).setHP(1);
                ((Minion)card17).setMinionObserver(new MurlocRaider(player, (Minion) card17));
                card17.setDescription("");
                return card17;


            case "Stonetusk Boar" :
                Card card18 = new Minion();
                card18.setName("Stonetusk Boar");
                card18.setManaCost(1);
                card18.setRarity(Rarity.COMMON);
                card18.setCardClass(CardClass.NEUTRAL);
                card18.setType(Type.MINION);
                card18.setValue(20);
                ((Minion)card18).setAttack(1);
                ((Minion)card18).setHP(1);
                ((Minion)card18).setMinionObserver(new StonetuskBoar(player, (Minion) card18));
                card18.setDescription("Charge");
                ((Minion)card18).setRush(true);
                return card18;


            case "Doomsayer" :
                Card card19 = new Minion();
                card19.setName("Doomsayer");
                card19.setManaCost(2);
                card19.setRarity(Rarity.EPIC);
                card19.setCardClass(CardClass.NEUTRAL);
                card19.setType(Type.MINION);
                card19.setValue(20);
                ((Minion)card19).setAttack(0);
                ((Minion)card19).setHP(7);
                ((Minion)card19).setMinionObserver(new Doomsayer(player, (Minion) card19));
                card19.setDescription("At the start of your turn, destroy ALL minions");
                return card19;


            case "Shieldbearer" :
                Card card20 = new Minion();
                card20.setName("Shieldbearer");
                card20.setManaCost(1);
                card20.setRarity(Rarity.RARE);
                card20.setCardClass(CardClass.NEUTRAL);
                card20.setType(Type.MINION);
                card20.setValue(20);
                ((Minion)card20).setAttack(0);
                ((Minion)card20).setHP(4);
                ((Minion)card20).setMinionObserver(new Shieldbearer(player, (Minion) card20));
                card20.setDescription("Taunt");
                ((Minion)card20).setTaunt(true);
                return card20;

            case"Sprint":
                Card card21 = new Spell();
                card21.setName("Sprint");
                card21.setManaCost(7);
                card21.setRarity(Rarity.COMMON);
                card21.setCardClass(CardClass.NEUTRAL);
                card21.setType(Type.SPELL);
                card21.setValue(20);
                ((Spell)card21).setSpellObserver(new Sprint(player));
                card21.setDescription("Draw 4 cards.");
                return card21;


            case"Swarm of Locusts":
                Card card22 = new Spell();
                card22.setName("Swarm of Locusts");
                card22.setManaCost(6);
                card22.setRarity(Rarity.RARE);
                card22.setCardClass(CardClass.NEUTRAL);
                card22.setType(Type.SPELL);
                card22.setValue(20);
                ((Spell)card22).setSpellObserver(new SwarmOfLocusts(player));
                card22.setDescription("Summon seven 1/1 Locusts with Rush.");
                return card22;

            case"Pharaoh's Blessing":
                Card card23 = new Spell();
                card23.setName("Pharaoh's Blessing");
                card23.setManaCost(6);
                card23.setRarity(Rarity.RARE);
                card23.setCardClass(CardClass.NEUTRAL);
                card23.setType(Type.SPELL);
                card23.setValue(20);
                ((Spell)card23).setSpellObserver(new PharaohsBlessing(player));
                card23.setDescription("Give a minion +4/+4, Divine Shield, and Taunt.");
                return card23;

            case"Book of Specters":
                Card card24 = new Spell();
                card24.setName("Book of Specters");
                card24.setManaCost(2);
                card24.setRarity(Rarity.RARE);
                card24.setCardClass(CardClass.NEUTRAL);
                card24.setType(Type.SPELL);
                card24.setValue(20);
                ((Spell)card24).setSpellObserver(new BookOfSpecters(player));
                card24.setDescription("Draw 3 cards. Discard any spells drawn.");
                return card24;

            case "Sathrovarr" :
                Card card25 = new Minion();
                card25.setName("Sathrovarr");
                card25.setManaCost(9);
                card25.setRarity(Rarity.LEGENDARY);
                card25.setCardClass(CardClass.NEUTRAL);
                card25.setType(Type.MINION);
                card25.setValue(20);
                ((Minion)card25).setAttack(5);
                ((Minion)card25).setHP(5);
                ((Minion)card25).setMinionObserver(new Sathrovarr(player, (Minion) card25));
                card25.setDescription("Battlecry: Choose a friendly minion. Add a copy of it to your hand, deck, and battlefield.");
                return card25;


            case "Tomb Warden" :
                Card card26 = new Minion();
                card26.setName("Tomb Warden");
                card26.setManaCost(8);
                card26.setRarity(Rarity.LEGENDARY);
                card26.setCardClass(CardClass.NEUTRAL);
                card26.setType(Type.MINION);
                card26.setValue(20);
                ((Minion)card26).setAttack(3);
                ((Minion)card26).setHP(6);
                ((Minion)card26).setMinionObserver(new TombWarden(player, (Minion) card26));
                card26.setDescription("Taunt\n" +
                        "Battlecry: Summon a copy of this minion.");
                ((Minion)card26).setTaunt(true);
                return card26;

            case "Security Rover" :
                Card card27 = new Minion();
                card27.setName("Security Rover");
                card27.setManaCost(6);
                card27.setRarity(Rarity.RARE);
                card27.setCardClass(CardClass.NEUTRAL);
                card27.setType(Type.MINION);
                card27.setValue(20);
                ((Minion)card27).setAttack(2);
                ((Minion)card27).setHP(6);
                ((Minion)card27).setMinionObserver(new SecurityRover(player, (Minion) card27));
                card27.setDescription("Whenever this minion\n" +
                        "takes damage, summon a\n" +
                        "2/3 Mech with Taunt.");
                return card27;


            case "Curio Collector" :
                Card card28 = new Minion();
                card28.setName("Curio Collector");
                card28.setManaCost(5);
                card28.setRarity(Rarity.RARE);
                card28.setCardClass(CardClass.NEUTRAL);
                card28.setType(Type.MINION);
                card28.setValue(20);
                ((Minion)card28).setAttack(4);
                ((Minion)card28).setHP(4);
                ((Minion)card28).setMinionObserver(new CurioCollector(player, (Minion) card28));
                card28.setDescription("Whenever you draw a card, gain +1/+1.");
                return card28;

            case"Strength in Numbers":
                Card card29 = new Spell();
                card29.setName("Strength in Numbers");
                card29.setManaCost(1);
                card29.setRarity(Rarity.COMMON);
                card29.setCardClass(CardClass.NEUTRAL);
                card29.setType(Type.SPELL);
                card29.setValue(20);
                ((Spell)card29).setSpellObserver(new StrengthInNumbers(player));
                card29.setDescription("Sidequest: Spend 10 Mana on minions.\n" +
                        "Reward: Summon a minion from your deck.");
                return card29;


            case"Learn Draconic":
                Card card30 = new Spell();
                card30.setName("Learn Draconic");
                card30.setManaCost(1);
                card30.setRarity(Rarity.COMMON);
                card30.setCardClass(CardClass.NEUTRAL);
                card30.setType(Type.SPELL);
                card30.setValue(20);
                ((Spell)card30).setSpellObserver(new LearnDraconic(player));
                card30.setDescription("Sidequest: Spend\n" +
                        "8 Mana on spells.\n" +
                        "Reward: Summon a\n" +
                        "6/6 Dragon.");
                return card30;


            case"Meanstreet Marshal":
                Card card32 = new Minion();
                card32.setName("Meanstreet Marshal");
                card32.setManaCost(1);
                card32.setRarity(Rarity.RARE);
                card32.setCardClass(CardClass.HUNTER);
                card32.setType(Type.MINION);
                card32.setValue(20);
                ((Minion)card32).setAttack(1);
                ((Minion)card32).setHP(2);
                ((Minion)card32).setMinionObserver(new MeanstreetMarshal(player, (Minion) card32));
                card32.setDescription("Deathrattle: If this minion has 2 or more Attack, draw a card.");
                return card32;


            case"High Priest Amet":
                Card card33 = new Minion();
                card33.setName("High Priest Amet");
                card33.setManaCost(4);
                card33.setRarity(Rarity.LEGENDARY);
                card33.setCardClass(CardClass.PRIEST);
                card33.setType(Type.MINION);
                card33.setValue(20);
                ((Minion)card33).setAttack(2);
                ((Minion)card33).setHP(7);
                ((Minion)card33).setMinionObserver(new HighPriestAmet(player, (Minion) card33));
                card33.setDescription("Whenever you summon a\n" +
                        "minion, set its Health equal\n" +
                        "to this minion's.");
                return card33;


            case"Demonheart":
                Card card34 = new Spell();
                card34.setName("Demonheart");
                card34.setManaCost(5);
                card34.setRarity(Rarity.EPIC);
                card34.setCardClass(CardClass.PRIEST);
                card34.setType(Type.SPELL);
                card34.setValue(20);
                ((Spell)card34).setSpellObserver(new Demonheart(player));
                card34.setDescription("Deal 5 damage to a minion.  If it's a friendly Demon, give it +5/+5 instead.");
                return card34;

            case"Heavy Axe":
                Card card35 = new Weapon();
                card35.setName("Heavy Axe");
                card35.setManaCost(1);
                card35.setRarity(Rarity.EPIC);
                card35.setCardClass(CardClass.NEUTRAL);
                card35.setType(Type.WEAPON);
                card35.setValue(20);
                ((Weapon)card35).setDurability(3);
                ((Weapon)card35).setAttack(1);
                ((Weapon)card35).setWeaponObserver(new HeavyAxe(player));
                card35.setDescription("");
                return card35;

            case"Blood Fury":
                Card card36 = new Weapon();
                card36.setName("Blood Fury");
                card36.setManaCost(3);
                card36.setRarity(Rarity.LEGENDARY);
                card36.setCardClass(CardClass.NEUTRAL);
                card36.setType(Type.WEAPON);
                card36.setValue(20);
                ((Weapon)card36).setDurability(8);
                ((Weapon)card36).setAttack(3);
                ((Weapon)card36).setWeaponObserver(new BloodFury(player));
                card36.setDescription("");
                return card36;

            case"Battle Axe":
                Card card37 = new Weapon();
                card37.setName("Battle Axe");
                card37.setManaCost(1);
                card37.setRarity(Rarity.LEGENDARY);
                card37.setCardClass(CardClass.MAGE);
                card37.setType(Type.WEAPON);
                card37.setValue(20);
                ((Weapon)card37).setDurability(2);
                ((Weapon)card37).setAttack(2);
                ((Weapon)card37).setWeaponObserver(new BattleAxe(player));
                card37.setDescription("");
                return card37;


            case "Locust" :
                Card card38 = new Minion();
                card38.setName("Locust");
                card38.setManaCost(1);
                card38.setRarity(Rarity.COMMON);
                card38.setCardClass(CardClass.NEUTRAL);
                card38.setType(Type.MINION);
                card38.setValue(20);
                ((Minion)card38).setAttack(1);
                ((Minion)card38).setHP(1);
                ((Minion)card38).setMinionObserver(new Locust(player, (Minion) card38));
                card38.setDescription("rush");
                ((Minion)card38).setRush(true);
                return card38;

            case "Sheep" :
                Card card39 = new Minion();
                card39.setName("Sheep");
                card39.setManaCost(1);
                card39.setRarity(Rarity.COMMON);
                card39.setCardClass(CardClass.NEUTRAL);
                card39.setType(Type.MINION);
                card39.setValue(20);
                ((Minion)card39).setAttack(1);
                ((Minion)card39).setHP(1);
                ((Minion)card39).setMinionObserver(new Sheep(player, (Minion) card39));
                card39.setDescription("");
                return card39;


            case "Sleepy Dragon" :
                Card card40 = new Minion();
                card40.setName("Sleepy Dragon");
                card40.setManaCost(9);
                card40.setRarity(Rarity.COMMON);
                card40.setCardClass(CardClass.NEUTRAL);
                card40.setType(Type.MINION);
                card40.setValue(20);
                ((Minion)card40).setAttack(6);
                ((Minion)card40).setHP(6);
                ((Minion)card40).setMinionObserver(new SleepyDragon(player, (Minion) card40));
                card40.setDescription("");
                return card40;

            case "Swamp King Dred" :
                Card card41 = new Minion();
                card41.setName("Swamp King Dred");
                card41.setManaCost(7);
                card41.setRarity(Rarity.COMMON);
                card41.setCardClass(CardClass.HUNTER);
                card41.setType(Type.MINION);
                card41.setValue(20);
                ((Minion)card41).setAttack(9);
                ((Minion)card41).setHP(9);
                ((Minion)card41).setMinionObserver(new SwampKingDred(player, (Minion) card41));
                card41.setDescription("");
                return card41;


            default :
                return null;
        }

    }
}
