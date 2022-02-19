package lt.debarz.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithValueSourceTest {
    /**
     * Parameterized tests allow a test to run multiple times with different arguments. The
     * great benefit is that we can write a single test to be performed using arguments that
     * check various input data. The methods are annotated with @ParameterizedTest.
     * We must declare at least one source providing the arguments for each invocation. The
     * arguments are then passed to the test method.
     * @ValueSource lets us specify a single array of literal values. At execution, this
     * array provides a single argument for each invocation of the parameterized test. The
     * following test checks the number of words in some phrases that are provided as
     * parameters.
     * */

    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
    @ValueSource(strings = {"Check three parameters",
            "JUnit in Action"})
    void testWordsInSentence(String sentence) {
        assertEquals(3, wordCounter.countWords(sentence));
    }
}
