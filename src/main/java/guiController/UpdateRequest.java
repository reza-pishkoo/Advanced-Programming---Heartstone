package guiController;

import model.GameModel;
import model.PlayerModel;

public class UpdateRequest extends GuiRequest{
    public UpdateRequest(PlayerModel player, GameModel gameModel) {
        super(player, gameModel);
    }

    @Override
    void execute(GuiController guiController) throws Exception {
        super.execute(guiController);
        guiController.getPlayPanel().updateByModel(gameModel);
    }
}
