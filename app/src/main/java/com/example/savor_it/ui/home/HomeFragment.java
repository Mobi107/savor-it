package com.example.savor_it.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.widget.TextView;

//import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;

import com.example.savor_it.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);

        Button searchBtn = root.findViewById(R.id.search_button);
        Button uploadBtn = root.findViewById(R.id.upload_button);
        Button requestBtn = root.findViewById(R.id.request_button);

        searchBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        requestBtn.setOnClickListener(this);

//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        return root;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.search_button:
                fragment = new SearchFragment();
                replaceFragment(fragment);
                break;
            case R.id.upload_button:
                fragment = new UploadFragment();
                replaceFragment(fragment);
                break;

            //add cases for other buttons and fragments when implemented
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}