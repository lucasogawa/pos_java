package com.ogawalucas.automobilesupplycontrol.utils;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String DATE_FORMAT = "MM/dd/yyyy";

    public static String format(Context context, Date date) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), DATE_FORMAT))
                .format(date);
        }

        return DateFormat.getMediumDateFormat(context).format(date);
    }
}
