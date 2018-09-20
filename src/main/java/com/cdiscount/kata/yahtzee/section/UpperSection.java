package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.Roll;

public class UpperSection extends Section {

    public UpperSection() {
        super(UpperSectionCategory.values());
    }

    @Override
    protected void specialBonus(Category section, Roll roll) {
        // doNothing
    }

    @Override
    public long getBonus() {
        if(total()>=63) {
            return 35;
        } else {
            return 0;
        }
    }
}
