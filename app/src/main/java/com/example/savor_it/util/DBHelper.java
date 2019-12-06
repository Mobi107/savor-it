package com.example.savor_it.util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    //TASK: DEFINE THE DATABASE VERSION AND NAME  (DATABASE CONTAINS MULTIPLE TABLES)
    static final String DATABASE_NAME = "SAVOR IT"; //remember change to public later
    private static final int DATABASE_VERSION = 1;

    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE USERS TABLE
    private static final String USERS_TABLE = "Users";
    private static final String USERS_KEY_FIELD_ID = "_id";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PHONE_NUMBER = "phone_number";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_IMAGE_NAME = "image_name";


    //TASK: DEFINE THE FIELDS (COLUMN NAMES) FOR THE LOGIN TABLE
    private static final String LOGIN_TABLE = "Login";
    private static final String LOGIN_KEY_FIELD_ID = "_id";
    private static final String FIELD_LOGIN_USER_ID = "user_id";
    private static final String FIELD_LOGIN_USER_ROLE = "role";



    // FIELD NAMES FOR THE EVENTS TABLE
    private static final String RECIPE_TABLE = "RECIPES";
    private static final String EVENTS_KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_OWNER_ID = "owner_id";
    private static final String FIELD_INGREDIENTS = "start_date";
    private static final String FIELD_END_DATE = "end_date";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_EVENT_IMAGE_NAME = "image_name";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + USERS_TABLE + "("
                + USERS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_USERNAME + " TEXT UNIQUE, "
                + FIELD_EMAIL + " TEXT UNIQUE, "
                + FIELD_PHONE_NUMBER + " TEXT, "
                + FIELD_PASSWORD + " TEXT, "
                + FIELD_IMAGE_NAME + " TEXT" + ")";
        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}