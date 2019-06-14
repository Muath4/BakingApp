package com.example.android.bakingapp.phone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.AdapterOfSteps;
import com.example.android.bakingapp.getdata.Recipe;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetail_PHONE extends AppCompatActivity {

    TextView description;
    PlayerView playerView;
    SimpleExoPlayer player;
    private Recipe.Step step;
    private TextView noVideoText;
    private Boolean isPlayerThere = false;
    private TextView nextButton;
    private TextView previousButton;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail__phone);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.notificationBar));


        recipe = getIntent().getParcelableExtra(AdapterOfSteps.KEY_OF_INTENT_RECIPE);
        step = (Recipe.Step) getIntent().getExtras().getSerializable(AdapterOfSteps.KEY_OF_INTENT_OF_CLICKED_STEP);
        settingDescription();


        setNextAndPreviousButton();

        initializePlayer();


    }

    @Override
    public void onStop() {
        super.onStop();
        if (isPlayerThere)
            player.stop();
    }

    private void initializePlayer() {
        playerView = findViewById(R.id.player_view);
        noVideoText = findViewById(R.id.no_video_to_show);
        playerView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        if (isConnect()) {
            if (!step.getVideoURL().equalsIgnoreCase("")) {
                playerView.setVisibility(View.VISIBLE);
                playerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                noVideoText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                settingThePlayer();
            } else {
                isPlayerThere = false;
                noVideoText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        } else {
            isPlayerThere = false;
            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }

    private void settingThePlayer() {
        isPlayerThere = true;
        player = ExoPlayerFactory.newSimpleInstance(this);
        player.setPlayWhenReady(true);

        playerView.setPlayer(player);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(step.getVideoURL()));
        player.prepare(videoSource);
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                playerView.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean isConnect() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    private void setNextAndPreviousButton() {

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Recipe.Step nextStep = recipe.getSteps().get(step.getId().intValue() + 1);
                    Log.i("*****", nextStep.getShortDescription());

                    step = nextStep;
                    if (isPlayerThere)
                        player.stop();
                    initializePlayer();
                    settingDescription();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.lastStep), Toast.LENGTH_LONG).show();
                }
            }

        });

        previousButton = findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    Recipe.Step previousStep = recipe.getSteps().get(step.getId().intValue() - 1);
                    Log.i("*****", previousStep.getShortDescription());
                    step = previousStep;
                    if (isPlayerThere)
                        player.stop();
                    initializePlayer();
                    settingDescription();
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), getString(R.string.firstStep), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void settingDescription() {
        description = findViewById(R.id.text_of_description);
        getSupportActionBar().setTitle(step.getShortDescription());
        description.setText(step.getDescription());
    }

}
