public class YahtzeeCategory extends FixedScoreCategory {

    @Override
    public int getFixedScore() {
        return 50;
    }

    @Override
    public boolean verifyCondition(Roll roll) {
        return roll.distinctDices().size() == 1;
    }
}
