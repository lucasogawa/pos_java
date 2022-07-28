package com.ogawalucas.exercise05;

public class MyRandomNumber {

    private static final IntervalException EX_INTERVAL =
        new IntervalException("Begin and End must be equal or grather than zero, and Begin must be less than End!");

    private RandomInt randomInt;
    private int oldInt = 0;

    public MyRandomNumber(RandomInt random) {
        this.randomInt = random;
    }

    public int nextRandomNumber(int begin, int end) throws IntervalException {
        validateInterval(begin, end);

        int nextInt;

        do {
            nextInt = randomInt.nextInt(begin, end);
        } while (nextInt == oldInt);

        oldInt = nextInt;

        return nextInt;
    }

    private void validateInterval(int begin, int end) throws IntervalException {
        if (begin < 0 || end < 0 || begin >= end) {
            throw EX_INTERVAL;
        }
    }
}
