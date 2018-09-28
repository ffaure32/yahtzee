package com.cdiscount.kata.yahtzee;

import com.cdiscount.kata.yahtzee.section.UpperSectionCategory;
import org.junit.Test;

public class GameTest {

    @Test
    public void initRound() {
        // ARRANGE
        Game game = new Game();
        Roll roll = Roll.newYahtzee(1);

        // ACT
        game.fillUpperCategory(UpperSectionCategory.ACES, roll);

        // ASSERT


    }
}
