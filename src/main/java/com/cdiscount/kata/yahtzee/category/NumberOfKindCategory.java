package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.Roll;
import java.util.Arrays;

public abstract class NumberOfKindCategory implements Category {

    @Override
    public long score(Roll roll) {
        if(checkValidCategory(roll)) {
            return Arrays.stream(roll.dices).sum();
        }
        return 0L;
    }

    private boolean checkValidCategory(Roll roll) {
        return maxOccurrencesOfSameDice(roll)
            >= getMinNumberOccurrences();
    }

    private long maxOccurrencesOfSameDice(Roll roll) {
        return roll.dicesByNumber()
            .values().stream().mapToLong(v -> v)
            .max().orElse(0L);
    }

    protected abstract int getMinNumberOccurrences();
}
