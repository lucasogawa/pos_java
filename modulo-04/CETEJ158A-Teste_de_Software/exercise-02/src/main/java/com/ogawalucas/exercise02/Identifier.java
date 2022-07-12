package com.ogawalucas.exercise02;

public class Identifier {

    private static final String PATTERN = "[a-zA-Z]{1}[a-zA-Z0-9]{0,5}";

    public void validate(String identifier) {
        if (!identifier.matches(PATTERN)) {
            throw new RuntimeException("Invalid identifier.");
        }
    }
}
