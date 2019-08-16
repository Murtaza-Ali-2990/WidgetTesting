package com.example.testapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class AppWidgetMainService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new AppWidgetMainFactory(this.getApplicationContext(), intent);
    }
}
