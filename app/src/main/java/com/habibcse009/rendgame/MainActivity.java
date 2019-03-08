package com.habibcse009.rendgame;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnReset;
    TextView txtHighScore, txtScore;
    //declere shared preferences object
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private final String SP_GAME = "com.habibcse009.rendgame.game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHighScore = findViewById(R.id.txt_high_score);
        txtScore = findViewById(R.id.txt_score);
        btnPlay = findViewById(R.id.btn_play);
        btnReset = findViewById(R.id.btn_reset);
        //Initialize font
        Typeface tf = Typeface.createFromAsset(getAssets(), "Milkshake.ttf");
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.otf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "aqua.ttf");
        btnReset.setTypeface(tf2);
        btnPlay.setTypeface(tf2);
        txtHighScore.setTypeface(tf2);
        txtScore.setTypeface(tf1);


        //for create sp file
        sp = getSharedPreferences(SP_GAME, MODE_PRIVATE);
        editor = sp.edit();
        //get value from shared preference file
        int hight_score = sp.getInt("high_score",0);
        txtHighScore.setText("High Score: "+hight_score);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int score=random.nextInt(2000);
                txtScore.setText(String.valueOf(score));
                //or txtScore.setText("Score: "+score);
                int getSaveScore=sp.getInt("high_score",0);
                if(score>getSaveScore){
                    txtHighScore.setText("High Score: "+score);
                    editor.putInt("high_score",score);
                    editor.apply();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               txtHighScore.setText("High Score: 0");
               txtScore.setText(""+0);
                editor.putInt("high_score",0);
                editor.apply();
            }
        });
    }
}
