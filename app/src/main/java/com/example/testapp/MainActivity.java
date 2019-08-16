package com.example.testapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataPass{

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<NameData> nameDataList = new ArrayList<>();
    private Input_Dialog input_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        nameDataList = databaseHandler.allNameData();

        adapter = new RecyclerAdapter(nameDataList);

        recyclerView = findViewById(R.id.recycler_view_for_name);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_dialog = new Input_Dialog();
                input_dialog.setListener(MainActivity.this);
                input_dialog.show(getSupportFragmentManager(), "Input");

            }
        });
    }

    @Override
    public void updateAdapter(NameData nameData) {

        nameDataList.add(nameData);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        final MainActivity mainActivity = this;
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "New Data added", Toast.LENGTH_LONG).show();
                NewAppWidget.sendRefreshBroadcast(MainActivity.this);
                AppWidgetMain.sendRefreshBroadcast(MainActivity.this);
            }
        });
    }

    @Override
    public void onDestroyCalled() {}

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
