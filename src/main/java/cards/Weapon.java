package cards;

public class Weapon extends Card {

    private int Durability;
    private int attack;

    public Weapon(){
    }

    public void setDurability(int durability) {
        Durability = durability;
    }

    public int getDurability() {
        return Durability;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
