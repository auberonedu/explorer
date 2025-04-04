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
}
