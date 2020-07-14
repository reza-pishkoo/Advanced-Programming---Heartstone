package guiController;

import model.GameModel;
import model.PlayerModel;

import javax.swing.*;

public class CorrectTargetRequest extends GuiRequest{
    public CorrectTargetRequest(PlayerModel player, GameModel gameModel) {
        super(player, gameModel);
    }

    @Override
    void execute(GuiController guiController) throws Exception {
        super.execute(guiController);
        guiController.setNeedTarget(false);
        JOptionPane.showMessageDialog(null, "target selected", "target", JOptionPane.ERROR_MESSAGE);
    }
}
