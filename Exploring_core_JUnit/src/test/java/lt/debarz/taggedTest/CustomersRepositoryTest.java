package lt.debarz.taggedTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("repository")
class CustomersRepositoryTest {
    private String CUSTOMER_NAME = "John Smith";
    private CustomersRepository repository = new CustomersRepository();

    @Test
    @DisplayName("Customer not exists in repo")
    void testNonExistence(){
        boolean exists = repository.contains("John Smith");

        assertFalse(exists);
    }

    @Test
    @DisplayName("Customer exists in repo")
    void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));
        assertTrue(repository.contains("John Smith"));
    }
}
