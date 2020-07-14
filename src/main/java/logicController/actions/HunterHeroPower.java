package logicController.actions;

import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class HunterHeroPower extends HeroPowerObserver{


    public HunterHeroPower(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(request instanceof AddToBattleGroundRequest)
            if(request.getPlayer() != getPlayer())
                if (((AddToBattleGroundRequest) request).getCard() instanceof Minion)
                    ((Minion) ((AddToBattleGroundRequest) request).getCard()).setHP
                            (((Minion) ((AddToBattleGroundRequest) request).getCard()).getHP() - 1);
    }

    @Override
    public void execute(LogicRequest request, LogicController logicController) {
        super.execute(request, logicController);
        logicController.register(new HunterHeroPower(getPlayer()));
    }
}
