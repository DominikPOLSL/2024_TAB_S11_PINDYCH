package com.example.carrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CarrentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrentApplication.class, args);
	}

	@GetMapping("/home")
    public String handleWelcome(){
        return "home";
    }
    
    @GetMapping("/admin/home")
    public String handleAdminWelcome(){
        return "home_admin";
    }  

    @GetMapping("employee/home")
    public String handleEmployeeWelcome(){
        return "home_employee";
    }  

}
