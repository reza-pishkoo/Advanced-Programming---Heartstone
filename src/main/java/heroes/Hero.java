package heroes;

import cards.Card;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hero {
    @Id
    private String name;
    @Column
    private int health;
    @Column
    private HeroType type;
    @Column
    private boolean CanAttack;
    @Column
    private int attack;
    @Column
    private String HeroPower;
    @Column
    private String SpecialPower;



    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public HeroType getType() {
        return type;
    }

    public boolean isCanAttack() {
        return CanAttack;
    }

    public int getAttack() {
        return attack;
    }

    public String getHeroPower() {
        return HeroPower;
    }

    public String getSpecialPower() {
        return SpecialPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setType(HeroType type) {
        this.type = type;
    }

    public void setCanAttack(boolean canAttack) {
        CanAttack = canAttack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHeroPower(String heroPower) {
        HeroPower = heroPower;
    }

    public void setSpecialPower(String specialPower) {
        SpecialPower = specialPower;
    }

    @Override
    public String toString() {
        return getName();
    }
}
