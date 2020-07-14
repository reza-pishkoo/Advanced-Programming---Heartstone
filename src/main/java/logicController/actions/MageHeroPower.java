package logicController.actions;

import cards.Minion;
import cards.Weapon;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import guiController.WrongTargetRequest;
import heroes.Hero;
import logicController.AttackRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.MyTargetRequest;
import model.PlayerModel;

public class MageHeroPower extends HeroPowerObserver{

    public MageHeroPower(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
    }

    @Override
    public void execute(LogicRequest request, LogicController logicController) {
        super.execute(request, logicController);
        if(getPlayer().getCurrentMana() >= 2){
            logicController.sendResponse(new TargetRequest(request.getPlayer(), logicController.getGameModel()));
            while (logicController.getRequests().isEmpty()) {
                logicController.sleep();
            }
            LogicRequest requestAns = logicController.getRequests().pollFirst();
            if (requestAns instanceof MyTargetRequest) {
                Object target = ((MyTargetRequest) requestAns).getTarget();
                if(requestAns.getPlayer() != getPlayer()) {
                    if (target instanceof Minion) {
                        ((Minion) target).setHP(((Minion) target).getHP() - 1);
                        getPlayer().getHero().setCanAttack(false);
                        logicController.sendResponse(new CorrectTargetRequest(request.getPlayer(), logicController.getGameModel()));
                    }
                    if (target instanceof Hero) {
                        ((Hero) target).setDefenceShield(((Hero) target).getDefenceShield() - 1);
                        if(((Hero) target).getDefenceShield() < 0) {
                            ((Hero) target).setHealth(((Hero) target).getHealth() - Math.abs(((Hero) target).getDefenceShield()));
                            ((Hero) target).setDefenceShield(0);
                        }
                        getPlayer().getHero().setCanAttack(false);
                        logicController.sendResponse(new CorrectTargetRequest(request.getPlayer(), logicController.getGameModel()));
                    }
                    if (target instanceof Weapon){
                        logicController.sendResponse(new WrongTargetRequest(request.getPlayer(), logicController.getGameModel()));
                    }
                }else{
                    logicController.sendResponse(new WrongTargetRequest(request.getPlayer(), logicController.getGameModel()));
                }
            }
        }
    }
}
