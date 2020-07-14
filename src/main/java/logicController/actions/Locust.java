package logicController.actions;

import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class Locust extends MinionObserver{


    public Locust(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        addToBattleGroundRequest.getCard().setCanUse(true);
        super.play(addToBattleGroundRequest, logicController);
    }
}
