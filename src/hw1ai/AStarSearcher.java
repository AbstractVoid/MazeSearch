package hw1ai;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}
        public double getF(State s){
            double F = 0;
            double H;
            H = Math.abs(s.getX()-maze.getGoalSquare().X) + Math.abs(s.getY()-maze.getGoalSquare().Y);
            F = s.getGValue() + H;
            return F;
        }
	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
            boolean cont = true;
		// FILL THIS METHOD
                StateFValuePair curr;
                State currState;
                ArrayList<State> successors = new ArrayList<State>();
		// CLOSED list is a Boolean array that indicates if a state associated with a given position in the maze has already been expanded. 
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		// OPEN list (aka Frontier list)
		PriorityQueue<StateFValuePair> open = new PriorityQueue<StateFValuePair>();
                
                State start = new State(maze.getPlayerSquare(), null, 0, 0);
                StateFValuePair startPair = new StateFValuePair(start, getF(start));
                open.add(startPair);

		// TODO initialize the root state and add
		// to OPEN list
		// ...

		while (cont) {
                    if (open.peek() == null){
                        break;
                    }
                    
                    curr= open.poll();
                    currState = curr.getState();
                    noOfNodesExpanded++;
                    closed[currState.getSquare().X][currState.getSquare().Y] = true;
                    if (currState.isGoal(maze)){
                        while (currState.getParent().getParent() != null){
					maze.setOneSquare(currState.getParent().getSquare(), '.');
					currState = currState.getParent();
					cost++;
				}
				cost++;
				return true;
                    }
                    successors = currState.getSuccessors(closed, maze, currState);
                    for (int i=0; i<successors.size(); i++){
                        StateFValuePair pair = new StateFValuePair(successors.get(i), getF(successors.get(i)));
                        open.add(pair);
                    }
			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded,
			// TODO update the maze if a solution found

			// use open.poll() to extract the minimum stateFValuePair.
			// use open.add(...) to add stateFValue pairs
		}

		// TODO return false if no solution
		return false;
	}

}
