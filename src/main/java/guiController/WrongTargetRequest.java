package guiController;

import model.GameModel;
import model.PlayerModel;

import javax.swing.*;

public class WrongTargetRequest extends GuiRequest{
    public WrongTargetRequest(PlayerModel player, GameModel gameModel) {
        super(player, gameModel);
    }

    @Override
    void execute(GuiController guiController) throws Exception {
        super.execute(guiController);
        guiController.setNeedTarget(false);
        JOptionPane.showMessageDialog(null, "false target", "target", JOptionPane.ERROR_MESSAGE);
    }
}
