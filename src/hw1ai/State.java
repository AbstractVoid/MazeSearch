package hw1ai;

import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 *
 * You should fill the getSuccessors(...) method of this class.
 *
 */
public class State {

    private Square square;
    private State parent;
    // Maintain the gValue (the distance from start)
    // You may not need it for the DFS but you will
    // definitely need it for AStar
    private int gValue;
    // States are nodes in the search tree, therefore each has a depth.
    private int depth;

    /**
     * @param square current square
     * @param parent parent state
     * @param gValue total distance from start
     */
    public State(Square square, State parent, int gValue, int depth) {
        this.square = square;
        this.parent = parent;
        this.gValue = gValue;
        this.depth = depth;
    }

    /**
     * @param visited closed[i][j] is true if (i,j) is already expanded
     * @param maze initial maze to get find the neighbors
     * @return all the successors of the current state
     */
    public ArrayList<State> getSuccessors(boolean[][] closed, Maze maze, State current) {
        // FILL THIS METHOD
        ArrayList<State> successors = new ArrayList<State>();
        int currX = getX();
        int currY = getY();
        int checkX;
        int checkY;
        State toAdd;
        //left down right up is order of pushing onto DFS stack
        // TODO check all four neighbors (up, right, down, left)
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                checkX = currX;
                checkY = currY-1;
                Square neighborOne = new Square(checkX, checkY);
                //check if X and Y are out of bounds 0 is lowest row and column
                if ((maze.getSquareValue(checkX, checkY) == '%') || checkX > maze.getNoOfRows() || checkX < 0 || checkY > maze.getNoOfCols() || checkY < 0 || closed[checkX][checkY] == true) {
                } else {
                    toAdd = new State(neighborOne, current, current.getGValue() + 1, current.getDepth() + 1);

                    successors.add(toAdd);
                }
            }
            if (i == 1) {
                checkX = currX+1;
                checkY = currY;
                Square neighborOne = new Square(checkX, checkY);
                //check if X and Y are out of bounds 0 is lowest row and column
                if ((maze.getSquareValue(checkX, checkY) == '%') || checkX > maze.getNoOfRows() || checkX < 0 || checkY > maze.getNoOfCols() || checkY < 0 || closed[checkX][checkY] == true) {
                } else {
                    toAdd = new State(neighborOne, current, current.getGValue() + 1, current.getDepth() + 1);

                    successors.add(toAdd);
                }
            }
            if (i == 2) {
                checkX = currX;
                checkY = currY+1;
                Square neighborOne = new Square(checkX, checkY);
                //check if X and Y are out of bounds 0 is lowest row and column
                if ((maze.getSquareValue(checkX, checkY) == '%') || checkX > maze.getNoOfRows() || checkX < 0 || checkY > maze.getNoOfCols() || checkY < 0 || closed[checkX][checkY] == true) {
                } else {
                    toAdd = new State(neighborOne, current, current.getGValue() + 1, current.getDepth() + 1);

                    successors.add(toAdd);
                }
            }
            if (i == 3) {
                checkX = currX-1;
                checkY = currY;
                Square neighborOne = new Square(checkX, checkY);
                //check if X and Y are out of bounds 0 is lowest row and column
                if ((maze.getSquareValue(checkX, checkY) == '%') || checkX > maze.getNoOfRows() || checkX < 0 || checkY > maze.getNoOfCols() || checkY < 0 || closed[checkX][checkY] == true) {
                } else {
                    toAdd = new State(neighborOne, current, current.getGValue() + 1, current.getDepth() + 1);

                    successors.add(toAdd);
                }
            }

        }
        // TODO return all unvisited neighbors
        // TODO remember that each successor's depth and gValue are
        // +1 of this object.
        return successors;

    }

    /**
     * @return x coordinate of the current state
     */
    public int getX() {
        return square.X;
    }

    /**
     * @return y coordinate of the current state
     */
    public int getY() {
        return square.Y;
    }

    /**
     * @param maze initial maze
     * @return true is the current state is a goal state
     */
    public boolean isGoal(Maze maze) {
        if (square.X == maze.getGoalSquare().X
                && square.Y == maze.getGoalSquare().Y) {
            return true;
        }

        return false;
    }

    /**
     * @return the current state's square representation
     */
    public Square getSquare() {
        return square;
    }

    /**
     * @return parent of the current state
     */
    public State getParent() {
        return parent;
    }

    /**
     * You may not need g() value in the DFS but you will need it in A-star
     * search.
     *
     * @return g() value of the current state
     */
    public int getGValue() {
        return gValue;
    }

    /**
     * @return depth of the state (node)
     */
    public int getDepth() {
        return depth;
    }
}
