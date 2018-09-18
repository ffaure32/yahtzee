public abstract class FixedScoreCategory implements Category {

    public static final int DEFAULT_SCORE = 0;

    @Override
    public long score(Roll roll) {
        if(verifyCondition(roll)) {
            return getFixedScore();
        }
        return DEFAULT_SCORE;
    }

    public abstract int getFixedScore();

    public abstract boolean verifyCondition(Roll roll);
}
