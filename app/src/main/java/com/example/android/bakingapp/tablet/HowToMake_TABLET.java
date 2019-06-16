package com.example.android.bakingapp.tablet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.WidgetService;
import com.example.android.bakingapp.adapters.AdapterOfIngredients;
import com.example.android.bakingapp.adapters.AdapterOfSteps;
import com.example.android.bakingapp.adapters.RecyclerAdapter;
import com.example.android.bakingapp.getdata.Recipe;
import com.example.android.bakingapp.phone.HowToMake_PHONE;
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
    public static TextView descriptionTextView;
    private static RecyclerView recyclerViewOfIngredients;
    public static int originHeight;
    public static ViewGroup.LayoutParams params;
    private Button buttonAddIngredientsToWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_make__tablet);
        descriptionTextView = findViewById(R.id.text_of_description);
        recyclerViewOfIngredients = findViewById(R.id.recycler_view_ingredients);
        buttonAddIngredientsToWidget = findViewById(R.id.add_to_widget);
        buttonAddIngredientsToWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIngredientsToWidget();
            }
        });

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
        try {
            if (AdapterOfSteps.isPlayerThere) {
                AdapterOfSteps.player.stop();
            }
            AdapterOfSteps.noVideoText.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            AdapterOfSteps.playerView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        recyclerViewOfIngredients.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        descriptionTextView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));

    }

    private void setIngredientsToWidget(){
        HowToMake_PHONE.ingredients = "";
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            HowToMake_PHONE.ingredients +=recipe.getIngredients().get(i).getIngredient() + " - " + recipe.getIngredients().get(i).getQuantity() + " " + recipe.getIngredients().get(i).getMeasure()+"\n";
        }
        WidgetService.updateWidget(this);
    }



}
