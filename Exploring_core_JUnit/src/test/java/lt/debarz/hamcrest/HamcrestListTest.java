package lt.debarz.hamcrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The Hamcrest library:
 * Hamcrest is not a testing framework itself, but it helps us declaratively specify simple
 * matching rules. These matching rules can be used in many situations, but they are
 * particularly helpful for unit testing.
 * */
public class HamcrestListTest {
    private List<String> values;
    @BeforeEach
    public void setUp () {
        values = new ArrayList< >();
        values.add("Michael");
        values.add("John");
        values.add("Edwin");
    }

    /**
     * As we write more unit tests and assertions, we inevitably find that some assertions
     * are big and hard to read. Our example company, Tested Data Systems, is working with
     * customers whose data may be kept in lists. Engineers may populate a list with values
     * like "Michael", "John", and "Edwin"; then they may search for customers like
     * "Oliver", "Jack", and "Harry", as in the following listing. This test is intended to
     * fail and show the description of the assertion failure.
     * */
    @Test
    @DisplayName("List without Hamcrest")
    public void testWithoutHamcrest() {
        assertEquals(3, values.size());
        assertTrue(values.contains("Oliver")
                || values.contains("Jack")
                || values.contains("Harry"));
    }

    /**
     * this time written with
     * the Hamcrest library
     * */
    @Test
    @DisplayName("List with Hamcrest")
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("Oliver"),
                equalTo("Jack"), equalTo("Harry"))));
    }
}
