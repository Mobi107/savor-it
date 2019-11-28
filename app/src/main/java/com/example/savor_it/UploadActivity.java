package com.example.savor_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {

    EditText et;
    EditText step;
    Button add_btn;
    Button second_add_btn;
    ListView listView;
    ListView secondListView;
    ArrayAdapter<String> ingredientsAdapter;
    ArrayList<String> ingredients;
    ArrayAdapter<String> stepsAdapter;
    ArrayList<String> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upload);

        et = (EditText) findViewById(R.id.addIngredient);
        step = (EditText) findViewById(R.id.addStep);
        add_btn = (Button) findViewById(R.id.add_btn);
        second_add_btn = (Button) findViewById(R.id.add_step_btn);
        listView = (ListView) findViewById(R.id.listView);
        secondListView = (ListView) findViewById(R.id.add_step_list_view);

        ingredients = new ArrayList<>();
        ingredientsAdapter = new ArrayAdapter<>(UploadActivity.this, android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(ingredientsAdapter);

        steps = new ArrayList<>();
        stepsAdapter = new ArrayAdapter<>(UploadActivity.this, android.R.layout.simple_list_item_1, steps);
        secondListView.setAdapter(stepsAdapter);
        onAddButtonClick();
    }


    public void onAddButtonClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = et.getText().toString();
                ingredients.add(result);
                ingredientsAdapter.notifyDataSetChanged();
            }
        });

        second_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = step.getText().toString();
                steps.add(result);
                stepsAdapter.notifyDataSetChanged();
            }
        });
    }
}
