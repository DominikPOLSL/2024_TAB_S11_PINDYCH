package com.example.carrent.rent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrent.reservation.ReservationService;

@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;
    private final ReservationService resService;

    @Autowired
    public RentController(RentService rentService, ReservationService resService) {
        this.rentService = rentService;
        this.resService = resService;
    }

    @GetMapping("/getAllRents")
    public List<Rent> getAllRents() {
        return rentService.getAllRents();
    }

    @GetMapping("/getRentById/{id}")
    public Optional<Rent> getRentById(@PathVariable int id) {
        return rentService.getRentById(id);
    }

    @PostMapping
    public void addRent(@RequestBody Rent rent) {
        rentService.addRent(rent);
    }

    @DeleteMapping("/deleteRent/{id}")
    public void deleteRent(@PathVariable int id) {
        rentService.deleteRent(id);
    }

    @PutMapping("updateRent/{id}")
    public void updateRent(@PathVariable int id, @RequestBody Rent rent) {
        rent.setRentId(id);
        rentService.updateRent(rent);
    }

    // @GetMapping("/findRR/{id}")
    //     public List<Optional<?>> findRentAndReservationById(Integer id) {
    //         return Arrays.asList(rentService.getRentById(id), resService.getReservationById(id));
    //     }


}
