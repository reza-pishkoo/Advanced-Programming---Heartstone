package logicController.actions;

import cards.Card;
import cards.Minion;
import cards.Weapon;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import guiController.WrongTargetRequest;
import heroes.Hero;
import logicController.*;
import model.PlayerModel;

public class HuntersMark extends SpellObserver{
    public HuntersMark(PlayerModel player) {
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
            if(requestAns.getPlayer() != getPlayer()) {
                if (target instanceof Minion) {
                    ((Minion) target).setHP(1);
                    logicController.sendResponse(new CorrectTargetRequest(getPlayer(), logicController.getGameModel()));
                }
                if (target instanceof Hero) {
                    logicController.sendResponse(new WrongTargetRequest(getPlayer(), logicController.getGameModel()));
                }
                if (target instanceof Weapon) {
                    logicController.sendResponse(new WrongTargetRequest(getPlayer(), logicController.getGameModel()));
                }
            }else{
                logicController.sendResponse(new WrongTargetRequest(getPlayer(), logicController.getGameModel()));
            }
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
