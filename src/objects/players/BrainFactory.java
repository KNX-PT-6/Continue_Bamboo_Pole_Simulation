package objects.players;


import objects.Enums.*;

public class BrainFactory {

    public static Brain getBrain(BRAINTYPE type) {
        switch (type) {
            case GREEDYPAIRS -> {
                return new Str_GreedyPairs();
            }
            case LAZYRANDOM -> {
                return new Str_LazyRandom();
            }
            case QUICKPAIRS -> {
                return new Str_QuickPairs();
            }
            case GREEDYPAIRS_2 -> {
                return new Str_GreedyPairs(2);
            }
            case GREEDYPAIRS_5 -> {
                return new Str_GreedyPairs(5);
            }
            case GREEDYPAIRS_10 -> {
                return new Str_GreedyPairs(10);
            }

            default -> {
                return new Str_LazyFirst();
            }
        }
    }
}
