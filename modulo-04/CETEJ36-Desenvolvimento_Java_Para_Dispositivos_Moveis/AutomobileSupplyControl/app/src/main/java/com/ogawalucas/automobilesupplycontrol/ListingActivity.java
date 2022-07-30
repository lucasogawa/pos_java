package com.ogawalucas.automobilesupplycontrol;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {

    private ListView lvAutomobile;

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
        setListViewItens();
        configureOnItemClick();
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
        var automobiles = new ArrayList<Automobile>();
        var nicknames = getResources().getStringArray(R.array.nicknames);
        var types = getResources().getStringArray(R.array.types);
        var brands = getResources().getStringArray(R.array.brands);
        var models = getResources().getStringArray(R.array.models);
        var colors = getResources().getStringArray(R.array.colors);
        var manufactoringYears = getResources().getStringArray(R.array.manufactoringYears);

        for (int cont = 0; cont < nicknames.length; cont++){
            automobiles.add(new Automobile(
                nicknames[cont],
                types[cont],
                brands[cont],
                models[cont],
                colors[cont],
                manufactoringYears[cont]
            ));
        }

        lvAutomobile.setAdapter(new AutomobileAdapter(this, automobiles));
    }
}