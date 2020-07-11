package logicController.actions;

import cards.Weapon;
import guiController.TargetRequest;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.MinionAttackRequest;

public abstract class SpellObserver implements Observer{

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {

    }

    public void attack(LogicRequest request, LogicController logicController) {

    }
    public void play(AddToBattleGroundRequest addToBattleGroundRequest) {
        addToBattleGroundRequest.getPlayer().getHandCards().remove(addToBattleGroundRequest.getCard());
    }
}
