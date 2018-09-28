package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.Roll;

public abstract class FixedScoreCategory implements Category {

    public static final int DEFAULT_SCORE = 0;

    @Override
    public long score(Roll roll) {
        if(verifyCondition(roll)) {
            return getFixedScore();
        }
        return DEFAULT_SCORE;
    }

    protected abstract int getFixedScore();

    protected abstract boolean verifyCondition(Roll roll);
}
