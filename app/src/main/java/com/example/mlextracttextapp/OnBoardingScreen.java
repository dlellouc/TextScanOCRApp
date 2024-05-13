package com.example.mlextracttextapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        EdgeToEdge.enable(this);

//        setContentView(R.layout.activity_on_boarding_screen);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        addFragment(new Step.Builder().setTitle("Extract Text From Images using ML")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("This app uses ML Kit to recognize text in images or video, such as the text of a street sign.")
                .build());

        addFragment(new Step.Builder().setTitle("Copy and Use the Extracted Text in Seconds")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable
                .setSummary("This app uses ML Kit to recognize text in images or video, such as the text of a street sign.")
                .build());

        addFragment(new Step.Builder().setTitle("Let's Start !")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.logo) // int top drawable
                .setSummary("This app uses ML Kit to recognize text in images or video, such as the text of a street sign.")
                .build());
    }

    @Override
    public void finishTutorial() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}