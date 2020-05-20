package com.jatin.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    int[] game = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int currentPlayer = 0; // 0 = cross  1 = zero  2 = empty
    boolean active = true;
    int full = 0;
    public void addIn(View view) {
        ImageView count = (ImageView) view;
        Button playAgain = (Button) findViewById(R.id.again);
        TextView win = (TextView) findViewById(R.id.textView);
        int selectedCount = Integer.parseInt(count.getTag().toString());
        if (game[selectedCount] == 2 && active) {
            game[selectedCount] = currentPlayer;
            count.setTranslationY(-500);
            if (currentPlayer == 0) {
                count.setImageResource(R.drawable.zero);
                currentPlayer = 1;
                full++;
            } else {
                count.setImageResource(R.drawable.cross);
                currentPlayer = 0;
                full++;
            }
            count.animate().translationYBy(500).rotation(1800).setDuration(300);

            for (int[] w : winPos) {
                if (game[w[0]] == game[w[1]] && game[w[1]] == game[w[2]] && game[w[0]] != 2) {
                    active = false;
                    String winner = "";
                    if (currentPlayer == 1) {
                        winner = "Zero";
                    } else {
                        winner = "Cross";
                    }

                    win.setText(winner + " has won!!");
                    win.setVisibility(View.VISIBLE);
                    playAgain.setVisibility((View.VISIBLE));

                }
            }
            if(full == 9){
                win.setText("Match Drawn!!");
                win.setVisibility(View.VISIBLE);
                playAgain.setVisibility((View.VISIBLE));
            }
        }
        else {
                win.setText("Start a new game.");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonReset = findViewById(R.id.again);
        buttonReset.setOnClickListener(this);

    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.again: active = true;
                             currentPlayer = 0;
                             full = 0;
                             Button playAgain = (Button) findViewById(R.id.again);
                             TextView win = (TextView) findViewById(R.id.textView);
                             win.setVisibility(View.INVISIBLE);
                             playAgain.setVisibility((View.INVISIBLE));
                             for (int i = 0; i < game.length; i++) {
                                game[i] = 2;
                             }
                             androidx.gridlayout.widget.GridLayout gl = findViewById(R.id.grid);
                             for (int i = 0; i < gl.getChildCount(); i++) {
                                 ((ImageView) gl.getChildAt(i)).setImageResource(0);
                             }
        }
    }
}
