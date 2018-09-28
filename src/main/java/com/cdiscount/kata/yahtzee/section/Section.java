package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.YahtzeeException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Section {

    private final Map<Category, Optional<Long>> rollsPerSection = new HashMap<>();

    public Section(Category[] categories) {
        Arrays.stream(categories)
            .forEach(category -> rollsPerSection.put(category, Optional.empty()));
    }

    public Optional<Long> getScore(Category category) {
        return rollsPerSection.get(category);
    }

    public void apply(Category section, Roll roll) {
        checkPreconditions(section);
        specialBonus(section, roll);
        rollsPerSection.put(section, Optional.of(section.score(roll)));
    }

    protected abstract void specialBonus(Category section, Roll roll);

    private void checkPreconditions(Category section) {
        verifyValidSection(section);
        verifyFreeCategory(section);
    }

    private void verifyFreeCategory(Category section) {
        if(rollsPerSection.get(section).isPresent()) {
            throw new YahtzeeException("La catégorie est déjà validée");
        }
    }

    private void verifyValidSection(Category section) {
        if(!rollsPerSection.keySet().contains(section)) {
            throw new YahtzeeException("La catégorie n'est pas valable pour cette section");
        }
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

    protected abstract long getBonus();
}
