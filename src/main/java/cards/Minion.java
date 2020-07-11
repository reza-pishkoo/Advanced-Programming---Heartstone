package cards;

import logicController.actions.MinionObserver;

import java.util.ArrayList;

public class Minion extends Card {

    private MinionObserver minionObserver;
    private int attack;
    private int HP;


    public Minion(){
    }




    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }



    public int getAttack() {
        return attack;
    }

    public int getHP() {
        return HP;
    }

    public MinionObserver getMinionObserver() {
        return minionObserver;
    }

    public void setMinionObserver(MinionObserver minionObserver) {
        this.minionObserver = minionObserver;
    }
}
