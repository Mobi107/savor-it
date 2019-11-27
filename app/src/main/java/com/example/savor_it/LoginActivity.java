package com.example.savor_it;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.savor_it.ui.home.HomeViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private TextView mNameTextView;
    private ImageView mLogoView;
    private TextView mStatusTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mLoginButton;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        mNameTextView = findViewById(R.id.name);
        mLogoView = findViewById(R.id.logo);
        mStatusTextView = findViewById(R.id.status);
        mEmailField = findViewById(R.id.fieldEmail);
        mPasswordField = findViewById(R.id.fieldPassword);
        mLoginButton = findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainHomeActivity.class);
                //Intent intent = new Intent(LoginActivity.this, HomeViewModel.class);
                startActivity(intent);
            }
        });
    }


}
