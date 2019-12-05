package com.example.savor_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.savor_it.model.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    private Recipe selectedRecipe;
    private TextView titleTextView;
    private ImageView recipePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Intent detailsIntent = getIntent();
        selectedRecipe = detailsIntent.getExtras().getParcelable("SelectedRecipe");
        titleTextView = this.findViewById(R.id.recipeDetailTitleTextView);
        titleTextView.setText(selectedRecipe.getTitle());
        recipePhoto = this.findViewById(R.id.recipeDetailsImageView);
        recipePhoto.setImageURI(selectedRecipe.getPhoto());
    }
}
