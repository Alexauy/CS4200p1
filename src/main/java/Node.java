public class Node implements Comparable<Node>{
    private PuzzleConfig config;
    private Node parent;
    private int gn;
    private int hn;

    public Node(PuzzleConfig config, Node parent, int gn, int hn){
        this.config = config;
        this.parent = parent;
        this.gn = gn;
        this.hn = hn;
    }

    public PuzzleConfig getConfig() {
        return config;
    }

    public Node getParent(){
        return parent;
    }

    public int getGn(){
        return gn;
    }

    public int getHn(){
        return hn;
    }

    public int getEstimatedCost(){
        return gn + hn;
    }

    @Override
    public int compareTo(Node other){
        return this.getEstimatedCost() - other.getEstimatedCost();
    }
}
