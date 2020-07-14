package logicController;

import cards.Card;
import cards.Minion;
import model.PlayerModel;


public class MyTargetRequest extends LogicRequest {

    private Object target;
    public MyTargetRequest(PlayerModel player, Object target) {
        super(player);
        this.target = target;
    }

    public Object getTarget() {
        return target;
    }

    @Override
    void execute(LogicController logicController) throws Exception {

    }
}
