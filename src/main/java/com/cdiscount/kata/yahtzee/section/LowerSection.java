package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.Roll;
import java.util.Optional;

public class LowerSection extends Section {
    private int yahtzeeBonusCount = 0;

    public LowerSection() {
        super(LowerSectionCategory.values());
    }

    @Override
    protected void specialBonus(Category section, Roll roll) {
        if(roll.isYahtzee() && yahtzeeAlreadyRealized()) {
            yahtzeeBonusCount++;
        }
    }

    private boolean yahtzeeAlreadyRealized() {
        Optional<Long> yahtzeeRoll = getScore(LowerSectionCategory.YAHTZEE);
        return yahtzeeRoll.orElse(0L) > 0L;
    }

    @Override
    public long getBonus() {
        return 100 * yahtzeeBonusCount;
    }
}
