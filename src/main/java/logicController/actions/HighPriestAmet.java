package logicController.actions;

import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class HighPriestAmet extends MinionObserver{


    public HighPriestAmet(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        if(request instanceof AddToBattleGroundRequest)
            if(((AddToBattleGroundRequest) request).getCard() instanceof Minion)
                ((Minion)((AddToBattleGroundRequest) request).getCard()).setHP(getCard().getHP());
        super.observeAfterRequest(request, logicController);
    }
}
