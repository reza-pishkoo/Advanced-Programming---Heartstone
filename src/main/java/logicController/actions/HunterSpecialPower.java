package logicController.actions;

import cards.Card;
import cards.Minion;
import logicController.LogicController;
import logicController.LogicRequest;
import model.PlayerModel;

public class HunterSpecialPower extends SpecialPowerObserver{

    public HunterSpecialPower(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        for(Card card : getPlayer().getHandCards()){
            if(card instanceof Minion){
                ((Minion)card).setRush(true);
            }
        }
        super.observeAfterRequest(request, logicController);
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
    }
}
