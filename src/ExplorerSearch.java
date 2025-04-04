import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too
        return -1;
    }

    /**
     * Finds indices of the space the explorer is starting from
     * represented as 0
     * 
     * throws IllegalArgumentException if no starting point is found
     * 
     * @param island locations on the island
     * @return array of indices ([0] = row, [1] = column) where explorer is starting 
     */
    public static int[] findStart(int[][] island){
        // for each array element, if element = 0, return indices

        // if there was no return in for loop, throw exception
        return new int[]{};
    }

    /**
     * Finds any field spaces that are directly accessible from the given starting point (current)
     * 
     * @param island locations on the island
     * @param current starting location
     * @return List of indices where the explorer can move to (field spaces)
     */
    public static List<int[]> nearbyFields(int[][] island, int[] current){
        // create variables for current row and column

        // check above curr

        // check below curr

        // check left

        // check right


        return new ArrayList<>();
    }


}
