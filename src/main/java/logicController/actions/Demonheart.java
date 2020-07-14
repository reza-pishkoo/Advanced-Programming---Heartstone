package logicController.actions;

import cards.Minion;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import logicController.AddToBattleGroundRequest;
import logicController.MyTargetRequest;
import model.PlayerModel;
import logicController.LogicController;
import logicController.LogicRequest;

public class Demonheart extends SpellObserver{
    public Demonheart(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        logicController.sendResponse(new TargetRequest(getPlayer(), logicController.getGameModel()));
        while (logicController.getRequests().isEmpty()) {
            logicController.sleep();
        }
        LogicRequest requestAns = logicController.getRequests().pollFirst();
        if (requestAns instanceof MyTargetRequest) {
            Object target = ((MyTargetRequest) requestAns).getTarget();
            if(target instanceof Minion) {
                if(((Minion) target).getMinionObserver().getPlayer().getBattleGroundCards().contains(target)) {
                    if (this.getPlayer() == ((Minion) target).getMinionObserver().getPlayer()) {
                        ((Minion) target).setHP(((Minion) target).getHP() + 5);
                        ((Minion) target).setAttack(((Minion) target).getAttack() + 5);
                    } else {
                        ((Minion) target).setHP(((Minion) target).getHP() - 5);
                    }
                }
            }
            logicController.sendResponse(new CorrectTargetRequest(getPlayer(), logicController.getGameModel()));
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
