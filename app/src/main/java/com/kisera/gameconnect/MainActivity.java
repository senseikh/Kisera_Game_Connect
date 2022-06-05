package com.kisera.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    //0 represent: image3 while 1 rep: game1 2:empty

    int [] gameState ={2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winningPositions ={{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    public void dropIn  (View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] ==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.image3);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.game1);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPositions : winningPositions) {

                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {
                    // the conditions above are for someone winning the game...
                    //someone has won the game

                    gameActive =false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "blue" ;
                    } else {

                        winner = "orange" ;
                    }

                    Button buttonPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);

                    TextView WinnerTextView = (TextView) findViewById(R.id.WinnerTextView);

                    WinnerTextView.setText(winner+ "has won!");

                    buttonPlayAgain.setVisibility(view.VISIBLE);

                    WinnerTextView.setVisibility(view.VISIBLE);
                }
            }

        }

    }

    public void playAgain (View view){

        Button buttonPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);

        TextView WinnerTextView = (TextView) findViewById(R.id.WinnerTextView);

        buttonPlayAgain.setVisibility(view.INVISIBLE);

        WinnerTextView.setVisibility(view.INVISIBLE);

        GridLayout gridEdit = (GridLayout) findViewById(R.id.gridEdit);

        for(int i=0; i<gridEdit.getChildCount(); i++) {

            ImageView counter = (ImageView) gridEdit.getChildAt(i);

            counter.setImageDrawable(null);
        }
        for (int i=0; i<gameState.length; i++){

            gameState [i] =2;
        }

        activePlayer = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}