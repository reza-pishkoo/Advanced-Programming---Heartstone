package logicController.actions;

import cards.Card;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

import java.util.HashSet;
import java.util.Set;

public class Warriors extends PassiveObserver{
    private Set<Card> changedCards = new HashSet();
    public Warriors(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        for (Card card : changedCards){
            if(!getPlayer().getBattleGroundCards().contains(card)) {
                getPlayer().getHero().setDefenceShield(getPlayer().getHero().getDefenceShield() + 2);
                changedCards.remove(card);
            }
        }
        for (Card card :this.getPlayer().getBattleGroundCards())
            changedCards.add(card);
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
        logicController.register(new Warriors(getPlayer()));
    }
}
