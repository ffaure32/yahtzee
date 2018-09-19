package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.Category;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Section {

    private Map<Category, Optional<Long>> rollsPerSection = new HashMap<>();

    public Section(Category[] categories) {
        Arrays.stream(categories)
            .forEach(category -> rollsPerSection.put(category, Optional.empty()));

    }
    public Optional<Long> getScore(Category category) {
        return rollsPerSection.get(category);
    }

    public void apply(Category section, Roll roll) {
        if(!rollsPerSection.keySet().contains(section)) {
            throw new IllegalArgumentException("La catégorie n'est pas valable pour cette section");
        }
        rollsPerSection.put(section, Optional.of(section.score(roll)));
    }

    public Long total() {
        return rollsPerSection.values()
            .stream()
            .map(o -> o.orElse(0L))
            .mapToLong(Long::longValue).sum();
    }

    public Optional<Long> totalWithBonus() {
        if (sectionTerminee()) {
            return Optional.of(total() + getBonus());
        } else {
            return Optional.empty();
        }
    }

    private boolean sectionTerminee() {
        return rollsPerSection.values().stream().filter(o -> !o.isPresent()).count() == 0;
    }

    public abstract long getBonus();
}
