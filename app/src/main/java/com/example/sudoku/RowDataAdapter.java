package com.example.sudoku;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

//holds row data of geid
public class RowDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<ArrayList<Integer>> sudokuData;
        private Context context;
        private itemselectListener listener;

        public RowDataAdapter(ArrayList<ArrayList<Integer>> sudokuData, Context context, itemselectListener listener) {
            this.context = context;
            this.sudokuData = sudokuData;
            this.listener = listener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.ist_view_item_final, parent, false);
            return new sudokurowdatalist(v) {

            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ColumnDataAdapter dataAdapter = new ColumnDataAdapter(sudokuData.get(holder.getAdapterPosition()), this.context, new ColumnDataAdapter.horizonatalItemselectListener() {
                @Override
                public void onItemSelected(String value,int position) {


                }
            });

            ((sudokurowdatalist) holder).listView.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();


            //logic to highlight the subtable inside sudoku table
            if(holder.getAdapterPosition()==0) {
                ((sudokurowdatalist) holder).topMarginView.setVisibility(View.VISIBLE);
            }
            if((holder.getAdapterPosition()+1)%3==0){
                ViewGroup.LayoutParams params = ((sudokurowdatalist) holder).bottomMargin.getLayoutParams();

                // params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.height = 6;
                ((sudokurowdatalist) holder).bottomMargin.setLayoutParams(params);
            }
        }


        @Override
        public int getItemCount() {
            return sudokuData.size();
        }

        class sudokurowdatalist extends RecyclerView.ViewHolder {
            public RecyclerView listView;
            public View topMarginView;
            public View bottomMargin;

            public sudokurowdatalist(View itemView) {
                super(itemView);
                listView = (RecyclerView) itemView.findViewById(R.id.list_view);
                listView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                topMarginView=itemView.findViewById(R.id.topmargin);
                bottomMargin=itemView.findViewById(R.id.bottommargin);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // listener.onItemSelected(textView.getText().toString());

                    }
                });
            }
        }


        interface itemselectListener {
            void onItemSelected(String location);
        }
    }

