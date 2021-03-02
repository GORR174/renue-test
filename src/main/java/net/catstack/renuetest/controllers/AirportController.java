package net.catstack.renuetest.controllers;

import net.catstack.renuetest.dao.AirportDAO;
import net.catstack.renuetest.models.Airport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportController {
    private AirportDAO airportDAO;

    public AirportController(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }

    public List<Airport> findAirportsByFieldValue(int column, String prefix) {
        return airportDAO.getAirportsByFieldValue(column, prefix);
    }
}
