import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.YahtzeeException;
import com.cdiscount.kata.yahtzee.section.LowerSectionCategory;
import com.cdiscount.kata.yahtzee.section.UpperSection;
import com.cdiscount.kata.yahtzee.section.UpperSectionCategory;
import java.util.Optional;
import org.junit.Test;

public class UpperSectionTest {
    @Test
    public void initUpperSection() {
        UpperSection upperSection = new UpperSection();

        assertThat(upperSection.total()).isEqualTo(0);
        Optional<Long> acesScore = upperSection.getScore(UpperSectionCategory.ACES);
        assertThat(acesScore.isPresent()).isFalse();
    }

    @Test
    public void fillAces() {
        UpperSection upperSection = new UpperSection();

        assertThat(upperSection.total()).isEqualTo(0);
        Roll roll = new Roll(1, 2, 1, 3, 1);

        upperSection.apply(UpperSectionCategory.ACES, roll);

        Optional<Long> acesScore = upperSection.getScore(UpperSectionCategory.ACES);
        assertThat(acesScore.isPresent()).isTrue();
        assertThat(acesScore.get()).isEqualTo(3);
        assertThat(upperSection.total()).isEqualTo(3);
        assertThat(upperSection.totalWithBonus().isPresent()).isEqualTo(false);
    }

    @Test
    public void fillAllNumbersWithoutBonus() {
        UpperSection upperSection = new UpperSection();

        assertThat(upperSection.total()).isEqualTo(0);
        Roll roll = new Roll(1, 2, 1, 3, 1);

        for(UpperSectionCategory category : UpperSectionCategory.values()) {
            upperSection.apply(category, roll);
        }

        assertThat(upperSection.total()).isEqualTo(8);
        assertThat(upperSection.totalWithBonus().get()).isEqualTo(8);
    }

    @Test
    public void fillAllNumbersWithBonus() {
        UpperSection upperSection = new UpperSection();

        assertThat(upperSection.total()).isEqualTo(0);
        int rollValue = 1;
        for(UpperSectionCategory category : UpperSectionCategory.values()) {
            Roll roll = new Roll(rollValue, rollValue, rollValue, rollValue, rollValue);
            upperSection.apply(category, roll);
            rollValue++;
        }

        assertThat(upperSection.total()).isEqualTo(105);
        assertThat(upperSection.totalWithBonus().get()).isEqualTo(140);
    }

    @Test(expected=YahtzeeException.class)
    public void applyWrongCategoryForSection() {
        UpperSection upperSection = new UpperSection();

        Roll roll = new Roll(1, 2, 1, 3, 1);

        upperSection.apply(LowerSectionCategory.THREE_OF_A_KIND, roll);
    }

}
