package com.example.sudoku;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//we can test all possible combinations here
public class EasySudokoSolverTest {

    //Difficult  sample
    @Test
    public void test_solve() {
        Integer[][] data={{8,5,6,0,1,4,7,3,0},{0,9,0,0,0,0,0,0,0},{2,4,0,0,0,0,1,6,0},{0,6,2,0,5,9,3,0,0},{0,3,1,8,0,2,4,5,0},
                {0,0,5,3,4,0,9,2,0},{0,2,4,0,0,0,0,7,3},{0,0,0,0,0,0,0,1,0},{0,1,8,6,3,0,2,9,4}};

        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        for(Integer[] row:data)
        {
            arrayList.add(new ArrayList<Integer>(Arrays.asList(row)));
        }
        ArrayList<ArrayList<Integer>> result_data = SudokuAlgoFactory.sudokoAlgoFactory.getAlgo("Easy").Solve(arrayList);

        assertEquals(result_data.get(7).get(2).intValue(), 9);
    }

    //Easy Sample
    @Test
    public void test_solve_sample_2() {
        Integer[][] data={{8,0,9,0,7,0,3,0,5},{0,0,0,8,0,2,0,0,0},{1,0,0,0,0,0,0,0,4},{0,8,0,6,0,5,0,1,0},{4,0,0,0,0,0,0,0,8},
                {0,9,0,7,0,4,0,6,0},{2,0,0,0,0,0,0,0,3},{0,0,0,4,0,3,0,0,0},{5,0,6,0,1,0,2,0,7}};

        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        for(Integer[] row:data)
        {
            arrayList.add(new ArrayList<Integer>(Arrays.asList(row)));
        }

        ArrayList<ArrayList<Integer>> result_data = SudokuAlgoFactory.sudokoAlgoFactory.getAlgo("Easy").Solve(arrayList);

        assertEquals(result_data.get(7).get(0).intValue(), 9);
    }
}