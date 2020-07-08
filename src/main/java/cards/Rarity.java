package cards;

public enum Rarity {
    COMMON{
        @Override
        public String rarity(){
            return "Common";
        }

        @Override
        public int rarityValue() {
            return 2;
        }
    },
    RARE{
        @Override
        public String rarity(){
            return "Rare";
        }

        @Override
        public int rarityValue() {
            return 3;
        }
    },
    EPIC{
        @Override
        public String rarity(){
            return "Epic";
        }

        @Override
        public int rarityValue() {
            return 4;
        }
    },
    FREE{
        @Override
        public String rarity(){
            return "Free";
        }

        @Override
        public int rarityValue() {
            return 1;
        }
    },
    LEGENDARY{
        @Override
        public String rarity(){
            return "Legendary";
        }

        @Override
        public int rarityValue() {
            return 5;
        }
    };

    public abstract String rarity();
    public abstract int rarityValue();
}
