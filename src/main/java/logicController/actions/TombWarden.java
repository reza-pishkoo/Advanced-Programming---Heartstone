package logicController.actions;

import cards.CardFactory;
import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

public class TombWarden extends MinionObserver{


    public TombWarden(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        addToBattleGroundRequest.getPlayer().getBattleGroundCards().add
                (CardFactory.build(addToBattleGroundRequest.getCard().getName(), getPlayer()));
        super.play(addToBattleGroundRequest, logicController);
    }
}
