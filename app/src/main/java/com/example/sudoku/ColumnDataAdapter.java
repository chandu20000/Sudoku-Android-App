package com.example.sudoku;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

//Hold column data of grid
public class ColumnDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<Integer> sudokuData;
        private Context context;
        private horizonatalItemselectListener listener;

        public ColumnDataAdapter(ArrayList<Integer> sudokuData, Context context, horizonatalItemselectListener listener) {
            this.context = context;
            this.sudokuData = sudokuData;
            this.listener = listener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.listview_items, parent, false);
            return new sudokucolumndatalist(v) {

            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            if(sudokuData.get(holder.getAdapterPosition())!=0)
            ((sudokucolumndatalist) holder).textView.setText(Integer.toString(sudokuData.get(holder.getAdapterPosition())));

            //logic to highlight the subtable inside sudoku table

            if(holder.getAdapterPosition()==0) {
                ((sudokucolumndatalist) holder).topMarginView.setVisibility(View.VISIBLE);
            }
            if((holder.getAdapterPosition()+1)%3==0){
                ViewGroup.LayoutParams params = ((sudokucolumndatalist) holder).bottomMargin.getLayoutParams();
                params.width = 6;
                ((sudokucolumndatalist) holder).bottomMargin.setLayoutParams(params);
            }
        }

        @Override
        public int getItemCount() {
            return sudokuData.size();
        }

        class sudokucolumndatalist extends RecyclerView.ViewHolder {
            public TextView textView;
            public View topMarginView;
            public View bottomMargin;

            public sudokucolumndatalist(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.label);

                topMarginView=itemView.findViewById(R.id.topmargin1);
                bottomMargin=itemView.findViewById(R.id.bottommargin1);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         listener.onItemSelected(textView.getText().toString(),getAdapterPosition());

                    }
                });
            }
        }


        interface horizonatalItemselectListener {
            void onItemSelected(String value,int position);
        }
    }

