package com.play.algorithms_practice.arrays_strings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UrlifyTest {




    @Nested
    class UrlifyOneTest {
        Urlify testCls = new Urlify();

        @Test
        void shouldReplaceAllSpaceInString() {
            String input = "Mr John Smith    ";
            String expectedOutput = "Mr%20John%20Smith";

            String actualOutput = testCls.urlify(input, 13);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }


        @Test
        void shouldReturnEmptyStringIfInputStringIsEmpty() {
            String input = "";
            String expectedOutput = "";

            String actualOutput = testCls.urlify(input, 0);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReplaceConsecutiveSpacesInString() {
            String input = "Mr John    Smith          ";
            String expectedOutput = "Mr%20John%20%20%20%20Smith";

            String actualOutput = testCls.urlify(input, 15);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReplaceSpacesInStringThatStartsWithSpace() {
            String input = " M  ";
            String expectedOutput = "%20M";

            String actualOutput = testCls.urlify(input, 2);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }


        @Test
        void shouldReplaceSpacesInStringThatOnlyContainsSpaces() {
            String input = "    ";
            String expectedOutput = "";

            String actualOutput = testCls.urlify(input, 0);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }
    }

    @Nested
    class Urlify2Test {
        Urlify2 testCls = new Urlify2();

        @Test
        void shouldReplaceAllSpaceInString() {
            String input = "Mr John Smith ";
            String expectedOutput = "Mr%20John%20Smith%20";

            String actualOutput = testCls.urlify(input);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }


        @Test
        void shouldReturnEmptyStringIfInputStringIsEmpty() {
            String input = "";
            String expectedOutput = "";

            String actualOutput = testCls.urlify(input);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReplaceConsecutiveSpacesInString() {
            String input = "Mr John    Smith ";
            String expectedOutput = "Mr%20John%20%20%20%20Smith%20";

            String actualOutput = testCls.urlify(input);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }

        @Test
        void shouldReplaceSpacesInStringThatStartsWithSpace() {
            String input = " M";
            String expectedOutput = "%20M";

            String actualOutput = testCls.urlify(input);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }


        @Test
        void shouldReplaceSpacesInStringThatOnlyContainsSpaces() {
            String input = "    ";
            String expectedOutput = "%20%20%20%20";

            String actualOutput = testCls.urlify(input);

            assertThat(actualOutput).isEqualTo(expectedOutput);
        }
    }



}