package com.ogawalucas.automobilesupplycontrol.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.ogawalucas.automobilesupplycontrol.R;

public class AlertUtils {

    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showAlert(
        Context context,
        String message,
        DialogInterface.OnClickListener listener
    ){
        var builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.alert);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(message);

        builder.setNeutralButton(R.string.ok, listener);

        builder.create().show();
    }

    public static void showConfirm(
        Context context,
        String message,
        DialogInterface.OnClickListener listener
    ){
        var builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.confirm);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(message);

        builder.setPositiveButton(R.string.yes, listener);
        builder.setNegativeButton(R.string.no, listener);

        builder.create().show();
    }
}
