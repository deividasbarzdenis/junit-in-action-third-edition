package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testPassengerCreation(){
        Passenger passenger = new Passenger("123-45-6789",
                "John Smith", "US");

        assertNotNull(passenger);
    }

    @Test
    void testInvalidCountryCode(){
        assertThrows(RuntimeException.class, () -> {
            Passenger passenger = new Passenger("900-45-6789",
                    "John Smith", "GJ");
        });
    }

    @Test
    void testPassengerToString(){
        Passenger passenger = new Passenger("123-45-6789",
                "John Smith", "US");
        assertEquals("Passenger John Smith with identifier: 123-45-6789 from US",
                passenger.toString());
    }
}
