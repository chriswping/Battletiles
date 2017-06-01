package com.example.chris.battletiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void cs(View v) {
        Toast.makeText(Welcome.this, "Coming soon!", Toast.LENGTH_SHORT).show();
    }


    public void mode(View v) {
        initialize();
        Button b = (Button) v;
        Intent myIntent = new Intent(Welcome.this, Placement.class);
        myIntent.putExtra("mode", b.getText().toString());
        myIntent.putExtra("player", 1);
        Welcome.this.startActivity(myIntent);
    }


    public void initialize() {
        Grids.p1Score = 17;
        Grids.p2Score = 17;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++) {
                Grids.p1Board[j][i] = 0;
                Grids.p2Board[j][i] = 0;
            }
        }
    }
}
