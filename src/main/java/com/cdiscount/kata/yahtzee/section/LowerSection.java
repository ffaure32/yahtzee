package com.cdiscount.kata.yahtzee.section;

public class LowerSection extends Section {

    public LowerSection() {
        super(LowerSectionCategory.values());
    }

    @Override
    public long getBonus() {
        return 0;
    }
}
