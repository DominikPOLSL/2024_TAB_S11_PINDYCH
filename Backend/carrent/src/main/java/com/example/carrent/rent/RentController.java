package com.example.carrent.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public List<Rent> getAllRents() {
        return rentService.getAllRents();
    }

    @GetMapping("/{id}")
    public Optional<Rent> getRentById(@PathVariable int id) {
        return rentService.getRentById(id);
    }

    @PostMapping
    public void addRent(@RequestBody Rent rent) {
        rentService.addRent(rent);
    }

    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable int id) {
        rentService.deleteRent(id);
    }

    @PutMapping("/{id}")
    public void updateRent(@PathVariable int id, @RequestBody Rent rent) {
        rent.setRentId(id);
        rentService.updateRent(rent);
    }
}
