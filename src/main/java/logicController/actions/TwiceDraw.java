package logicController.actions;

import data.Log;
import gui.playPanel.PlayPanel;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.endTurn;
import model.PlayerModel;

import java.util.Random;

public class TwiceDraw extends PassiveObserver{
    public TwiceDraw(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(request instanceof endTurn)
            if(getPlayer() == logicController.getGameModel().getCurrentPlayer()){
                Random random = new Random();
                if(getPlayer().getCurrentDeck().size() > 0) {
                    int cardIndex = random.nextInt(getPlayer().getCurrentDeck().size());
                    if (getPlayer().getHandCards().size() < 10) {
                        getPlayer().getHandCards().add
                                (getPlayer().getCurrentDeck().get(cardIndex));
                    }
//                    Log.bodyLogger("game", "draw " + logicController.getGameModel().getNextPlayer().getCurrentDeck().get(cardIndex).toString());
//                    PlayPanel.getInstance().gameLogPanel.appendText("draw  " + logicController.getGameModel().getNextPlayer().getCurrentDeck().get(cardIndex).toString());
                    getPlayer().getCurrentDeck().remove(cardIndex);
                }
            }
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
        logicController.register(new TwiceDraw(getPlayer()));
    }
}
