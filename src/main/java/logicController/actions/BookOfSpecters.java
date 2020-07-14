package logicController.actions;

import cards.Spell;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

public class BookOfSpecters extends SpellObserver{
    public BookOfSpecters(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        for (int i = 0; i < 3; i++) {
            logicController.addToHand();
        }
        for (int i = 1; i < 4; i++) {
            if(getPlayer().getHandCards().get(getPlayer().getHandCards().size() - i) instanceof Spell)
                getPlayer().getHandCards().remove(getPlayer().getHandCards().size() - i);
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
