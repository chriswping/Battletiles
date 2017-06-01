package com.example.chris.battletiles;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;

public class Placement extends AppCompatActivity {
    public int piece = 5, phase = 1, three = 0, orientation = 0, jc = 107, player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);
        Intent intent = getIntent();
        player = intent.getIntExtra("player", 0);
        if(player > 2){
            playing();
        }
        else {
            topText();
        }

    }

    public void place(View v) {
        if (player == 1 || player == 2) {
            char x = c1(v);
            int y = c2(v);
            int code = x;
            if (piece > 1 && phase == 1) {
                int legit = valid(v, code, orientation, y, x, piece);
                if (orientation == 0) {
                    if (legit == 1) {
                        phase = 2;
                        if (phase == 2) {
                            for (int i = 0; i < piece; i++) {
                                Button b = (Button) findViewById(nextIDs(v, code, i, orientation, y, x));
                                b.setBackgroundColor(Color.RED);
                            }
                            phase = 3;
                        }
                    } else
                        Toast.makeText(Placement.this, "Invalid placement", Toast.LENGTH_SHORT).show();
                } else if (orientation == 1) {
                    if (legit == 1) {
                        phase = 2;
                        if (phase == 2) {
                            for (int i = 0; i < piece; i++) {
                                Button b = (Button) findViewById(nextIDs(v, code, i, orientation, y, x));
                                b.setBackgroundColor(Color.RED);
                            }
                            phase = 3;
                        }
                    } else
                        Toast.makeText(Placement.this, "Invalid placement", Toast.LENGTH_SHORT).show();
                }
            }

            if (phase == 3) {
                alert(v, code, orientation, y, x, piece);
            }
            phase = 1;
        }
        else if(player == 3){
            char x = c1(v);
            int y = c2(v);
            int codex = x - 97;
            int codey = y - 1;;
            if(Grids.p2Board[codey][codex] == 1){
                Grids.p2Board[codey][codex] = 2;
                Grids.p2Score--;
                Toast.makeText(Placement.this, "Hit!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(Placement.this, Intermediate.class);
                myIntent.putExtra("player", 3);
                Placement.this.startActivity(myIntent);
            }
            else if(Grids.p2Board[codey][codex] == 0) {
                Grids.p2Board[codey][codex] = 3;
                Toast.makeText(Placement.this, "Miss.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(Placement.this, Intermediate.class);
                myIntent.putExtra("player", 3);
                Placement.this.startActivity(myIntent);
            }
            else if(Grids.p2Board[codey][codex] == 2) {
                Toast.makeText(Placement.this, "You've already destroyed this spot.", Toast.LENGTH_SHORT).show();
            }
            else if(Grids.p2Board[codey][codex] == 3) {
                Toast.makeText(Placement.this, "You've already tried this spot.", Toast.LENGTH_SHORT).show();
            }
        }

        else if(player == 4){
            char x = c1(v);
            int y = c2(v);
            int codex = x - 97;
            int codey = y - 1;;
            if(Grids.p1Board[codey][codex] == 1){
                Grids.p1Board[codey][codex] = 2;
                Grids.p1Score--;
                Toast.makeText(Placement.this, "Hit!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(Placement.this, Intermediate.class);
                myIntent.putExtra("player", 4);
                Placement.this.startActivity(myIntent);
            }
            else if(Grids.p1Board[codey][codex] == 0) {
                Grids.p1Board[codey][codex] = 3;
                Toast.makeText(Placement.this, "Miss.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(Placement.this, Intermediate.class);
                myIntent.putExtra("player", 4);
                Placement.this.startActivity(myIntent);
            }
            else if(Grids.p1Board[codey][codex] == 2) {
                Toast.makeText(Placement.this, "You've already destroyed this spot.", Toast.LENGTH_SHORT).show();
            }
            else if(Grids.p1Board[codey][codex] == 3) {
                Toast.makeText(Placement.this, "You've already tried this spot.", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void alert(View v, int code, int orientation, int y, char x, int piece){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(Placement.this);
        final int code2 = code;
        final int orientation2 = orientation;
        final int y2 = y;
        final char x2 = x;
        final int piece2 = piece;
        final View v2 = v;

        a_builder.setMessage("Is this placement right?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        change();
                        topText();

                        if(piece2 == 2) {
                            if(player == 1) {
                                Intent myIntent = new Intent(Placement.this, Placement.class);
                                myIntent.putExtra("player", 2);
                                Placement.this.startActivity(myIntent);
                            }
                            else if(player == 2){
                                Intent myIntent = new Intent(Placement.this, Intermediate.class);
                                myIntent.putExtra("player", 5);
                                Placement.this.startActivity(myIntent);
                            }
                        }
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(orientation2 == 0){
                            for(int i = 0; i < piece2; i++) {
                                Button b = (Button) findViewById(nextIDs(v2, code2, i, orientation2, y2, x2));
                                b.setBackgroundColor(0xff33b5e5);
                            }
                        }
                        else{
                            for(int i = 0; i < piece2; i++) {
                                Button b = (Button) findViewById(nextIDs(v2, code2, i, orientation2, y2, x2));
                                b.setBackgroundColor(0xff33b5e5);
                            }
                        }
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.setTitle("Confirm");
        alert.show();
    }


    public void change(){

        if (piece == 3 && three == 0) {
            three++;
        }
        else if (piece == 5 || piece == 4 || piece == 2 || (piece == 3 && three == 1)) {
            piece--;
        }

    }

    public void rotate(View v){
        if(piece > 0 && phase == 1) {
            if(orientation == 0) {
                orientation = 1;
                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText("Vertical");
            }
            else if(orientation == 1) {
                orientation = 0;
                TextView tv = (TextView)findViewById(R.id.textView);
                tv.setText("Horizontal");
            }
        }
    }
    public int valid(View v, int code, int orientation, int y, char x, int piece){
        int legit = 1;
        if(orientation == 0){
            if((jc - code) >= piece) {
                for (int i = 0; i < piece; i++) {
                    Button b = (Button) findViewById(nextIDs(v, code, i, orientation, y, x));
                    ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
                    int colorId = buttonColor.getColor();
                    if (colorId == Color.RED) {
                        legit = 0;
                    }
                }
            }
            else
                legit = 0;
        }
        else{
            if((11 - y) >= piece) {
                for (int i = 0; i < piece; i++) {
                    Button b = (Button) findViewById(nextIDs(v, code, i, orientation, y, x));
                    ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
                    int colorId = buttonColor.getColor();
                    if (colorId == Color.RED) {
                        legit = 0;
                    }
                }
            }
            else
                legit = 0;
        }
        return legit;
    }

    public int nextIDs(View v, int code, int i, int orientation, int y, char x){
        if(orientation == 0) {
            int code2 = code + i;
            String id = Character.toString((char) code2);
            id = id + "_" + y;
            return getResources().getIdentifier(id, "id", getPackageName());
        }
        else{
            String id = Character.toString(x) + "_" + (y + i);
            return getResources().getIdentifier(id, "id", getPackageName());
        }
    }

    public char c1(View v){
        String buttonID = getResources().getResourceName(v.getId());
        String bID = buttonID.substring(buttonID.lastIndexOf("/") + 1);
        String[] coordinates = bID.split("_");
        return coordinates[0].charAt(0);
    }

    public int c2(View v){
        String buttonID = getResources().getResourceName(v.getId());
        String bID = buttonID.substring(buttonID.lastIndexOf("/") + 1);
        String[] coordinates = bID.split("_");
        return Integer.parseInt(coordinates[1]);
    }

    public void topText(){
        TextView tText = (TextView)findViewById(R.id.top);
        Intent intent = getIntent();
        player = intent.getIntExtra("player", 0);
        StringBuffer theText = new StringBuffer();
        theText.append("  Player " + player + " : ");
        if(piece == 5){
           theText.append("Aircraft Carrier (5)");
        }
        else if(piece == 4){
            theText.append("Battleship (4)");
        }
        else if(piece == 3){
            if(three == 0)
                theText.append("Submarine (3)");
            else
                theText.append("Cruiser (3)");
        }
        else if(piece == 2){
            theText.append("Destroyer (2)");
        }
        else{
            setGrid(player);
        }
        tText.setText(theText);
    }
    public void setGrid(int player){
        if(player == 1){
            for(int i = 0; i < 10; i++){
                int code = 97 + i;
                String id0 = Character.toString((char) code);
                id0 = id0 + "_";
                for(int j = 0; j < 10; j++){
                    String id = id0 + (j+1);
                    Button b = (Button)findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
                    int colorId = buttonColor.getColor();
                    if (colorId == Color.RED) {
                        Grids.p1Board[j][i] = 1;
                    }
                }
            }
        }

        if(player == 2){
            for(int i = 0; i < 10; i++){
                int code = 97 + i;
                String id0 = Character.toString((char) code);
                id0 = id0 + "_";
                for(int j = 0; j < 10; j++){
                    String id = id0 + (j+1);
                    Button b = (Button)findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    ColorDrawable buttonColor = (ColorDrawable) b.getBackground();
                    int colorId = buttonColor.getColor();
                    if (colorId == Color.RED) {
                        Grids.p2Board[j][i] = 1;
                    }
                }
            }
        }
    }


    public void playing(){
        ImageView b = (ImageView)findViewById(R.id.boats);
        b.setVisibility(View.GONE);
        ImageButton c = (ImageButton)findViewById(R.id.rotate);
        c.setVisibility(View.GONE);
        TextView d = (TextView)findViewById(R.id.textView);
        d.setVisibility(View.GONE);
        loadGrid();

    }

    public void loadGrid(){
        if(player == 3){
            for(int i = 0; i < 10; i++){
                int code = 97 + i;
                String id0 = Character.toString((char) code);
                id0 = id0 + "_";
                for(int j = 0; j < 10; j++){
                    String id = id0 + (j+1);
                    Button b = (Button)findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    if (Grids.p2Board[j][i] == 2) {
                        b.setBackgroundColor(Color.BLACK);
                    }
                    else if(Grids.p2Board[j][i] == 3){
                        b.setBackgroundColor(Color.GREEN);
                    }
                }
            }
        }

        if(player == 4){
            for(int i = 0; i < 10; i++){
                int code = 97 + i;
                String id0 = Character.toString((char) code);
                id0 = id0 + "_";
                for(int j = 0; j < 10; j++){
                    String id = id0 + (j+1);
                    Button b = (Button)findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    if (Grids.p1Board[j][i] == 2) {
                        b.setBackgroundColor(Color.BLACK);
                    }
                    else if(Grids.p1Board[j][i] == 3){
                        b.setBackgroundColor(Color.GREEN);
                    }

                }
            }
        }

    }

    /*
    public void idk() {
        Intent intent = getIntent();
        String lol = intent.getStringExtra("mode");
        Toast.makeText(Placement.this, lol, Toast.LENGTH_SHORT).show();
    }
    */
}

