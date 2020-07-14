package logicController.actions;

import cards.Card;
import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

public class ChaosNova extends SpellObserver{
    public ChaosNova(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        for(Card card : logicController.getGameModel().getNextPlayer().getBattleGroundCards())
            ((Minion)card).setHP(((Minion)card).getHP() - 4);
        for(Card card : logicController.getGameModel().getCurrentPlayer().getBattleGroundCards())
            ((Minion)card).setHP(((Minion)card).getHP() - 4);
        super.play(addToBattleGroundRequest, logicController);
    }
}
