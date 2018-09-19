package com.cdiscount.kata.yahtzee.category;

import com.cdiscount.kata.yahtzee.Roll;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BigStraightCategory extends FixedScoreCategory {

    private static final Set<Integer> SET1 = generateFiveStraight(1);
    private static final Set<Integer> SET2 = generateFiveStraight(2);

    @Override
    public int getFixedScore() {
        return 40;
    }

    @Override
    public boolean verifyCondition(Roll roll) {
        Set<Integer> set = roll.distinctDices();
        return set.containsAll(SET1)
            || set.containsAll(SET2);
    }

    public static Set<Integer> generateFiveStraight(int start) {
        return IntStream.range(start, start + 5).mapToObj(Integer::new).collect(Collectors.toSet());
    }
}
