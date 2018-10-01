package com.cdiscount.kata.yahtzee;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.section.LowerSectionCategory;
import com.cdiscount.kata.yahtzee.section.UpperSectionCategory;
import org.junit.Test;

public class RoundTest {

    @Test
    public void aucunBonus() {
        // ARRANGE
        Round round = new Round();
        round.fillCategory(LowerSectionCategory.YAHTZEE, Roll.newYahtzee(3));

        // ACT
        int rewardCount = round.getYahtzeeRewardCount();

        // ASSERT
        assertThat(rewardCount).isEqualTo(0);
    }

    @Test
    public void unBonusSectionDuBas() {
        // ARRANGE
        Round round = new Round();
        round.fillCategory(LowerSectionCategory.YAHTZEE, Roll.newYahtzee(3));
        round.fillCategory(LowerSectionCategory.THREE_OF_A_KIND, Roll.newYahtzee(2));

        // ACT
        int rewardCount = round.getYahtzeeRewardCount();

        // ASSERT
        assertThat(rewardCount).isEqualTo(1);
    }

    @Test
    public void unBonusSectionDuHaut() {
        // ARRANGE
        Round round = new Round();
        round.fillCategory(LowerSectionCategory.YAHTZEE, Roll.newYahtzee(3));
        round.fillCategory(UpperSectionCategory.TWOS, Roll.newYahtzee(2));

        // ACT
        int rewardCount = round.getYahtzeeRewardCount();

        // ASSERT
        assertThat(rewardCount).isEqualTo(1);
    }

    @Test
    public void bonusFailedBecauseNoYathzeeInYathzeeCategory() {
        // PREPARE
        Round round = new Round();
        round.fillCategory(LowerSectionCategory.YAHTZEE, new Roll(1, 2, 3, 4, 5));
        round.fillCategory(UpperSectionCategory.TWOS, Roll.newYahtzee(2));

        // ACT
        int rewardCount = round.getYahtzeeRewardCount();

        // ASSERT
        assertThat(rewardCount).isEqualTo(0);
    }

    @Test
    public void roundGetLowerSectionScoreShouldNotAddBonusYathzeePoints() {
        // PREPARE
        Round round = new Round();
        Roll roll = new Roll(1, 2, 3, 4, 5);
        round.fillCategory(LowerSectionCategory.THREE_OF_A_KIND, roll);
        round.fillCategory(LowerSectionCategory.FOUR_OF_A_KIND, roll);
        round.fillCategory(LowerSectionCategory.FULL_HOUSE, roll);
        round.fillCategory(LowerSectionCategory.SMALL_STRAIGHT, roll);
        round.fillCategory(LowerSectionCategory.BIG_STRAIGHT, roll);
        round.fillCategory(LowerSectionCategory.YAHTZEE, roll);

        // ACT
        int lowerSectionScore = round.getLowerSectionScore();

        // ASSERT
        assertThat(lowerSectionScore).isEqualTo(0 + 0 + 0 + 30 + 40 + 0);
    }

    @Test
    public void roundGetUpperSectionScoreShouldAddBonusPoints() {
        // PREPARE
        Round round = new Round();
        int i = 1;
        round.fillCategory(UpperSectionCategory.ACES, Roll.newYahtzee(i++));
        round.fillCategory(UpperSectionCategory.TWOS, Roll.newYahtzee(i++));
        round.fillCategory(UpperSectionCategory.THREES, Roll.newYahtzee(i++));
        round.fillCategory(UpperSectionCategory.FOURS, Roll.newYahtzee(i++));
        round.fillCategory(UpperSectionCategory.FIVES, Roll.newYahtzee(i++));
        round.fillCategory(UpperSectionCategory.SIXES, Roll.newYahtzee(i));

        // ACT
        int upperSectionScore = round.getUpperSectionScore();

        // ASSERT
        assertThat(upperSectionScore).isEqualTo(5+10+15+20+25+30+35);
    }
}
