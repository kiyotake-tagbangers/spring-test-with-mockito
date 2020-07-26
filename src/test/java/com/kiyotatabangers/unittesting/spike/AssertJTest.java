package com.kiyotatabangers.unittesting.spike;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.endsWith;

/**
 * @author KIYOTA, Takeshi
 */
public class AssertJTest {

    @Test
    public void learning(){
        List<Integer> numbers = Arrays.asList(12,15,45);

        // MatcherAssert.assertThat(numbers, hasSize(3));
        // MatcherAssert.assertThat(numbers, hasItems(12,45));
        // MatcherAssert.assertThat(numbers, everyItem(greaterThan(10)));
        // MatcherAssert.assertThat(numbers, everyItem(lessThan(100)));
        // MatcherAssert.assertThat("", isEmptyString());
        // MatcherAssert.assertThat("ABCDE", containsString("BCD"));
        // MatcherAssert.assertThat("ABCDE", startsWith("AB"));
        // MatcherAssert.assertThat("ABCDE", endsWith("DE"));

        // AssertJ allows for chaining
        assertThat(numbers).hasSize(3).contains(12,15)
                .allMatch(x -> x > 10)
                .allMatch(x -> x < 100)
                .noneMatch(x -> x < 0);

        assertThat("").isEmpty();
        assertThat("ABCDE").contains("BCD")
                .startsWith("ABC")
                .endsWith("CDE");
    }
}
