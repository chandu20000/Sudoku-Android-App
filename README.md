# Sudoku

App to solve sudoku puzzle(entry level)

Instructions
1.Code runs on Android studio
2.Sample sudoku puzzle Data is hard coded in the code itself (SudokuFragment.java).We change the sample data to run app for multiple samples.In sample data 0 is referred as no data
 
public class SudokuFragment extends Fragment {
    private RecyclerView _recyclerView;

    //we can feed sudoku data via hardcoded
    //Integer[][] _data={{8,5,6,0,1,4,7,3,0},{0,9,0,0,0,0,0,0,0},{2,4,0,0,0,0,1,6,0},{0,6,2,0,5,9,3,0,0},{0,3,1,8,0,2,4,5,0},
      //          {0,0,5,3,4,0,9,2,0},{0,2,4,0,0,0,0,7,3},{0,0,0,0,0,0,0,1,0},{0,1,8,6,3,0,2,9,4}};

    Integer[][] _data={{8,0,9,0,7,0,3,0,5},{0,0,0,8,0,2,0,0,0},{1,0,0,0,0,0,0,0,4},{0,8,0,6,0,5,0,1,0},{4,0,0,0,0,0,0,0,8},
            {0,9,0,7,0,4,0,6,0},{2,0,0,0,0,0,0,0,3},{0,0,0,4,0,3,0,0,0},{5,0,6,0,1,0,2,0,7}};

    //holds sudoku puzzle
    ArrayList<ArrayList<Integer>> sudokuData=new ArrayList<>();


3.We can test sudoku Algo via unit test written under android unit test module(EasySudokoSolverTest.java) with multiple samples

 Sample:
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

4.Android App will load hardcoded data into  Recycler view,on click of solve button, app will try to solve the puzzle

