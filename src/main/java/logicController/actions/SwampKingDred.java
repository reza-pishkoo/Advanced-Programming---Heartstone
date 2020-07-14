package logicController.actions;

import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class SwampKingDred extends MinionObserver{
    public SwampKingDred(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(request instanceof AddToBattleGroundRequest)
            if(((AddToBattleGroundRequest) request).getCard() instanceof Minion)
                if(request.getPlayer() != getPlayer()){
                    ((Minion) ((AddToBattleGroundRequest) request).getCard()).setHP
                            (((Minion) ((AddToBattleGroundRequest) request).getCard()).getHP() - getCard().getAttack());
                    getCard().setHP(getCard().getHP() - (((Minion) ((AddToBattleGroundRequest) request).getCard()).getAttack()));
                }
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        super.play(addToBattleGroundRequest, logicController);
    }
}
