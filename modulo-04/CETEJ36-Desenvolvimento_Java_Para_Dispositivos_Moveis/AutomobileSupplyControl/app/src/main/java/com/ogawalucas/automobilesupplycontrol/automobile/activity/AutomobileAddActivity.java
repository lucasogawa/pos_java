package com.ogawalucas.automobilesupplycontrol.automobile.activity;

import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ADD_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_EDIT_MODE;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_ID;
import static com.ogawalucas.automobilesupplycontrol.constants.KeyConstants.KEY_MODE;
import static com.ogawalucas.automobilesupplycontrol.utils.AlertUtils.showToast;
import static com.ogawalucas.automobilesupplycontrol.utils.ValidateUtils.isRadioButtonValid;
import static com.ogawalucas.automobilesupplycontrol.utils.ValidateUtils.isTextViewValid;

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

import com.ogawalucas.automobilesupplycontrol.R;
import com.ogawalucas.automobilesupplycontrol.automobile.dao.AutomobileDao;
import com.ogawalucas.automobilesupplycontrol.automobile.model.Automobile;
import com.ogawalucas.automobilesupplycontrol.automobile.model.EType;
import com.ogawalucas.automobilesupplycontrol.database.Database;

public class AutomobileAddActivity extends AppCompatActivity {

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

    private AutomobileDao automobileDao;

    public static void openAddMode(AppCompatActivity activity) {
        var intent = new Intent(activity, AutomobileAddActivity.class);

        intent.putExtra(KEY_MODE, KEY_ADD_MODE);

        activity.startActivityForResult(intent, KEY_ADD_MODE);
    }

    public static void openEditMode(AppCompatActivity activity, long id) {
        var intent = new Intent(activity, AutomobileAddActivity.class);

        intent.putExtra(KEY_MODE, KEY_EDIT_MODE);
        intent.putExtra(KEY_ID, id);

        activity.startActivityForResult(intent, KEY_EDIT_MODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atuomobile_add);

        setActionBar();
        mapAttributes();
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

        automobileDao = Database.get(this).automobileDao();
    }

    private void setSpinnersOptions() {
        var adapter =
            ArrayAdapter.createFromResource(this, R.array.brands, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spBrand.setAdapter(adapter);
    }

    private void initializeData() {
        var bundle = getIntent().getExtras();

        if (bundle != null) {
            if (bundle.getInt(KEY_MODE, KEY_ADD_MODE) == KEY_ADD_MODE) {
                setTitle(getString(R.string.add_automobile));
            } else {
                setTitle(getString(R.string.edit_automobile));

                var automobile = automobileDao.findById(bundle.getLong(KEY_ID));

                etNickname.setText(automobile.getNickname());
                cbTravelCar.setChecked(automobile.isTravel());
                rgType.check(getTypeId(automobile.getType()));
                spBrand.setSelection(getBrandId(automobile.getBrand()));
                etModel.setText(automobile.getModel());
                etColor.setText(automobile.getColor());
                etManufactoringYear.setText(automobile.getManufactoringYear());
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
        etNickname.setText(null);
        cbTravelCar.setChecked(false);
        rgType.clearCheck();
        etModel.setText(null);
        etColor.setText(null);
        etManufactoringYear.setText(null);

        showToast(this, getString(R.string.cleaned), Toast.LENGTH_SHORT);
    }

    public void save() {
        if (isFieldsValid()) {
            createOrUpdate();
        }
    }

    private boolean isFieldsValid() {
        return isTextViewValid(this, tvNickname, etNickname)
            && isRadioButtonValid(this, tvType, rgType)
            && isTextViewValid(this, tvModel, etModel)
            && isTextViewValid(this, tvColor, etColor)
            && isTextViewValid(this, tvManufactoringYear, etManufactoringYear);
    }

    private void createOrUpdate() {
        var bundle = getIntent().getExtras();
        var automobile = new Automobile(
            etNickname.getText().toString(),
            cbTravelCar.isChecked(),
            getType(rgType.getCheckedRadioButtonId()),
            (String) spBrand.getSelectedItem(),
            etModel.getText().toString(),
            etColor.getText().toString(),
            etManufactoringYear.getText().toString()
        );
        automobile.setId(bundle.getLong(KEY_ID));

        if (bundle.getInt(KEY_MODE, KEY_ADD_MODE) == KEY_ADD_MODE) {
            automobileDao.create(automobile);
        } else {
            automobileDao.update(automobile);
        }

        setResult(Activity.RESULT_OK, new Intent());
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