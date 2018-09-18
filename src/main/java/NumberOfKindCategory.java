import java.util.Arrays;

public abstract class NumberOfKindCategory implements Category {

    @Override
    public long score(Roll roll) {
        if(checkValidCategory(roll)) {
            return Arrays.stream(roll.dices).sum();
        }
        return 0L;
    }

    public boolean checkValidCategory(Roll roll) {
        return maxOccurrencesOfSameDice(roll)
            >= getMinNumberOccurrences();
    }

    private long maxOccurrencesOfSameDice(Roll roll) {
        return roll.dicesByNumber()
            .values().stream().mapToLong(v -> v)
            .max().orElse(0L);
    }

    public abstract int getMinNumberOccurrences();
}
