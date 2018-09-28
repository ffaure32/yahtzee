package com.cdiscount.kata.yahtzee;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.section.LowerSectionCategory;
import com.cdiscount.kata.yahtzee.section.UpperSection;
import com.cdiscount.kata.yahtzee.section.UpperSectionCategory;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class UpperSectionTest {

    private UpperSection upperSection;

    @Before
    public void setUp() throws Exception {
        upperSection = new UpperSection();
    }

    @Test
    public void initUpperSection() {

        Optional<Long> acesScore = upperSection.getScore(UpperSectionCategory.ACES);
        assertThat(acesScore.isPresent()).isFalse();
    }

    @Test
    public void fillAces() {
        Roll roll = new Roll(1, 2, 1, 3, 1);

        upperSection.apply(UpperSectionCategory.ACES, roll);

        Optional<Long> acesScore = upperSection.getScore(UpperSectionCategory.ACES);
        assertThat(acesScore.isPresent()).isTrue();
        assertThat(acesScore.get()).isEqualTo(3);
        assertThat(upperSection.total()).isEqualTo(3);
        assertThat(upperSection.totalWithBonus().isPresent()).isEqualTo(false);
    }

    @Test
    public void fillAllNumbersWithoutBonus() {
        Roll roll = new Roll(1, 2, 1, 3, 1);

        for(UpperSectionCategory category : UpperSectionCategory.values()) {
            upperSection.apply(category, roll);
        }

        assertThat(upperSection.total()).isEqualTo(8);
        assertThat(upperSection.totalWithBonus().get()).isEqualTo(8);
    }

    @Test
    public void fillAllNumbersWithBonus() {
        int rollValue = 1;
        for(UpperSectionCategory category : UpperSectionCategory.values()) {
            Roll roll = Roll.newYahtzee(rollValue);
            upperSection.apply(category, roll);
            rollValue++;
        }

        assertThat(upperSection.total()).isEqualTo(105);
        assertThat(upperSection.totalWithBonus().get()).isEqualTo(140);
    }


    @Test(expected=YahtzeeException.class)
    public void applyWrongCategoryForSection() {
        Roll roll = new Roll(1, 2, 1, 3, 1);

        upperSection.apply(LowerSectionCategory.THREE_OF_A_KIND, roll);
    }

}
