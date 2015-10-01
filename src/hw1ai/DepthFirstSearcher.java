package hw1ai;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Depth-First Search (DFS)
 *
 * You should fill the search() method of this class.
 */
public class DepthFirstSearcher extends Searcher {

    /**
     * Calls the parent class constructor.
     *
     * @see Searcher
     * @param maze initial maze.
     */
    public DepthFirstSearcher(Maze maze) {
        super(maze);
    }

    /**
     * Main depth first search algorithm.
     *
     * @return true if the search finds a solution, false otherwise.
     */
    public boolean search() {
        // FILL THIS METHOD
        State start = new State(maze.getPlayerSquare(), null, 0, 0);
        boolean cont = true;
        boolean cycle = false;
        ArrayList<State> successors = new ArrayList<State>();
        // CLOSED list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been expanded.
        boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

        // ...

        // Stack implementing the Frontier list
        LinkedList<State> stack = new LinkedList<State>();
        stack.push(start);

        while (cont) {
            // TODO return true if find a solution]
            if (stack.peek() == null){
                break;
            }
            State curr = stack.pop(); //pop the first state, check if goal
            noOfNodesExpanded++;
            if (curr.isGoal(maze)) {
                while (curr.getParent() != null) { //show maze solution perhaps go through parents replacing with . until parent is null
                    
                    maze.setOneSquare(curr.getParent().getSquare(), '.');
                    cost++;
                    curr = curr.getParent();
                }
                maze.setOneSquare(maze.getPlayerSquare(), 'H');
                return true;
            } else {
                closed[curr.getX()][curr.getY()] = true;
                successors = curr.getSuccessors(closed, maze, curr);
                for (int i = 0; i < successors.size(); i++) {
                  State test = successors.get(i).getParent();
                  cycle = false;
                  while (test!=null){
                      if (test.equals(successors.get(i))) cycle = true;
                          test = test.getParent();
                          
                      }
                  if (!cycle){
                     stack.push(successors.get(i)); 
                  }
                  
                    
                    
              
                }
            }

            }

            //expand and push children to stack





            // TODO maintain the cost, noOfNodesExpanded

            // TODO update the maze if a solution found

            // use stack.pop() to pop the stack.
            // use stack.push(...) to elements to stack
            // If the search stack is empty, return with failure
            // (false).

            // Otherwise pop the next search node from the top of
            // the stack.

            // If the search node is a goal node, store it and return
            // with success (true).

            // Otherwise, expand the node and push each of its
            // children into the stack.
        

        // TODO return false if no solution
        return false;
    }
}
