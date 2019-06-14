package com.example.android.bakingapp.getdata;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingapp.MainActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.RecyclerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class RecipesData {
    private Retrofit retrofit;
    private Activity activity;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    public RecipesData(Context context) {
        activity = (Activity) context;
    }

    public void getRecipe() {

        if(!isConnect()){
            Toast.makeText(activity,activity.getString(R.string.noConnectionToInternet),Toast.LENGTH_LONG).show();
            return;
        }
        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        Services services = retrofit.create(Services.class);
        Call<ArrayList<Recipe>> call = services.getRecipes();

        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {

                if (response.isSuccessful()) {
                    final ArrayList<Recipe> recipeArrayList = response.body();
                    connectRecyclerWithAdapter(recipeArrayList);

                } else {
                    Toast.makeText(activity,String.valueOf(response.errorBody()),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Toast.makeText(activity,activity.getString(R.string.noInternet),Toast.LENGTH_LONG).show();
                Log.i("********F", "onFailure -- " + t.getMessage());
            }
        });

    }

    private void connectRecyclerWithAdapter(ArrayList<Recipe> r) {
        recyclerView = activity.findViewById(R.id.main_recycler_id);
        if(MainActivity.isPhone)
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(activity,3));

        adapter = new RecyclerAdapter(activity, r);
        recyclerView.setAdapter(adapter);

    }


    private boolean isConnect() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
