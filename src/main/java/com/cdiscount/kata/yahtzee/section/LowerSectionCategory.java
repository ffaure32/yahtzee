package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.category.BigStraightCategory;
import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.category.FourOfAKindCategory;
import com.cdiscount.kata.yahtzee.category.FullCategory;
import com.cdiscount.kata.yahtzee.category.LuckyCategory;
import com.cdiscount.kata.yahtzee.category.SmallStraightCategory;
import com.cdiscount.kata.yahtzee.category.ThreeOfAKindCategory;
import com.cdiscount.kata.yahtzee.category.YahtzeeCategory;

public enum LowerSectionCategory implements Category {
    THREE_OF_A_KIND(new ThreeOfAKindCategory()),
    FOUR_OF_A_KIND(new FourOfAKindCategory()),
    FULL_HOUSE(new FullCategory()),
    SMALL_STRAIGHT(new SmallStraightCategory()),
    BIG_STRAIGHT(new BigStraightCategory()),
    YAHTZEE(new YahtzeeCategory()),
    LUCKY(new LuckyCategory());

    private final Category category;

    LowerSectionCategory(Category category) {
        this.category = category;
    }

    public long score(Roll roll) {
        return category.score(roll);
    }

}
