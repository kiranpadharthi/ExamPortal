package com.zoho.examportal.utils;

import org.springframework.stereotype.Component;

@Component
public class DifficultyFinder {

    public int getLevel(int score){

        if(score<2){
            return 0;
        }
        else if(score<4){
            return 1;
        }
        else{
            return 2;
        }

    }
}
