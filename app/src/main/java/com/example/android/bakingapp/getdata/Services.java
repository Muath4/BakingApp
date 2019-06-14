package com.example.android.bakingapp.getdata;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {

    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipes();
}
