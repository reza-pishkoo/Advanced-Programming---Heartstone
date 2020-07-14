package logicController;

import logicController.actions.*;
import model.PlayerModel;

public class SetPassiveRequest extends LogicRequest{
    String passive;
    public SetPassiveRequest(PlayerModel player, String passive) {
        super(player);
        this.passive = passive;
    }

    @Override
    void execute(LogicController logicController) throws Exception {
        getPlayer().setPassive(passive);
        switch (passive){
            case "Off Cards":
                new OffCards(getPlayer()).perform(this, logicController);
                break;
            case "Warriors":
                new Warriors(getPlayer()).perform(this, logicController);
                break;
            case "Nurse":
                new Nurse(getPlayer()).perform(this, logicController);
                break;
            case "Twice draw":
                new TwiceDraw(getPlayer()).perform(this, logicController);
                break;
            case "Mana jump":
                new ManaJump(getPlayer()).perform(this, logicController);
                break;
            default :
                return;
        }
    }
}
