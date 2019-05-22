package com.example.sudoku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class SudokuFragment extends Fragment {
    private RecyclerView _recyclerView;

    //we can feed sudoku data via hardcoded
    //Integer[][] _data={{8,5,6,0,1,4,7,3,0},{0,9,0,0,0,0,0,0,0},{2,4,0,0,0,0,1,6,0},{0,6,2,0,5,9,3,0,0},{0,3,1,8,0,2,4,5,0},
      //          {0,0,5,3,4,0,9,2,0},{0,2,4,0,0,0,0,7,3},{0,0,0,0,0,0,0,1,0},{0,1,8,6,3,0,2,9,4}};

    Integer[][] _data={{8,0,9,0,7,0,3,0,5},{0,0,0,8,0,2,0,0,0},{1,0,0,0,0,0,0,0,4},{0,8,0,6,0,5,0,1,0},{4,0,0,0,0,0,0,0,8},
            {0,9,0,7,0,4,0,6,0},{2,0,0,0,0,0,0,0,3},{0,0,0,4,0,3,0,0,0},{5,0,6,0,1,0,2,0,7}};

    //holds sudoku puzzle
    ArrayList<ArrayList<Integer>> sudokuData=new ArrayList<>();

         @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.sudoku_layout, container, false);
             _recyclerView = (RecyclerView) view.findViewById(R.id.rec_view);
             _recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

             sudokuData = new ArrayList<>();
             for(Integer[] row:_data)
             {
                 sudokuData.add(new ArrayList<Integer>(Arrays.asList(row)));
             }

             notifyDataChanges();

             //Solve button
             Button solve_button= (Button) view.findViewById(R.id.appCompatButton);
             solve_button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     sudokuData = SudokuAlgoFactory.sudokoAlgoFactory.getAlgo("Easy").Solve(sudokuData);
                   notifyDataChanges();
                 }
             });
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
    }

    //updates grid data
    public  void notifyDataChanges()
    {
        RowDataAdapter rowDataAdapter = new RowDataAdapter(sudokuData, getActivity(), new RowDataAdapter.itemselectListener() {
            @Override
            public void onItemSelected(String location) {
                int a=3;
                Log.d("","cc");

            }
        });
        _recyclerView.setAdapter(rowDataAdapter);
        rowDataAdapter.notifyDataSetChanged();

    }
}

