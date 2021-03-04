package net.catstack.renuetest.parsers;

import net.catstack.renuetest.models.Airport;

import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Stream;

public class AirportsParser extends CsvParser {
    public AirportsParser(String separator) {
        super(separator);
    }

    private final Function<String[], Airport> mapper = Airport::new;

    public Stream<Airport> getAirportsFromFile(String fileUri) throws IOException {
        return getObjectsFromFile(fileUri, mapper);
    }
}
