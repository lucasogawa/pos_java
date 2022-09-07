package com.ogawalucas.automobilesupplycontrol.automobile.activity;

import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ADD_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ARCHIVE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_EDIT_MODE;
import static com.ogawalucas.automobilesupplycontrol.utils.NumberUtils.roundDouble;

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
import com.ogawalucas.automobilesupplycontrol.about.activity.AboutActivity;
import com.ogawalucas.automobilesupplycontrol.automobile.adapter.AutomobileAdapter;
import com.ogawalucas.automobilesupplycontrol.automobile.adapter.AutomobileAdapterView;
import com.ogawalucas.automobilesupplycontrol.automobile.adapter.AvgSupply;
import com.ogawalucas.automobilesupplycontrol.automobile.dao.AutomobileDao;
import com.ogawalucas.automobilesupplycontrol.automobile.enums.ESortBy;
import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;
import com.ogawalucas.automobilesupplycontrol.database.Database;
import com.ogawalucas.automobilesupplycontrol.supply.activity.SupplyAddActivity;
import com.ogawalucas.automobilesupplycontrol.supply.activity.SupplyListingActivity;
import com.ogawalucas.automobilesupplycontrol.supply.dao.SupplyDao;
import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;
import com.ogawalucas.automobilesupplycontrol.utils.AlertUtils;

import java.util.ArrayList;
import java.util.List;

public class AutomobileListingActivity extends AppCompatActivity {

    private static final String KEY_SORT_BY_NICKNAME = "SORT_BY_NICKNAME";

    private String sortByNickname = ESortBy.ASC.name();

    private ActionMode actionMode;
    private int selectedPosition = -1;
    private View selectedView;
    private ActionMode.Callback actionModeCallback;

    private ListView lvAutomobile;
    private AutomobileAdapter automobileAdapter;
    private ArrayList<Automobile> automobiles;

    private AutomobileDao automobileDao;
    private SupplyDao supplyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_listing);

        mapAttributes();
        loadPreferences();
        configureListView();
    }

    private void mapAttributes() {
        lvAutomobile = findViewById(R.id.lvAutomobile);
        actionModeCallback = getActionModeMenuItemSelected();

        var databaase = Database.get(this);
        automobileDao = databaase.automobileDao();
        supplyDao = databaase.supplyDao();
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
        AutomobileAddActivity.openEditMode(this, automobiles.get(selectedPosition).getId());
    }

    private void deleteAutomobile() {
        AlertUtils.showConfirm(
            this,
            getString(R.string.do_you_really_want_to_delete) + " " + automobiles.get(selectedPosition).getNickname() + "?"
                + "\n" + getString(R.string.it_will_remove_all_supplies),
            (dialog, option) -> {
                if (option == DialogInterface.BUTTON_POSITIVE) {
                    deleteSupplies();
                    automobileDao.delete(automobiles.get(selectedPosition));
                    setListViewItens();
                }
            }
        );
    }

    private void deleteSupplies() {
        for (Supply supply : supplyDao.findByAutomobileId(automobiles.get(selectedPosition).getId())) {
            supplyDao.delete(supply);
        }
    }

    private void loadPreferences() {
        loadPreferenceSortByNickname();
    }

    private void loadPreferenceSortByNickname() {
        sortByNickname = getSharedPreferences(KEY_ARCHIVE, Context.MODE_PRIVATE)
            .getString(KEY_SORT_BY_NICKNAME, sortByNickname);
    }

    private void setListViewItens() {
        automobiles = findAll();

        automobileAdapter = new AutomobileAdapter(this, getAdapterView());

        lvAutomobile.setAdapter(automobileAdapter);
    }

    private ArrayList<Automobile> findAll() {
        return ESortBy.valueOf(sortByNickname) == ESortBy.ASC
            ? new ArrayList<>(automobileDao.findAllOrderByNicknameAsc())
            : new ArrayList<>(automobileDao.findAllOrderByNicknameDesc());
    }

    private List<AutomobileAdapterView> getAdapterView() {
        var adapterView = new ArrayList<AutomobileAdapterView>();

        for (Automobile automobile : automobiles) {
            var avgSupplies = new ArrayList<AvgSupply>();

            for (String typeOfFuel : supplyDao.findTypesOfFuelByAutomobileId(automobile.getId())) {
                avgSupplies.add(new AvgSupply(typeOfFuel, getAvgSupply(automobile.getId(), typeOfFuel)));
            }

            adapterView.add(new AutomobileAdapterView(automobile.getNickname(), avgSupplies));
        }

        return adapterView;
    }

    private double getAvgSupply(long automobileId, String typeOfFuel) {
        return roundDouble(supplyDao.findAvgKilometersPerLiterByAutomobileIdAndTypeOfFuel(automobileId, typeOfFuel));
    }

    private void configureListView() {
        configureOnItemClick();
        setListViewItens();
    }

    private void configureOnItemClick() {
        lvAutomobile.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvAutomobile.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            openSupplyListingActivity();
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

    private void openSupplyListingActivity() {
        SupplyListingActivity.open(this, automobiles.get(selectedPosition).getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listing_automobile_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;

        switch (ESortBy.valueOf(sortByNickname)) {
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
            case R.id.miAddSupply:
                openAddSupplyActivity();
                return true;

            case R.id.miAddAutomobile:
                openAddActivity();
                return true;

            case R.id.miAsc:
                savePreferenceSortByNickname(ESortBy.ASC.name());
                return true;

            case R.id.miDesc:
                savePreferenceSortByNickname(ESortBy.DESC.name());
                return true;

            case R.id.miAbout:
                openAboutActivity();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void savePreferenceSortByNickname(String newValue) {
        var editor = getSharedPreferences(KEY_ARCHIVE, Context.MODE_PRIVATE).edit();

        editor.putString(KEY_SORT_BY_NICKNAME, newValue);
        editor.commit();

        sortByNickname = newValue;

        setListViewItens();
    }

    public void openAddSupplyActivity() {
        SupplyAddActivity.openAddMode(this);
    }

    public void openAddActivity() {
        AutomobileAddActivity.openAddMode(this);
    }

    public void openAboutActivity() {
        AboutActivity.open(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == KEY_ADD_MODE || requestCode == KEY_EDIT_MODE)
            && resultCode == Activity.RESULT_OK
        ) {
            setListViewItens();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        setListViewItens();
    }
}