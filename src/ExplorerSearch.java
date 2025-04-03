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
        int[] start = startingLocation(island);
        boolean[][] visited = new boolean[island.length][island[0].length];

        return reachableArea(island, start, visited);
    }
    
    public static int reachableArea(int[][] island, int[] current, boolean[][] visited) {
        int row = current[0];
        int col = current[1];

        if (row < 0 || row >= island.length || col < 0 || col >= island[0].length || island[row][col] == 2 || island[row][col] == 3 || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;
        int count = 1;
        
        List<int[]> moves = possibleMoves(island, current);
        for (int[] move : moves) {
            count += reachableArea(island, move, visited);
        }

        return count;
    }

    public static List<int[]> possibleMoves(int[][] island, int[] current) {
        int row = current[0];
        int col = current[1];

        List<int[]> moves = new ArrayList<>();

        // UP
        if (row - 1 >= 0 && island[row - 1][col] != 2 && island[row - 1][col] != 3) {
            moves.add(new int[]{row - 1, col});
        }

        // DOWN 
        if (row + 1 < island.length && island[row + 1][col] != 2 && island[row + 1][col] != 3) {
            moves.add(new int[]{row + 1, col});
        }

        // LEFT
        if (col - 1 >= 0 && island[row][col - 1] != 2 && island[row][col - 1] != 3) {
            moves.add(new int[]{row, col - 1});
        }

        // RIGHT
        if (col + 1 < island[0].length && island[row][col + 1] != 2 && island[row][col + 1] != 3) {
            moves.add(new int[]{row, col + 1});
        }

        return moves;
    }

    public static int[] startingLocation(int[][] island) {
        for (int r = 0; r < island.length; r++) {
            for (int c = 0; c < island[0].length; c++) {
                if (island[r][c] == 0) {
                    return new int[]{r, c};
                }
            }
        }
        throw new IllegalArgumentException("No starting position present");
    }
}
