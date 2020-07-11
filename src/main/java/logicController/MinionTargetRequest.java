package logicController;

import cards.Card;
import cards.Minion;
import model.PlayerModel;


public class MinionTargetRequest extends LogicRequest {

    private Object target;
    public MinionTargetRequest(PlayerModel player, Object target) {
        super(player);
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    void execute(LogicController logicController) throws Exception {
        super.execute(logicController);
    }
}
