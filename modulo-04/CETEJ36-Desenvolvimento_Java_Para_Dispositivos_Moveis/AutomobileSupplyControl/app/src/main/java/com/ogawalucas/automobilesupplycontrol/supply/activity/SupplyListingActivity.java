package com.ogawalucas.automobilesupplycontrol.supply.activity;

import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ADD_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ADD_MODE_BY_PARAMS;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ARCHIVE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_EDIT_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ID;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import com.ogawalucas.automobilesupplycontrol.R;
import com.ogawalucas.automobilesupplycontrol.automobile.enums.ESortBy;
import com.ogawalucas.automobilesupplycontrol.database.Database;
import com.ogawalucas.automobilesupplycontrol.supply.adapter.SupplyAdapter;
import com.ogawalucas.automobilesupplycontrol.supply.dao.SupplyDao;
import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;
import com.ogawalucas.automobilesupplycontrol.utils.AlertUtils;

import java.util.ArrayList;

public class SupplyListingActivity extends AppCompatActivity {

    private static final String KEY_SORT_BY_DATE = "SORT_BY_DATE";

    private String sortByDate = ESortBy.ASC.name();

    private ActionMode actionMode;
    private int selectedPosition = -1;
    private View selectedView;
    private ActionMode.Callback actionModeCallback;

    private ListView lvSupplies;
    private SupplyAdapter supplyAdapter;
    private ArrayList<Supply> supplies;

    private SupplyDao supplyDao;

    private long automobileId = -1;

    public static void open(AppCompatActivity activity, long id) {
        var intent = new Intent(activity, SupplyListingActivity.class);

        intent.putExtra(KEY_ID, id);

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_listing);

        setActionBar();
        mapAttributes();
        loadPreferences();
        configureListView();
    }

    private void setActionBar() {
        var actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void mapAttributes() {
        lvSupplies = findViewById(R.id.lvSupplies);
        actionModeCallback = getActionModeMenuItemSelected();

        supplyDao = Database.get(this).supplyDao();

        automobileId = getAutomobileId();
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
                        deleteSupply();
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

                lvSupplies.setEnabled(true);
            }
        };
    }

    private void openEditActivity() {
        SupplyAddActivity.openEditMode(this, supplies.get(selectedPosition).getId());
    }

    private void deleteSupply() {
        AlertUtils.showConfirm(
            this,
            getString(R.string.do_you_really_want_to_delete) + "\n" + supplies.get(selectedPosition).getDate(),
            (dialog, option) -> {
                if (option == DialogInterface.BUTTON_POSITIVE) {
                    supplyDao.delete(supplies.get(selectedPosition));
                    setListViewItens();
                }
            }
        );
    }

    private long getAutomobileId() {
        var automobileId = -1L;
        var bundle = getIntent().getExtras();

        if (bundle != null) {
            var automobile = Database.get(this).automobileDao().findById(bundle.getLong(KEY_ID));

            setTitle(automobile.getNickname());

            automobileId = automobile.getId();
        }

        return automobileId;
    }

    private void setListViewItens() {
        supplies = findAll();

        supplyAdapter = new SupplyAdapter(this, supplies);

        lvSupplies.setAdapter(supplyAdapter);
    }

    private ArrayList<Supply> findAll() {
        return ESortBy.valueOf(sortByDate) == ESortBy.ASC
            ? new ArrayList<>(supplyDao.findAllOrderByDateAsc())
            : new ArrayList<>(supplyDao.findAllOrderByDateDesc());
    }

    private void loadPreferences() {
        loadPreferenceSortByNickname();
    }

    private void loadPreferenceSortByNickname() {
        sortByDate = getSharedPreferences(KEY_ARCHIVE, Context.MODE_PRIVATE)
            .getString(KEY_SORT_BY_DATE, sortByDate);
    }

    private void configureListView() {
        configureOnItemClick();
        setListViewItens();
    }

    private void configureOnItemClick() {
        lvSupplies.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvSupplies.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            openEditActivity();
        });

        lvSupplies.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }

            selectedPosition = position;
            selectedView = view;
            actionMode = startSupportActionMode(actionModeCallback);

            view.setBackgroundColor(Color.LTGRAY);
            lvSupplies.setEnabled(false);

            return true;
        });
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void cancel() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listing_supply_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;

        switch (ESortBy.valueOf(sortByDate)) {
            case ASC:
                item = menu.findItem(R.id.miAsc);
                break;

            case DESC:
                item = menu.findItem(R.id.miDesc);
                break;

            default:
                return false;
        }

        item.setChecked(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miAdd:
                openAddActivity();
                return true;

            case R.id.miAsc:
                savePreferenceSortByDate(ESortBy.ASC.name());
                return true;

            case R.id.miDesc:
                savePreferenceSortByDate(ESortBy.DESC.name());
                return true;

            case android.R.id.home:
                cancel();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void savePreferenceSortByDate(String newValue) {
        var editor = getSharedPreferences(KEY_ARCHIVE, Context.MODE_PRIVATE).edit();

        editor.putString(KEY_SORT_BY_DATE, newValue);
        editor.commit();

        sortByDate = newValue;

        setListViewItens();
    }

    public void openAddActivity() {
        SupplyAddActivity.openAddModeByAutomobileId(this, automobileId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == KEY_ADD_MODE || requestCode == KEY_ADD_MODE_BY_PARAMS || requestCode == KEY_EDIT_MODE)
            && resultCode == Activity.RESULT_OK
        ) {
            setListViewItens();
        }
    }
}