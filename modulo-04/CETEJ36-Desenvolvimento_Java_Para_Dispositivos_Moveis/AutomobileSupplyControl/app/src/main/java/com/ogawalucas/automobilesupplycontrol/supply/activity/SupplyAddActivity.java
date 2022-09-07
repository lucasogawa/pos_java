package com.ogawalucas.automobilesupplycontrol.supply.activity;

import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ADD_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ADD_MODE_BY_PARAMS;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_EDIT_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ID;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_MODE;
import static com.ogawalucas.automobilesupplycontrol.utils.AlertUtils.showToast;
import static com.ogawalucas.automobilesupplycontrol.utils.ValidateUtils.isTextViewValid;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ogawalucas.automobilesupplycontrol.R;
import com.ogawalucas.automobilesupplycontrol.automobile.dao.AutomobileDao;
import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;
import com.ogawalucas.automobilesupplycontrol.database.Database;
import com.ogawalucas.automobilesupplycontrol.supply.dao.SupplyDao;
import com.ogawalucas.automobilesupplycontrol.supply.model.Supply;
import com.ogawalucas.automobilesupplycontrol.utils.AlertUtils;
import com.ogawalucas.automobilesupplycontrol.utils.DateUtils;

import java.util.Calendar;
import java.util.List;

public class SupplyAddActivity extends AppCompatActivity {

    private TextView tvAutomobile;
    private Spinner spAutomobile;
    private TextView tvFuelStation;
    private EditText etFuelStation;
    private TextView tvDate;
    private Calendar calendarDate;
    private EditText etDate;
    private TextView tvTypeOfFuel;
    private Spinner spTypeOfFuel;
    private TextView tvKilometers;
    private EditText etKilometers;
    private TextView tvLiters;
    private EditText etLiters;
    private TextView tvAmountPaid;
    private EditText etAmountPaid;

    private SupplyDao supplyDao;

    private AutomobileDao automobileDao;
    private List<Automobile> automobiles;

    public static void openAddMode(AppCompatActivity activity) {
        var intent = new Intent(activity, SupplyAddActivity.class);

        intent.putExtra(KEY_MODE, KEY_ADD_MODE);

        activity.startActivityForResult(intent, KEY_ADD_MODE);
    }

    public static void openAddModeByAutomobileId(AppCompatActivity activity, long automobileId) {
        var intent = new Intent(activity, SupplyAddActivity.class);

        intent.putExtra(KEY_MODE, KEY_ADD_MODE_BY_PARAMS);
        intent.putExtra(KEY_ID, automobileId);

        activity.startActivityForResult(intent, KEY_ADD_MODE_BY_PARAMS);
    }

    public static void openEditMode(AppCompatActivity activity, long id) {
        var intent = new Intent(activity, SupplyAddActivity.class);

        intent.putExtra(KEY_MODE, KEY_EDIT_MODE);
        intent.putExtra(KEY_ID, id);

        activity.startActivityForResult(intent, KEY_EDIT_MODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_add);

        setActionBar();
        mapAttributes();
        configureDatePicker();
        setSpinnersOptions();

        initializeData();
    }

    private void setActionBar() {
        var actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void mapAttributes() {
        tvAutomobile = findViewById(R.id.tvAutomobile);
        spAutomobile = findViewById(R.id.spAutomobile);

        tvFuelStation = findViewById(R.id.tvFuelStation);
        etFuelStation = findViewById(R.id.etFuelStation);

        tvDate = findViewById(R.id.tvDate);
        calendarDate = Calendar.getInstance();
        etDate = findViewById(R.id.etDate);

        tvTypeOfFuel = findViewById(R.id.tvTypeOfFuel);
        spTypeOfFuel = findViewById(R.id.spTypeOfFuel);

        tvKilometers = findViewById(R.id.tvKilometers);
        etKilometers = findViewById(R.id.etKilometers);

        tvLiters = findViewById(R.id.tvLiters);
        etLiters = findViewById(R.id.etLiters);

        tvAmountPaid = findViewById(R.id.tvAmountPaid);
        etAmountPaid = findViewById(R.id.etAmountPaid);

        var database = Database.get(this);

        supplyDao = database.supplyDao();

        automobileDao = database.automobileDao();
        automobiles = automobileDao.findAllOrderByNicknameAsc();
    }

    private void configureDatePicker() {
        etDate.setFocusable(false);
        etDate.setOnClickListener(view -> {
            DatePickerDialog picker = new DatePickerDialog(
                SupplyAddActivity.this,
                (datePicker, year, month, dayOfMonth) -> {
                    calendarDate.set(year, month, dayOfMonth);
                    etDate.setText(DateUtils.format(this, calendarDate.getTime()));
                },
                calendarDate.get(Calendar.YEAR),
                calendarDate.get(Calendar.MONTH),
                calendarDate.get(Calendar.DAY_OF_MONTH)
            );
            picker.show();
        });
    }

    private void setSpinnersOptions() {
        setSpinnersOptionsAutomobile();
        setSpinnersOptionsTypesOfFuel();
    }

    private void setSpinnersOptionsAutomobile() {
        spAutomobile.setAdapter(
            new ArrayAdapter<>(
                SupplyAddActivity.this,
                android.R.layout.simple_list_item_1,
                automobiles
            )
        );
    }

    private void setSpinnersOptionsTypesOfFuel() {
        var adapter =
            ArrayAdapter.createFromResource(this, R.array.typesOfFuel, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTypeOfFuel.setAdapter(adapter);
    }

    private void initializeData() {
        var bundle = getIntent().getExtras();

        if (bundle != null) {
            switch (bundle.getInt(KEY_MODE)) {
                case KEY_ADD_MODE:
                    setTitle(getString(R.string.add_supply));
                    validateAutomobiles();
                    break;

                case KEY_ADD_MODE_BY_PARAMS:
                    setTitle(getString(R.string.add_supply));
                    spAutomobile.setSelection(getAutomobilePosition(bundle.getLong(KEY_ID)));
                    spAutomobile.setEnabled(false);
                    break;

                default:
                    setTitle(getString(R.string.edit_supply));
                    var supply = supplyDao.findById(bundle.getLong(KEY_ID));

                    spAutomobile.setSelection(getAutomobilePosition(supply.getId()));
                    etFuelStation.setText(supply.getFuelStation());
                    calendarDate.setTime(supply.getDate());
                    etDate.setText(DateUtils.format(this, supply.getDate()));
                    spTypeOfFuel.setSelection(getTypeOfFuelPosition(supply.getTypeOfFuel()));
                    etKilometers.setText(String.valueOf(supply.getKilometers()));
                    etLiters.setText(String.valueOf(supply.getLiters()));
                    etAmountPaid.setText(String.valueOf(supply.getAmountPaid()));

                    spAutomobile.setEnabled(false);
                    break;
            }
        }
    }

    private void validateAutomobiles() {
        if (automobiles.size() == 0) {
            AlertUtils.showAlert(
                this,
                getString(R.string.no_automobiles_added),
                (dialog, option) -> {
                    if (option == DialogInterface.BUTTON_NEUTRAL) {
                        finish();
                    }
                }
            );
        }
    }

    private int getAutomobilePosition(long id) {
        int pos = 0;

        for (int i = 0; i < automobiles.size(); i++) {
            if (automobiles.get(i).getId() == id) {
                pos = i;
                break;
            }
        }

        return pos;
    }

    private int getTypeOfFuelPosition(String typeOfFuel) {
        var pos = 0;
        var typesOfFuel = getResources().getStringArray(R.array.typesOfFuel);

        for (int i = 0; i < typesOfFuel.length; i++) {
            if (typesOfFuel[i].equals(typeOfFuel)) {
                pos = i;
            }
        }

        return pos;
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
        getMenuInflater().inflate(R.menu.add_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miClear:
                clearFields();
                return true;

            case R.id.miSave:
                save();
                return true;

            case android.R.id.home:
                cancel();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void clearFields() {
        etFuelStation.setText(null);
        etDate.setText(null);
        etKilometers.setText(null);
        etLiters.setText(null);
        etAmountPaid.setText(null);

        showToast(this, getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void save() {
        if (isFieldsValid()) {
            createOrUpdate();
        }
    }

    private boolean isFieldsValid() {
        return isTextViewValid(this, tvFuelStation, etFuelStation)
            && isTextViewValid(this, tvDate, etDate)
            && isTextViewValid(this, tvKilometers, etKilometers)
            && isTextViewValid(this, tvKilometers, etLiters)
            && isTextViewValid(this, tvAmountPaid, etAmountPaid);
    }

    private void createOrUpdate() {
        var bundle = getIntent().getExtras();
        var supply = new Supply(
            ((Automobile) spAutomobile.getSelectedItem()).getId(),
            etFuelStation.getText().toString(),
            calendarDate.getTime(),
            (String) spTypeOfFuel.getSelectedItem(),
            Double.parseDouble(etKilometers.getText().toString()),
            Double.parseDouble(etLiters.getText().toString()),
            Double.parseDouble(etAmountPaid.getText().toString())
        );

        var keyMode = bundle.getInt(KEY_MODE, KEY_ADD_MODE);

        if (keyMode == KEY_ADD_MODE || keyMode == KEY_ADD_MODE_BY_PARAMS) {
            supplyDao.create(supply);
        } else {
            supply.setId(bundle.getLong(KEY_ID));
            supplyDao.update(supply);
        }

        setResult(Activity.RESULT_OK, new Intent());
        finish();
    }
}