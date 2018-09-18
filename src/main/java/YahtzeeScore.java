public class YahtzeeScore {

    public long compute(Roll roll, Category category) {
        return category.score(roll);
    }

    public long compute(Roll roll, CategoryLegacy category) {
        return category.compute(roll);
    }
}
