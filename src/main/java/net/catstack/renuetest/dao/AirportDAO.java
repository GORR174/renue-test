package net.catstack.renuetest.dao;

import net.catstack.renuetest.models.Airport;
import net.catstack.renuetest.parsers.AirportsParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportDAO {
    private LinkedList<Airport> airports = new LinkedList<>();

    @Value("${app.airportsData.path}")
    private String dataFilePath;
    @Value("${app.airportsData.csvSeparator}")
    private String dataSeparator;

    public List<Airport> getAirports() {
        return airports;
    }

    public List<Airport> getAirportsByFieldValue(int column, String prefix) {
        try {
            AirportsParser parser = new AirportsParser(dataSeparator);

            return parser.getAirportsFromFile(new ClassPathResource(dataFilePath).getFile().getPath())
                    .filter(airport -> airport.getAirportField(column).startsWith(prefix))
                    .sorted(Comparator.comparing(a -> a.getAirportField(column)))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл " + dataFilePath + " не найден");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
