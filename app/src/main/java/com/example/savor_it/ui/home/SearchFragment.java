package com.example.savor_it.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savor_it.R;

public class SearchFragment extends Fragment {

    GridView gridView;

    //replace this hardcoded array with actual uploaded recipe titles
    String[] values = {
            "Fried Chicken",
            "Veg Rice",
            "Broccoli Soup",
            "Meatballs"
    };

    //replace this with actual images of recipes from drawable
    int[] images = {
            R.drawable.friedchicken,
            R.drawable.vegrice,
            R.drawable.broccolisoup,
            R.drawable.meatballs
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        //populate Gridview
        gridView = (GridView) root.findViewById(R.id.grid_view);

        //option 1 kept crashing
        GridAdapter gridAdapter = new GridAdapter(this.getActivity().getApplicationContext(), values, images);
        gridView.setAdapter(gridAdapter);



        return root;
    }
}
