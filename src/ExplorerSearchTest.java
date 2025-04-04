import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    //  !! findStart tests
    @Test
    public void testfindStart_middle(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,0,3,3},
            {3,1,2,1,2,1},
            {1,1,1,2,1,1},
        };
        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = new int[]{2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testfindStart_veryFirst(){
        int[][] island = {
            {0,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,1},
        };
        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = new int[]{0, 0};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testfindStart_veryLast(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,0},
        };
        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = new int[]{4, 5};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testfindStart_noStart(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,1,1},
            {1,1,1,2,1,2},
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.findStart(island);
        });
        assertEquals("Island must have a starting location", exception.getMessage());
    }

    @Test
    public void testfindStart_smallIsland(){
        int[][] island = {
            {1,1,1},
            {3,2,3},
            {1,0,1}
        };
        int[] actual = ExplorerSearch.findStart(island);
        int[] expected = new int[]{2, 1};
        assertArrayEquals(expected, actual);
    }


    // !! nearbyFields tests

    @Test
    public void testNearbyFields_currentOnStart(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,0,3,3},
            {3,1,2,1,2,1},
            {1,1,1,2,1,1},
        };
        List<int[]> actual = ExplorerSearch.nearbyFields(island, new int[]{2, 3});
        Set<String> actualSet = toSet(actual);
        assertEquals(3, actual.size());
        assertTrue(actualSet.contains("1, 3"));
        assertTrue(actualSet.contains("3, 3"));
        assertTrue(actualSet.contains("2, 2"));
    }

    @Test
    public void testNearbyFields_currentInCorner(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,0,3,3},
            {3,1,2,1,2,1},
            {1,1,1,2,1,1},
        };
        List<int[]> actual = ExplorerSearch.nearbyFields(island, new int[]{0, 0});
        Set<String> actualSet = toSet(actual);
        assertEquals(1, actual.size());
        assertTrue(actualSet.contains("0, 1"));
    }

    @Test
    public void testNearbyFields_currentOnRightCoast(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,0,3,3},
            {3,1,2,1,2,1},
            {1,1,1,2,1,1},
        };
        List<int[]> actual = ExplorerSearch.nearbyFields(island, new int[]{3, 5});
        Set<String> actualSet = toSet(actual);
        assertEquals(1, actual.size());
        assertTrue(actualSet.contains("4, 5"));
    }

    @Test
    public void testNearbyFields_currentOnBottomCoast(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,0,3,3},
            {3,1,2,1,2,1},
            {1,1,1,2,1,1},
        };
        List<int[]> actual = ExplorerSearch.nearbyFields(island, new int[]{4, 1});
        Set<String> actualSet = toSet(actual);
        assertEquals(3, actual.size());
        assertTrue(actualSet.contains("4, 0"));
        assertTrue(actualSet.contains("4, 2"));
        assertTrue(actualSet.contains("3, 1"));
    }

    @Test
    public void testNearbyFields_noFields(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,2,3,3},
            {3,1,2,0,2,1},
            {1,1,1,2,1,1},
        };
        List<int[]> actual = ExplorerSearch.nearbyFields(island, new int[]{3, 3});
        List<int[]> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void testNearbyFields_allFields(){
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,1,0,1,1},
            {1,1,1,1,1,1},
        };
        List<int[]> actual = ExplorerSearch.nearbyFields(island, new int[]{3, 3});
        Set<String> actualSet = toSet(actual);
        assertEquals(4, actual.size());
        assertTrue(actualSet.contains("4, 3"));
        assertTrue(actualSet.contains("2, 3"));
        assertTrue(actualSet.contains("3, 4"));
        assertTrue(actualSet.contains("3, 2"));
    }

    private Set<String> toSet(List<int[]> li){
        Set<String> indexSet = new HashSet<>();
        for (int[] arr : li){
            indexSet.add(arr[0] + ", " + arr[1]);
        }
        return indexSet;
    }

}
