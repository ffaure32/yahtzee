package com.cdiscount.kata.yahtzee;

public class YahtzeeScore {

    public long compute(Roll roll, Category category) {
        return category.score(roll);
    }

}