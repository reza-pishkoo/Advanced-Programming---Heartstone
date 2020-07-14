package logicController;

import cards.Card;
import cards.Minion;
import cards.Weapon;
import model.PlayerModel;

public class AttackRequest extends LogicRequest {

    Card attacker;

    public AttackRequest(PlayerModel player, Card attacker) {
        super(player);
        this.attacker = attacker;
    }

    public Card getAttacker() {
        return attacker;
    }

    @Override
    void execute(LogicController logicController) throws Exception{
        super.execute(logicController);
        if(attacker instanceof Minion)
            ((Minion)attacker).getMinionObserver().attack(this, logicController);
        if(attacker instanceof Weapon)
            ((Weapon)attacker).getWeaponObserver().attack(this, logicController);
    }

}
