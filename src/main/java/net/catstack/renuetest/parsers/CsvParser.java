package net.catstack.renuetest.parsers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class CsvParser {
    private final String separator;

    public CsvParser(String separator) {
        this.separator = separator;
    }

    public <T> Stream<T> getObjectsFromFile(String filePath, Function<String[], T> mapper) throws IOException {
        return Files.newBufferedReader(Paths.get(filePath)).lines()
                .map(line ->
                        Arrays.stream(line.split(separator))
                                .map(field -> {
                                    if (field.startsWith("\"")) {
                                        field = field.substring(1);
                                        field = field.substring(0, field.length() - 1);
                                    }

                                    return field;
                                })
                                .toArray(String[]::new)
                )
                .map(mapper);
    }
}
