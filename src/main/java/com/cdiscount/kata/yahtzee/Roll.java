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
        verifyDicesNumber(dices);
        verifyDicesValues(dices);
        this.dices = dices;
    }

    private void verifyDicesValues(int[] dices) {
        Arrays.stream(dices).forEach(i -> {
            if(i<1 || i > 6) {
                throw new IllegalArgumentException("dé invalide");
            }
        });
    }

    private void verifyDicesNumber(int[] dices) {
        if(dices.length != 5) {
            throw new IllegalArgumentException("Un lancer doit comporter 5 dés");
        }
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
