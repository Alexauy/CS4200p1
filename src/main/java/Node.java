public class Node implements Comparable<Node>{
    PuzzleConfig config;
    Node parent;
    int gn;
    int hn;

    public Node(PuzzleConfig config, Node parent, int gn, int hn){
        this.config = config;
        this.parent = parent;
        this.gn = gn;
        this.hn = hn;
    }

    public int getEstimatedCost(){
        return gn + hn;
    }

    @Override
    public int compareTo(Node other){
        return this.getEstimatedCost() - other.getEstimatedCost();
    }
}
