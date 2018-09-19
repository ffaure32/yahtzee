package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.Roll;
import java.util.Arrays;

public class LuckyCategory implements Category {

    @Override
    public long score(Roll roll) {
        return Arrays.stream(roll.dices).sum();
    }
}
