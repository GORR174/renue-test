package net.catstack.renuetest;

import net.catstack.renuetest.controllers.AirportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.Scanner;

@SpringBootApplication
public class RenuetestApplication implements CommandLineRunner {

    @Autowired
    private AirportController airportController;

    @Value("${app.column}")
    private int column;

    public static void main(String[] args) {
        SpringApplication.run(RenuetestApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (args.length > 0) {
            try {
                column = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода параметра колонки. Используется стандартный параметр: " + column);
            }
        }

        System.out.print("Введите строку: ");

        Scanner scanner = new Scanner(System.in);
        var prefix = scanner.nextLine();

        if (prefix.isBlank()) {
            System.out.println("Ошибка: вы ввели пустую строку");
            return;
        }

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            var airports = airportController.findAirportsByFieldValue(column, prefix);
            stopWatch.stop();

            airports.forEach(System.out::println);
            System.out.println("\nКоличество найденных строк: " + airports.size());
            System.out.println("Время, затраченное на поиск: " + stopWatch.getLastTaskTimeMillis() + "мс.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: Указанной колонки (" + column + ") не существует");
        }
    }
}
