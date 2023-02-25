import objects.Enums;
import objects.game.Gameboard;
import objects.players.BrainFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static final int RUNS = 10;
    public static final int RUN_PER_SIMULATION = 100000;


//    public static final int PLOT_GAP = 1000;

    public static void run() {

        for (Enums.BRAINTYPE player_1 :
                Enums.BRAINTYPE.values()) {
            for (Enums.BRAINTYPE player_2 :
                    Enums.BRAINTYPE.values()) {
                runSimulation(player_1, player_2);
            }
        }

//        Enums.BRAINTYPE player_1 = Enums.BRAINTYPE.QUICKPAIRS;
//        Enums.BRAINTYPE player_2 = Enums.BRAINTYPE.LAZYFIRST;
//        runSimulation(player_1, player_2);
    }


    private static void runSimulation(Enums.BRAINTYPE player_1, Enums.BRAINTYPE player_2) {
        double[] results = new double[RUNS];

//        final double[] plots = new double[RUN_PER_SIMULATION / PLOT_GAP];

        for (int i = 0; i < RUNS; i++) {

            int sum_of_results = 0;
            for (int j = 1; j < RUN_PER_SIMULATION + 1; j++) {
                Gameboard gb = new Gameboard(2);
                gb.AssignStrategy(0, BrainFactory.getBrain(player_1));
                gb.AssignStrategy(1, BrainFactory.getBrain(player_2));
                int q = gb.gameRun();
                //Compute how many times Player 1 wins
                if (q == 0) {
                    sum_of_results += 1;
                }

//                if (j % PLOT_GAP == 0) {
//                    plots[j / PLOT_GAP - 1] = (double)sum_of_results / j;
//                }

//                System.out.println((q + 1) + "th player won the " + j + "th round.");
            }

//            Utils.logTo("PLOT_" + (i + 1) + ".txt", Utils.arrayToString(plots));
//            System.out.println(RUN_PER_SIMULATION + "run performed, player 1 won " + sum_of_results + "times.");
            results[i] = (double) sum_of_results / RUN_PER_SIMULATION;
        }
        Arrays.sort(results);
        Utils.logTo(player_1.toString() + "_" + player_2.toString() + ".txt", Utils.arrayToString(results));
        Utils.logTo("ConfidenceInterval_" + player_1.toString() + "_" + player_2.toString() + ".txt", Utils.arrayToConfidenceInterval(results));
    }

}
