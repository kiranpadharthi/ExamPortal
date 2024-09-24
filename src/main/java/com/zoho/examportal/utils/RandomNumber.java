package com.zoho.examportal.utils;


import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomNumber {

    public int getNum(int start, int end){
        return ThreadLocalRandom.current().nextInt(start,end+1);
    }
}
