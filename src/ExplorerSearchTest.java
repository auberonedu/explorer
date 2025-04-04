import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);
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
        assertEquals("Island must have starting location", exception.getMessage());
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
        assertEquals(expected, actual);
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
        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{1, 3});
        expected.add(new int[]{3, 3});
        expected.add(new int[]{2, 2});
        assertEquals(expected, actual);
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
        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{0, 1});
        assertEquals(expected, actual);
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
        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{4, 5});
        assertEquals(expected, actual);
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
        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{4, 0});
        expected.add(new int[]{4, 2});
        expected.add(new int[]{3, 1});
        assertEquals(expected, actual);
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
        List<int[]> expected = new ArrayList<>();
        expected.add(new int[]{4, 3});
        expected.add(new int[]{2, 3});
        expected.add(new int[]{3, 4});
        expected.add(new int[]{3, 2});
        assertEquals(expected, actual);
    }

}
