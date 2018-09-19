package com.cdiscount.kata.yahtzee;

import org.junit.Test;

public class RollTest {
    @Test(expected = IllegalArgumentException.class)
    public void wrongNumberOfDices() {
        Roll roll = new Roll(1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongDice() {
        Roll roll = new Roll(1, 2, 3, 4, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void diceTooBig() {
        Roll roll = new Roll(1, 2, 3, 4, 7);
    }

}