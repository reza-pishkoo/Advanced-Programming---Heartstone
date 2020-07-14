package logicController.actions;

import cards.Card;
import cards.Minion;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.endTurn;
import model.PlayerModel;

public class Dreadscale extends MinionObserver{


    public Dreadscale(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(request instanceof endTurn){
            if(getPlayer() == logicController.getGameModel().getNextPlayer()){
                for(Card card : logicController.getGameModel().getNextPlayer().getBattleGroundCards())
                    ((Minion)card).setHP(((Minion)card).getHP() - 1);
                for(Card card : logicController.getGameModel().getCurrentPlayer().getBattleGroundCards())
                    ((Minion)card).setHP(((Minion)card).getHP() - 1);
            }
        }
    }
}
