package com.ogawalucas.exercise05;

import java.util.Random;

public class RandomInt {

    public int nextInt(int begin, int end) {
        return new Random().nextInt(begin, end);
    }
}
