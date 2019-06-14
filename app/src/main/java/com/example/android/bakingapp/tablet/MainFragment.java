package com.example.android.bakingapp.tablet;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.AdapterOfIngredients;
import com.example.android.bakingapp.adapters.AdapterOfSteps;
import com.example.android.bakingapp.adapters.RecyclerAdapter;
import com.example.android.bakingapp.getdata.Recipe;

public class MainFragment extends Fragment {
    private Recipe recipe;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private Activity activity;
    private AdapterOfSteps adapterOfSteps;
    private TextView ingredientsTextView;
    Recipe.Step step;
    private AdapterOfIngredients adapterOfIngredients;
    private RecyclerView recyclerViewOfIngredients;
    public static Boolean isGetOriginalHeight = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tablet_fragment, container, false);


        recipe = activity.getIntent().getExtras().getParcelable(RecyclerAdapter.KEY_RECIPE);
        ingredientsTextView = rootView.findViewById(R.id.ingredients_text_view);
        ingredientsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerViewOfIngredients = activity.findViewById(R.id.recycler_view_ingredients);
                Log.i("*****", String.valueOf(recipe));
                adapterOfIngredients = new AdapterOfIngredients(activity, recipe);
                recyclerViewOfIngredients.setLayoutManager(new GridLayoutManager(activity, 3));
                recyclerViewOfIngredients.setAdapter(adapterOfIngredients);
                HowToMake_TABLET.params = recyclerViewOfIngredients.getLayoutParams();
                if (!isGetOriginalHeight) {
                    HowToMake_TABLET.originHeight = HowToMake_TABLET.params.height;
                    isGetOriginalHeight = true;
                }
                Log.i("********", String.valueOf(HowToMake_TABLET.params.height));
                HowToMake_TABLET.setRecyclerToSecScreen();

            }
        });

        recyclerView = rootView.findViewById(R.id.main_recycler_id);
        adapterOfSteps = new AdapterOfSteps(activity, recipe);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapterOfSteps);


        return rootView;
    }


}
