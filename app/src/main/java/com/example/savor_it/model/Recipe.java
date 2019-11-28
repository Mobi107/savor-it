package com.example.savor_it.model;

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String ownerId;
    private String ownerName;
    private List<String> groupIds;
    private String title;
    private String photo;
    private List<RecipeDetails> detailsList;


    public Recipe() {}

    public Recipe(FirebaseUser user, String title) {
        this.ownerId = user.getUid();
        this.ownerName = user.getDisplayName();
        if (TextUtils.isEmpty(this.ownerName)) {
            this.ownerName = user.getEmail();
        }

        this.title = title;
        detailsList = new ArrayList<>();
        groupIds = new ArrayList<>();
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<RecipeDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<RecipeDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public void addRecipeDetails(RecipeDetails recipeDetails) {
        this.detailsList.add(recipeDetails);
    }
}
