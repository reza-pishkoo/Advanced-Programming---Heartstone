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
        //TODO
        System.out.println("!");
        System.out.println(logicController.getGameModel().getCurrentPlayer());
        System.out.println(player);
        if (logicController.getGameModel().getCurrentPlayer() != player)
            throw new Exception();
    }

}




