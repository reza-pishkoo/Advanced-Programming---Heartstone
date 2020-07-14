package logicController.actions;

import cards.Minion;
import cards.Weapon;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import guiController.WrongTargetRequest;
import heroes.Hero;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.MyTargetRequest;
import model.PlayerModel;

public class PharaohsBlessing extends SpellObserver{
    public PharaohsBlessing(PlayerModel player) {
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
            if(requestAns.getPlayer() == getPlayer()) {
                if (target instanceof Minion) {
                    ((Minion)target).setTaunt(true);
                    ((Minion)target).setHP(((Minion)target).getHP() + 4);
                    ((Minion)target).setAttack(((Minion)target).getAttack() + 4);
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
