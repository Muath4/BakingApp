package com.example.android.bakingapp.tablet;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.AdapterOfIngredients;
import com.example.android.bakingapp.adapters.RecyclerAdapter;
import com.example.android.bakingapp.getdata.Recipe;

import static com.example.android.bakingapp.tablet.MainFragment.isGetOriginalHeight;

public class HowToMake_TABLET extends AppCompatActivity {
    Recipe recipe;
    Recipe.Step step;
    private static TextView descriptionTextView;
    private static RecyclerView recyclerViewOfIngredients;
    private static AdapterOfIngredients adapterOfIngredients;
    public static int originHeight;
    public static ViewGroup.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_make__tablet);
        descriptionTextView = findViewById(R.id.text_of_description);
        recyclerViewOfIngredients = findViewById(R.id.recyclerview_ingredients);

        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.notificationBar));
        try {
            recipe = getIntent().getExtras().getParcelable(RecyclerAdapter.KEY_RECIPE);
            getSupportActionBar().setTitle(recipe.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setTextToSecScreen(String s) {
        if (isGetOriginalHeight)
            params.height = 0;
        recyclerViewOfIngredients.setLayoutParams(params);
        descriptionTextView.setText(s);
        descriptionTextView.setVisibility(View.VISIBLE);
    }

    public static void setRecyclerToSecScreen() {
        params.height = originHeight;
        recyclerViewOfIngredients.setLayoutParams(params);
        descriptionTextView.setText("");
        descriptionTextView.setVisibility(View.INVISIBLE);
    }


}
