package com.example.savor_it.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

//import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recipe implements Parcelable {

    private int mId;
    private String ownerId;
    private String ownerName;
    private List<String> groupIds;
    private String title;
    private String photoURI;
    //Default for now, get rid of later
    private List<String> ingredients = Arrays.asList("Fruit", "Sugar");
    private List<String> steps = Arrays.asList("Cook food", "Get food");
    private String audioFilename;


    public Recipe() {}



    public Recipe(int mId, String ownerId, String ownerName, String title, String photoUri) {
        this.mId = mId;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.title = title;
        this.photoURI = photoUri;
    }

//    public Recipe(FirebaseUser user, String title) {
//        this.ownerId = user.getUid();
//        this.ownerName = user.getDisplayName();
//        if (TextUtils.isEmpty(this.ownerName)) {
//            this.ownerName = user.getEmail();
//        }
//
//        this.title = title;
//        groupIds = new ArrayList<>();
//    }


    protected Recipe(Parcel in) {
        mId = in.readInt();
        ownerId = in.readString();
        ownerName = in.readString();
        groupIds = in.createStringArrayList();
        title = in.readString();
        photoURI = in.readString();
        ingredients = in.createStringArrayList();
        steps = in.createStringArrayList();
        audioFilename = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

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


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public String getAudioFilename() {
        return audioFilename;
    }

    public void setAudioFilename(String audioFilename) {
        this.audioFilename = audioFilename;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(ownerId);
        dest.writeString(ownerName);
        dest.writeStringList(groupIds);
        dest.writeString(title);
        dest.writeString(photoURI);
        dest.writeStringList(ingredients);
        dest.writeStringList(steps);
        dest.writeString(audioFilename);
    }

    public static Uri getPhoto(Recipe recipe) {
        return Uri.parse(recipe.photoURI);
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public static Creator<Recipe> getCREATOR() {
        return CREATOR;
    }
}
