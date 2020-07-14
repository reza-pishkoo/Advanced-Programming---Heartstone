package logicController.actions;

import cards.CardFactory;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

public class SwarmOfLocusts extends SpellObserver{

    public SwarmOfLocusts(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        for(int i = this.getPlayer().getBattleGroundCards().size(); i < 7; i++){
            logicController.getRequests().add(new AddToBattleGroundRequest
                    (this.getPlayer(), CardFactory.build("Locust", this.getPlayer())));
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
