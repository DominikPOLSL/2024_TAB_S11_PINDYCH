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

    @DeleteMapping("/deleteRent/{id}")
    public Optional<Rent> deleteRent(@PathVariable int id) {
        return rentService.deleteRent(id);
    }

    @PutMapping("/{id}")
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
