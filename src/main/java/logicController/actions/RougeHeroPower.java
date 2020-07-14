package logicController.actions;

import cards.Card;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

import java.util.Random;

public class RougeHeroPower extends HeroPowerObserver{


    public RougeHeroPower(PlayerModel player) {
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
        if(getPlayer().getCurrentMana() >= 3) {
            if (logicController.getGameModel().getNextPlayer().getCurrentDeck().size() > 0) {
                int x = new Random().nextInt(logicController.getGameModel().getNextPlayer().getCurrentDeck().size());
                Card y = logicController.getGameModel().getNextPlayer().getCurrentDeck().get(x);
                getPlayer().getHandCards().add(y);
                logicController.getGameModel().getNextPlayer().getCurrentDeck().remove(y);
            }
            if (getPlayer().getWeapon() != null) {
                if (logicController.getGameModel().getNextPlayer().getHandCards().size() > 0) {
                    int x = new Random().nextInt(logicController.getGameModel().getNextPlayer().getHandCards().size());
                    Card y = logicController.getGameModel().getNextPlayer().getHandCards().get(x);
                    getPlayer().getHandCards().add(y);
                    logicController.getGameModel().getNextPlayer().getHandCards().remove(y);
                }
            }
            getPlayer().getHero().setCanAttack(false);
            getPlayer().setCurrentMana(getPlayer().getCurrentMana() - 3);
        }
    }
}
