package logicController;

import logicController.actions.*;
import model.PlayerModel;

public class PowerRequest extends LogicRequest{
    public PowerRequest(PlayerModel player) {
        super(player);
    }

    @Override
    void execute(LogicController logicController) throws Exception {
        super.execute(logicController);
        switch (getPlayer().getHero().getName()) {
            case "Mage":
                new MageHeroPower(getPlayer()).execute(this, logicController);
                break;
            case "Rouge":
                new RougeHeroPower(getPlayer()).execute(this, logicController);
                break;
            case "Hunter":
                new HunterHeroPower(getPlayer()).execute(this, logicController);
                break;
            case "Warlock":
                new WarlockHeroPower(getPlayer()).execute(this, logicController);
                break;
            default:
                return;
        }
    }
}
