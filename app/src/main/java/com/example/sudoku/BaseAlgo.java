package com.example.sudoku;

import java.util.ArrayList;

//base class  to hold instance of different algorithms

public abstract class BaseAlgo {
    public abstract ArrayList<ArrayList<Integer>> Solve(ArrayList<ArrayList<Integer>> sudokuData);
}
