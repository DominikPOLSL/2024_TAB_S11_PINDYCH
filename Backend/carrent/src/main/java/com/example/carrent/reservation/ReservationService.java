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

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final RentRepository rentRepository;

    private final VehicleCarGiverRepository vehicleCarGiverRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository, BrandRepository brandRepository, ModelRepository modelRepository, RentRepository rentRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.rentRepository = rentRepository;
    }

    public List<ReservationRecord> getAllReservations() {
        ArrayList<ReservationRecord> list = new ArrayList<ReservationRecord>();

        for(Reservation reservation : reservationRepository.findAll())
        {
                Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
                Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);

                list.add(new ReservationRecord(
                        reservation.getReservationId(),
                        brand.getBrandName(),
                        model.getModelName(),
                        reservation.getStartTime().toString(),
                        reservation.getEndTime().toString()
                ));
            }

        return list;
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

    public List<ReservationRecord> PrintAllReservation() {
        ArrayList<ReservationRecord> list = new ArrayList<ReservationRecord>();

        for(Reservation reservation : reservationRepository.findAll())
        {

                Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
                Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);

                list.add(new ReservationRecord(
                        reservation.getReservationId(),
                        brand.getBrandName(),
                        model.getModelName(),
                        reservation.getStartTime().toString(),
                        reservation.getEndTime().toString()
                ));
        }
        return list;
    }

    public List<ReservationRecord> PrintAllReservationEmployeeId(int id) {
        ArrayList<ReservationRecord> list = new ArrayList<ReservationRecord>();

        for(Reservation reservation : reservationRepository.findAll())
        {
            if(reservation.getCarGiverId() == id) {
                Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
                Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);

                list.add(new ReservationRecord(
                        reservation.getReservationId(),
                        brand.getBrandName(),
                        model.getModelName(),
                        reservation.getStartTime().toString(),
                        reservation.getEndTime().toString()
                ));
            }
        }
        return list;
    }

    public List<ReservationRecord> PrintAllReservationGiverId(int id) {
        ArrayList<ReservationRecord> list = new ArrayList<ReservationRecord>();

        for(Reservation reservation : reservationRepository.findAll())
        {
            if(reservation.getCarGiverId() == id) {
                Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElse(null);
                Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);

                list.add(new ReservationRecord(
                        reservation.getReservationId(),
                        brand.getBrandName(),
                        model.getModelName(),
                        reservation.getStartTime().toString(),
                        reservation.getEndTime().toString()
                ));
            }
        }
        return list;
    }
    public Reservation addReservation(@NotNull ReservationSave reservationSave) {
        Reservation reservation = new Reservation();

        reservation.setEmployeeId(reservationSave.employeeId());
        reservation.setCarGiverId(reservationSave.carGiverId());
        reservation.setEndTime(Date.valueOf(reservationSave.endTime()));
        reservation.setStartTime(Date.valueOf(reservationSave.endTime()));
        reservation.setPrivateUsage(false);

        for (Vehicle vehicle : vehicleRepository.findAll()) {
            Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
            if (model != null && model.getModelName().equals(reservationSave.model())) {
                Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
                if (brand != null && brand.getBrandName().equals(reservationSave.brand())) {
                    reservation.setVehicleId(vehicle.getVehicleId());
                    break;
                }
            }
        }
        reservationRepository.save(reservation);
        return reservation;
    }

    public ArrayList<Reservation> getReservationByAttribute(String data) {
            ArrayList<Reservation> list = new ArrayList<>();
            for(Reservation r : reservationRepository.findAll()) {
                Optional<Vehicle> v = vehicleRepository.findById(r.getVehicleId());
                Optional<Model> m = modelRepository.findById(v.get().getModelId());
                Optional<Brand> b  = brandRepository.findById(m.get().getModelId());
                if(m.get().getModelName().contains(data)) {
                    list.add(r);
                }

                if(b.get().getBrandName().contains(data)) {
                    list.add(r);
                }

                if (r.getStartTime().toString().compareTo(data) <= 0 && data.compareTo(r.getEndTime().toString()) <= 0) {
                    list.add(r);
                }

                String employeeIdString = String.valueOf(r.getReservationId());
                if(employeeIdString.contains(data)) {
                    list.add(r);
                }
            }
            ArrayList<Reservation> uniqueList = new ArrayList<>();
            for (Reservation r : list) {
                if (!uniqueList.contains(r)) {
                    uniqueList.add(r);
                }
            }
            return uniqueList;

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
