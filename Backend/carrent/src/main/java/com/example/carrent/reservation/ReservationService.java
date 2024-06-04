package com.example.carrent.reservation;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.employee.Employee;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import com.example.carrent.rent.Rent;
import com.example.carrent.rent.RentRepository;
import com.example.carrent.rent.RentService;
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
    private final RentRepository rentRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository, BrandRepository brandRepository, ModelRepository modelRepository, RentRepository rentRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.rentRepository = rentRepository;
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
                .filter(reservation -> reservation.getEmployeeId() == id)
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

    public List<ReservationRecord> getReservationsByAttribute(String data) {
        ArrayList<ReservationRecord> list  = new ArrayList<>();

        for(Reservation reservation : reservationRepository.findAll()) {

            Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
            if (vehicle == null) return null;

            Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
            if (model == null) return null;

            Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
            if (brand == null) return null;

            if(model.getModelName().contains(data))
                list.add(mapToReservationRecord(reservation));
            if(brand.getBrandName().contains(data))
                list.add(mapToReservationRecord(reservation));
            if(model.getModelName().contains(data))
                list.add(mapToReservationRecord(reservation));
        }
        return list;
    }

    public boolean isReserved(int id){
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return reservation.getReserved();
    }

    public ReservationRecord mapToReservationRecord(Reservation reservation) {

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

    public List<RentRecord> PrintAllRentsByUserId(int id) {
        List<RentRecord> list = new ArrayList<>();

        for (Reservation reservation : reservationRepository.findAll()) {
            if (reservation.getEmployeeId() == id) {
                ReservationRecord reservationRecord = mapToReservationRecord(reservation);
                if (reservationRecord != null) { // Ensure reservationRecord is not null
                    RentRecord rentRecord = new RentRecord(
                            reservationRecord.id(),
                            reservationRecord.brand(),
                            reservationRecord.model(),
                            reservationRecord.startTime()
                    );
                    list.add(rentRecord);
                }
            }
        }
        return list;
    }
    public List<RentRecord> getRentByAttribute(String data) {
        ArrayList<RentRecord> list = new ArrayList<>();

        for (Rent rent : rentRepository.findAll()) {
            Optional<Reservation> reservationOpt = reservationRepository.findById(rent.getReservationId());
            if (reservationOpt.isEmpty()) {
                continue;
            }
            Reservation reservation = reservationOpt.get();

            Optional<Vehicle> vehicleOpt = vehicleRepository.findById(reservation.getVehicleId());
            if (vehicleOpt.isEmpty()) {
                continue;
            }
            Vehicle vehicle = vehicleOpt.get();

            Optional<Model> modelOpt = modelRepository.findById(vehicle.getModelId());
            if (modelOpt.isEmpty()) {
                continue;
            }
            Model model = modelOpt.get();

            Optional<Brand> brandOpt = brandRepository.findById(model.getBrandId());
            if (brandOpt.isEmpty()) {
                continue;
            }
            Brand brand = brandOpt.get();

            boolean matches = model.getModelName().contains(data) ||
                    brand.getBrandName().contains(data) ||
                    (reservation.getStartTime().toString().compareTo(data) <= 0 && data.compareTo(reservation.getEndTime().toString()) <= 0) ||
                    String.valueOf(reservation.getReservationId()).contains(data);

            if (matches) {
                list.add(new RentRecord(
                        rent.getRentId(),
                        model.getModelName(),
                        brand.getBrandName(),
                        reservation.getStartTime().toString()
                ));
            }
        }
        return list;
    }
}
