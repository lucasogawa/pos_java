package com.ogawalucas.exercise02;

import org.junit.Test;

public class IdentifierTest {

    private final Identifier identifier = new Identifier();

    // Case 00
    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenTheIndentifierHasLengthZero() {
        identifier.validate("");
    }

    // Case 01, 03 and 05
    @Test
    public void shouldNotThrowException_whenTheIndentifierStartsWithLetterContainsLetterNumbersAndHasLegthOneToSix() {
        identifier.validate("T0");
    }

    // Case 02
    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenTheIndentifierHasLengthMoreThanSix() {
        identifier.validate("A1b2C3d");
    }

    // Case 04
    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenTheIndentifierDontStartsWithLetter() {
        identifier.validate("2B3");
    }

    // Case 06
    @Test(expected = RuntimeException.class)
    public void shouldThrowException_whenTheIndentifierHasCharacterOtherThanetterAndNumbers() {
        identifier.validate("Z-12");
    }
}
