package com.example.savor_it.ui.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savor_it.R;
import com.example.savor_it.model.Request;
import com.example.savor_it.model.User;

import java.util.ArrayList;
import java.util.List;

public class RequestFragment extends Fragment {

    private Spinner spinnerUserName;
    private EditText editText;
    private Button requestButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_request, container, false);
        spinnerUserName = root.findViewById(R.id.userNameSpinner);
        editText = root.findViewById(R.id.recipeNameRequesteditText);
        requestButton = root.findViewById(R.id.requestButton);
        final List<String> username = new ArrayList<>();
        username.add("Mom");
        username.add("Aunt");
        spinnerUserName.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, username));
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName = editText.getText().toString();

                if (recipeName.length() != 0 && spinnerUserName.getSelectedItemPosition() != 0) {
                    String recipentId = (String) spinnerUserName.getSelectedItem();
                    Request request = new Request("mom", recipentId, recipeName);
                }
            }
        });
        return root;
    }



}