import java.util.List;

public class Main {
    public static void main(String[] args){
        int[] testBoard = {1, 5, 4, 7, 6, 2, 0, 3, 8};

        PuzzleConfig p = new PuzzleConfig(testBoard);

        AStar solver = new AStar();

        Node goal = solver.solve(p, 1);

        List<PuzzleConfig> solutionPath = solver.traceSolutionPath(goal);

        int step = 0;
        for (PuzzleConfig state : solutionPath) {
            System.out.println("Step " + step + ":");
            System.out.println(state); // calls your toString method for nice 3x3 output
            step++;
        }

        System.out.println("Search cost: " + solver.getSearchCost());
    }
}
