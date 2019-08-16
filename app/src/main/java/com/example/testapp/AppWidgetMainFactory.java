package com.example.testapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class AppWidgetMainFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private int appWidgetID;
    private List<NameData> nameDataList = new ArrayList<>();
    private DatabaseHandler databaseHandler;

    public AppWidgetMainFactory(Context context, Intent intent) {
        this.context = context;
        appWidgetID = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }
    @Override
    public void onCreate() {
        databaseHandler = new DatabaseHandler(context);
    }

    @Override
    public void onDataSetChanged() {
        nameDataList = databaseHandler.allNameData();
    }

    @Override
    public void onDestroy() {
        databaseHandler.close();
    }

    @Override
    public int getCount() {
        return nameDataList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.show_data_layout);
        remoteViews.setTextViewText(R.id.show_name, nameDataList.get(position).getName());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
