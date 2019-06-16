package com.example.android.bakingapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class WidgetService extends IntentService {

    public static final String UPDATE_WIDGETS ="updateTheWidget";

    public WidgetService(){
        super("WidgetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        setUpdate();


    }

    public static void updateWidget(Context context){
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(UPDATE_WIDGETS);
        context.startService(intent);
    }
    private void setUpdate(){
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
        //Trigger data update to handle the GridView widgets and force a data refresh
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.add_to_widget);
        //Now update all widgets
        WidgetProvider.updateWidgets(this, appWidgetManager,appWidgetIds);
    }
}
