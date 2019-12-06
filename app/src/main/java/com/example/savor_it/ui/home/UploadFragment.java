package com.example.savor_it.ui.home;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.savor_it.MainHomeActivity;
import com.example.savor_it.R;
import com.example.savor_it.model.Recipe;
import com.example.savor_it.model.RecipeDetails;

import java.io.IOException;
import java.util.ArrayList;

public class UploadFragment extends Fragment {


    private static final int REQUEST_CODE_PHOTO = 100;
    EditText et;
    EditText step;
    EditText nameText;
    Button add_btn;
    Button second_add_btn;
    ListView listView;
    ListView secondListView;
    ArrayAdapter<String> ingredientsAdapter;
    ArrayList<String> ingredients;
    ArrayAdapter<String> stepsAdapter;
    ArrayList<String> steps;
    ImageView imageView;
    private Uri imageUri;
    Recipe recipe;
    private MediaRecorder mediaRecorder;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    private static String fileName = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private ImageButton recordButton;
    private ImageButton stopButton;
    private Button saveButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_upload, container, false);

        et = (EditText) root.findViewById(R.id.addIngredient);
        step = (EditText) root.findViewById(R.id.addStep);
        nameText = (EditText) root.findViewById(R.id.editText2);
        add_btn = (Button) root.findViewById(R.id.add_btn);
        second_add_btn = (Button) root.findViewById(R.id.add_step_btn);
        listView = (ListView) root.findViewById(R.id.listView);
        secondListView = (ListView) root.findViewById(R.id.add_step_list_view);
        imageView = root.findViewById(R.id.imageView2);
        recordButton = (ImageButton) root.findViewById(R.id.record_button);
        stopButton = (ImageButton) root.findViewById(R.id.stop_btn);
        saveButton = (Button) root.findViewById(R.id.save_btn);
        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        ingredients = new ArrayList<>();
        ingredientsAdapter = new ArrayAdapter<>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(ingredientsAdapter);

        steps = new ArrayList<>();
        stepsAdapter = new ArrayAdapter<>(this.getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, steps);
        secondListView.setAdapter(stepsAdapter);
        recipe = new Recipe();

        onAddButtonClick();

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecording();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save to the database
                recipe.setAudioFilename(fileName);
                MainHomeActivity activity = (MainHomeActivity) getActivity();
                recipe.setTitle(nameText.getText().toString());
                saveButton.setTag(recipe);
                activity.uploadRecipe(saveButton);


            }
        });


        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) return;

    }

    public void onAddButtonClick() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = et.getText().toString();
                ingredients.add(result);
                ingredientsAdapter.notifyDataSetChanged();
                recipe.setIngredients(ingredients);
            }
        });

        second_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = step.getText().toString();
                steps.add(result);
                stepsAdapter.notifyDataSetChanged();
                recipe.setSteps(steps);
                    }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEventImage(v);
            }
        });
    }


    private void selectEventImage(View v) {
        // List of all the permissions to request from user
        ArrayList<String> permList = new ArrayList<>();

        // Check to see if permissions to camera has been granted
        int cameraPermission = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED)
            permList.add(android.Manifest.permission.CAMERA);

        // Check if permissions to camera is granted
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);

        // Check if permissions to writing external storage is granted
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (writeExternalStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If the list has items (size > 0), request permissions from the user:
        if (permList.size() > 0)
        {
            // Convert ArrayList into an Array of Strings
            String[] perms = new String[permList.size()];
            // Request permission from user
            ActivityCompat.requestPermissions(getActivity(), permList.toArray(perms), REQUEST_CODE_PHOTO);
        }

        // If we have all 3 permissions, open ImageGallery
        if (cameraPermission == PackageManager.PERMISSION_GRANTED
                && readExternalStoragePermission == PackageManager.PERMISSION_GRANTED
                && writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED)
        {
            // Start an intent to launch gallery and take pics
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE_PHOTO);
        } else {
            Toast.makeText(getContext(),
                    "OCC Community Service Hours requires camera and external storage permission",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void startRecording() {
        fileName = getActivity().getExternalCacheDir().getAbsolutePath();
        fileName += "/" + nameText.getText().toString() + ".3gp";
        //fileName += "/audiorecordtest.3gp";


        System.out.println("Here's the file name" + fileName);
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(fileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mediaRecorder.start();
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        recipe.setAudioFilename(fileName);
        mediaRecorder = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == REQUEST_CODE_PHOTO && resultCode == Activity.RESULT_OK)
        {
            // Set the imageURI to the data
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            recipe.setPhoto(imageUri);
        }
    }


}
