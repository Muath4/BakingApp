package com.example.android.bakingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.getdata.Recipe;
import com.example.android.bakingapp.phone.HowToMake_PHONE;

public class AdapterOfIngredients extends RecyclerView.Adapter<AdapterOfIngredients.ViewHolder> {

    private Recipe recipe;
    public static final String KEY_OF_INTENT = "keyOfClickedRecipe";
    Activity activity;

    public AdapterOfIngredients(Context context, Recipe recipe) {
        this.recipe = recipe;
        activity = (Activity) context;
    }


    @NonNull
    @Override
    public AdapterOfIngredients.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

            holder.textView.setText(recipe.getIngredients().get(position).getIngredient() + "\n" + recipe.getIngredients().get(position).getQuantity() + " " + recipe.getIngredients().get(position).getMeasure());

    }

    @Override
    public int getItemCount() {
        return recipe.getIngredients().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_of_item);
        }

    }
}
