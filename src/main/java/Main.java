import java.util.*;

public class Main {
    public static int numInversions(int[] board){
        int inversions = 0;

//>>>ChatGPT was used to debug
        for(int i = 0; i<board.length-1; i++){
            //Skip the 0 tile since it is empty
            for(int j = i+1; j<board.length; j++){
                if(board[j] == 0){
                    continue;
                }

                if(board[i] > board[j]){
                    inversions++;
                }
            }
        }
        return inversions;
    }
//<<<

    public static boolean solvable(int[] board){
        boolean isSolvable;

        if(numInversions(board)%2 == 0){
            isSolvable = true;
        }else{
            isSolvable = false;
        }

        return isSolvable;
    }

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        int[] inputtedBoard = new int[9];
        int inputMethod = 0;
        int inputH = 0;

        //Method selection
        System.out.println("Select Input Method:");
        System.out.println("[1] Random");
        System.out.println("[2] Manual Input");

        try{
            inputMethod = scnr.nextInt();
            scnr.nextLine();
        }catch(InputMismatchException e){
            System.out.println("The entered input is not '1' or '2', please try again");
            System.exit(1);
        }

        if(inputMethod == 1){
            List<Integer> possibleNums = new ArrayList<>();
            for(int i = 0; i<9; i++){
                possibleNums.add(i);
            }

            do{
                //Keep regenerating random configs until it is solvable
                Collections.shuffle(possibleNums);

                for(int i = 0; i<inputtedBoard.length; i++){
                    inputtedBoard[i] = possibleNums.get(i);
                }
            }while(!solvable(inputtedBoard));
        }else{
            System.out.println("Please enter your puzzle: ");
            int counter = 0;

//>>>ChatGPT was used to debug
            for(int r = 0; r<3; r++){
                String row = scnr.nextLine();
                String[] stripped = row.trim().split("\\s+");

                //Messed up input is entered
                if(stripped.length != 3){
                    System.out.println("The puzzle inputted is not 3x3, please try again");
                    System.exit(1);
                }

                for(int c = 0; c<3; c++){
                    try{
                        int val = Integer.parseInt(stripped[c]);
                        inputtedBoard[counter++] = val;
                    }catch(NumberFormatException e){
                        System.out.println("The input contains an invalid number, please try again");
                        System.exit(1);
                    }
                }
            }

            if(!solvable(inputtedBoard)){
                System.out.println("Sorry, the input is not a solvable puzzle configuration");
                System.exit(1);
            }
        }
//<<<
        //Puzzle configuration has been made through one of the methods
        PuzzleConfig p = new PuzzleConfig(inputtedBoard);

        //Heuristic selection
        System.out.println("Select H Function:");
        System.out.println("[1] H1");
        System.out.println("[2] H2");

        try{
            inputH = scnr.nextInt();
            if(inputH != 1 && inputH != 2){
                System.out.println("The entered input is not '1' or '2', please try again");
                System.exit(1);
            }
        }catch(InputMismatchException e){
            System.out.println("The input is invalid, please try again");
            System.exit(1);
        }

        //Printing of solution, steps, and search cost
        System.out.println("Puzzle:");
        System.out.println(p);

        AStar solver = new AStar();
        Node goal = solver.solve(p, inputH);
        List<PuzzleConfig> solutionPath = solver.traceSolutionPath(goal);

        for(int i = 1; i<solutionPath.size(); i++){
            System.out.println("Step " + i + ":");
            System.out.println(solutionPath.get(i));
        }

        System.out.println("Search cost: " + solver.getSearchCost());
    }
}
