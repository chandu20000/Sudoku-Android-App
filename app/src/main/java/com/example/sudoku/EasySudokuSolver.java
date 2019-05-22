package com.example.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Sudoku solver for entry level(easy level)
public class EasySudokuSolver extends  BaseAlgo {
    private ArrayList<ArrayList<Integer>> _horDat;
    private ArrayList<ArrayList<Integer>> _vertDat;
    private ArrayList<ArrayList<Integer>> _Matrixdata;
    private ArrayList<ArrayList<Integer>> _missingElements;
    private ArrayList<Map<String,Position>> _missingPositions;
    private ArrayList<Map<String,Position>> _missingPositionsHorizontal;
    private ArrayList<Map<String,Position>> _missingPositionsMatrix;


    //Entry pooint for Algo to solve sudoku
    @Override
    public  ArrayList<ArrayList<Integer>>  Solve(ArrayList<ArrayList<Integer>>  sudokuData)
    {
        int maxTry=0;
        prepareData(sudokuData);
        while (!isEmpty() && maxTry<=1000)
        {
            maxTry++;
        for (int i=_missingElements.size()-1;i>=0;i--) {
            for (int j = _missingElements.get(i).size() - 1; j >= 0; j--) {
                for(Map.Entry<String, Position> e : _missingPositions.get(i).entrySet())
                 {
                    if( _missingElements.get(i).size()>j) {

                        Position pos = e.getValue();
                        int group_index = ((pos.i / 3) * 3) + (pos.j / 3);
                        int missing_element = _missingElements.get(i).get(j);

                        //try to insert data to row of sudoku table
                        if (!_vertDat.get(pos.j).contains(missing_element) && !_Matrixdata.get(group_index).contains(missing_element) && !pos.isResolved) {
                            List<Position> grouppos = possibleToInsertMatrix(missing_element, group_index);
                            List<Position> horPos = possibleToInsertHorizontally(missing_element, pos.j);
                            if ((grouppos.size() == 0 && horPos.size() == 0) || _missingElements.get(i).size() == 1) {
                                updateValue(missing_element, pos);
                                break;
                            }

                            if (grouppos.size() == 1) {
                                updateValue(missing_element, grouppos.get(0));
                                break;
                            }

                            if (horPos.size() == 1) {
                                updateValue(missing_element, horPos.get(0));
                                break;
                            }
                        }
                    }
                }
            }
        }
        }
       return _horDat;
    }

    //Prepares data required to solve sudoku
     public void prepareData(ArrayList<ArrayList<Integer>>  sudokuData)
     {

         _horDat=sudokuData;
         _vertDat=new ArrayList<>();
         _Matrixdata =new ArrayList<>();
         _missingElements=new ArrayList<>();
         _missingPositions=new ArrayList<>();
         _missingPositionsMatrix =new ArrayList<>();
         _missingPositionsHorizontal=new ArrayList<>();


         for(int i=0;i<sudokuData.size();i++)
         {
             ArrayList<Integer> rowMissingData =new ArrayList<>();
             HashMap<String,Position> rowMissingPositionData =new HashMap<>();

             for (int j=0;j<sudokuData.get(i).size();j++)
             {
                 int group_index=((i/3)*3)+(j/3);

                 if(_vertDat.size()<=j) {
                     _vertDat.add(new ArrayList<Integer>());
                 }
                 if(_missingPositionsHorizontal.size()<=j) {
                     _missingPositionsHorizontal.add(new HashMap<String, Position>());
                 }


                 if(_Matrixdata.size()<=group_index)
                 {
                     _Matrixdata.add(new ArrayList<Integer>());
                     _missingPositionsMatrix.add(new HashMap<String, Position>());
                 }

                 if(sudokuData.get(i).get(j)==0)
                 {
                     rowMissingPositionData.put(i+"_"+j,new Position(i,j));
                     _missingPositionsHorizontal.get(j).put(i+"_"+j, new Position(i,j));
                     _missingPositionsMatrix.get(group_index).put(i+"_"+j,new Position(i,j));

                 }

                 _vertDat.get(j).add(sudokuData.get(i).get(j));
                 _Matrixdata.get(group_index).add(sudokuData.get(i).get(j));


             }
             for(int k=1;k<=9;k++)
             {
                 if(!sudokuData.get(i).contains(k))
                 {
                     rowMissingData.add(k);
                 }
             }
             _missingElements.add(rowMissingData);
             _missingPositions.add(rowMissingPositionData);
         }
     }

     //check the possiblity of insertion  to subtable of sudoku
     public List<Position> possibleToInsertMatrix(int missing_element, int group_Index)
     {
         ArrayList<Position> posList=new ArrayList<>();
         for(Map.Entry<String, Position> e : _missingPositionsMatrix.get(group_Index).entrySet())
         {
             Position pos =e.getValue();
             if (!_vertDat.get(pos.j).contains(missing_element) && !_horDat.get(pos.i).contains(missing_element) && !pos.isResolved) {
                 posList.add(pos);
             }

         }

         return posList;
     }

    //check the possiblity of insertion into column of sudoku table
    public List<Position> possibleToInsertHorizontally(int missing_element,int verticalIndex)
    {
        ArrayList<Position> posList=new ArrayList<>();

        for(Map.Entry<String, Position> e : _missingPositionsHorizontal.get(verticalIndex).entrySet())
        {
            Position pos =e.getValue();
            int group_index = ((pos.i / 3) * 3) + (pos.j / 3);
            if (!_horDat.get(pos.i).contains(missing_element) && !_Matrixdata.get(group_index).contains(missing_element) && !pos.isResolved) {
                posList.add(pos);
            }

        }

        return posList;
    }

    //check the possiblity of insertion  to subtable of sudoku
    public void updateValue(int missing_Element,Position pos)
    {
        _horDat.get(pos.i).set(pos.j,missing_Element);
        _vertDat.get(pos.j).set(pos.i,missing_Element);
        for(int i=0;i<_missingElements.get(pos.i).size();i++) {
            if(_missingElements.get(pos.i).get(i)==missing_Element)
            {
                _missingElements.get(pos.i).remove(i);
                break;
            }
        }
        int group_index=((pos.i/3)*3)+(pos.j/3);
        int groupposIndex=((pos.i%3)*3)+((pos.j%3));
        _Matrixdata.get(group_index).set(groupposIndex,missing_Element);

        _missingPositionsHorizontal.get(pos.j).get(pos.i+"_"+pos.j).isResolved=true;
        _missingPositionsMatrix.get(group_index).get(pos.i+"_"+pos.j).isResolved=true;
        _missingPositions.get(pos.i).get(pos.i+"_"+pos.j).isResolved=true;
    }

    public boolean isEmpty()
    {
        boolean isEmpty=true;

        for(int i=0;i<_missingElements.size();i++)
        {
            if(_missingElements.get(i).size()!=0)
            {
                isEmpty=false;
                break;
            }
        }

        return isEmpty;
    }

}
