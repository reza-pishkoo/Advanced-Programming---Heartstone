package cards;

public enum Type {
    MINION{
        @Override
        public String type(){
            return "Minion";
        }

        @Override
        public int typeValue() {
            return 0;
        }
    },
    SPELL{
        @Override
        public String type(){
            return "Spell";
        }

        @Override
        public int typeValue() {
            return 0;
        }
    },
    WEAPON{
        @Override
        public String type(){
            return "Weapon";
        }

        @Override
        public int typeValue() {
            return 0;
        }
    },
    QUEST{
        @Override
        public String type(){
            return "Quest";
        }

        @Override
        public int typeValue() {
            return 0;
        }
    };

    public abstract String type();

    public abstract int typeValue();
}
