import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;

public class AStar {
    private int searchCost;

    public int getSearchCost(){
        return searchCost;
    }

    public Node solve(PuzzleConfig initial, int heuristic){
        int h = 0;
        searchCost = 0;
        //Frontier: we use a priority queue so that least cost paths are explored first
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        //Explored set: hashset is good because it has constant lookup time
        HashSet<String> explored = new HashSet<>();

        if(heuristic == 1){
            h = initial.h1();
        }else{
            h = initial.h2();
        }
        Node firstNode = new Node(initial, null, 0 , h);
        frontier.add(firstNode);
        searchCost++;

        //While there are still elements to explore
        while(!frontier.isEmpty()){
            //Removes the node with the lowest cost
            Node curr = frontier.poll();

            if(curr.getConfig().isGoal()){
                return curr;
            }else{
                explored.add(curr.getConfig().toString());

                List<PuzzleConfig> nextConfigs = curr.getConfig().getLegalMoves();
                for(int i = 0; i<nextConfigs.size(); i++){
                    PuzzleConfig next = nextConfigs.get(i);

                    searchCost++;

                    //Avoid exploring already explored nodes
                    if(explored.contains(next.toString())) {
                        continue;
                    }

                    int updatedGn = curr.getGn() + 1;
                    int updatedHn;

                    if(heuristic == 1){
                        updatedHn = next.h1();
                    }else{
                        updatedHn = next.h2();
                    }

                    Node child = new Node(next, curr, updatedGn, updatedHn);
                    frontier.add(child);
                }
            }
        }
        return null;
    }

    //From the goal, build a path backwards by following the parents, then we can reverse it
    public List<PuzzleConfig> traceSolutionPath(Node goal){
        List<PuzzleConfig> solutionPath = new ArrayList<>();
        Node curr = goal;

        while(curr != null){
            solutionPath.add(curr.getConfig());
            curr = curr.getParent();
        }

        Collections.reverse(solutionPath);

        return solutionPath;
    }
}
