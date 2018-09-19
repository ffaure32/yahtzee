import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.section.LowerSection;
import com.cdiscount.kata.yahtzee.section.LowerSectionCategory;
import java.util.Optional;
import org.junit.Test;

public class LowerSectionTest {
    @Test
    public void initUpperSection() {
        LowerSection lowerSection = new LowerSection();

        assertThat(lowerSection.total()).isEqualTo(0);
        Optional<Long> acesScore = lowerSection.getScore(LowerSectionCategory.THREE_OF_A_KIND);
        assertThat(acesScore.isPresent()).isFalse();
    }

    @Test
    public void fillAces() {
        LowerSection lowerSection = new LowerSection();

        Roll roll = new Roll(1, 2, 1, 3, 1);

        lowerSection.apply(LowerSectionCategory.THREE_OF_A_KIND, roll);

        Optional<Long> acesScore = lowerSection.getScore(LowerSectionCategory.THREE_OF_A_KIND);
        assertThat(acesScore.isPresent()).isTrue();
        assertThat(acesScore.get()).isEqualTo(8);
        assertThat(lowerSection.total()).isEqualTo(8);
        assertThat(lowerSection.totalWithBonus()).isEqualTo(8);
    }

    @Test
    public void fillAllNumbers() {
        LowerSection lowerSection = new LowerSection();

        Roll roll = new Roll(1, 2, 1, 2, 1);

        for(LowerSectionCategory category : LowerSectionCategory.values()) {
            lowerSection.apply(category, roll);
        }

        assertThat(lowerSection.total()).isEqualTo(39);
        assertThat(lowerSection.totalWithBonus()).isEqualTo(139);
    }

}
