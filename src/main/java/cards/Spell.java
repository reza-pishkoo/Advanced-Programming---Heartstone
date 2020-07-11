package cards;

import logicController.actions.SpellObserver;

public class Spell extends Card {

    private SpellObserver spellObserver;

    public Spell(){
    }

    public SpellObserver getSpellObserver() {
        return spellObserver;
    }

    public void setSpellObserver(SpellObserver spellObserver) {
        this.spellObserver = spellObserver;
    }
}
