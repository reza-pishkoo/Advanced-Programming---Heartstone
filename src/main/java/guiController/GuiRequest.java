package guiController;

import logicController.LogicController;
import model.GameModel;
import model.PlayerModel;

public abstract class GuiRequest {
    PlayerModel player;
    GameModel gameModel;

    public GuiRequest(PlayerModel player, GameModel gameModel) {
        this.player = player;
        this.gameModel = gameModel;
    }
    public PlayerModel getPlayer() {
        return player;
    }
    void execute(GuiController guiController)throws Exception{
        guiController.getPlayPanel().updateByModel(gameModel);
    }
}
