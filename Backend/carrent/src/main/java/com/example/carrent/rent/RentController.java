package com.example.carrent.rent;

import java.util.Arrays;
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

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.ModelRepository;
import com.example.carrent.reservation.Reservation;
import com.example.carrent.reservation.ReservationRepository;
import com.example.carrent.reservation.ReservationService;
import com.example.carrent.vehicle.Vehicle;
import com.example.carrent.vehicle.VehicleRepository;
import com.example.carrent.model.Model;
//import ch.qos.logback.core.model.Model;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/rents")
public class RentController {

    private final RentService rentService;
    private final ReservationRepository resRepository;

    private final RentRepository rentRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public RentController(RentService rentService, ReservationRepository resRepository, BrandRepository brandRepository, ModelRepository modelRepository, VehicleRepository vehicleRepository, RentRepository rentRepository) {
        this.rentService = rentService;
        this.resRepository = resRepository;
        this.rentRepository = rentRepository;
        this.vehicleRepository = vehicleRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
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

    @PutMapping("/updateRent/{id}")
    public void updateRent(@PathVariable int id, @RequestBody Rent rent) {
        rent.setRentId(id);
        rentService.updateRent(rent);
    }


    @GetMapping("/createNewRent/{id}")
    public List<String> createNewRent(@PathVariable int id) {

        Rent rent = new Rent();

        Reservation reservation = resRepository.findById(id).orElseThrow();
        Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElseThrow();
        Model model = modelRepository.findById(vehicle.getModelId()).orElseThrow();
        Brand brand = brandRepository.findById(model.getBrandId()).orElseThrow();

        rent.setCost(0);
        rent.setDistance(0);
        rent.setReservationId(id);

        reservation.setReserved(true);

        rentRepository.save(rent);
        
        return Arrays.asList(reservation.getStartTime().toString(),model.getModelName(),brand.getBrandName());
        
    }
    

    // @GetMapping("/findRR/{id}")
    // public List<Optional<?>> findRR(@PathVariable Integer id) {
    //     if (id == null) {
    //         // Handle null case, possibly throw an exception or return an appropriate response
    //     }
    //     Optional<Rent> rent = rentService.getRentById(id);
    //     Optional<?> reservation = Optional.empty();
    //     if (rent.isPresent()) {
    //         Integer reservationId = rentService.getReservation(id);
    //         if (reservationId != null) {
    //             reservation = resRepository.getReservationById(reservationId);
    //         }
    //     }
    //     return Arrays.asList(rent, reservation);
    // }


}
