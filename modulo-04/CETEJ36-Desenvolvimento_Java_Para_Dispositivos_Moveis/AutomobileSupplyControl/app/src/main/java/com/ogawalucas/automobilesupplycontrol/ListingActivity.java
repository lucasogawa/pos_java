package com.ogawalucas.automobilesupplycontrol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {

    private ListView lvAutomobile;
    private AutomobileAdapter automobileAdapter;
    private ArrayList<Automobile> automobiles;

    private ActionMode actionMode;
    private int selectedPosition = -1;
    private View selectedView;
    private ActionMode.Callback actionModeCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        mapAttributes();
        configureListView();
    }

    private void mapAttributes() {
        lvAutomobile = findViewById(R.id.lvAutomobile);
        actionModeCallback = getActionModeMenuItemSelected();
    }

    private ActionMode.Callback getActionModeMenuItemSelected() {
        return new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                var inflate = mode.getMenuInflater();

                inflate.inflate(R.menu.listing_item_selected, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.miEdit:
                        openEditActivity();
                        mode.finish();
                        return true;

                    case R.id.miDelete:
                        deleteAutomobile();
                        mode.finish();
                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                if (selectedView != null) {
                    selectedView.setBackgroundColor(Color.TRANSPARENT);
                }

                actionMode = null;
                selectedView = null;

                lvAutomobile.setEnabled(true);
            }
        };
    }

    private void openEditActivity() {
        AddActivity.openEditMode(this, automobiles.get(selectedPosition));
    }

    private void deleteAutomobile() {
        automobiles.remove(selectedPosition);
        automobileAdapter.notifyDataSetChanged();
    }

    private void configureListView() {
        configureOnItemClick();
        setListViewItens();
    }

    private void configureOnItemClick() {
        lvAutomobile.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvAutomobile.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            openEditActivity();
        });

        lvAutomobile.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }

            selectedPosition = position;
            selectedView = view;
            actionMode = startSupportActionMode(actionModeCallback);

            view.setBackgroundColor(Color.LTGRAY);
            lvAutomobile.setEnabled(false);

            return true;
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
        AddActivity.openAddMode(this);
    }

    public void openAboutActivity() {
        AboutActivity.open(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AddActivity.KEY_ADD_MODE) {
                addAutomobile(data.getExtras());
            } else {
                editAutomobile(data.getExtras());
            }

            automobileAdapter.notifyDataSetChanged();
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
    }

    private void editAutomobile(Bundle bundle) {
        automobiles.get(selectedPosition).edit(
            bundle.getString(AddActivity.KEY_NICKNAME),
            bundle.getBoolean(AddActivity.KEY_TRAVEL_CAR),
            EType.valueOf(bundle.getString(AddActivity.KEY_TYPE)),
            bundle.getString(AddActivity.KEY_BRAND),
            bundle.getString(AddActivity.KEY_MODEL),
            bundle.getString(AddActivity.KEY_COLOR),
            bundle.getString(AddActivity.KEY_MANUFACTORING_YEAR)
        );
    }
}