package com.example.android.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.android.bakingapp.getdata.RecipesData;

public class MainActivity extends AppCompatActivity {
    RecipesData recipesData = new RecipesData(this);
    public static Boolean isPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Window window = this.getWindow();
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.notificationBar));
        if (findViewById(R.id.text_view_intro_tablet) == null) {//phone
                isPhone =true;
                recipesData.getRecipe();
            }
            else {
                isPhone = false;
                recipesData.getRecipe();
            }


    }
}



