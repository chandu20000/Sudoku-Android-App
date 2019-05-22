package com.example.sudoku;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateFragment(new SudokuFragment(), "sudoku", false);
    }

    public void updateFragment(Fragment fragment, String tag, boolean isAddedtoBackStack) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
        if (isAddedtoBackStack) {
            transaction.replace(R.id.frag_container, fragment, tag).addToBackStack(tag).commit();
        } else {
            transaction.replace(R.id.frag_container, fragment, tag).commitAllowingStateLoss();

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
