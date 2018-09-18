import java.util.Arrays;

public class NumberCategory implements Category {

    private final int diceNumber;

    public NumberCategory(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    @Override
    public long score(Roll roll) {
        return Arrays.stream(roll.dices).filter(i -> i == this.diceNumber).sum();
    }
}
