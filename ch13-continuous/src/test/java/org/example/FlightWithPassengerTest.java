package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightWithPassengerTest {

    private Flight flight = new Flight("AA123", 1);

    @Test
    void testAddRemovePassengers(){
        Passenger passenger = new Passenger("124-56-7890",
                "Micheal Johnson", "US");

        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testNumberOfSeats(){
        Passenger passenger1 = new Passenger("124-56-7890",
                "Micheal Johnson", "US");
        flight.addPassenger(passenger1);
        assertEquals(1, flight.getNumberOfPassengers());

        Passenger passenger2 = new Passenger("127-23-7991",
                "John Smith", "GB");
        assertThrows(RuntimeException.class, () -> {
            flight.addPassenger(passenger2);
        });
    }
}
