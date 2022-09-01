package com.ogawalucas.automobilesupplycontrol.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.ogawalucas.automobilesupplycontrol.R;

public class AlertUtils {

    public static void showConfirm(
        Context contexto,
        String message,
        DialogInterface.OnClickListener listener
    ){
        var builder = new AlertDialog.Builder(contexto);

        builder.setTitle(R.string.confirm);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(message);

        builder.setPositiveButton(R.string.yes, listener);
        builder.setNegativeButton(R.string.no, listener);

        builder.create().show();
    }
}
