package com.ogawalucas.automobilesupplycontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    public static final int KEY_ADD_MODE = 1;
    public static final int KEY_EDIT_MODE = 2;

    public static final String KEY_MODE = "MODE";
    public static final String KEY_NICKNAME = "NICKNAME";
    public static final String KEY_TRAVEL_CAR = "TRAVEL_CAR";
    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_BRAND = "BRAND";
    public static final String KEY_MODEL = "MODEL";
    public static final String KEY_COLOR = "COLOR";
    public static final String KEY_MANUFACTORING_YEAR = "MANUFACTORING_YEAR";

    private static final String MSG_EMPTY_FIELDS = "%s: %s.";

    private TextView tvNickname;
    private EditText etNickname;
    private CheckBox cbTravelCar;
    private TextView tvType;
    private RadioGroup rgType;
    private TextView tvBrand;
    private Spinner spBrand;
    private TextView tvModel;
    private EditText etModel;
    private TextView tvColor;
    private EditText etColor;
    private TextView tvManufactoringYear;
    private EditText etManufactoringYear;

    public static void openAddMode(AppCompatActivity activity) {
        var intent = new Intent(activity, AddActivity.class);

        intent.putExtra(KEY_MODE, KEY_ADD_MODE);

        activity.startActivityForResult(intent, KEY_ADD_MODE);
    }

    public static void openEditMode(AppCompatActivity activity, Automobile automobile) {
        var intent = new Intent(activity, AddActivity.class);

        intent.putExtra(KEY_MODE, KEY_EDIT_MODE);

        intent.putExtra(KEY_NICKNAME, automobile.getNickname());
        intent.putExtra(KEY_TRAVEL_CAR, automobile.isTravel());
        intent.putExtra(KEY_TYPE, automobile.getType().name());
        intent.putExtra(KEY_BRAND, automobile.getBrand());
        intent.putExtra(KEY_MODEL, automobile.getModel());
        intent.putExtra(KEY_COLOR, automobile.getColor());
        intent.putExtra(KEY_MANUFACTORING_YEAR, automobile.getManufactoringYear());

        activity.startActivityForResult(intent, KEY_EDIT_MODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setActionBar();
        mapAttributes();
        setSpinnersOptions();

        initializeData();
    }

    private void initializeData() {
        var bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getInt(KEY_MODE, KEY_ADD_MODE) == KEY_ADD_MODE) {
                setTitle(getString(R.string.add_automobile));
            } else {
                setTitle(getString(R.string.edit_automobile));

                etNickname.setText(bundle.getString(KEY_NICKNAME));
                cbTravelCar.setChecked(bundle.getBoolean(KEY_TRAVEL_CAR));
                rgType.check(getTypeId(EType.valueOf(bundle.getString(KEY_TYPE))));
                spBrand.setSelection(getBrandId(bundle.getString(KEY_BRAND)));
                etModel.setText(bundle.getString(KEY_MODEL));
                etColor.setText(bundle.getString(KEY_COLOR));
                etManufactoringYear.setText(bundle.getString(KEY_MANUFACTORING_YEAR));
            }
        }
    }

    private int getTypeId(EType type) {
        var rbId = R.id.rbCar;

        switch (type) {
            case MOTORCYCLE:
                rbId = R.id.rbMotorcycle;
                break;

            case PICKUP:
                rbId = R.id.rbPickup;
                break;

            case TRUCK:
                rbId = R.id.rbTruck;
                break;

            case OTHERS:
                rbId = R.id.rbOthers;
                break;

            default:
                break;
        }

        return rbId;
    }

    private int getBrandId(String brand) {
        int pos = 0;

        var brands = getResources().getStringArray(R.array.brands);

        for (int i = 0; i < brands.length; i++) {
            if (brands[i].equals(brand)) {
                pos = i;
            }
        }

        return pos;
    }

    private void setActionBar() {
        var actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void mapAttributes() {
        tvNickname = findViewById(R.id.tvNickname);
        etNickname = findViewById(R.id.etNickname);

        cbTravelCar = findViewById(R.id.cbTravelCar);

        tvType = findViewById(R.id.tvAutomobileCar);
        rgType = findViewById(R.id.rgAutomobileCar);

        tvBrand = findViewById(R.id.tvBrand);
        spBrand = findViewById(R.id.spBrand);

        tvModel = findViewById(R.id.tvModel);
        etModel = findViewById(R.id.etModel);

        tvColor = findViewById(R.id.tvColor);
        etColor = findViewById(R.id.etColor);

        tvManufactoringYear = findViewById(R.id.tvManufactoringYear);
        etManufactoringYear = findViewById(R.id.etManufactoringYear);
    }

    private void setSpinnersOptions() {
        var adapter =
            ArrayAdapter.createFromResource(this, R.array.brands, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spBrand.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    private void cancel(){
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
        etNickname.setText(null);
        cbTravelCar.setChecked(false);
        rgType.clearCheck();
        etModel.setText(null);
        etColor.setText(null);
        etManufactoringYear.setText(null);

        showToast(getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void save() {
        if (isFieldsValid()) {
            sendDataInActivityResult();
        }
    }

    private boolean isFieldsValid() {
        return isNicknameValid()
            && isAutomobileTypeValid()
            && isModelValid()
            && isColorValid()
            && isManufactoringYearValid();
    }

    private boolean isNicknameValid() {
        if (etNickname.getText() == null || etNickname.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), tvNickname.getText()), Toast.LENGTH_LONG);
            etNickname.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isAutomobileTypeValid() {
        if (rgType.getCheckedRadioButtonId() == -1) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), tvType.getText()), Toast.LENGTH_LONG);
            rgType.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isModelValid() {
        if (etModel.getText() == null || etModel.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), etModel.getText()), Toast.LENGTH_LONG);
            etModel.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isColorValid() {
        if (etColor.getText() == null || etColor.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), etColor.getText()), Toast.LENGTH_LONG);
            etColor.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isManufactoringYearValid() {
        if (etManufactoringYear.getText() == null || etManufactoringYear.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), tvManufactoringYear.getText()), Toast.LENGTH_LONG);
            etManufactoringYear.requestFocus();
            return false;
        }

        return true;
    }

    private void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }

    private void sendDataInActivityResult() {
        var intent = new Intent();

        intent.putExtra(KEY_NICKNAME, etNickname.getText().toString());
        intent.putExtra(KEY_TRAVEL_CAR, cbTravelCar.isChecked());
        intent.putExtra(KEY_TYPE, getType(rgType.getCheckedRadioButtonId()).name());
        intent.putExtra(KEY_BRAND, (String) spBrand.getSelectedItem());
        intent.putExtra(KEY_MODEL, etModel.getText().toString());
        intent.putExtra(KEY_COLOR, etColor.getText().toString());
        intent.putExtra(KEY_MANUFACTORING_YEAR, etManufactoringYear.getText().toString());

        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    private EType getType(int checkedRadioButtonId) {
        EType type;

        switch (checkedRadioButtonId) {
            case R.id.rbCar:
                type = EType.CAR;
                break;
            case R.id.rbMotorcycle:
                type = EType.MOTORCYCLE;
                break;
            case R.id.rbPickup:
                type = EType.PICKUP;
                break;
            case R.id.rbTruck:
                type = EType.TRUCK;
                break;
            default:
                type = EType.OTHERS;
                break;
        }

        return type;
    }
}