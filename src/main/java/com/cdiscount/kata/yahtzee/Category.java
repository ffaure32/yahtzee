package com.cdiscount.kata.yahtzee;

import static java.util.stream.Collectors.groupingBy;

public interface Category {
    public long score(Roll roll);


}
