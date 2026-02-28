import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzleConfig {
    private int[] board = new int[9];

    public PuzzleConfig(int[] board){
        for(int i = 0; i<board.length; i++){
            this.board[i] = board[i];
        }
    }

    public int[] getPuzzleConfig(){
        return board.clone();
    }

    //Finds where the 0 puzzle piece is
    private int findZero(){
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

        if(numberOfMatches == board.length){
            achieved = true;
        }

        return achieved;
    }

    //h1 = the number of misplaced tiles
    public int h1(){
        int[] solution = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int misplaced = 0;

        for(int i = 0; i<board.length; i++){
            //Skip the 0 tile because it is an empty space
            if(board[i] != 0 && board[i] != solution[i]){
                misplaced++;
            }
        }

        return misplaced;
    }
//>>>ChatGPT was used to debug the h2 method<<<
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

//>>>ChatGPT was used to debug getLegalMoves()<<<
    //Get legal move configurations given current state
    public List<PuzzleConfig> getLegalMoves(){
        List<PuzzleConfig >legalMoves = new ArrayList<>();
        int zeroR = findZero()/3;
        int zeroC = findZero()%3;

        //(r, c) can move: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int i = 0; i<directions.length; i++){
            int newR = zeroR + directions[i][0];
            int newC = zeroC + directions[i][1];

            //Check whether the coordinate after will still be within the bounds
            if((newR >= 0 && newR <3) && (newC >= 0 && newC < 3)){
                //If so, then its valid and we should save the config
                int[] copy = board.clone();

                int zeroIndex = zeroR*3 + zeroC;
                int swappedIndex = newR*3 + newC;

                int temp = copy[zeroIndex];
                copy[zeroIndex] = copy[swappedIndex];
                copy[swappedIndex] = temp;

                legalMoves.add(new PuzzleConfig(copy));
            }

        }
        return legalMoves;
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
}
