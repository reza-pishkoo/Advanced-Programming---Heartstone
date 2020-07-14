package logicController.actions;

import cards.Card;
import guiController.UpdateRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

import java.util.HashSet;
import java.util.Set;

public class OffCards extends PassiveObserver{

    private Set changedCards = new HashSet();
    public OffCards(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        for (Card card :this.getPlayer().getHandCards()){
            if(!changedCards.contains(card))
                card.setManaCost(card.getManaCost() - 1);
        }
        for (Card card :this.getPlayer().getHandCards())
            changedCards.add(card);
        logicController.sendResponse(new UpdateRequest(getPlayer(), logicController.getGameModel()));
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
        logicController.register(new OffCards(getPlayer()));
    }
}
