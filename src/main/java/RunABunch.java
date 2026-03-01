import java.io.*;
import java.util.*;

public class RunABunch {
    private List<IterationResult> results = new ArrayList<>();
    private int numRuns;

    public RunABunch(int numRuns){
        this.numRuns = numRuns;
    }

    public void run(){
        for(int i = 1; i<=numRuns; i++){
            int[] board = new int[9];

            do{
                //Same logic as main, repeat until solvable config
                List<Integer> possibleNums = new ArrayList<>();
                for(int j = 0; j<9; j++){
                    possibleNums.add(j);
                }

                //Keep regenerating random configs until it is solvable
                Collections.shuffle(possibleNums);

                for(int j = 0; j<board.length; j++){
                    board[j] = possibleNums.get(j);
                }
            }while(!Main.solvable(board));

            PuzzleConfig p1 = new PuzzleConfig(board.clone());
            PuzzleConfig p2 = new PuzzleConfig(board.clone());

            //H1 stats and data
            AStar solver1 = new AStar();

            long h1Start = System.nanoTime();
            solver1.solve(p1, 1);
            long h1Stop = System.nanoTime();

            int h1Cost = solver1.getSearchCost();
            double h1Time = (h1Stop - h1Start)/1000000.0;

            //H2 stats and data
            AStar solver2 = new AStar();

            long h2Start = System.nanoTime();
            solver2.solve(p2, 2);
            long h2Stop = System.nanoTime();

            int h2Cost = solver2.getSearchCost();
            double h2Time = (h2Stop - h1Start)/1000000.0;

            results.add(new IterationResult(i, h1Cost, h2Cost, h1Time, h2Time));
        }
    }

    public void writeToFile(String filename) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
//>>>ChatGPT used to fix results.txt formatting for better readability<<<
            writer.printf("%-5s %-10s %-10s %-12s %-12s%n",
                    "Test", "H1 Cost", "H2 Cost", "H1 Time(ms)", "H2 Time(ms)");

            for(int i = 0; i<results.size(); i++){
                writer.printf("%-5d %-10d %-10d %-12.3f %-12.3f%n",
                        results.get(i).getIteration(), results.get(i).getH1Cost(), results.get(i).getH2Cost(), results.get(i).getH1Time(), results.get(i).getH2Time());
            }

            //Calculating average stats and printing
            System.out.print("\n");

            double avgH1Cost = 0;
            double avgH2Cost = 0;
            double avgH1Time = 0;
            double avgH2Time = 0;

            for(int i = 0; i<results.size(); i++){
                avgH1Cost += results.get(i).getH1Cost();
                avgH2Cost += results.get(i).getH2Cost();
                avgH1Time += results.get(i).getH1Time();
                avgH2Time += results.get(i).getH2Time();
            }

            int n = results.size();
            writer.println();
            writer.println("Average H1 Cost: " + avgH1Cost / n);
            writer.println("Average H2 Cost: " + avgH2Cost / n);
            writer.println("Average H1 Time(ms): " + avgH1Time / n);
            writer.println("Average H2 Time(ms): " + avgH2Time / n);
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

