package com.ogawalucas.automobilesupplycontrol.about.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.ogawalucas.automobilesupplycontrol.R;

public class AboutActivity extends AppCompatActivity {

    public static void open(AppCompatActivity activity) {
        var intent = new Intent(activity, AboutActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setActionBar();
    }

    private void setActionBar() {
        var actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onContextItemSelected(item);
    }
}