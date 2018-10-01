package com.cdiscount.kata.yahtzee.section;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.YahtzeeException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Section {

    private final Map<Category, Optional<Long>> scorePerCategory = new HashMap<>();

    public Section(Category[] categories) {
        Arrays.stream(categories)
            .forEach(category -> scorePerCategory.put(category, Optional.empty()));
    }

    public Optional<Long> getScore(Category category) {
        return scorePerCategory.get(category);
    }

    public void apply(Category section, Roll roll) {
        checkPreconditions(section);
        scorePerCategory.put(section, Optional.of(section.score(roll)));
    }

    private void checkPreconditions(Category section) {
        verifyValidCategory(section);
        verifyFreeCategory(section);
    }

    private void verifyFreeCategory(Category section) {
        if(scorePerCategory.get(section).isPresent()) {
            throw new YahtzeeException("La catégorie est déjà validée");
        }
    }

    private void verifyValidCategory(Category section) {
        if(!scorePerCategory.keySet().contains(section)) {
            throw new YahtzeeException("La catégorie n'est pas valable pour cette section");
        }
    }

    public Long total() {
        long sectionTotalScore = scorePerCategory.values()
            .stream()
            .map(o -> o.orElse(0L))
            .mapToLong(Long::longValue).sum();

        return sectionTotalScore ;
    }

    public Optional<Long> totalWithBonus() {
        if (sectionTerminee()) {
            return Optional.of(total() + getBonus());
        } else {
            return Optional.empty();
        }
    }

    private boolean sectionTerminee() {
        return scorePerCategory.values().stream().filter(o -> !o.isPresent()).count() == 0;
    }

    protected abstract long getBonus();
}
