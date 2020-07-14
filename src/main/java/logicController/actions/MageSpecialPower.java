package logicController.actions;

import cards.Card;
import cards.Minion;
import guiController.UpdateRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

import java.util.HashSet;
import java.util.Set;

public class MageSpecialPower extends SpecialPowerObserver{
    private Set changedCards = new HashSet();

    public MageSpecialPower(PlayerModel player) {
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
                if(card instanceof Minion)
                    card.setManaCost(Math.max(card.getManaCost() - 2, 0));
        }
        for (Card card :this.getPlayer().getHandCards())
            if(card instanceof Minion)
                changedCards.add(card);
        logicController.sendResponse(new UpdateRequest(getPlayer(), logicController.getGameModel()));
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
        logicController.register(new MageSpecialPower(getPlayer()));
    }
}
