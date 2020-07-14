package logicController.actions;

import cards.Minion;
import data.Log;
import gui.playPanel.PlayPanel;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

import java.util.Random;

public class GnomishInventor extends MinionObserver{


    public GnomishInventor(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        Random random = new Random();
        if(getPlayer().getCurrentDeck().size() > 0) {
            int cardIndex = random.nextInt(getPlayer().getCurrentDeck().size());
            if (getPlayer().getHandCards().size() < 10) {
                getPlayer().getHandCards().add
                        (getPlayer().getCurrentDeck().get(cardIndex));
            }
                    Log.bodyLogger("game", "draw " + getPlayer().getCurrentDeck().get(cardIndex).toString());
                    PlayPanel.getInstance().gameLogPanel.appendText("draw  " + getPlayer().getCurrentDeck().get(cardIndex).toString());
            getPlayer().getCurrentDeck().remove(cardIndex);
        }
        super.play(addToBattleGroundRequest, logicController);
    }
}
