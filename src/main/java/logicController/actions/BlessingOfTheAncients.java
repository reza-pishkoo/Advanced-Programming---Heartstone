package logicController.actions;

import cards.Card;
import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

public class BlessingOfTheAncients extends SpellObserver{
    public BlessingOfTheAncients(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        for(Card minion : getPlayer().getBattleGroundCards()){
            ((Minion)minion).setHP(((Minion)minion).getHP() + 1);
            ((Minion)minion).setAttack(((Minion)minion).getAttack() + 1);
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
