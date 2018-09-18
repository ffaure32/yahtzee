import java.util.Map;

public class FullCategory implements Category {

    @Override
    public long score(Roll roll) {
        Map<Integer, Long> dicesByNumber = dicesByNumber(roll);
        if(dicesByNumber.values().contains(Long.valueOf(3))
            && dicesByNumber.values().contains(Long.valueOf(2))) {
            return 25;
        }
        return 0;
    }
}
