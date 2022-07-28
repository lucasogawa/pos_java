package com.ogawalucas.exercise05;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MyRandomNumberTest {

    @Test(expected = IntervalException.class)
    public void mustThrowException_whenBeginIsLessThanZero() throws IntervalException {
        new MyRandomNumber(null).nextRandomNumber(-1, 0);
    }

    @Test(expected = IntervalException.class)
    public void mustThrowException_whenEndIsLessThanZero() throws IntervalException {
        new MyRandomNumber(null).nextRandomNumber(0, -1);
    }

    @Test(expected = IntervalException.class)
    public void mustThrowException_whenBeginIsEqualOrGratherThanEnd() throws IntervalException {
        new MyRandomNumber(null).nextRandomNumber(0, 0);

        new MyRandomNumber(null).nextRandomNumber(1, 0);
    }

    @Test
    public void mustReturnNextInt_whenIntervalIsValid() throws IntervalException {
        var randomIntMock = Mockito.mock(RandomInt.class);
        var myRandomNumber = new MyRandomNumber(randomIntMock);

        when(randomIntMock.nextInt(0, 1))
            .thenReturn(1);

        assertEquals(
            1,
            myRandomNumber.nextRandomNumber(0, 1)
        );
    }

    @Test
    public void mustReturnNextIntDifferenteThanLastInt_whenIntervalIsValid() throws IntervalException {
        var randomIntMock = Mockito.mock(RandomInt.class);
        var myRandomNumber = new MyRandomNumber(randomIntMock);

        when(randomIntMock.nextInt(0, 10))
            .thenReturn(1, 1, 10);

        myRandomNumber.nextRandomNumber(0, 10);

        assertEquals(
            10,
            myRandomNumber.nextRandomNumber(0, 10)
        );
    }
}
