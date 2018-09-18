import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public interface Category {
    public long score(Roll roll);

    default Map<Integer, Long> dicesByNumber(Roll roll) {
        return Arrays.stream(roll.dices)
            .mapToObj(Integer::valueOf)
            .collect(groupingBy(Function.identity(), counting()));
    }


}
