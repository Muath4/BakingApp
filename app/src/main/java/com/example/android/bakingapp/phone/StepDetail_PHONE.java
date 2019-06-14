package com.example.android.bakingapp.phone;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.AdapterOfSteps;
import com.example.android.bakingapp.getdata.Recipe;
import com.google.android.exoplayer2.ui.PlayerView;

public class StepDetail_PHONE extends AppCompatActivity {

    LinearLayout linearLayout;
    TextView description;
    PlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail__phone);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.notificationBar));
        //playerView = findViewById(R.id.player_view);
        linearLayout = findViewById(R.id.linear_layout_step_detail_phone);
        Recipe.Step step = (Recipe.Step) getIntent().getExtras().getSerializable(AdapterOfSteps.KEY_OF_INTENT);
        getSupportActionBar().setTitle(step.getShortDescription());
        description = findViewById(R.id.text_of_description);
        description.setText(step.getDescription());
    }
}
