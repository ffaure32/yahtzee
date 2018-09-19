package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.Roll;
import java.util.Arrays;

public class NumberCategory implements Category {

    private final int diceNumber;

    public NumberCategory(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    @Override
    public long score(Roll roll) {
        return Arrays.stream(roll.dices).filter(i -> i == this.diceNumber).sum();
    }
}
