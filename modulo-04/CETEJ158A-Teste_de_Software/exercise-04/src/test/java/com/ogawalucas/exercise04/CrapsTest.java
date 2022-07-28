package com.ogawalucas.exercise04;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class CrapsTest {

    @Test
    public void test_01() {
        var dadoMock = Mockito.mock(Dice.class);
        var craps = new Craps();

        craps.setDice(dadoMock);

        Mockito.when(dadoMock.roll())
            .thenReturn(1);
        craps.rollDices();

        assertTrue(craps.isTheEndOfTheGame());
        assertEquals(2, craps.getWinner());
    }

    @Test
    public void test_02() {
        var dadoMock = Mockito.mock(Dice.class);
        var craps = new Craps();

        craps.setDice(dadoMock);

        Mockito.when(dadoMock.roll())
            .thenReturn(5, 2);
        craps.rollDices();

        assertTrue(craps.isTheEndOfTheGame());
        assertEquals(1, craps.getWinner());
    }

    @Test
    public void test_03() {
        var dadoMock = Mockito.mock(Dice.class);
        var craps = new Craps();

        craps.setDice(dadoMock);

        Mockito.when(dadoMock.roll()).thenReturn(3, 5);
        craps.rollDices();
        assertFalse(craps.isTheEndOfTheGame());

        Mockito.when(dadoMock.roll()).thenReturn(6, 5);
        craps.rollDices();
        assertFalse(craps.isTheEndOfTheGame());

        Mockito.when(dadoMock.roll()).thenReturn(5, 2);
        craps.rollDices();

        assertTrue(craps.isTheEndOfTheGame());
        assertEquals(2, craps.getWinner());
    }

    @Test
    public void test_04() {
        var dadoMock = Mockito.mock(Dice.class);
        var craps = new Craps();

        craps.setDice(dadoMock);

        Mockito.when(dadoMock.roll()).thenReturn(1, 3);
        craps.rollDices();
        assertFalse(craps.isTheEndOfTheGame());

        Mockito.when(dadoMock.roll()).thenReturn(2, 2);
        craps.rollDices();

        assertTrue(craps.isTheEndOfTheGame());
        assertEquals(1, craps.getWinner());
    }
}
