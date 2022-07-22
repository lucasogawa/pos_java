package com.ogawalucas.automobilesupplycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AutomobileActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_principal);

        mapAttributes();
        setSpinners();
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

    private void setSpinners() {
        var adapter =
                ArrayAdapter.createFromResource(this, R.array.brands, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spBrand.setAdapter(adapter);
    }

    public void clearEditTexts(View view) {
        etNickname.setText(null);
        cbTravelCar.setChecked(false);
        rgAutomobileType.clearCheck();
        etModel.setText(null);
        etColor.setText(null);
        etManufactoringYear.setText(null);

        showToast(getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void validateFields(View view) {
        validateNickname();
        validateModel();
        validateColor();
        validateManufactoringYear();
    }

    private void validateNickname() {
        if (etNickname.getText() == null || etNickname.getText().length() == 0) {
            showToast(String.format("%s: %s.", getString(R.string.empty_fields), tvNickname.getText()), Toast.LENGTH_LONG);
            etNickname.requestFocus();
        }
    }

    private void validateModel() {
        if (etModel.getText() == null || etNickname.getText().length() == 0) {
            showToast(String.format("%s: %s.", getString(R.string.empty_fields), etModel.getText()), Toast.LENGTH_LONG);
            etModel.requestFocus();
        }
    }

    private void validateColor() {
        if (etColor.getText() == null || etColor.getText().length() == 0) {
            showToast(String.format("%s: %s.", getString(R.string.empty_fields), etColor.getText()), Toast.LENGTH_LONG);
            etColor.requestFocus();
        }
    }

    private void validateManufactoringYear() {
        if (etManufactoringYear.getText() == null || etManufactoringYear.getText().length() == 0) {
            showToast(String.format("%s: %s.", getString(R.string.empty_fields), etManufactoringYear.getText()), Toast.LENGTH_LONG);
            etManufactoringYear.requestFocus();
        }
    }

    private void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}