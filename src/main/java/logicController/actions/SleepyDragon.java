package logicController.actions;

import cards.Minion;
import model.PlayerModel;

public class SleepyDragon extends MinionObserver{
    public SleepyDragon(PlayerModel player, Minion card) {
        super(player, card);
    }
}
