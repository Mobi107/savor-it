package com.example.savor_it.model;

import java.util.List;

public class User {
    private String userId;
    private String name;
    private String avatarUrl;
    private List<Group> myGroups;
    private List<Recipe> myRecipes;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Group> getMyGroups() {
        return myGroups;
    }

    public void setMyGroups(List<Group> myGroups) {
        this.myGroups = myGroups;
    }

    public List<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public void setMyRecipes(List<Recipe> myRecipes) {
        this.myRecipes = myRecipes;
    }
}
