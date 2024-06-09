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

/**
 * Service class for managing reservations.
 */
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final RentRepository rentRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository,
            BrandRepository brandRepository, ModelRepository modelRepository, RentRepository rentRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.rentRepository = rentRepository;
    }

    /**
     * Retrieves all reservations.
     * 
     * @return List of ReservationRecord objects representing all reservations.
     */
    public List<ReservationRecord> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::mapToReservationRecord)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a reservation by its ID.
     * 
     * @param id The ID of the reservation to retrieve.
     * @return Optional containing the reservation, or empty if not found.
     */
    public Optional<Reservation> getReservationById(int id) {
        return reservationRepository.findById(id);
    }

    /**
     * Deletes a reservation by its ID.
     * 
     * @param id The ID of the reservation to delete.
     */
    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }

    /**
     * Updates a reservation.
     * 
     * @param reservation The updated reservation.
     */
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    /**
     * Retrieves all reservations associated with a specific employee.
     * 
     * @param id The ID of the employee.
     * @return List of ReservationRecord objects representing reservations
     *         associated with the employee.
     */
    public List<ReservationRecord> getAllReservationsByEmployeeId(int id) {
        return reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getEmployeeId() == id)
                .map(this::mapToReservationRecord)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new reservation.
     * 
     * @param reservationSave The details of the reservation to add.
     * @return The newly added reservation.
     */
    public Reservation addReservation(@NotNull ReservationSave reservationSave) {
        Reservation reservation = new Reservation();
        reservation.setEmployeeId(reservationSave.employeeId());
        reservation.setCarGiverId(reservationSave.carGiverId());
        reservation.setEndTime(Date.valueOf(reservationSave.endTime().substring(0, 10)));
        reservation.setStartTime(Date.valueOf(reservationSave.startTime().substring(0, 10)));
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

    /**
     * Retrieves reservations based on a given attribute.
     * 
     * @param data The attribute data to search for.
     * @return List of ReservationRecord objects representing reservations matching
     *         the attribute.
     */
    public List<ReservationRecord> getReservationsByAttribute(String data) {
        ArrayList<ReservationRecord> list = new ArrayList<>();

        for (Reservation reservation : reservationRepository.findAll()) {

            Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
            if (vehicle == null)
                return null;

            Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
            if (model == null)
                return null;

            Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
            if (brand == null)
                return null;

            if (model.getModelName().contains(data))
                list.add(mapToReservationRecord(reservation));
            if (brand.getBrandName().contains(data))
                list.add(mapToReservationRecord(reservation));
            if (model.getModelName().contains(data))
                list.add(mapToReservationRecord(reservation));
        }
        return list;
    }

    /**
     * Checks if a reservation is reserved.
     * 
     * @param id The ID of the reservation.
     * @return True if reserved, false otherwise.
     */
    public boolean isReserved(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        return reservation.getReserved();
    }

    /**
     * Maps a Reservation object to a ReservationRecord object.
     * 
     * @param reservation The Reservation object to map.
     * @return The corresponding ReservationRecord object.
     */
    public ReservationRecord mapToReservationRecord(Reservation reservation) {

        Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
        if (vehicle == null)
            return null;

        Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
        if (model == null)
            return null;

        Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
        if (brand == null)
            return null;

        return new ReservationRecord(
                reservation.getReservationId(),
                brand.getBrandName(),
                model.getModelName(),
                reservation.getStartTime().toString(),
                reservation.getEndTime().toString(),
                reservation.getReserved());
    }

    /**
     * Retrieves all rents associated with a specific user.
     * 
     * @param id The ID of the user.
     * @return List of ReservationRecord objects representing rents associated with
     *         the user.
     */
    public List<ReservationRecord> PrintAllRentsByUserId(int id) {
        List<ReservationRecord> list = new ArrayList<>();

        for (Rent Rent : rentRepository.findAll()) {
            Reservation reservation = reservationRepository.getReferenceById(Rent.getReservationId());
            if (reservation.getEmployeeId() == id) {
                ReservationRecord temp = mapToReservationRecord(reservation);
                ReservationRecord reservationRecord = (new ReservationRecord(
                        Rent.getRentId(),
                        temp.model(),
                        temp.brand(),
                        temp.startTime(),
                        temp.endTime(),
                        temp.reserved()));
                list.add(reservationRecord);
            }
        }
        return list;
    }

    /**
     * Retrieves rents based on a given attribute.
     * 
     * @param data The attribute data to search for.
     * @return List of ReservationRecord objects representing rents matching the
     *         attribute.
     */
    public List<ReservationRecord> getRentByAttribute(String data) {
        ArrayList<ReservationRecord> list = new ArrayList<>();

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
            list.add(new ReservationRecord(
                    rent.getRentId(),
                    model.getModelName(),
                    brand.getBrandName(),
                    reservation.getStartTime().toString(),
                    reservation.getEndTime().toString(),
                    reservation.getReserved()));
        }
        List<ReservationRecord> uniqueList = list.stream()
                .distinct()
                .toList();
        return uniqueList;
    }
}
