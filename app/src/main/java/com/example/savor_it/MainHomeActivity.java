package com.example.savor_it;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.savor_it.model.Recipe;
import com.example.savor_it.ui.home.GridAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainHomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirestore = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_mainhome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_request, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static final Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException{
        Resources res = context.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" +res.getResourcePackageName(resId)
                +'/'+ res.getResourceTypeName(resId)
                + '/' +res.getResourceEntryName(resId)
        );
    }

    public void viewEventDetails(View view) {
        if (view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Recipe selectedEvent = (Recipe) selectedLayout.getTag();
            Log.i("Recipe", selectedEvent.toString());
            Intent detailsIntent = new Intent(this, RecipeDetailsActivity.class);
            detailsIntent.putExtra("SelectedRecipe", selectedEvent);
            startActivity(detailsIntent);
        }
    }

    public void uploadRecipe(View view) {
        if (view instanceof Button) {
            Button saveBtn = (Button) view;
            Recipe selectedRecipe = (Recipe) saveBtn.getTag();
            Log.i("Recipe", selectedRecipe.toString());
            mFirestore.collection("recipe").add(selectedRecipe);
            Intent detailsIntent = new Intent(this, RecipeDetailsActivity.class);
            detailsIntent.putExtra("SelectedRecipe", selectedRecipe);
            startActivity(detailsIntent);
        }
    }

    public void uploadMockRecipes(List<Recipe> recipeList) {
        for (Recipe recipe: recipeList) {
            mFirestore.collection("recipe").add(recipe);
        }
    }

    public void loadAllRecipes(final List<Recipe> recipes, final GridAdapter gridAdapter) {
        //asynchronously retrieve multiple documents
          final Task<QuerySnapshot> result =  mFirestore.collection("recipe").get();
          result.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                  List<DocumentSnapshot> documents = result.getResult().getDocuments();
                  for (DocumentSnapshot document : documents) {
                      recipes.add(document.toObject(Recipe.class));
                  }
                  gridAdapter.notifyDataSetChanged();
              }

          });

    }
}
