package logicController.actions;

import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public abstract class HeroPowerObserver implements Observer{

    private PlayerModel player;

    public HeroPowerObserver(PlayerModel player) {
        this.player = player;
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {

    }

    public void execute(LogicRequest request, LogicController logicController){

    }

    public PlayerModel getPlayer() {
        return player;
    }
}
