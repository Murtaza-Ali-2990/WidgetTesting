package com.example.testapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private List<NameData> nameDataList;

    RecyclerAdapter(List<NameData> nameDataList) {
        this.nameDataList = nameDataList;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView name;

        private MyHolder(View view) {
            super(view);
            name = view.findViewById(R.id.show_name);
        }

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_data_layout, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        NameData nameData = nameDataList.get(i);
        myHolder.name.setText(nameData.getName());

    }

    @Override
    public int getItemCount() {
        return nameDataList.size();
    }
}
