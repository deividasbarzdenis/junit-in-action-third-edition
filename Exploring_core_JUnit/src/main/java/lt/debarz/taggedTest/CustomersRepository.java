package lt.debarz.taggedTest;

import java.util.ArrayList;
import java.util.List;

public class CustomersRepository {
    private List<Customer> customers = new ArrayList<>();

    public boolean contains(String name) {
        return customers.stream().anyMatch(customer -> customer.getName().equals(name));
    }

    public void persist(Customer customer) {
        customers.add(customer);
    }
}
