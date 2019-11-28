package com.example.savor_it.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savor_it.R;

import java.util.ArrayList;

public class UploadFragment extends Fragment {

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_upload, container, false);

        et = (EditText) root.findViewById(R.id.addIngredient);
        step = (EditText) root.findViewById(R.id.addStep);
        add_btn = (Button) root.findViewById(R.id.add_btn);
        second_add_btn = (Button) root.findViewById(R.id.add_step_btn);
        listView = (ListView) root.findViewById(R.id.listView);
        secondListView = (ListView) root.findViewById(R.id.add_step_list_view);

        ingredients = new ArrayList<>();
        ingredientsAdapter = new ArrayAdapter<>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(ingredientsAdapter);

        steps = new ArrayList<>();
        stepsAdapter = new ArrayAdapter<>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, steps);
        secondListView.setAdapter(stepsAdapter);
        onAddButtonClick();

        return root;
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
