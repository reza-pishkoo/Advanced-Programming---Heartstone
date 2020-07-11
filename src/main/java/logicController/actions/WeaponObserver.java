package logicController.actions;

import cards.Weapon;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;

public class WeaponObserver implements Observer{

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {

    }

    public void attack(LogicRequest request, LogicController logicController) {

    }
    public void play(AddToBattleGroundRequest addToBattleGroundRequest) {
        addToBattleGroundRequest.getPlayer().setWeapon((Weapon)addToBattleGroundRequest.getCard());
        addToBattleGroundRequest.getPlayer().getHandCards().remove(addToBattleGroundRequest.getCard());
    }
}
