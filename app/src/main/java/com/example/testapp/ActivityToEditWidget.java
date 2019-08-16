package com.example.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ActivityToEditWidget extends AppCompatActivity implements DataPass {

    private Input_Dialog input_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_edit_widget);

        input_dialog = new Input_Dialog();
        input_dialog.setListener(this);
        input_dialog.show(getSupportFragmentManager(), "Input");
    }

    @Override
    public void updateAdapter(NameData nameData) {
        ActivityToEditWidget activityToEditWidget = this;
        activityToEditWidget.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NewAppWidget.sendRefreshBroadcast(ActivityToEditWidget.this);
                AppWidgetMain.sendRefreshBroadcast(ActivityToEditWidget.this);
            }
        });
    }

    @Override
    public void onDestroyCalled() {
        finish();
    }
}
