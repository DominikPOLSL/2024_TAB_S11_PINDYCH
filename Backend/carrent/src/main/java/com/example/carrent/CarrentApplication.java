package com.example.carrent;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrent.brand.Brand;

@SpringBootApplication
@RestController
public class CarrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrentApplication.class, args);
	}

}
