package logicController.actions;

import cards.Minion;
import model.PlayerModel;

public class Sheep extends MinionObserver{
    public Sheep(PlayerModel player, Minion card) {
        super(player, card);
    }
}
