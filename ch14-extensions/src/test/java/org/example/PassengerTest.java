package org.example;

import org.example.extensions.DatabaseOperationsExtension;
import org.example.extensions.ExecutionContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ExecutionContextExtension.class, DatabaseOperationsExtension.class})
class PassengerTest {

    @Test
    void testPassenger() throws IOException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertEquals("Passenger John Smith with identifier: 123-456-789",
                passenger.toString());
    }

}
