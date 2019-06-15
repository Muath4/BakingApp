package com.example.android.bakingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.MainActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.getdata.Recipe;
import com.example.android.bakingapp.phone.StepDetail_PHONE;
import com.example.android.bakingapp.tablet.HowToMake_TABLET;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class AdapterOfSteps extends RecyclerView.Adapter<AdapterOfSteps.ViewHolder> {

    private Context context;
    private Recipe recipe;
    public static final String KEY_OF_INTENT_OF_CLICKED_STEP = "keyOfClickedRecipe";
    public static final String KEY_OF_INTENT_RECIPE = "keyOfTheStepsOfRecipe";
    public static PlayerView playerView;
    public static SimpleExoPlayer player;
    //private Recipe.Step step;
    public static TextView noVideoText;
    public static Boolean isPlayerThere = false;

    Activity activity;

    public AdapterOfSteps(Context context, Recipe recipe) {
        this.context = context;
        this.recipe = recipe;
        activity = (Activity) context;
    }


    @NonNull
    @Override
    public AdapterOfSteps.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(recipe.getSteps().get(position).getShortDescription());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.isPhone) {
                    Intent intent = new Intent(context, StepDetail_PHONE.class);
                    intent.putExtra(KEY_OF_INTENT_RECIPE, recipe);
                    intent.putExtra(KEY_OF_INTENT_OF_CLICKED_STEP, recipe.getSteps().get(position));
                    context.startActivity(intent);
                } else {
                    HowToMake_TABLET.descriptionTextView.setVisibility(View.VISIBLE);
                    //HowToMake_TABLET.identifyTheStep(recipe.getSteps().get(position));
                    if(isPlayerThere){
                        player.stop();
                        isPlayerThere = false;
                    }

                    initializePlayer(recipe.getSteps().get(position));

                    HowToMake_TABLET.setDescriptionToSecScreen(recipe.getSteps().get(position).getDescription());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return recipe.getSteps().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_of_item);
        }

    }



    private void initializePlayer(Recipe.Step s) {
        playerView = activity.findViewById(R.id.player_view);
        noVideoText = activity.findViewById(R.id.no_video_to_show);
        playerView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        if (isConnect()) {
            if (!s.getVideoURL().equalsIgnoreCase("")) {
                playerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
                noVideoText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                settingThePlayer(s);
            } else {
                isPlayerThere = false;
                noVideoText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        } else {
            isPlayerThere = false;
            Toast.makeText(activity, activity.getString(R.string.noInternet), Toast.LENGTH_LONG).show();
        }
    }




    private void settingThePlayer(Recipe.Step s) {
        isPlayerThere = true;
        player = ExoPlayerFactory.newSimpleInstance(activity);
        player.setPlayWhenReady(true);

        playerView.setPlayer(player);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(activity,
                Util.getUserAgent(activity, activity.getString(R.string.app_name)));
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(s.getVideoURL()));
        player.prepare(videoSource);
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                playerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        });

    }


    private boolean isConnect() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
