package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public abstract class SpecialPowerObserver implements Observer{

    private PlayerModel player;

    public SpecialPowerObserver(PlayerModel player) {
        this.player = player;
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {

    }

    public void perform(LogicRequest request, LogicController logicController){

    }

    public PlayerModel getPlayer() {
        return player;
    }
}
