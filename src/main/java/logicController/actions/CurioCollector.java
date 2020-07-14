package logicController.actions;

import cards.Card;
import cards.Minion;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import logicController.LogicRequest;
import logicController.endTurn;
import model.PlayerModel;

public class CurioCollector extends MinionObserver{


    public CurioCollector(PlayerModel player, Minion card) {
        super(player, card);
    }

    @Override
    public void observeAfterRequest(LogicRequest request, LogicController logicController) {
        super.observeAfterRequest(request, logicController);
        if(request instanceof endTurn){
            for (int i = 0; i < logicController.getGameModel().getNextPlayer().getBattleGroundCards().size(); i++){
                if(logicController.getGameModel().getNextPlayer().getBattleGroundCards().get(i).getName().equalsIgnoreCase("Curio Collector")){
                    ((Minion) logicController.getGameModel().getNextPlayer().getBattleGroundCards().get(i)).setAttack(((Minion) logicController.getGameModel().getNextPlayer().getBattleGroundCards().get(i)).getAttack() + 1);
                    ((Minion) logicController.getGameModel().getNextPlayer().getBattleGroundCards().get(i)).setHP(((Minion) logicController.getGameModel().getNextPlayer().getBattleGroundCards().get(i)).getHP() + 1);
                }
            }
        }
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        super.play(addToBattleGroundRequest, logicController);
    }
}
