package logicController.actions;

import cards.Minion;
import cards.Weapon;
import guiController.CantAttackRequest;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import guiController.WrongTargetRequest;
import heroes.Hero;
import logicController.*;
import model.PlayerModel;

public class WeaponObserver implements Observer{
    private PlayerModel player;

    public WeaponObserver(PlayerModel player) {
        this.player = player;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        if(player.getWeapon().getDurability() <= 0){
            player.setWeapon(null);
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
                        if (target instanceof Minion) {
                            player.getWeapon().setDurability(player.getWeapon().getDurability() - 1);
                            ((Minion) target).setHP(((Minion) target).getHP() - request.getPlayer().getWeapon().getAttack());
                            ((AttackRequest) request).getAttacker().setCanUse(false);
                            logicController.sendResponse(new CorrectTargetRequest(request.getPlayer(), logicController.getGameModel()));
                        }
                        if (target instanceof Hero) {
                            player.getWeapon().setDurability(player.getWeapon().getDurability() - 1);
                            ((Hero) target).setDefenceShield(((Hero) target).getDefenceShield() - request.getPlayer().getWeapon().getAttack());
                            if(((Hero) target).getDefenceShield() < 0) {
                                ((Hero) target).setHealth(((Hero) target).getHealth() - Math.abs(((Hero) target).getDefenceShield()));
                                ((Hero) target).setDefenceShield(0);
                            }
                            ((AttackRequest) request).getAttacker().setCanUse(false);
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
    public void play(AddToBattleGroundRequest addToBattleGroundRequest) {
        addToBattleGroundRequest.getPlayer().setWeapon((Weapon)addToBattleGroundRequest.getCard());
        addToBattleGroundRequest.getPlayer().getHandCards().remove(addToBattleGroundRequest.getCard());
    }
}
