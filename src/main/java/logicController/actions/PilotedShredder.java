package logicController.actions;

import cards.Minion;
import model.PlayerModel;

public class PilotedShredder extends MinionObserver{

    public PilotedShredder(PlayerModel player, Minion card) {
        super(player, card);
    }
}
