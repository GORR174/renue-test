package net.catstack.renuetest.dao;

import net.catstack.renuetest.models.Airport;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportDAO {
    private List<Airport> airports;

    public AirportDAO() {
        loadFromFile();
    }

    public void loadFromFile() {
        airports = List.of(
                new Airport(new String[] {"1", "Test1-1", "Test2-1"}),
                new Airport(new String[] {"2", "Test1-2", "Test2-2"}),
                new Airport(new String[] {"3", "Tast1-3", "Test2-3"}),
                new Airport(new String[] {"4", "Aast1-4", "Test2-4"})
        );
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Airport> getAirportsByFieldValue(int row, String prefix) {
        return airports.stream()
                .filter(airport -> airport.getAirportField(row).startsWith(prefix))
                .sorted(Comparator.comparing(a -> a.getAirportField(row)))
                .collect(Collectors.toList());
    }
}
