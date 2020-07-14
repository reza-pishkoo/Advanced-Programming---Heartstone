package guiController;

import model.GameModel;
import model.PlayerModel;

import javax.swing.*;

public class CantAttackRequest extends GuiRequest{
    public CantAttackRequest(PlayerModel player, GameModel gameModel) {
        super(player, gameModel);
    }

    @Override
    void execute(GuiController guiController) throws Exception {
        super.execute(guiController);
        JOptionPane.showMessageDialog(null, "can't attack now", "attack", JOptionPane.ERROR_MESSAGE);
    }
}
