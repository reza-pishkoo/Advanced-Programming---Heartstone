package guiController;

import model.GameModel;
import model.PlayerModel;

import javax.swing.*;

public class TargetRequest extends GuiRequest{

    public TargetRequest(PlayerModel player, GameModel gameModel) {
        super(player, gameModel);
    }

    @Override
    void execute(GuiController guiController) throws Exception {
        super.execute(guiController);
        guiController.setNeedTarget(true);
        JOptionPane.showMessageDialog(null, "choose target", "target", JOptionPane.ERROR_MESSAGE);

    }
}
