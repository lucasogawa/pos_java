package com.ogawalucas.activity04client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SumService {

    @Autowired
    private SumClient client;

    public int sum(int number1, int number2) {
        return client.sum(number1, number2);
    }
}
