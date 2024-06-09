package com.example.carrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Lab1 class is the main class for a Counter Strike Match results program.
 *
 * @author Przemyslaw Bednarz, Dominik Pindych, Dawid Flis, Mateusz Jasek, Jakub Deska
 * @version 1.0
 */
@SpringBootApplication
@RestController
public class CarrentApplication {

    /**
     * The entry point of the Car Rent application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(CarrentApplication.class, args);
    }
}
