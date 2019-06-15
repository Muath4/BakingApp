package com.example.android.bakingapp.tablet;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.AdapterOfIngredients;
import com.example.android.bakingapp.adapters.AdapterOfSteps;
import com.example.android.bakingapp.adapters.RecyclerAdapter;
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

public class HowToMake_TABLET extends AppCompatActivity {
    Recipe recipe;
    private static Recipe.Step step;
    public static TextView descriptionTextView;
    private static RecyclerView recyclerViewOfIngredients;
    private static AdapterOfIngredients adapterOfIngredients;
    public static int originHeight;
    public static ViewGroup.LayoutParams params;
    static PlayerView playerView;
    static SimpleExoPlayer player;
    //private Recipe.Step step;
    private TextView noVideoText;
    private static Boolean isPlayerThere = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_make__tablet);
        descriptionTextView = findViewById(R.id.text_of_description);
        recyclerViewOfIngredients = findViewById(R.id.recycler_view_ingredients);

        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.notificationBar));
        try {
            recipe = getIntent().getExtras().getParcelable(RecyclerAdapter.KEY_RECIPE);
            getSupportActionBar().setTitle(recipe.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void setDescriptionToSecScreen(String s) {
//        if (isGetOriginalHeight)
//            params.height = 0;
        recyclerViewOfIngredients.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        descriptionTextView.setText(s);
        descriptionTextView.setPadding(7,7,7,7);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ((LinearLayout.LayoutParams) layoutParams).setMargins(10,10,10,10);
        descriptionTextView.setLayoutParams(layoutParams);

    }

    static void setRecyclerOfIngredientsToSecScreen() {
        params.height = originHeight;
        recyclerViewOfIngredients.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        descriptionTextView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        AdapterOfSteps.noVideoText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        AdapterOfSteps.playerView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        if (AdapterOfSteps.isPlayerThere) {
            AdapterOfSteps.playerView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            AdapterOfSteps.player.stop();
        }


    }


//    private void initializePlayer() {

//        playerView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
//        if (isConnect()) {
//            if (!step.getVideoURL().equalsIgnoreCase("")) {
//                playerView.setVisibility(View.VISIBLE);
//                playerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                noVideoText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
//                settingThePlayer();
//            } else {
//                isPlayerThere = false;
//                noVideoText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            }
//        } else {
//            isPlayerThere = false;
//            Toast.makeText(this, getString(R.string.noInternet), Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void settingThePlayer() {
//        isPlayerThere = true;
//        player = ExoPlayerFactory.newSimpleInstance(this);
//        player.setPlayWhenReady(true);
//
//        playerView.setPlayer(player);
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, getString(R.string.app_name)));
//        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(step.getVideoURL()));
//        player.prepare(videoSource);
//        player.addListener(new Player.EventListener() {
//            @Override
//            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                playerView.setVisibility(View.VISIBLE);
//            }
//        });
//
//    }


    public static void identifyTheStep(Recipe.Step s) {
        step = s;

    }


    private boolean isConnect() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
