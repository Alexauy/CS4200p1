public class PuzzleConfig {
    private int[] board = new int[9];

    public PuzzleConfig(int[] board){
        for(int i = 0; i<board.length; i++){
            this.board[i] = board[i];
        }
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
        int[] testBoard = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        PuzzleConfig p = new PuzzleConfig(testBoard);

        System.out.print(p);
    }
}
