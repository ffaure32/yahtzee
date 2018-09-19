import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.Roll;
import com.cdiscount.kata.yahtzee.category.BigStraightCategory;
import com.cdiscount.kata.yahtzee.Category;
import com.cdiscount.kata.yahtzee.category.FourOfAKindCategory;
import com.cdiscount.kata.yahtzee.category.FullCategory;
import com.cdiscount.kata.yahtzee.category.LuckyCategory;
import com.cdiscount.kata.yahtzee.category.NumberCategory;
import com.cdiscount.kata.yahtzee.category.SmallStraightCategory;
import com.cdiscount.kata.yahtzee.category.ThreeOfAKindCategory;
import com.cdiscount.kata.yahtzee.category.YahtzeeCategory;
import com.cdiscount.kata.yahtzee.YahtzeeScore;
import org.junit.Before;
import org.junit.Test;

public class YahtzeeScoreTest {

    private YahtzeeScore score;

    @Before
    public void setUp() throws Exception {
        score = new YahtzeeScore();
    }

    @Test
    public void test_all_ones() {
        Roll roll = new Roll(1, 1, 1, 1, 1);

        Category ones = new NumberCategory(1);
        long result = score.compute(roll, ones);

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void test_one_one() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 3, 4, 5);

        // ACT
        long result = score.compute(roll, new NumberCategory(1));

        // ASSERT
        assertThat(result).isEqualTo(1);


    }

    @Test
    public void test_one_two() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 3, 4, 5);

        // ACT
        long result = score.compute(roll, new NumberCategory(2));

        // ASSERT
        assertThat(result).isEqualTo(2);


    }

    @Test
    public void test_two_sixes() {
        // ARRANGE
        Roll roll = new Roll(3, 4, 6, 6, 5);

        // ACT
        // ASSERT
        assertThat(score.compute(roll, new NumberCategory(6))).isEqualTo(12);
        assertThat(score.compute(roll, new NumberCategory(5))).isEqualTo(5);
        assertThat(score.compute(roll, new NumberCategory(4))).isEqualTo(4);
        assertThat(score.compute(roll, new NumberCategory(3))).isEqualTo(3);
    }

    @Test
    public void test_figure_three_fours() {
        // ARRANGE
        Roll roll = new Roll(4, 4, 4, 5, 6);

        // ACT
        long result = score.compute(roll, new ThreeOfAKindCategory());

        // ASSERT
        assertThat(result).isEqualTo(23);

    }

    @Test
    public void test_figure_wrong_three_of_a_kind() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 3, 5, 6);

        // ACT
        long result = score.compute(roll, new ThreeOfAKindCategory());

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_four_twos() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 2, 6);

        // ACT
        long result = score.compute(roll, new FourOfAKindCategory());

        // ASSERT
        assertThat(result).isEqualTo(14);

    }

    @Test
    public void test_figure_wrong_four_of_a_kind() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 4, 6);

        // ACT
        long result = score.compute(roll, new FourOfAKindCategory());

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_full() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 3, 3);

        // ACT
        long result = score.compute(roll, new FullCategory());

        // ASSERT
        assertThat(result).isEqualTo(25);

    }

    @Test
    public void test_figure_full_wrong() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 1, 3, 3);

        // ACT
        long result = score.compute(roll, new FullCategory());

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_small_straight() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 6);

        // ACT
        long result = score.compute(roll, new SmallStraightCategory());

        // ASSERT
        assertThat(result).isEqualTo(30);

    }

    @Test
    public void test_figure_small_straight_wrong() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 5, 3, 6);

        // ACT
        long result = score.compute(roll, new SmallStraightCategory());

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_small_straight_with_big_straight() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 5);

        // ACT
        long result = score.compute(roll, new SmallStraightCategory());

        // ASSERT
        assertThat(result).isEqualTo(30);

    }


    @Test
    public void test_figure_big_straight() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 5);

        // ACT
        long result = score.compute(roll, new BigStraightCategory());

        // ASSERT
        assertThat(result).isEqualTo(40);

    }

    @Test
    public void test_figure_big_straight_wrong() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 6);

        // ACT
        long result = score.compute(roll, new BigStraightCategory());

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_yahtzee() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 2, 2);

        // ACT
        long result = score.compute(roll, new YahtzeeCategory());

        // ASSERT
        assertThat(result).isEqualTo(50);

    }

    @Test
    public void test_figure_yahtzee_wrong() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 6);

        // ACT
        long result = score.compute(roll, new YahtzeeCategory());

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_lucky_minimal() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 1, 1, 1);

        // ACT
        long result = score.compute(roll, new LuckyCategory());

        // ASSERT
        assertThat(result).isEqualTo(6);

    }

    @Test
    public void test_lucky_max() {
        // ARRANGE
        Roll roll = new Roll(6, 6, 6, 5, 6);

        // ACT
        long result = score.compute(roll, new LuckyCategory());

        // ASSERT
        assertThat(result).isEqualTo(29);

    }

}
