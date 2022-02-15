package lt.debarz.coreAnnotations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class showing the @DisplayName annotation.")
class SUTTest {

    /**
     * The @DisplayName annotation can be used over classes and test methods. It helps
     * the engineers at Tested Data Systems declare their own display name for an annotated
     * test class or test method. Typically, this annotation is used for test reporting in IDEs
     * and build tools. The string argument of the @DisplayName annotation may contain
     * spaces, special characters, and even emojis
     */
    private SUT systemUnderTest = new SUT();

    @Test
    @DisplayName("Our system under test says hello.")
    void testHello() {
        assertEquals("Hello", systemUnderTest.hello());
    }

    @Test
    @DisplayName(":D")
    void testTalking(){
        assertEquals("How are you?", systemUnderTest.talk());
    }

    @Test
    void testBye(){
        assertEquals("Bye", systemUnderTest.bye());
    }

}
