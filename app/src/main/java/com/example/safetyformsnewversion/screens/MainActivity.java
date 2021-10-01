package com.example.safetyformsnewversion.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.safetyformsnewversion.R;
import com.example.safetyformsnewversion.screens.MainMenuFragmnet;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MainMenuFragmnet()).commit();


    }
}