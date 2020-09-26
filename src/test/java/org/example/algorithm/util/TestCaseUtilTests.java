package org.example.algorithm.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Changhee Choi
 * @since 27/08/2020
 */
public class TestCaseUtilTests {

    @Test
    void option을_사용하지_않는_경우_소문자_대문자_숫자가_조합된_문자열이_생성된다() {
        int loop = 100000;

        for (int i = 0; i < loop; i++) {
            String randomString = TestCaseUtil.generateString(100);
            assertThat(randomString.length()).isEqualTo(100);
            assertThat(Pattern.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+$", randomString)).isTrue();
        }
    }

    @Test
    void lower_options이_false이면_소문자가_포함되지_않는_문자열이_생성된다() {
        int loop = 100000;

        for (int i = 0; i < loop; i++) {
            String randomString = TestCaseUtil.generateString(100, false, true, true);
            assertThat(randomString.length()).isEqualTo(100);
            assertThat(Pattern.matches("^[^a-z]+$", randomString)).isTrue();
        }
    }

    @Test
    void upper_options이_false이면_대문자가_포함되지_않는_문자열이_생성된다() {
        int loop = 100000;

        for (int i = 0; i < loop; i++) {
            String randomString = TestCaseUtil.generateString(100, true, false, true);
            assertThat(randomString.length()).isEqualTo(100);
            assertThat(Pattern.matches("^[^A-Z]+$", randomString)).isTrue();
        }
    }

    @Test
    void digit_options이_false이면_숫자가_포함되지_않는_문자열이_생성된다() {
        int loop = 100000;

        for (int i = 0; i < loop; i++) {
            String randomString = TestCaseUtil.generateString(100, true, true, false);
            assertThat(randomString.length()).isEqualTo(100);
            assertThat(Pattern.matches("^[^0-9]+$", randomString)).isTrue();
        }
    }

    @Test
    void 생성된_숫자는_모두_min_이상의_값이다() {
        int[] numbers = TestCaseUtil.generateRandomNumbers(10000, 5, 100);
        assertThat(Arrays.stream(numbers).filter(number -> number < 5).count()).isEqualTo(0);
    }

    @Test
    void 생성된_숫자는_모두_max_이하의_값이다() {
        int[] numbers = TestCaseUtil.generateRandomNumbers(10000, 5, 100);
        assertThat(Arrays.stream(numbers).filter(number -> number > 100).count()).isEqualTo(0);
    }

    @Test
    void max_bound_값도_생성된다() {
        int[] numbers = TestCaseUtil.generateRandomNumbers(10000, 5, 100);
        assertThat(Arrays.stream(numbers).filter(number -> number == 100).count()).isGreaterThan(0);
    }

    @Test
    void min_bound_값도_생성된다() {
        int[] numbers = TestCaseUtil.generateRandomNumbers(10000, 5, 100);
        assertThat(Arrays.stream(numbers).filter(number -> number == 5).count()).isGreaterThan(0);
    }

    @Test
    void 중복된_숫자는_포함되지_않는다() {
        int[] numbers = TestCaseUtil.generateRandomUniqueNumbers(10000, 0, 1000000000);
        assertThat(numbers).doesNotHaveDuplicates();
    }
}