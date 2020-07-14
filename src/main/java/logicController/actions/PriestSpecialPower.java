package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class PriestSpecialPower extends SpecialPowerObserver{

    public PriestSpecialPower(PlayerModel player) {
        super(player);
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
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
    }
}
