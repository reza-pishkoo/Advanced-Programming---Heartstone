package logicController;

import cards.Card;
import cards.Minion;
import model.PlayerModel;

public class AddToBattleGroundRequest extends LogicRequest {
   Card card;

    public AddToBattleGroundRequest(PlayerModel player, Card card) {
        super(player);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    void execute(LogicController logicController) throws Exception{
        super.execute(logicController);
        logicController.addCardToBattleground(card, this);
    }
}
