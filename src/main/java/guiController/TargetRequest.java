package guiController;

import model.GameModel;
import model.PlayerModel;

public class TargetRequest extends GuiRequest{

    public TargetRequest(PlayerModel player, GameModel gameModel) {
        super(player, gameModel);
    }

    @Override
    void execute(GuiController guiController) throws Exception {
        super.execute(guiController);
        System.out.println("f");
        guiController.setNeedTarget(true);
    }
}
