package com.cdiscount.kata.yahtzee.section;

public class UpperSection extends Section {

    public UpperSection() {
        super(UpperSectionCategory.values());
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
