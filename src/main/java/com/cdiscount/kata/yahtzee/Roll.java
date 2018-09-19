package com.cdiscount.kata.yahtzee;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Roll {

    public final int[] dices;

    public Roll(int ... dices) {
        this.dices = dices;
    }

    public Map<Integer, Long> dicesByNumber() {
        return Arrays.stream(dices)
            .mapToObj(Integer::valueOf)
            .collect(groupingBy(Function.identity(), counting()));
    }

    public Set<Integer> distinctDices() {
        return Arrays.stream(dices)
            .mapToObj(Integer::valueOf)
            .sorted()
            .collect(Collectors.toSet());
    }
}
