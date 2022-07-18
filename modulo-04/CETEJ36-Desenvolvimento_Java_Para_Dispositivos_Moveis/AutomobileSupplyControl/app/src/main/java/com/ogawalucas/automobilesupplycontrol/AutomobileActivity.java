package com.ogawalucas.automobilesupplycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AutomobileActivity extends AppCompatActivity {

    private EditText edNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mapAttributes();
    }

    private void mapAttributes() {
        edNickname = findViewById(R.id.etNickname);
    }

    public void clearEditTexts(View view) {
        edNickname.setText(null);
    }
}