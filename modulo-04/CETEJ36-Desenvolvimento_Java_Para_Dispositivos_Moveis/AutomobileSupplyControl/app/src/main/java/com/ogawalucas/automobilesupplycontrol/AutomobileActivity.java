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

import java.util.Objects;

public class AutomobileActivity extends AppCompatActivity {

    private TextView tvNickname;
    private EditText edNickname;
    private CheckBox cbTravelCar;
    private TextView tvAutomobileType;
    private RadioGroup rgAutomobileType;
    private TextView tvBrand;
    private Spinner spBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mapAttributes();
        setSpinners();
    }

    private void mapAttributes() {
        tvNickname = findViewById(R.id.tvNickname);
        edNickname = findViewById(R.id.etNickname);
        cbTravelCar = findViewById(R.id.cbTravelCar);
        tvAutomobileType = findViewById(R.id.tvAutomobileCar);
        rgAutomobileType = findViewById(R.id.rgAutomobileCar);
        tvBrand = findViewById(R.id.tvBrand);
        spBrand = findViewById(R.id.spBrand);
    }

    private void setSpinners() {
        var adapter =
                ArrayAdapter.createFromResource(this, R.array.brands, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spBrand.setAdapter(adapter);
    }

    public void clearEditTexts(View view) {
        edNickname.setText(null);
        cbTravelCar.setChecked(false);
        rgAutomobileType.clearCheck();

        showToast(getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void validateFields(View view) {
        validateNickname();
    }

    private void validateNickname() {
        if (edNickname.getText() == null || edNickname.getText().length() == 0) {
            showToast(String.format("%s: %s.", getString(R.string.empty_fields), tvNickname.getText()), Toast.LENGTH_LONG);
            edNickname.requestFocus();
        }
    }

    private void showToast(String message, int duration) {
        Toast.makeText(this, message, duration).show();
    }
}