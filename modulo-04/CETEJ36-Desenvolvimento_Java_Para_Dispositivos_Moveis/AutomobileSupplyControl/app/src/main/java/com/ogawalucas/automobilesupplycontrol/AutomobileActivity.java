package com.ogawalucas.automobilesupplycontrol;

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

public class AutomobileActivity extends AppCompatActivity {

    private static final String MSG_EMPTY_FIELDS = "%s: %s.";

    private TextView tvNickname;
    private EditText etNickname;
    private CheckBox cbTravelCar;
    private TextView tvAutomobileType;
    private RadioGroup rgAutomobileType;
    private TextView tvBrand;
    private Spinner spBrand;
    private TextView tvModel;
    private EditText etModel;
    private TextView tvColor;
    private EditText etColor;
    private TextView tvManufactoringYear;
    private EditText etManufactoringYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);

        mapAttributes();
        setSpinnersOptions();
    }

    private void mapAttributes() {
        tvNickname = findViewById(R.id.tvNickname);
        etNickname = findViewById(R.id.etNickname);

        cbTravelCar = findViewById(R.id.cbTravelCar);

        tvAutomobileType = findViewById(R.id.tvAutomobileCar);
        rgAutomobileType = findViewById(R.id.rgAutomobileCar);

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
        rgAutomobileType.clearCheck();
        etModel.setText(null);
        etColor.setText(null);
        etManufactoringYear.setText(null);

        showToast(getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void save(View view) {
        validateFields();
    }

    private void validateFields() {
        if (validateNickname()) {
            return;
        }
        if (validateTravelCar()) {
            return;
        }
        if (validateAutomobileType()) {
            return;
        }
        if (validateBrand()) {
            return;
        }
        if (validateModel()) {
            return;
        }
        if (validateColor()) {
            return;
        }

        validateManufactoringYear();
    }

    private boolean validateNickname() {
        if (etNickname.getText() == null || etNickname.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), tvNickname.getText()), Toast.LENGTH_LONG);
            etNickname.requestFocus();
            return true;
        }

        return false;
    }

    private boolean validateTravelCar() {
        cbTravelCar.isChecked();

        return false;
    }

    private boolean validateBrand() {
        spBrand.getSelectedItem();

        return false;
    }

    private boolean validateAutomobileType() {
        if (rgAutomobileType.getCheckedRadioButtonId() == -1) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), tvAutomobileType.getText()), Toast.LENGTH_LONG);
            rgAutomobileType.requestFocus();
            return true;
        }

        return false;
    }

    private boolean validateModel() {
        if (etModel.getText() == null || etNickname.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), etModel.getText()), Toast.LENGTH_LONG);
            etModel.requestFocus();
            return true;
        }

        return false;
    }

    private boolean validateColor() {
        if (etColor.getText() == null || etColor.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), etColor.getText()), Toast.LENGTH_LONG);
            etColor.requestFocus();
            return true;
        }

        return false;
    }

    private boolean validateManufactoringYear() {
        if (etManufactoringYear.getText() == null || etManufactoringYear.getText().length() == 0) {
            showToast(String.format(MSG_EMPTY_FIELDS, getString(R.string.empty_fields), etManufactoringYear.getText()), Toast.LENGTH_LONG);
            etManufactoringYear.requestFocus();
            return true;
        }

        return false;
    }

    private void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}