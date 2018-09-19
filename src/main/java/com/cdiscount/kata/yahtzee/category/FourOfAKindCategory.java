package com.cdiscount.kata.yahtzee.category;

public class FourOfAKindCategory extends NumberOfKindCategory {

    @Override
    public int getMinNumberOccurrences() {
        return 4;
    }
}
