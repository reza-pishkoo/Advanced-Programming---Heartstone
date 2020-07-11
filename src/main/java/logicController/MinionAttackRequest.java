package logicController;

import cards.Card;
import cards.Minion;
import model.PlayerModel;

public class MinionAttackRequest extends LogicRequest {

    Minion attacker;

    public MinionAttackRequest(PlayerModel player, Minion attacker) {
        super(player);
        this.attacker = attacker;
    }

    public Minion getAttacker() {
        return attacker;
    }

    @Override
    void execute(LogicController logicController) throws Exception{
        System.out.println("c");
        attacker.getMinionObserver().attack(this, logicController);
    }

}
