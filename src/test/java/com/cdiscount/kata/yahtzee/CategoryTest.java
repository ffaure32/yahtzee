package com.cdiscount.kata.yahtzee;

import static org.assertj.core.api.Assertions.assertThat;

import com.cdiscount.kata.yahtzee.category.BigStraightCategory;
import com.cdiscount.kata.yahtzee.category.FourOfAKindCategory;
import com.cdiscount.kata.yahtzee.category.FullCategory;
import com.cdiscount.kata.yahtzee.category.LuckyCategory;
import com.cdiscount.kata.yahtzee.category.NumberCategory;
import com.cdiscount.kata.yahtzee.category.SmallStraightCategory;
import com.cdiscount.kata.yahtzee.category.ThreeOfAKindCategory;
import com.cdiscount.kata.yahtzee.category.YahtzeeCategory;
import org.junit.Test;

public class CategoryTest {

    @Test
    public void test_all_ones() {
        Roll roll = Roll.newYahtzee(1);

        Category ones = new NumberCategory(1);
        long result =  ones.score(roll);

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void test_one_one() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 3, 4, 5);

        // ACT
        long result =  new NumberCategory(1).score(roll);

        // ASSERT
        assertThat(result).isEqualTo(1);


    }

    @Test
    public void test_one_two() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 3, 4, 5);

        // ACT
        long result =  new NumberCategory(2).score(roll);

        // ASSERT
        assertThat(result).isEqualTo(2);


    }

    @Test
    public void test_two_sixes() {
        // ARRANGE
        Roll roll = new Roll(3, 4, 6, 6, 5);

        // ACT
        // ASSERT
        assertThat( new NumberCategory(6).score(roll)).isEqualTo(12);
        assertThat( new NumberCategory(5).score(roll)).isEqualTo(5);
        assertThat( new NumberCategory(4).score(roll)).isEqualTo(4);
        assertThat( new NumberCategory(3).score(roll)).isEqualTo(3);
    }

    @Test
    public void test_figure_three_fours() {
        // ARRANGE
        Roll roll = new Roll(4, 4, 4, 5, 6);

        // ACT
        long result =  new ThreeOfAKindCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(23);

    }

    @Test
    public void test_figure_wrong_three_of_a_kind() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 3, 5, 6);

        // ACT
        long result =  new ThreeOfAKindCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_four_twos() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 2, 6);

        // ACT
        long result =  new FourOfAKindCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(14);

    }

    @Test
    public void test_figure_wrong_four_of_a_kind() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 4, 6);

        // ACT
        long result =  new FourOfAKindCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_full() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 2, 3, 3);

        // ACT
        long result =  new FullCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(25);

    }

    @Test
    public void test_figure_full_wrong() {
        // ARRANGE
        Roll roll = new Roll(2, 2, 1, 3, 3);

        // ACT
        long result =  new FullCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_small_straight() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 6);

        // ACT
        long result =  new SmallStraightCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(30);

    }

    @Test
    public void test_figure_small_straight_wrong() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 5, 3, 6);

        // ACT
        long result =  new SmallStraightCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_small_straight_with_big_straight() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 5);

        // ACT
        long result =  new SmallStraightCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(30);

    }


    @Test
    public void test_figure_big_straight() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 5);

        // ACT
        long result =  new BigStraightCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(40);

    }

    @Test
    public void test_figure_big_straight_wrong() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 6);

        // ACT
        long result =  new BigStraightCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_figure_yahtzee() {
        // ARRANGE
        Roll roll = Roll.newYahtzee(2);

        // ACT
        long result =  new YahtzeeCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(50);

    }

    @Test
    public void test_figure_yahtzee_wrong() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 4, 3, 6);

        // ACT
        long result =  new YahtzeeCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(0);

    }

    @Test
    public void test_lucky_minimal() {
        // ARRANGE
        Roll roll = new Roll(1, 2, 1, 1, 1);

        // ACT
        long result =  new LuckyCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(6);

    }

    @Test
    public void test_lucky_max() {
        // ARRANGE
        Roll roll = new Roll(6, 6, 6, 5, 6);

        // ACT
        long result =  new LuckyCategory().score(roll);

        // ASSERT
        assertThat(result).isEqualTo(29);

    }

}
