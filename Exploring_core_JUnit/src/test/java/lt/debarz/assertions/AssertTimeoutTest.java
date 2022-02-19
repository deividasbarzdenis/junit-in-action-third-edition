package lt.debarz.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class AssertTimeoutTest {
    private SUT systemUnderTest = new SUT("Our system under test");

    /**
     * assertTimeout waits until the executable finishes B. The failure message looks
     * something like this: execution exceeded timeout of 500 ms by 193 ms.
     * assertTimeoutPreemptively stops the executable when the time has expired
     * c. The failure message looks like this: execution timed out after 500 ms.
     * In some situations, you expect a test to be executed and to throw an exception, so
     * you may force the rest to run under inappropriate conditions or to receive inappropriate input.
     * In our example, it is natural that the SUT that tries to run without a job
     * assigned to it will throw an exception. Again, JUnit 5 offers an elegant solution
     * */
    @Test
    @DisplayName("A job is executed within a timeout")
    void testTimeout() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeout(ofMillis(500), () -> systemUnderTest.run(200));
    }
    @Test
    @DisplayName("A job is executed preemptively within a timeout")
    void testTimeoutPreemptively() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));
        assertTimeoutPreemptively(ofMillis(500),
                () -> systemUnderTest.run(200));
    }
}
