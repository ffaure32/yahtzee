import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return dicesByNumber(roll)
            .values().stream().mapToLong(v -> v)
            .max().orElse(0L);
    }

    private Map<Integer, Long> dicesByNumber(Roll roll) {
        return Arrays.stream(roll.dices)
            .mapToObj(Integer::valueOf)
            .collect(groupingBy(Function.identity(), counting()));
    }

    public abstract int getMinNumberOccurrences();
}
