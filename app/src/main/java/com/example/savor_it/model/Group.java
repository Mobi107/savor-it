package com.example.savor_it.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String name;
    private String adminId;
    private List<User> userList;
    private List<Recipe> recipeList;

    public Group(String name, String adminId) {
        this.name = name;
        this.adminId = adminId;
        userList = new ArrayList<>();
        recipeList = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
