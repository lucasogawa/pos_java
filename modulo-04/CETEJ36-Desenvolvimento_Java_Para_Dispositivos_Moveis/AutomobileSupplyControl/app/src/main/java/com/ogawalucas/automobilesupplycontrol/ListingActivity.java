package com.ogawalucas.automobilesupplycontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {

    private ListView lvAutomobile;
    private AutomobileAdapter automobileAdapter;
    private ArrayList<Automobile> automobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        mapAttributes();
        configureListView();
    }

    private void mapAttributes() {
        lvAutomobile = findViewById(R.id.lvAutomobile);
    }

    private void configureListView() {
        configureOnItemClick();
        setListViewItens();
    }

    private void configureOnItemClick() {
        lvAutomobile.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    var automobile = (Automobile) lvAutomobile.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), automobile.getNickname() + getString(R.string.was_clicked), Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void setListViewItens() {
        automobiles = new ArrayList<>();

        automobileAdapter = new AutomobileAdapter(this, automobiles);

        lvAutomobile.setAdapter(automobileAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listing_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miAdd:
                openAddActivity();
                return true;

            case R.id.miAbout:
                openAboutActivity();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void openAddActivity() {
        AddActivity.open(this);
    }

    public void openAboutActivity() {
        AboutActivity.open(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            addAutomobile(data.getExtras());
        }
    }

    private void addAutomobile(Bundle bundle) {
        automobiles.add(new Automobile(
            bundle.getString(AddActivity.KEY_NICKNAME),
            bundle.getBoolean(AddActivity.KEY_TRAVEL_CAR),
            EType.valueOf(bundle.getString(AddActivity.KEY_TYPE)),
            bundle.getString(AddActivity.KEY_BRAND),
            bundle.getString(AddActivity.KEY_MODEL),
            bundle.getString(AddActivity.KEY_COLOR),
            bundle.getString(AddActivity.KEY_MANUFACTORING_YEAR)
        ));

        automobileAdapter.notifyDataSetChanged();
    }
}