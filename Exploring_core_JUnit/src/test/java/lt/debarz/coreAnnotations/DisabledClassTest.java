package lt.debarz.coreAnnotations;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Feature is still under construction.")
public class DisabledClassTest {
    /**
     * The @Disabled annotation can be used over classes and test methods. It signals tha
     * the annotated test class or test method is disabled and should not be executed. The
     * programmers at Tested Data Systems use it to give their reasons for disabling a test so
     * the rest of the team knows exactly why that was done. If this annotation is applied to a
     * class, it disables all the methods of the test. Also, the disabled tests and the reasons fo
     * their being disabled are displayed differently on each programmer’s console when the
     * programmer runs them from the IDE
     * */
    private SUT1 systemUnderTest= new SUT1("Our system under test");

    @Test
    @Disabled
    void testRegularWork(){
        boolean canReceiveRegularWork  = systemUnderTest.canReceiveRegularWork();
        assertTrue(canReceiveRegularWork);
    }

    @Test
    @Disabled("Feature still under construction.")
    void testAdditionalWork() {
        boolean canReceiveAdditionalWork =
                systemUnderTest.canReceiveAdditionalWork();
        assertFalse(canReceiveAdditionalWork);
    }


}
