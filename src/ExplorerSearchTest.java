import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 1, 3, 3 },
                { 3, 1, 2, 1, 0, 1 },
                { 1, 1, 1, 2, 1, 1 },
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // unit testing reachableArea - blockedIn
    @Test
    public void testReachableArea_blockedIn() {
        int[][] island = {
                { 1, 1, 1, 3, 1, 1 },
                { 3, 2, 3, 1, 3, 1 },
                { 1, 1, 1, 1, 3, 1 },
                { 3, 1, 2, 2, 0, 3 },
                { 1, 1, 1, 1, 3, 1 },
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }

    // multiplePaths
    @Test
    public void testReachableArea_multiplePaths() {
        int[][] island = {
                { 0, 1, 1, 1, 1 },
                { 1, 3, 1, 2, 1 },
                { 1, 1, 1, 1, 1 },
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(13, actual);
    }

    // unit testing startPoint - explorer notFound
    @Test
    public void testStartPoint_notFound() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.startPoint(island);
        });
        assertEquals("No explorer present", exception.getMessage());
    }

    // startPoint top left corner
    @Test
    public void testStartPoint_topLeftCorner() {
        int[][] island = {
                { 0, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };
        int[] expected = { 0, 0 };
        assertArrayEquals(expected, ExplorerSearch.startPoint(island));
    }

    // startPoint dead center
    @Test
    public void testStartPoint_deadCenter() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        int[] expected = { 1, 1 };
        assertArrayEquals(expected, ExplorerSearch.startPoint(island));
    }

    // possibleMoves unit testing - allFourOpen
    @Test
    public void testPossibleMoves_allDirectionsOpen() {
        int[][] island = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        int[] location = { 1, 1 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    // right edge with open left
    @Test
    public void testPossibleMoves_rightEdgeWithOpenLeft() {
        int[][] island = {
                { 1, 1, 0 }
        };
        int[] location = { 0, 2 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1"));
    }

    // left edge open right
    @Test
    public void testPossibleMoves_leftEdgeWithOpenRight() {
        int[][] island = {
                { 0, 1 }
        };
        int[] location = { 0, 0 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1"));
    }

    // top left walled in
    @Test
    public void testPossibleMoves_topLeftWalledIn() {
        int[][] island = {
                { 0, 3 },
                { 3, 3 }
        };
        int[] location = { 0, 0 };
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        assertTrue(moves.isEmpty());
    }

    // blocked above by obstacle
    @Test
    public void testPossibleMoves_blockedAboveByObstacle() {
        int[][] island = {
                { 3, 3, 3 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        int[] location = { 1, 1 };
        Set<String> moveSet = toSet(ExplorerSearch.possibleMoves(island, location));

        assertFalse(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    // helper method copied from Auberon's unit tests for DFS Matrix project
    // done in class today
    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
