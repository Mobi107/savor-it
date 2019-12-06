package com.example.savor_it;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.savor_it.model.Recipe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe selectedRecipe;
    private TextView titleTextView;
    private ImageView recipePhoto;
    private ListView ingredientsListView;
    private ListView stepsListView;
    private ArrayAdapter<String> ingredientsAdapter;
    private ArrayList<String> ingredients;
    private ArrayAdapter<String> stepsAdapter;
    private ArrayList<String> steps;
    private ImageButton playButton;
    private ImageButton stopButton;
    private MediaPlayer mMediaPlayer;
    private SeekBar mSeekBar;
    private Timer mTimer;
    private static final String LOG_TAG = "Audio Record";
    private static String fileName = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Intent detailsIntent = getIntent();
        selectedRecipe = detailsIntent.getExtras().getParcelable("SelectedRecipe");
        titleTextView = this.findViewById(R.id.recipeDetailTitleTextView);
        titleTextView.setText(selectedRecipe.getTitle());
        recipePhoto = this.findViewById(R.id.recipeDetailsImageView);
        recipePhoto.setImageURI(selectedRecipe.getPhoto());

        ingredientsListView = (ListView) this.findViewById(R.id.ingredients_list);
        stepsListView = (ListView) this.findViewById(R.id.steps_list);
        ingredients = (ArrayList) selectedRecipe.getIngredients();
        ingredientsAdapter = new ArrayAdapter<>(this.getApplicationContext(), android.R.layout.simple_list_item_1, ingredients);
        ingredientsListView.setAdapter(ingredientsAdapter);

        steps = (ArrayList) selectedRecipe.getSteps();
        stepsAdapter = new ArrayAdapter<>(this.getApplicationContext(), android.R.layout.simple_list_item_1, steps);
        stepsListView.setAdapter(stepsAdapter);
        mSeekBar = new SeekBar(this.getApplicationContext());
        
        playButton = (ImageButton) findViewById(R.id.play_button);
        stopButton = (ImageButton) findViewById(R.id.stop_button);

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlaying();
            }
        });

    }

    private void startPlaying() {
        mMediaPlayer = new MediaPlayer();
        String filename = selectedRecipe.getAudioFilename();
        try {
            mMediaPlayer.setDataSource(filename);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            setSeekBar();
        } catch (IOException e) {
            System.out.println("Error" + e);
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    private void setSeekBar() {
        final int duration = mMediaPlayer.getDuration();
        final int amountToUpdate = duration / 100;
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!(amountToUpdate * mSeekBar.getProgress() >= duration)) {
                            int p = mSeekBar.getProgress();
                            p += 1;
                            mSeekBar.setProgress(p);
                        }
                    }
                });
            };
        }, amountToUpdate);
    }
}
