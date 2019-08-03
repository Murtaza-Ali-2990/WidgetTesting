package com.example.testapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

interface DataPass {

    void updateAdapter(NameData nameData);
}

public class Input_Dialog extends DialogFragment {

    private EditText nameText;
    private Button submitButton;
    private NameData nameData;
    private DatabaseHandler databaseHandler;
    private DataPass context;


    public Input_Dialog() {
    }

    public void setListener(DataPass context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.get_name_text, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameText = view.findViewById(R.id.name_data);
        submitButton = view.findViewById(R.id.submit_button);
        nameData = new NameData();
        databaseHandler = new DatabaseHandler(getActivity());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = nameText.getText().toString().trim();
                nameData.setName(nameString);
                databaseHandler.addNameData(nameData);

                setDataListener(context);

                getDialog().dismiss();
            }
        });
    }

    public void setDataListener(DataPass context) {
        context.updateAdapter(nameData);
    }

}