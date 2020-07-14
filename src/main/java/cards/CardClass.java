package cards;

public enum CardClass {
    MAGE{
        @Override
        public String cardClass(){
            return "mage";
        }
    },
    ROUGE{
        @Override
        public String cardClass(){
            return "rouge";
        }
    },
    WARLOCK{
        @Override
        public String cardClass(){
            return "warlock";
        }
    },
    HUNTER{
        @Override
        public String cardClass(){
            return "hunter";
        }
    },
    PRIEST{
        @Override
        public String cardClass(){
            return "priest";
        }
    },
    NEUTRAL{
        @Override
        public String cardClass(){
            return "neutral";
        }
    };

    public abstract String cardClass();
}
