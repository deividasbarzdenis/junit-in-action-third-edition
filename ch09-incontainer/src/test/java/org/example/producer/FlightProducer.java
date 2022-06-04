package org.example.producer;

import org.example.FlightBuilderUtil;
import org.example.airport.Flight;

import javax.ws.rs.Produces;
import java.io.IOException;

public class FlightProducer {
    @Produces
    public Flight createFlight() throws IOException {
        return FlightBuilderUtil.buildFlightFromCsv();
    }
}
