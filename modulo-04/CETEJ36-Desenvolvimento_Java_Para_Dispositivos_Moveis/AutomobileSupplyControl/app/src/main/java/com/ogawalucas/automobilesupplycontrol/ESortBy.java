package com.ogawalucas.automobilesupplycontrol;

public enum ESortBy {

    ASC(1),
    DESC(-1);

    private int value;

    ESortBy(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
