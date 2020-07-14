package logicController.actions;

import cards.Card;
import cards.CardFactory;
import cards.Minion;
import cards.Spell;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class LearnDraconic extends SpellObserver{
    private int useManaOnSpell;
    public LearnDraconic(PlayerModel player) {
        super(player);
        useManaOnSpell = 0;
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(request instanceof AddToBattleGroundRequest){
            if(request.getPlayer() == getPlayer()){
                if(((AddToBattleGroundRequest) request).getCard() instanceof Spell){
                    useManaOnSpell += ((AddToBattleGroundRequest) request).getCard().getManaCost();
                }
            }
        }
        if(useManaOnSpell >= 8){
            Card x = CardFactory.build("Sleepy Dragon", getPlayer());
            logicController.register(((Minion)x).getMinionObserver());
            getPlayer().getBattleGroundCards().add(x);
            logicController.unregister(this);
        }
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        super.play(addToBattleGroundRequest, logicController);
        logicController.register(new LearnDraconic(getPlayer()));
    }
}
