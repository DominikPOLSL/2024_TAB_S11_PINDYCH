package com.example.carrent.reservation;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.employee.Employee;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import com.example.carrent.vehicle.Vehicle;
import com.example.carrent.vehicle.VehicleRepository;
import com.example.carrent.vehiclecargiver.VehicleCarGiverRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository, BrandRepository brandRepository, ModelRepository modelRepository, VehicleCarGiverRepository vehicleCarGiverRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    public List<ReservationRecord> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::mapToReservationRecord)
                .collect(Collectors.toList());
    }

    public Optional<Reservation> getReservationById(int id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public List<ReservationRecord> getAllReservationsByEmployeeId(int id) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getCarGiverId() == id)
                .map(this::mapToReservationRecord)
                .collect(Collectors.toList());
    }

    public Reservation addReservation(@NotNull ReservationSave reservationSave) {
        Reservation reservation = new Reservation();
        reservation.setEmployeeId(reservationSave.employeeId());
        reservation.setCarGiverId(reservationSave.carGiverId());
        reservation.setEndTime(Date.valueOf(reservationSave.endTime().substring(0,10)));
        reservation.setStartTime(Date.valueOf(reservationSave.startTime().substring(0,10)));
        reservation.setPrivateUsage(false);

        Optional<Vehicle> vehicleOptional = vehicleRepository.findAll().stream()
                .filter(vehicle -> {
                    Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                    if (model != null && model.getModelName().equals(reservationSave.model())) {
                        Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
                        return brand != null && brand.getBrandName().equals(reservationSave.brand());
                    }
                    return false;
                })
                .findFirst();

        vehicleOptional.ifPresent(vehicle -> reservation.setVehicleId(vehicle.getVehicleId()));
        reservationRepository.save(reservation);
        return reservation;
    }

    public List<Reservation> getReservationsByAttribute(String data) {
        return reservationRepository.findAll().stream()
                .filter(r -> {
                    Optional<Vehicle> vehicleOpt = vehicleRepository.findById(r.getVehicleId());
                    if (vehicleOpt.isEmpty()) return false;

                    Vehicle vehicle = vehicleOpt.get();
                    Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                    if (model == null) return false;

                    Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
                    if (brand == null) return false;

                    boolean matches = model.getModelName().contains(data) ||
                            brand.getBrandName().contains(data) ||
                            (r.getStartTime().toString().compareTo(data) <= 0 && data.compareTo(r.getEndTime().toString()) <= 0) ||
                            String.valueOf(r.getReservationId()).contains(data);

                    return matches;
                })
                .distinct()
                .collect(Collectors.toList());
    }


    public boolean isReserved(int id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return reservation.getReserved();
    }

    private ReservationRecord mapToReservationRecord(Reservation reservation) {
        Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
        if (vehicle == null) return null;

        Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
        if (model == null) return null;

        Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
        if (brand == null) return null;

        return new ReservationRecord(
                reservation.getReservationId(),
                brand.getBrandName(),
                model.getModelName(),
                reservation.getStartTime().toString(),
                reservation.getEndTime().toString()
        );
    }


}
