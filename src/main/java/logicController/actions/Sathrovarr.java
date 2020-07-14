package logicController.actions;

import cards.CardFactory;
import cards.Minion;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import logicController.*;
import model.PlayerModel;

public class Sathrovarr extends MinionObserver{


    public Sathrovarr(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        logicController.sendResponse(new TargetRequest(addToBattleGroundRequest.getPlayer(), logicController.getGameModel()));
        while (logicController.getRequests().isEmpty()) {
            logicController.sleep();
        }
        LogicRequest requestAns = logicController.getRequests().pollFirst();
        if (requestAns instanceof MyTargetRequest) {
            Object target = ((MyTargetRequest) requestAns).getTarget();
            if(target instanceof Minion)
                if(this.getPlayer().getBattleGroundCards().contains(target)){
                    this.getPlayer().getBattleGroundCards().add(CardFactory.build(((Minion) target).getName(), null));
                    this.getPlayer().getHandCards().add(CardFactory.build(((Minion) target).getName(), null));
                    this.getPlayer().getCurrentDeck().add(CardFactory.build(((Minion) target).getName(), null));
                }
        }
        logicController.sendResponse(new CorrectTargetRequest(addToBattleGroundRequest.getPlayer(), logicController.getGameModel()));
        super.play(addToBattleGroundRequest, logicController);
    }
}
