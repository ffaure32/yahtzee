import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.YahtzeeException;
import com.cdiscount.kata.yahtzee.section.LowerSection;
import com.cdiscount.kata.yahtzee.section.LowerSectionCategory;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class LowerSectionTest {

    private LowerSection lowerSection;

    @Before
    public void setUp() throws Exception {
        lowerSection = new LowerSection();
    }

    @Test
    public void initUpperSection() {
        assertThat(lowerSection.total()).isEqualTo(0);
        Optional<Long> acesScore = lowerSection.getScore(LowerSectionCategory.THREE_OF_A_KIND);
        assertThat(acesScore.isPresent()).isFalse();
    }

    @Test
    public void fillAces() {
        Roll roll = new Roll(1, 2, 1, 3, 1);

        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, roll);

        Optional<Long> acesScore = lowerSection.getScore(LowerSectionCategory.THREE_OF_A_KIND);
        assertThat(acesScore.isPresent()).isTrue();
        assertThat(acesScore.get()).isEqualTo(8);
        assertThat(lowerSection.total()).isEqualTo(8);
        assertThat(lowerSection.totalWithBonus().isPresent()).isEqualTo(false);
    }

    @Test
    public void fillAllNumbers() {
        Roll roll = new Roll(1, 2, 1, 2, 1);

        for(LowerSectionCategory category : LowerSectionCategory.values()) {
            lowerSection.apply(category, roll);
        }

        assertThat(lowerSection.total()).isEqualTo(39);
        assertThat(lowerSection.totalWithBonus().get()).isEqualTo(39);
    }


    @Test
    public void oneYahtzeeBonus() {
        Roll yahtzee = new Roll(2, 2, 2, 2, 2);
        lowerSection.apply(LowerSectionCategory.YAHTZEE, yahtzee);
        Roll missedRoll = new Roll(1, 2, 1, 2, 3);
        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, missedRoll);
        lowerSection.apply(LowerSectionCategory.FOUR_OF_A_KIND, missedRoll);
        lowerSection.apply(LowerSectionCategory.FULL_HOUSE, missedRoll);
        lowerSection.apply(LowerSectionCategory.SMALL_STRAIGHT, missedRoll);
        lowerSection.apply(LowerSectionCategory.BIG_STRAIGHT, missedRoll);

        lowerSection.apply(LowerSectionCategory.LUCKY, yahtzee);

        assertThat(lowerSection.total()).isEqualTo(60);
        assertThat(lowerSection.totalWithBonus().get()).isEqualTo(160);
    }

    @Test
    public void oneYahtzeeWithYahtzeeCategoryMissed() {
        Roll yahtzee = new Roll(2, 2, 2, 2, 2);
        Roll missedRoll = new Roll(1, 2, 1, 2, 3);
        lowerSection.apply(LowerSectionCategory.YAHTZEE, missedRoll);
        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, missedRoll);
        lowerSection.apply(LowerSectionCategory.FOUR_OF_A_KIND, missedRoll);
        lowerSection.apply(LowerSectionCategory.FULL_HOUSE, missedRoll);
        lowerSection.apply(LowerSectionCategory.SMALL_STRAIGHT, missedRoll);
        lowerSection.apply(LowerSectionCategory.BIG_STRAIGHT, missedRoll);

        lowerSection.apply(LowerSectionCategory.LUCKY, yahtzee);

        assertThat(lowerSection.total()).isEqualTo(10);
        assertThat(lowerSection.totalWithBonus().get()).isEqualTo(10);
    }

    @Test
    public void twoYahtzeeBonus() {
        Roll yahtzee = new Roll(2, 2, 2, 2, 2);
        lowerSection.apply(LowerSectionCategory.YAHTZEE, yahtzee);
        Roll missedRoll = new Roll(1, 2, 1, 2, 3);
        lowerSection.apply(LowerSectionCategory.FOUR_OF_A_KIND, missedRoll);
        lowerSection.apply(LowerSectionCategory.FULL_HOUSE, missedRoll);
        lowerSection.apply(LowerSectionCategory.SMALL_STRAIGHT, missedRoll);
        lowerSection.apply(LowerSectionCategory.BIG_STRAIGHT, missedRoll);

        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, yahtzee);
        lowerSection.apply(LowerSectionCategory.LUCKY, yahtzee);

        assertThat(lowerSection.total()).isEqualTo(70);
        assertThat(lowerSection.totalWithBonus().get()).isEqualTo(270);
    }

    @Test(expected = YahtzeeException.class)
    public void figureAlreadyOccupied() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 1, 2, 1);
        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, roll);

        // ACT
        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, roll);
    }
}
