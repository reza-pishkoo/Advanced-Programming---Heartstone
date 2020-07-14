package logicController.actions;

import cards.Card;
import cards.CardFactory;
import cards.Minion;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.endTurn;
import model.PlayerModel;

public class Nurse extends PassiveObserver{
    public Nurse(PlayerModel player) {
        super(player);
    }

    @Override
    public void observeBeforeRequest(LogicRequest request, LogicController logicController) throws Exception {
        super.observeBeforeRequest(request, logicController);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        Card x = null;
        if(request instanceof endTurn){
            if(getPlayer() == logicController.getGameModel().getNextPlayer()){
                for (int i = 0; i < getPlayer().getBattleGroundCards().size(); i++){
                    if(i == 0)
                        x = getPlayer().getBattleGroundCards().get(i);
                    else{
                        if(((Minion)CardFactory.build(x.getName(), getPlayer())).getHP() - ((Minion)x).getHP() <
                                ((Minion)CardFactory.build(getPlayer().getBattleGroundCards().get(i).getName(), getPlayer())).getHP() -
                                        ((Minion)getPlayer().getBattleGroundCards().get(i)).getHP())
                            x = getPlayer().getBattleGroundCards().get(i);
                    }
                }
                ((Minion)x).setHP(((Minion)CardFactory.build(x.getName(), getPlayer())).getHP());
            }
        }
    }

    @Override
    public void perform(LogicRequest request, LogicController logicController) {
        super.perform(request, logicController);
        logicController.register(new Nurse(getPlayer()));
    }
}
