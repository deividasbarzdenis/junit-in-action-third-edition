package lt.debarz.nestedTest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * An inner class is a class that is a member of another class. It can access any private
 * instance variable of the outer class, as it is effectively part of that outer class. The typical use case is when two classes are tightly coupled, and it’s logical to provide direct
 * access from the inner class to all instance variables of the outer class. For example, we
 * may test a flight that has two types of passengers trying to board. The behavior of the
 * flight will be described in the outer test class, while the behavior of each type of
 * passenger will be described in its own nested class. Each passenger is able to interact
 * with the flight. The nested tests will follow the business logic and lead to writing
 * clearer code, as you will be able to follow the testing process more easily.
 * Following this tight-coupling idea, nested tests give the test writer more capabilities
 * to express the relationships among several groups of tests. Inner classes may be
 * package-private.
 * The Tested Data Systems company works with customers. Each customer has a gender, a first name, a last name, sometimes a middle name, and the date when they
 * became a customer (if known). Some parameters may not be present, so the engineers are using the builder pattern to create and test a customer.
 * The following listing demonstrates the use of the @Nested annotation on the class
 * NestedTestsTest. The customer being tested is John Michael Smith, and the date
 * when he became a customer is known.
 * */
class NestedTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";

    @Nested
    class BuilderTest {
        private String MIDDLE_NAME = "Michael";

        @Test
        void customerBuilder() throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date customerDate = simpleDateFormat.parse("04-21-2019");
            Customer customer = new Customer.Builder(
                    Gender.MALE, LAST_NAME, FIRST_NAME)
                    .withMiddleName(MIDDLE_NAME)
                    .withBecomeCustomer(customerDate)
                    .build();
            assertAll( () -> {
                assertEquals(Gender.MALE, customer.getGender());
                assertEquals( FIRST_NAME, customer.getFirstName());
                assertEquals( LAST_NAME, customer.getLastName());
                assertEquals( MIDDLE_NAME, customer.getMiddleName());
                assertEquals( customerDate, customer.getBecomeCustomer());
            });
        }
    }
}
