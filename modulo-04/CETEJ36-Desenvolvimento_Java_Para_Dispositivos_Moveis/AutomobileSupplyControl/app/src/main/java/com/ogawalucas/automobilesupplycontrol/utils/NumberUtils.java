package com.ogawalucas.automobilesupplycontrol.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    public static double roundDouble(double number) {
        return BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
    }
}
