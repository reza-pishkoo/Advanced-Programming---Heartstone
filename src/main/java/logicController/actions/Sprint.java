package logicController.actions;

import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

public class Sprint extends SpellObserver{

    public Sprint(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        for (int i = 0; i < 4; i++) {
            logicController.addToHand();
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
