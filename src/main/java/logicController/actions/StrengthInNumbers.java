package logicController.actions;

import cards.Card;
import cards.Minion;
import cards.Spell;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

import java.util.Random;

public class StrengthInNumbers extends SpellObserver{
    private int useManaOnSpell;
    public StrengthInNumbers(PlayerModel player) {
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
        if(useManaOnSpell >= 10){
            int x = new Random().nextInt(getPlayer().getCurrentDeck().size());
            Card y = getPlayer().getCurrentDeck().get(Math.abs(x));
            logicController.register(((Minion)y).getMinionObserver());
            getPlayer().getBattleGroundCards().add(y);
            getPlayer().getCurrentDeck().remove(y);
            logicController.unregister(this);
        }
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        super.play(addToBattleGroundRequest, logicController);
        logicController.register(new StrengthInNumbers(getPlayer()));
    }
}
