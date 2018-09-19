package com.cdiscount.kata.yahtzee.category;

public class ThreeOfAKindCategory extends NumberOfKindCategory {

    @Override
    public int getMinNumberOccurrences() {
        return 3;
    }
}
