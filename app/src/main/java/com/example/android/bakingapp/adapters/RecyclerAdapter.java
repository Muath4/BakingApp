package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.MainActivity;
import com.example.android.bakingapp.phone.HowToMake_PHONE;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.getdata.Recipe;
import com.example.android.bakingapp.tablet.HowToMake_TABLET;

import java.util.ArrayList;

import static android.graphics.Color.WHITE;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Recipe> recipeArrayList;
    public static final String KEY_RECIPE = "keyOfRecipeOfIntent";

    public RecyclerAdapter(Context context, ArrayList<Recipe> recipeArrayList) {
        this.context = context;
        this.recipeArrayList = recipeArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {

        holder.textView.setText(recipeArrayList.get(position).getName());
        holder.textView.setTextColor(WHITE);

        if(!MainActivity.isPhone) {
            holder.textView.setPadding(5, 70, 5, 70);
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HowToMake_TABLET.class);
                    intent.putExtra(KEY_RECIPE, recipeArrayList.get(position));
                    context.startActivity(intent);
                }
            });
        }else {

            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HowToMake_PHONE.class);
                    intent.putExtra(KEY_RECIPE, recipeArrayList.get(position));
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_of_item);
        }

    }
}




