package logicController.actions;

import guiController.UpdateRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.endTurn;
import model.PlayerModel;

public class ManaJump extends PassiveObserver{
    public ManaJump(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(getPlayer().getTurn() == 1){
            if(logicController.getGameModel().getRound() == 1){
                getPlayer().setCurrentMana(2);
            }
        }
        if(request instanceof endTurn)
            if(getPlayer() == logicController.getGameModel().getCurrentPlayer()){
                if(getPlayer().getCurrentMana() < 10){
                    getPlayer().setCurrentMana(getPlayer().getCurrentMana() + 1);
                    logicController.sendResponse(new UpdateRequest(getPlayer(), logicController.getGameModel()));
                }
            }
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
        logicController.register(new ManaJump(getPlayer()));

    }
}
