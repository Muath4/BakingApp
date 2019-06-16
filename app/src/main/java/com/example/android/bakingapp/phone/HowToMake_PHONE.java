package com.example.android.bakingapp.phone;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.WidgetProvider;
import com.example.android.bakingapp.WidgetService;
import com.example.android.bakingapp.adapters.AdapterOfIngredients;
import com.example.android.bakingapp.adapters.AdapterOfSteps;
import com.example.android.bakingapp.adapters.RecyclerAdapter;
import com.example.android.bakingapp.getdata.Recipe;

public class HowToMake_PHONE extends AppCompatActivity {
    private RecyclerView recyclerViewOfSteps;
    private RecyclerView recyclerViewOfIngredients;
    private Recipe recipe;
    private AdapterOfSteps adapterOfSteps;
    private AdapterOfIngredients adapterOfIngredients;
    private TextView ingredientsTextView;
    public static boolean isIngredientsShow = false;
    private int originHight;
    private TextView stepsTextView;
    public static String ingredients;
    private Button buttonAddIngredientsToWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_make__phone);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.notificationBar));
        recipe = getIntent().getExtras().getParcelable(RecyclerAdapter.KEY_RECIPE);
        getSupportActionBar().setTitle(recipe.getName());

        recyclerViewOfSteps = findViewById(R.id.recyclerview_detail);
        adapterOfSteps = new AdapterOfSteps(this, recipe);
        recyclerViewOfSteps.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewOfSteps.setAdapter(adapterOfSteps);
        buttonAddIngredientsToWidget = findViewById(R.id.add_to_widget);

        buttonAddIngredientsToWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIngredientsToWidget();
            }
        });

        recyclerViewOfIngredients = findViewById(R.id.recycler_view_ingredients);
        recyclerViewOfIngredients.setLayoutManager(new GridLayoutManager(this, 2));
        adapterOfIngredients = new AdapterOfIngredients(this, recipe);
        final ViewGroup.LayoutParams params = recyclerViewOfIngredients.getLayoutParams();
        originHight = params.height;
        ingredientsTextView = findViewById(R.id.ingredients_text_view);
        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isIngredientsShow) {
                    isIngredientsShow = false;
                    //recyclerViewOfSteps.setVisibility(View.VISIBLE);
                    ingredientsTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    params.height = 0;
                    recyclerViewOfIngredients.setLayoutParams(params);
                } else {
                    ingredientsTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.notificationBar));
                    params.height = originHight;
                    recyclerViewOfIngredients.setLayoutParams(params);
                    isIngredientsShow = true;
                    recyclerViewOfIngredients.setAdapter(adapterOfIngredients);
                    //***Log.i("****",AdapterOfIngredients.ingredients);
                    //recyclerViewOfSteps.setVisibility(View.INVISIBLE);

                }

            }
        });

        stepsTextView = findViewById(R.id.steps_text_view);
        stepsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isIngredientsShow) {
                    ingredientsTextView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    isIngredientsShow = false;
                    recyclerViewOfSteps.setVisibility(View.VISIBLE);

                    params.height = 0;
                    recyclerViewOfIngredients.setLayoutParams(params);
                }
            }
        });
    }


    private void setIngredientsToWidget(){
        ingredients = "";
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            ingredients +=recipe.getIngredients().get(i).getIngredient() + " - " + recipe.getIngredients().get(i).getQuantity() + " " + recipe.getIngredients().get(i).getMeasure()+"\n";
        }
        WidgetService.updateWidget(this);
    }


}
