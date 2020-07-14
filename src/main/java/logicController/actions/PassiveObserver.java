package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public abstract class PassiveObserver implements Observer{

    private PlayerModel player;

    public PassiveObserver(PlayerModel player) {
        this.player = player;
    }

    public PlayerModel getPlayer() {
        return player;
    }


    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {

    }

    public void perform(LogicRequest request, LogicController logicController){

    }
}
