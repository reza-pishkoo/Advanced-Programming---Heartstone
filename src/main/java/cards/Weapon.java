package cards;

import logicController.actions.WeaponObserver;

public class Weapon extends Card {

    private int Durability;
    private int attack;
    private WeaponObserver weaponObserver;

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

    public WeaponObserver getWeaponObserver() {
        return weaponObserver;
    }

    public void setWeaponObserver(WeaponObserver weaponObserver) {
        this.weaponObserver = weaponObserver;
    }
}
