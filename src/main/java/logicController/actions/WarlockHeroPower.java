package logicController.actions;

import cards.Card;
import cards.Minion;
import cards.Weapon;
import guiController.CorrectTargetRequest;
import guiController.TargetRequest;
import guiController.WrongTargetRequest;
import heroes.Hero;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.MyTargetRequest;
import model.PlayerModel;

import java.util.Random;

public class WarlockHeroPower extends HeroPowerObserver{


    public WarlockHeroPower(PlayerModel player) {
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
        int x = new Random().nextInt(2);
        getPlayer().getHero().setHealth(getPlayer().getHero().getHealth() - 2);

        if(getPlayer().getHandCards().size() > 0){
            if(x == 1){
                int y = new Random().nextInt(getPlayer().getHandCards().size());
                ((Minion)getPlayer().getHandCards().get(y)).setAttack(((Minion)getPlayer().getHandCards().get(y)).getAttack() + 1);
                ((Minion)getPlayer().getHandCards().get(y)).setHP(((Minion)getPlayer().getHandCards().get(y)).getHP() + 1);
            }
            if(x == 0){
                Card y = getPlayer().getCurrentDeck().get(0);
                getPlayer().getCurrentDeck().remove(y);
                getPlayer().getHandCards().add(y);
            }
        }else {
            Card y = getPlayer().getCurrentDeck().get(0);
            getPlayer().getCurrentDeck().remove(y);
            getPlayer().getHandCards().add(y);
        }

        getPlayer().getHero().setCanAttack(false);
    }
}
