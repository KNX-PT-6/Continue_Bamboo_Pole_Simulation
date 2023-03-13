package objects.players;


import objects.Enums.*;

public class BrainFactory {

    public static Brain getBrain(BRAINTYPE type) {
        switch (type) {
            case PAIRS -> {
                return new Str_Pairs();
            }
            case PAIRS_1 -> {
                return new Str_PairsWithSingle();
            }
            case PAIRS_2 -> {
                return new Str_PairsWithSingle(2);
            }
            case PAIRS_5 -> {
                return new Str_PairsWithSingle(5);
            }
            case PAIRS_10 -> {
                return new Str_PairsWithSingle(10);
            }

            case RANDOM -> {
                return new Str_Random();
            }
            default -> {
                return new Str_First();
            }
        }
    }
}
