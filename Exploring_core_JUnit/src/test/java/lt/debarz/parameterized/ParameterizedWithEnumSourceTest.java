package lt.debarz.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

public class ParameterizedWithEnumSourceTest {
    /**
     * @EnumSource enables us to use enum instances. The annotation provides an optional
     * names parameter that lets us specify which instances must be used or excluded. By
     * default, all instances of an enum are used.
     * The following listing shows the use of the @EnumSource annotation to check the
     * number of words in some phrases that are provided as enum instances.
     * */
    private WordCounter wordCounter = new WordCounter();
    @ParameterizedTest
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }
    @ParameterizedTest
    @EnumSource(value=Sentences.class,
            names = { "JUNIT_IN_ACTION", "THREE_PARAMETERS" })
    void testSelectedWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }
    @ParameterizedTest
    @EnumSource(value=Sentences.class, mode = EXCLUDE, names =
            { "THREE_PARAMETERS" })
    void testExcludedWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }

    enum Sentences {
        JUNIT_IN_ACTION("JUnit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");
        private final String sentence;
        Sentences(String sentence) {
            this.sentence = sentence;
        }
        public String value() {
            return sentence;
        }
    }
}
