package logicController.actions;

import cards.CardFactory;
import cards.Minion;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.MyTargetRequest;
import model.PlayerModel;

public class Polymorph extends SpellObserver{
    public Polymorph(PlayerModel player) {
        super(player);
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
            if(target instanceof Minion) {
                if (this.getPlayer() != ((Minion) target).getMinionObserver().getPlayer()) {
                    if(((Minion) target).getMinionObserver().getPlayer().getBattleGroundCards().contains(target)) {
                        ((Minion) target).getMinionObserver().getPlayer().getBattleGroundCards().remove(target);
                        ((Minion) target).getMinionObserver().getPlayer().getBattleGroundCards().add
                                (CardFactory.build("Sheep", ((Minion) target).getMinionObserver().getPlayer()));
                    }
                }
            }
            logicController.sendResponse(new CorrectTargetRequest(getPlayer(), logicController.getGameModel()));
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
