import java.util.Arrays;

public enum CategoryLegacy {
    ONES(1), TWOS(2), THREES(3), FOURS(4), FIVES(5), SIXES(6), THREE_OF_A_KIND() {
        @Override
        public long compute(Roll roll) {
            return Arrays.stream(roll.dices).sum();
        }
    }, FOUR_OF_A_KIND() {
        @Override
        public long compute(Roll roll) {
            return Arrays.stream(roll.dices).sum();
        }
    };

    public final int diceNumber;
    CategoryLegacy(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    CategoryLegacy() {
        diceNumber = -1;
    }

    public long compute(Roll roll) {
        return Arrays.stream(roll.dices).filter(i -> i == this.diceNumber).sum();
    }

}
