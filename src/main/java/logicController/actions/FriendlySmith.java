package logicController.actions;

import cards.CardFactory;
import logicController.AddToBattleGroundRequest;
import logicController.LogicController;
import model.PlayerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FriendlySmith extends SpellObserver{
    public FriendlySmith(PlayerModel player) {
        super(player);
    }

    @Override
    public void play(AddToBattleGroundRequest addToBattleGroundRequest, LogicController logicController) {
        List<String> weapons = new ArrayList<>();
        weapons.add("Blood Fury");
        weapons.add("Battle Axe");
        weapons.add("Heavy Axe");
        int x = new Random().nextInt(3);
        getPlayer().getCurrentDeck().add(CardFactory.build(weapons.get(Math.abs(x)), getPlayer()));
        super.play(addToBattleGroundRequest, logicController);
    }
}
