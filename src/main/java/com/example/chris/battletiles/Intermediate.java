package com.example.chris.battletiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Intermediate extends AppCompatActivity {
    public int player;
    public int finish = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);
        Intent intent = getIntent();
        player = intent.getIntExtra("player", 0);
        Toast.makeText(Intermediate.this, ""+ player, Toast.LENGTH_SHORT);
        textSet(player);
    }

    public void textSet(int player){
        TextView t = (TextView)findViewById(R.id.text);
        if(Grids.p1Score == 0 || Grids.p2Score == 0){
            if(Grids.p1Score == 0){
                t.setText("Player 2 has won!\nPress continue for a new game.");
                finish = 1;
            }
            else {
                t.setText("Player 1 has won!\nPress continue for a new game.");
                finish = 1;
            }
        }
        else {
            if (player == 3) {
                t.setText("Player 2's turn.\nPress Continue.\nP1 tiles left: " + Grids.p1Score + "\n P2 tiles left: " + Grids.p2Score);
            } else if (player == 4) {
                t.setText("Player 1's turn.\nPress Continue.\nP1 tiles left: " + Grids.p1Score + "\n P2 tiles left: " + Grids.p2Score);
            } else if (player == 5) {
                t.setText("Game begin: Player 1's turn.\nPress Continue to begin." + "\nP1 tiles left: " + Grids.p1Score + "\n P2 tiles left: " + Grids.p2Score);
            }
        }
    }

    public void cont(View view){
        if(finish == 1){
            Intent myIntent = new Intent(Intermediate.this, Welcome.class);
            Intermediate.this.startActivity(myIntent);
        }
        else {
            if (player == 3) {
                Intent myIntent = new Intent(Intermediate.this, Placement.class);
                myIntent.putExtra("player", 4);
                Intermediate.this.startActivity(myIntent);
            } else if (player == 4) {
                Intent myIntent = new Intent(Intermediate.this, Placement.class);
                myIntent.putExtra("player", 3);
                Intermediate.this.startActivity(myIntent);
            } else if (player == 5) {
                Intent myIntent = new Intent(Intermediate.this, Placement.class);
                myIntent.putExtra("player", 3);
                Intermediate.this.startActivity(myIntent);
            }
        }
    }

}