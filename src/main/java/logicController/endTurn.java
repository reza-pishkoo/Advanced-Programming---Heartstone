package logicController;

import model.PlayerModel;

public class endTurn extends LogicRequest {

    public endTurn(PlayerModel player) {
        super(player);
    }

    @Override
    void execute(LogicController logicController) throws Exception{
        logicController.endTurn();
    }
}
