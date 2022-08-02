package com.ogawalucas.automobilesupplycontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

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

    public static void open(AppCompatActivity activity) {
        var intent = new Intent(activity, AddActivity.class);

        activity.startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle(getString(R.string.listing_automobile));

        mapAttributes();
        setSpinnersOptions();
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

    public void clearFields(View view) {
        etNickname.setText(null);
        cbTravelCar.setChecked(false);
        rgType.clearCheck();
        etModel.setText(null);
        etColor.setText(null);
        etManufactoringYear.setText(null);

        showToast(getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void save(View view) {
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
        intent.putExtra(KEY_TYPE, getType(rgType.getCheckedRadioButtonId()).toString());
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

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}