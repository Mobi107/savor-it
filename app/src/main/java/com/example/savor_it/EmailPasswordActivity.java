package com.example.savor_it;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class EmailPasswordActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private TextView mNameTextView;
    private ImageView mLogoView;
    private TextView mStatusTextView;
    private EditText mEmailField;
    private EditText mPasswordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailpassword);

        // Views
        mNameTextView = findViewById(R.id.name);
        mLogoView = findViewById(R.id.logo);
        mStatusTextView = findViewById(R.id.status);
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);
    }
}
