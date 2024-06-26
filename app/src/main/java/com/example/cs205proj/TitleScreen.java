package com.example.cs205proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;

public class TitleScreen extends AppCompatActivity {

    MediaPlayer titleMusic;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);
        Button buttonStart = findViewById(R.id.button_start);
        titleMusic = MediaPlayer.create(TitleScreen.this, R.raw.title_music);
        titleMusic.setLooping(true);
        titleMusic.start();

//        delete db if needed
//        this.deleteDatabase("Game");

//      REQUIREMENT create / get data from sqlLite
        db = DatabaseHelper.getInstance(this, "Game");
        Typeface typeface = ResourcesCompat.getFont(this, R.font.space_normal);
        TextView highscore = findViewById(R.id.highscore);
        highscore.setTypeface(typeface);
        highscore.setText("Highscore: " + db.getLatestScore());

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleMusic.stop();
                Intent intent = new Intent(TitleScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        titleMusic.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop and release MediaPlayer resources
        if (titleMusic != null) {
            titleMusic.stop();
            titleMusic.release();
            titleMusic = null;
        }
    }
}