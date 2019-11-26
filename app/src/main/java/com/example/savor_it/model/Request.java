package com.example.savor_it.model;

public class Request {
    private String senderId;
    private String userId;
    private String recipeName;
    private String status;

    public Request(String senderId, String userId, String recipeName) {
        this.senderId = senderId;
        this.userId = userId;
        this.recipeName = recipeName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
