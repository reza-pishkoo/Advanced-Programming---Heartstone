package logicController.actions;

import cards.Minion;
import cards.Spell;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class ManaWyrm extends MinionObserver{

    public ManaWyrm(PlayerModel player, Minion card) {
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
            if(((AddToBattleGroundRequest) request).getCard() instanceof Spell)
                if(request.getPlayer() == getPlayer())
                    getCard().setAttack(getCard().getAttack() + 1);
    }
}
