package com.ogawalucas.automobilesupplycontrol.utils;

import static com.ogawalucas.automobilesupplycontrol.constants.StringConstants.MSG_EMPTY_FIELDS;
import static com.ogawalucas.automobilesupplycontrol.utils.AlertUtils.showToast;

import android.content.Context;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ogawalucas.automobilesupplycontrol.R;

public class ValidateUtils {

    public static boolean isTextViewValid(Context context, TextView textView, EditText editText) {
        if (editText.getText() == null || editText.getText().length() == 0) {
            showToast(context, String.format(MSG_EMPTY_FIELDS, context.getString(R.string.empty_fields), textView.getText()), Toast.LENGTH_LONG);
            editText.requestFocus();
            return false;
        }

        return true;
    }

    public static boolean isRadioButtonValid(Context context, TextView textView, RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            showToast(context, String.format(MSG_EMPTY_FIELDS, context.getString(R.string.empty_fields), textView.getText()), Toast.LENGTH_LONG);
            radioGroup.requestFocus();
            return false;
        }

        return true;
    }
}
