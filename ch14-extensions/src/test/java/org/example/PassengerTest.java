package org.example;

import org.example.extensions.DatabaseOperationsExtension;
import org.example.extensions.ExecutionContextExtension;
import org.example.jdbc.PassengerDao;
import org.example.jdbc.PassengerExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ExecutionContextExtension.class, DatabaseOperationsExtension.class})
class PassengerTest {

    private PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() throws IOException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertEquals("Passenger John Smith with identifier: 123-456-789",
                passenger.toString());
    }

    @Test
    void testInsertPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");

        passengerDao.insert(passenger);

        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void testUpdatePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");

        passengerDao.insert(passenger);
        passengerDao.update("123-456-789", "Micheal Smith");

        assertEquals("Micheal Smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void testDeletePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");

        passengerDao.insert(passenger);
        passengerDao.delete(passenger);

        assertNotNull(passengerDao.getById("123-456-789"));

    }

}
