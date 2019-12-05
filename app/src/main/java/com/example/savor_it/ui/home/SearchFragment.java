package com.example.savor_it.ui.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savor_it.MainHomeActivity;
import com.example.savor_it.R;
import com.example.savor_it.model.Recipe;

import java.util.ArrayList;
import java.util.List;

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

    List<Recipe> allRecipesList;

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        this.context = getContext();
        //populate Gridview
        gridView = (GridView) root.findViewById(R.id.grid_view);

        allRecipesList = new ArrayList<>();
        allRecipesList.add(new Recipe(1, "a", "Mom", "Fried Chicken",  MainHomeActivity.getUriToResource(context,R.drawable.friedchicken)));
        allRecipesList.add(new Recipe(2, "a", "Mom", "Veg Rice",  MainHomeActivity.getUriToResource(context,R.drawable.vegrice)));
        allRecipesList.add(new Recipe(3, "a", "Mom", "Broccoli Soup",  MainHomeActivity.getUriToResource(context,R.drawable.broccolisoup)));
        allRecipesList.add(new Recipe(4, "a", "Mom", "Meatballs",  MainHomeActivity.getUriToResource(context,R.drawable.meatballs)));

        //option 1 kept crashing
        GridAdapter gridAdapter = new GridAdapter(context, allRecipesList);
        gridView.setAdapter(gridAdapter);



        return root;
    }
}
