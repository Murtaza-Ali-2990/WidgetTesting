package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    NameData nameData = new NameData();
    Context context;
    Intent intent;

    DatabaseHandler databaseHandler;

    private void getData() {
        databaseHandler = new DatabaseHandler(context);

        nameData = databaseHandler.returnData();
    }

    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        getData();
    }

    @Override
    public void onDataSetChanged() {
        getData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public RemoteViews getViewsAt() {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        remoteViews.setTextViewText(R.id.appwidget_text, nameData.getName());

        return remoteViews;
    }
}
