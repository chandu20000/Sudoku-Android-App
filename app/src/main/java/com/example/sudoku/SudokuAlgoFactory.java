package com.example.sudoku;

import java.util.HashMap;
import java.util.Map;

/*
Algo factory provides different types of algo based on difficulty level
 */
public class SudokuAlgoFactory {

    private Map<String,BaseAlgo> _algoProvider=new HashMap<>();

    public static final  SudokuAlgoFactory sudokoAlgoFactory = new SudokuAlgoFactory();

    private SudokuAlgoFactory()
    {
        _algoProvider.put("Easy",new EasySudokuSolver());
    }

    public BaseAlgo getAlgo(String difficultylevel)
    {
        return _algoProvider.get(difficultylevel);
    }

}
