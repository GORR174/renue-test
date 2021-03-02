package net.catstack.renuetest.models;

import java.util.Arrays;

public class Airport {
    private String[] airportFields;

    public Airport(String[] airportFields) {
        this.airportFields = airportFields;
    }

    public String getAirportField(int fieldNumber) {
        return airportFields[fieldNumber];
    }

    @Override
    public String toString() {
        return "Airport{" +
                "airportFields=" + Arrays.toString(airportFields) +
                '}';
    }
}
