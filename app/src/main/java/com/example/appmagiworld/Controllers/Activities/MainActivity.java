package com.example.appmagiworld.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appmagiworld.R;

public class MainActivity extends AppCompatActivity {
    private Button mFollowingButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        passageToSecondActivity();
    }

    public void setUpViews(){
        mFollowingButton = findViewById(R.id.buttonStartSecondActivity);
    }

    public void passageToSecondActivity(){
        mFollowingButton.setOnClickListener(v -> {
            Intent PlayerOneActivity = new Intent(MainActivity.this, PlayerOneActivity.class);
            startActivity(PlayerOneActivity);
        });
    }

}