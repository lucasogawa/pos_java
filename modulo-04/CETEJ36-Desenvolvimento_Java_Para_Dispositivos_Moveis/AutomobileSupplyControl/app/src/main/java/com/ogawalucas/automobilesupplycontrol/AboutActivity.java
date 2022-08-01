package com.ogawalucas.automobilesupplycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AboutActivity extends AppCompatActivity {

    public static void open(AppCompatActivity activity) {
        var intent = new Intent(activity, AboutActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}