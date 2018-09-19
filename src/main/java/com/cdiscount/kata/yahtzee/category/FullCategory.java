package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Roll;
import java.util.Collection;

public class FullCategory extends FixedScoreCategory {

    public static final int FULL_SCORE = 25;

    @Override
    public int getFixedScore() {
        return FULL_SCORE;
    }

    @Override
    public boolean verifyCondition(Roll roll) {
        Collection<Long> dicesByNumber = roll.dicesByNumber().values();
        return (dicesByNumber.contains(Long.valueOf(3))
            && dicesByNumber.contains(Long.valueOf(2)));
    }
}
