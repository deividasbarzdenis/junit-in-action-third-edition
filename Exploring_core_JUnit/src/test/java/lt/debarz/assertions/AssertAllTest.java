package lt.debarz.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {

    /**
     * This section presents a few examples provided by the assertions package. Listing 2.11 shows some of the overloaded
     * assertAll methods. The heading parameter
     * allows us to recognize the group of assertions within the assertAll() methods. The
     * failure message of the assertAll() method can provide detailed information about
     * every particular assertion within a group. Also, we’re using the @DisplayName annotation to provide
     * easy-to-understand information about what the test is looking for.
     * Our purpose is the verification of the same system under test (SUT) class that we
     * introduced earlier.
     * After the heading parameter from the assertAll method, we provide the rest of
     * the arguments as a collection of executables—a shorter, more convenient way to assert
     * that supplied executables do not throw exceptions.
     * */
    @Test
    @DisplayName(
            "SUT should default to not being under current verification")
    void testSystemNotVerified() {
        SUT systemUnderTest = new SUT("Our system under test");
        assertAll("By default, SUT is not under current verification",
                () -> assertEquals("Our system under test",
                        systemUnderTest.getSystemName()),
                () -> assertFalse(systemUnderTest.isVerified()));
    }

    @Test
    @DisplayName("SUT should be under current verification")
    void testSystemUnderVerification() {
        SUT systemUnderTest = new SUT("Our system under test");
        systemUnderTest.verify();
        assertAll("SUT under current verification",
                () -> assertEquals("Our system under test",
                        systemUnderTest.getSystemName()),
                () -> assertTrue(systemUnderTest.isVerified())
        );
    }

    /**
     * The next listing shows the use of some assertion methods with messages. Thanks to
     * Supplier<String>, the instructions required to create a complex message aren’t
     * provided in the case of success. We can use lambda or method references to verify our
     * SUT; they improve performance.
     * */

    SUT systemUnderTest = new SUT("Our system under test");

    @Test
    @DisplayName("SUT should be under current verification")
    void testSystemUnderVerification1() {
        systemUnderTest.verify();
        assertTrue(systemUnderTest.isVerified(),
                () -> "System should be under verification");
    }

    @Test
    @DisplayName("SUT should not be under current verification")
    void testSystemNotUnderVerification() {
        assertFalse(systemUnderTest.isVerified(),
                () -> "System should not be under verification.");
    }
    @Test
    @DisplayName("SUT should have no current job")
    void testNoJob() {
        assertNull(systemUnderTest.getCurrentJob(),
                () -> "There should be no current job");
    }
}
