package com.example.testapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    ListView listView;
    ListAdapter adapter;
    List<NameData> nameDataList;
    static NameData nameData;
    static DatabaseHandler databaseHandler;
    static Intent intent;
    static AppWidgetManager appWidgetManager;
    int[] appWIDs;
    Context context;

    static DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        this.intent = intent;


        Log.d("CALL", "On recieve");
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        databaseHandler = new DatabaseHandler(context);
        nameData = databaseHandler.returnData();
        // Construct the RemoteViews object
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        setRemoteAdapter(context, views);

        views.setPendingIntentTemplate(R.id.appwidget_text, pendingIntent);
        views.setTextViewText(R.id.appwidget_text, nameData.getName());

        Log.e("MSG", nameData.getName());

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d("CALL", "On update called");
        this.appWidgetManager = appWidgetManager;
        appWIDs = appWidgetIds;
        this.context = context;
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        databaseHandler = new DatabaseHandler(context);
        nameData =  new NameData();
        nameDataList = new ArrayList<>();

        nameDataList = databaseHandler.allNameData();
        adapter = new ArrayAdapter<>(context, R.layout.show_data_layout, nameDataList);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static void setRemoteAdapter(Context context,@NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.appwidget_text, new Intent(context, WidgetService.class));
    }
}

