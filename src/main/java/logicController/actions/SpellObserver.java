package logicController.actions;

import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public abstract class SpellObserver implements Observer{
    private PlayerModel player;

    public SpellObserver(PlayerModel player) {
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

    public void attack(LogicRequest request, LogicController logicController) {

    }
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        addToBattleGroundRequest.getPlayer().getHandCards().remove(addToBattleGroundRequest.getCard());
    }
}
