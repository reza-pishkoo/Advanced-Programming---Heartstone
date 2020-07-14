package logicController.actions;

import cards.Minion;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class MeanstreetMarshal extends MinionObserver{
    public MeanstreetMarshal(PlayerModel player, Minion card) {
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
}
