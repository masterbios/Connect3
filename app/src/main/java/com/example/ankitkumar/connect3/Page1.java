package com.example.ankitkumar.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Page1 extends AppCompatActivity {
    // yellow = 0, red = 1

    String winner = "";

    boolean over = false;

    int gameState[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int wins[][] = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    int activePlayer = 0;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tap = Integer.parseInt(counter.getTag().toString());

        if(gameState[tap] == -1 && !over) {

            counter.setTranslationY(-2000);
            gameState[tap] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(2000).rotation(720).setDuration(200);

            for (int r[] : wins) {

                if (gameState[r[0]] == gameState[r[1]] && gameState[r[1]] == gameState[r[2]] && gameState[r[2]] != -1) {
                    over = true;
                    if (activePlayer == 1) {
                        winner = "Yellow Won!";
                    } else {
                        winner = "Red Won!";
                    }

                    // make final as called by inner class
                    final TextView box = (TextView) findViewById(R.id.winnerbox);
                    final Button play = (Button) findViewById(R.id.playagain);

                    box.setText(winner);

                    //clear string everytime for determining draw
                    winner = "";

                    // delay visibility of button and text
                    box.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            box.setVisibility(View.VISIBLE);
                            play.setVisibility(View.VISIBLE);
                        }
                    }, 201);

                }
            }
            if(allFilled() && winner.length() == 0){

                final TextView box = (TextView) findViewById(R.id.winnerbox);
                final Button play = (Button) findViewById(R.id.playagain);

                winner = "Game Draw!";
                box.setText(winner);

                //clear string everytime for determining draw
                winner = "";

                // delay visibility of button and text
                box.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        box.setVisibility(View.VISIBLE);
                        play.setVisibility(View.VISIBLE);
                    }
                }, 201);
            }
        }

    }

    public boolean allFilled(){
        for(int x : gameState){
            if(x == -1){
                return false;
            }
        }
        return true;
    }

    public void replay(View view){

         TextView box = (TextView) findViewById(R.id.winnerbox);
         Button play = (Button) findViewById(R.id.playagain);

        box.setVisibility(View.INVISIBLE);
        play.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout grid = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i < grid.getChildCount(); i++){
            ImageView col = (ImageView) grid.getChildAt(i);
            col.setImageDrawable(null);
        }

        Arrays.fill(gameState, -1);
        over = false;
        activePlayer = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
    }
}
