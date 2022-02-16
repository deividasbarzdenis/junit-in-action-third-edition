package lt.debarz.taggedTest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * If you are familiar with JUnit 4, tagged tests are replacements for JUnit 4 categories. You
 * can use the @Tag annotation over classes and test methods. Later, you can use tags to
 * filter test discovery and execution.
 * Listing 2.8 presents the CustomerTest tagged class, which tests the correct creation of Tested Data Systems customers.
 * Listing 2.9 presents the CustomersRepositoryTest tagged class, which tests the existence and nonexistence of
 * customers inside a repository. One use case may be to group your tests into a few categories based on the business
 * logic and the things you are effectively testing. (Each test
 * category has its own tag.) Then you may decide to run only some tests or alternate
 * among categories, depending on your current needs.
 * */
@Tag("individual")
class CustomerTest {

    private String CUSTOMER_NAME = "John Smith";

    @Test
    void testCustomer(){
        Customer customer = new Customer(CUSTOMER_NAME);
        assertEquals("John Smith", customer.getName());
    }
}
