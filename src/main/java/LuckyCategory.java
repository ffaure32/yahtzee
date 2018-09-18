import java.util.Arrays;

public class LuckyCategory implements Category {

    @Override
    public long score(Roll roll) {
        return Arrays.stream(roll.dices).sum();
    }
}
