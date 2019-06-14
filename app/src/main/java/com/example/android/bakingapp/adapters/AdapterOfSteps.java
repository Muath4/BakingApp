package com.example.android.bakingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.MainActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.getdata.Recipe;
import com.example.android.bakingapp.phone.StepDetail_PHONE;
import com.example.android.bakingapp.tablet.HowToMake_TABLET;


public class AdapterOfSteps extends RecyclerView.Adapter<AdapterOfSteps.ViewHolder> {

    private Context context;
    private Recipe recipe;
    public static final String KEY_OF_INTENT = "keyOfClickedRecipe";
    Activity activity;

    public AdapterOfSteps(Context context, Recipe recipe){
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
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.textView.setText(recipe.getSteps().get(position).getShortDescription());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.isPhone){
                Intent intent = new Intent(context, StepDetail_PHONE.class);
                intent.putExtra(KEY_OF_INTENT,recipe.getSteps().get(position));
                context.startActivity(intent);
            }
            else{

                HowToMake_TABLET.setTextToSecScreen(recipe.getSteps().get(position).getDescription());
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
}
