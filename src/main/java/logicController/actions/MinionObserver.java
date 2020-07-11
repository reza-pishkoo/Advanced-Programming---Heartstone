package logicController.actions;

import cards.Minion;
import guiController.TargetRequest;
import logicController.*;

public abstract class MinionObserver implements Observer {

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {

    }

    public void attack(LogicRequest request, LogicController logicController) {
        System.out.println("d");
        if(request instanceof MinionAttackRequest){
            System.out.println("e");
            logicController.sendResponse(new TargetRequest(request.getPlayer(), logicController.getGameModel()));
            while (logicController.getRequests().isEmpty()){
                logicController.sleep();
            }
            LogicRequest requestAns = logicController.getRequests().pollFirst();
            if(requestAns instanceof MinionTargetRequest){
                ((MinionAttackRequest) request).getAttacker().setCanUse(false);
                Object target = ((MinionTargetRequest) requestAns).getTarget();
                if(target instanceof Minion){
                    ((MinionAttackRequest) request).getAttacker().setHP
                            (((MinionAttackRequest) request).getAttacker().getHP() - ((Minion) target).getAttack());
                    ((Minion) target).setHP(((Minion) target).getHP() - ((MinionAttackRequest) request).getAttacker().getAttack());
                }
            }
        }
    }
    public void play(AddToBattleGroundRequest addToBattleGroundRequest) {
        addToBattleGroundRequest.getPlayer().getBattleGroundCards().add(addToBattleGroundRequest.getCard());
        addToBattleGroundRequest.getPlayer().getHandCards().remove(addToBattleGroundRequest.getCard());
    }

//    @Override
//    public void play() {
//        super.play();
//        //
//    }


}
