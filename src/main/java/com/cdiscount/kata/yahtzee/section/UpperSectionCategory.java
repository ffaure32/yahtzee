package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.category.NumberCategory;

public enum UpperSectionCategory implements Category {
    ACES,
    TWOS,
    THREES,
    FOURS,
    FIVES,
    SIXES;

    private final Category category;

    UpperSectionCategory() {
        category = new NumberCategory(this.ordinal()+1);
    }

    public long score(Roll roll) {
        return category.score(roll);
    }
}
