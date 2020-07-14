package logicController.actions;

import cards.Card;
import cards.Minion;
import cards.Weapon;
import guiController.CantAttackRequest;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import guiController.WrongTargetRequest;
import heroes.Hero;
import logicController.*;
import model.PlayerModel;

public abstract class MinionObserver implements Observer {

    private PlayerModel player;
    private Minion card;

    public MinionObserver(PlayerModel player, Minion card) {
        this.card = card;
        this.player = player;
    }


    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {

    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        if (card.getHP() <= 0) {
            player.getBattleGroundCards().remove(card);
            logicController.unregister(this);
        }
    }

    public void attack(LogicRequest request, LogicController logicController) {
        if(request instanceof AttackRequest){
            if (!((AttackRequest) request).getAttacker().isCanUse()){
                logicController.sendResponse(new CantAttackRequest(request.getPlayer(), logicController.getGameModel()));
            }else {
                logicController.sendResponse(new TargetRequest(request.getPlayer(), logicController.getGameModel()));
                while (logicController.getRequests().isEmpty()) {
                    logicController.sleep();
                }
                LogicRequest requestAns = logicController.getRequests().pollFirst();
                if (requestAns instanceof MyTargetRequest) {
                    Object target = ((MyTargetRequest) requestAns).getTarget();
                    if(requestAns.getPlayer() != player) {
                        boolean canAtt = true;
                        boolean taunt = false;
                        for(Card card : requestAns.getPlayer().getBattleGroundCards()){
                            if(((Minion)card).isTaunt())
                                taunt = true;
                        }
                        if(!((Minion)target).isTaunt() && taunt)
                            canAtt = false;
                        if(canAtt) {
                            if (target instanceof Minion) {
                                card.setHP(card.getHP() - ((Minion) target).getAttack());
                                ((Minion) target).setHP(((Minion) target).getHP() - card.getAttack());
                                ((AttackRequest) request).getAttacker().setCanUse(false);
                                logicController.sendResponse(new CorrectTargetRequest(request.getPlayer(), logicController.getGameModel()));
                            }
                            if (target instanceof Hero) {
                                ((Hero) target).setDefenceShield(((Hero) target).getDefenceShield() - card.getAttack());
                                if(((Hero) target).getDefenceShield() < 0) {
                                    ((Hero) target).setHealth(((Hero) target).getHealth() - Math.abs(((Hero) target).getDefenceShield()));
                                    ((Hero) target).setDefenceShield(0);
                                }
                                ((AttackRequest) request).getAttacker().setCanUse(false);
                                logicController.sendResponse(new CorrectTargetRequest(request.getPlayer(), logicController.getGameModel()));
                            }
                            if (target instanceof Weapon) {
                                logicController.sendResponse(new WrongTargetRequest(request.getPlayer(), logicController.getGameModel()));
                            }
                        }
                    }else{
                        logicController.sendResponse(new WrongTargetRequest(request.getPlayer(), logicController.getGameModel()));
                    }
                }
            }
        }
    }
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        if(((Minion)addToBattleGroundRequest.getCard()).isRush())
            ((Minion)addToBattleGroundRequest.getCard()).setCanUse(true);
        addToBattleGroundRequest.getPlayer().getBattleGroundCards().add(addToBattleGroundRequest.getCard());
        addToBattleGroundRequest.getPlayer().getHandCards().remove(addToBattleGroundRequest.getCard());
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public Minion getCard() {
        return card;
    }
}
