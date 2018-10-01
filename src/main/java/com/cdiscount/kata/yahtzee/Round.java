package com.cdiscount.kata.yahtzee;

import static java.util.Arrays.asList;

import com.cdiscount.kata.yahtzee.section.LowerSection;
import com.cdiscount.kata.yahtzee.section.LowerSectionCategory;
import com.cdiscount.kata.yahtzee.section.Section;
import com.cdiscount.kata.yahtzee.section.UpperSection;

public class Round {

    public static final long ZERO = 0l;
    private LowerSection lowerSection = new LowerSection();
    private UpperSection upperSection = new UpperSection();
    private int yahtzeeBonusCount;


    public void fillCategory(Category category, Roll roll) {
        incrementYahtzeeCount(roll);
        getSection(category).apply(category, roll);
    }

    private void incrementYahtzeeCount(Roll roll) {
        if (lowerSection.getScore(LowerSectionCategory.YAHTZEE).orElse(ZERO) > 0) {
            if (roll.isYahtzee()) {
                yahtzeeBonusCount++;
            }
        }
    }

    private Section getSection(Category category) {
        if (asList(LowerSectionCategory.values()).contains(category)) {
            return lowerSection;
        }
        return upperSection;
    }

    public int getYahtzeeRewardCount() {
        return yahtzeeBonusCount;
    }

    public int getLowerSectionScore() {
        return Math.toIntExact(lowerSection.total());
    }

    public int getUpperSectionScore() {
        return Math.toIntExact(upperSection.totalWithBonus().orElse(0l));
    }
}
