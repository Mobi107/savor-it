package com.example.savor_it.ui.request;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.savor_it.R;

import java.util.Arrays;

public class RequestFragment extends Fragment {

    private RequestViewModel requestViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        requestViewModel =
                ViewModelProviders.of(this).get(RequestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_request, container, false);
        Spinner spinner = root.findViewById(R.id.spinner);
        // Iterate through all users. This will still retrieve users in batches,

        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Arrays.asList("choose a person to request", "Mom", "Dad", "Aunt")));
        return root;
    }
}