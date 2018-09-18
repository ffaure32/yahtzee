import java.util.Collection;
import java.util.Map;

public class FullCategory extends FixedScoreCategory {

    public static final int FULL_SCORE = 25;

    @Override
    public long score(Roll roll) {
        Map<Integer, Long> dicesByNumber = dicesByNumber(roll);
        if(dicesByNumber.values().contains(Long.valueOf(3))
            && dicesByNumber.values().contains(Long.valueOf(2))) {
            return FULL_SCORE;
        }
        return 0;
    }

    @Override
    public int getFixedScore() {
        return FULL_SCORE;
    }

    @Override
    public boolean verifyCondition(Roll roll) {
        Collection<Long> dicesByNumber = dicesByNumber(roll).values();
        return (dicesByNumber.contains(Long.valueOf(3))
            && dicesByNumber.contains(Long.valueOf(2)));
    }
}
