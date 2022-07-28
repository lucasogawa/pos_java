package com.ogawalucas.exercise04;

public class Craps {

    private int sum, points, winner;
    private boolean firstRound = true;
    private Dice dice = new Dice();

    public int getWinner() {
        return winner;
    }

    void setDice(Dice newDice) {
        this.dice = newDice;
    }

    public boolean isTheEndOfTheGame() {
        return winner == 1 || winner == 2;
    }

    public int rollDices() {
        sum = dice.roll() + dice.roll();

        if (firstRound) {
            if (sum == 7 || sum == 11)
                winner = 1;
            else if (sum == 2 || sum == 3 || sum == 12)
                winner = 2;
            else
                points = sum;

            firstRound = false;
        } else {
            if (sum == points)
                winner = 1;
            else if (sum == 7)
                winner = 2;
        }

        return sum;
    }
}
