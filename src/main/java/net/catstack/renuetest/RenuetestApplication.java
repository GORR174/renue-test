package net.catstack.renuetest;

import net.catstack.renuetest.controllers.AirportController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RenuetestApplication implements CommandLineRunner {

	@Autowired
	private AirportController airportController;

	public static void main(String[] args) {
		SpringApplication.run(RenuetestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(airportController.findAirportsByFieldValue(1, "T"));
	}
}
