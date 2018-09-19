package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Roll;

public class YahtzeeCategory extends FixedScoreCategory {

    @Override
    public int getFixedScore() {
        return 50;
    }

    @Override
    public boolean verifyCondition(Roll roll) {
        return roll.distinctDices().size() == 1;
    }
}
