package logicController;

import model.PlayerModel;

public abstract class LogicRequest {
    PlayerModel player;

    public LogicRequest(PlayerModel player) {
        this.player = player;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    void execute(LogicController logicController)throws Exception{
        //TODO phase 4
        if (logicController.getGameModel().getCurrentPlayer() != player)
            throw new Exception();
    }

}




