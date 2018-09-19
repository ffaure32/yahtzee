package com.cdiscount.kata.yahtzee.section;

public class UpperSection extends Section {

    public UpperSection() {
        super(UpperSectionCategory.values());
    }

    @Override
    public long getBonus() {
        return 35;
    }
}
