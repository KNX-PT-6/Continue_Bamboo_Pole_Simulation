import objects.Enums;
import objects.game.Gameboard;
import objects.players.BrainFactory;

import java.time.Clock;
import java.util.Arrays;

public class Test_Extended {
    public static final int RUNS = 10;
    public static final int RUN_PER_SIMULATION = 100000;
    public static final int NUM_OF_DECKS=2;


//    public static final int PLOT_GAP = 1000;


    public static void run() {
        StringBuilder simulationLog = new StringBuilder();

        Enums.BRAINTYPE pQ = Enums.BRAINTYPE.PAIRS;
        Enums.BRAINTYPE pF = Enums.BRAINTYPE.FIRST;

        Enums.BRAINTYPE[] players = new Enums.BRAINTYPE[]{pQ, pF};
        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pQ, pQ, pF, pF},1));
        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pF, pQ, pQ, pF},2));
        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pF, pF, pQ, pQ},3));
        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pQ, pF, pQ, pF},2));
        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pF, pQ, pF, pQ},3));
        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pQ, pF, pF, pQ},3));
//        simulationLog.append(runSimulation(players));

//        for (Enums.BRAINTYPE player_1 :
//                players) {
//            for (Enums.BRAINTYPE player_2 :
//                    players) {
//                for (Enums.BRAINTYPE player_3 :
//                        players) {
//                    for (Enums.BRAINTYPE player_4 :
//                            players) {
//                        simulationLog.append(runSimulation(new Enums.BRAINTYPE[]{pQ, player_2, player_3, player_4}));
//                    }
//                }
//            }

//            for (Enums.BRAINTYPE player_2 :
//                    Enums.BRAINTYPE.values()) {
//                System.out.println(player_1+"_"+player_2);
//            }
//        }

        Utils.logTo(Clock.systemUTC().instant().toString().replace(':','_') + ".log", simulationLog.toString());
    }


    private static String runSimulation(Enums.BRAINTYPE[] players, int player_to_track) {
        double[] results = new double[RUNS];

//        final double[] plots = new double[RUN_PER_SIMULATION / PLOT_GAP];
        StringBuilder simulationLog = new StringBuilder();
        simulationLog.append("Num of decks: ");
        simulationLog.append(NUM_OF_DECKS);
        simulationLog.append("\t");
        for(int k=0;k< players.length;k++){
            simulationLog.append("Player ");
            simulationLog.append(k);
            simulationLog.append(": ");
            simulationLog.append(players[k].toString());
            simulationLog.append(", ");
        }
        simulationLog.append("\r\n");
        for (int i = 0; i < RUNS; i++) {

            int sum_of_results = 0;
            for (int j = 1; j < RUN_PER_SIMULATION + 1; j++) {
                Gameboard gb = new Gameboard(players.length,NUM_OF_DECKS);
                for(int k=0;k< players.length;k++){
                    gb.AssignStrategy(k, BrainFactory.getBrain(players[k]));
                }
                int q = gb.gameRun();
                //Compute how many times Player 1 wins
                if (q == player_to_track) {
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
        simulationLog.append(Utils.arrayToString(results));
        simulationLog.append("\r\n");
        simulationLog.append(Utils.arrayToConfidenceInterval(results));
        simulationLog.append("\r\n");
        simulationLog.append("\r\n");
        return simulationLog.toString();
    }

}
