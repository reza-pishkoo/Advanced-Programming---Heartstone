package logicController;

import model.PlayerModel;

public class endTurn extends LogicRequest {

    boolean isDeckReader;
    public endTurn(PlayerModel player, boolean isDeckReader) {
        super(player);
        this.isDeckReader = isDeckReader;
    }

    @Override
    void execute(LogicController logicController) throws Exception{
        logicController.endTurn(isDeckReader);
    }
}
