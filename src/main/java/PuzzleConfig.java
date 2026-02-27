public class PuzzleConfig {
    private int[] board = new int[9];

    public PuzzleConfig(int[] board){
        for(int i = 0; i<board.length; i++){
            this.board[i] = board[i];
        }
    }

    //Finds where the 0 puzzle piece is
    public int findZero(){
        int indexOfZ = 0;

        for(int i = 0; i<board.length; i++){
            if(board[i] == 0){
                indexOfZ = i;
            }
        }

        return indexOfZ;
    }

    //Checks to see if the puzzle is in the goal state
    public boolean isGoal(){
        boolean achieved = false;
        int[] solution = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int numberOfMatches = 0;

        for(int i = 0; i<board.length; i++){
            if(board[i] == solution[i]){
                numberOfMatches++;
            }else{
                break;
            }
        }

        if(numberOfMatches==board.length){
            achieved = true;
        }

        return achieved;
    }

    //h1 = the number of misplaced tiles
    public int h1(){
        int[] solution = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int misplaced = 0;

        for(int i = 0; i<board.length; i++){
            if(board[i] != solution[i]){
                misplaced++;
            }
        }

        return misplaced;
    }

    //h2 = the sum of the distances of the tiles from their goal positions
    // |x1 - x2| + |y1 - y2|
    public int h2(){
        int distance = 0;
        int[] solution = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[][] twoDBoard = new int[3][3];

        //Convert 1D array to 2D for convenience
        for(int i = 0; i<board.length; i++){
            int r = i/3;
            int c = i%3;
            twoDBoard[r][c] = board[i];
        }

        for(int r = 0; r<board.length/3; r++){
            for(int c = 0; c<board.length/3; c++){
                int puzzleP = twoDBoard[r][c];
                //Skip the 0 tile because it is an empty space
                if(puzzleP != 0){
                    distance += Math.abs(r - (solution[puzzleP]/3)) + Math.abs(c - (solution[puzzleP]%3));
                }
            }
        }

        return distance;
    }

    public String toString(){
        String stringBoard = "";
        int counter = 1;

        for(int i = 0; i<board.length; i++){
            if(counter!=3){
                stringBoard += board[i] + " ";
                counter++;
            }else{
                if(i<8){
                    stringBoard += board[i] + "\n";
                    counter = 1;
                }else {
                    stringBoard += board[i];
                    counter = 1;
                }
            }
        }
        return stringBoard;
    }

    public static void main(String[] args){
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        PuzzleConfig p = new PuzzleConfig(testBoard);
        System.out.println(p);

        System.out.println(p.h2());

        /*
        1 2 3
        8 0 4
        7 6 5

        7 2 4
        5 0 6
        8 3 1
         */
    }
}
