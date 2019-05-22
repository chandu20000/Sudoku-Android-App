package com.example.sudoku;

//holds position of dataMember od sudoku table
public class Position {
    public int i;
    public  int j;
    public  boolean isResolved;

    public Position(int i,int j)
    {
        this.i=i;
        this.j=j;
        isResolved=false;
    }
}
