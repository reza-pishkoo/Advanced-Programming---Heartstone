package logicController;

import logicController.actions.*;
import model.PlayerModel;

public class SetHeroRequest extends LogicRequest{
    public SetHeroRequest(PlayerModel player) {
        super(player);
    }

    @Override
    void execute(LogicController logicController) throws Exception {
        super.execute(logicController);
        switch (getPlayer().getHero().getName()){
            case "Mage":
                new MageSpecialPower(getPlayer()).perform(this, logicController);
                break;
            case "Rouge":
                new RougeSpecialPower(getPlayer()).perform(this, logicController);
                break;
            case "Hunter":
                new HunterSpecialPower(getPlayer()).perform(this, logicController);
                new HunterHeroPower(getPlayer()).execute(this, logicController);
                break;
            case "Priest":
                new PriestSpecialPower(getPlayer()).perform(this, logicController);
                break;
            case "Warlock":
                new WarlockSpecialPower(getPlayer()).perform(this, logicController);
                break;
            default:
                return;
        }
    }
}
