import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SmallStraightCategory extends FixedScoreCategory {
    private static final Set<Integer> SET1 = generateFourStraight(1);
    private static final Set<Integer> SET2 = generateFourStraight(2);
    private static final Set<Integer> SET3 = generateFourStraight(3);

    @Override
    public int getFixedScore() {
        return 30;
    }

    @Override
    public boolean verifyCondition(Roll roll) {
        Set<Integer> set = roll.distinctDices();
        return set.containsAll(SET1)
            || set.containsAll(SET2)
            || set.containsAll(SET3);
    }

    public static Set<Integer> generateFourStraight(int start) {
        return IntStream.range(start, start+4).mapToObj(Integer::new).collect(Collectors.toSet());
    }
}
