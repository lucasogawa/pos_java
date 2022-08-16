package com.ogawalucas.automobilesupplycontrol;

import android.content.Context;

public enum EType {

    CAR,
    MOTORCYCLE,
    PICKUP,
    TRUCK,
    OTHERS;

    public String toString(Context context) {
        switch (this) {
            case CAR:
                return context.getString(R.string.car);
            case MOTORCYCLE:
                return context.getString(R.string.motorcycle);
            case PICKUP:
                return context.getString(R.string.pickup);
            case TRUCK:
                return context.getString(R.string.truck);
            default:
                return context.getString(R.string.others);
        }
    }
}
