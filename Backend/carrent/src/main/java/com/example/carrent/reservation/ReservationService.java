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

    private final VehicleCarGiverRepository vehicleCarGiverRepository;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, VehicleRepository vehicleRepository, BrandRepository brandRepository, ModelRepository modelRepository, VehicleCarGiverRepository vehicleCarGiverRepository) {
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.vehicleCarGiverRepository = vehicleCarGiverRepository;
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

    public ArrayList<ReservationRecord> getReservationByAttribute(String data) {
        ArrayList<ReservationRecord> list = new ArrayList<>();

        for (Reservation r : reservationRepository.findAll()) {
            Optional<Vehicle> vOpt = vehicleRepository.findById(r.getVehicleId());
            if (vOpt.isEmpty()) {
                continue;
            }
            Vehicle v = vOpt.get();

            Optional<Model> mOpt = modelRepository.findById(v.getModelId());
            if (mOpt.isEmpty()) {
                continue;
            }
            Model m = mOpt.get();

            Optional<Brand> bOpt = brandRepository.findById(m.getBrandId());
            if (bOpt.isEmpty()) {
                continue;
            }
            Brand b = bOpt.get();

            if (m.getModelName().contains(data) || b.getBrandName().contains(data)) {
                list.add(new ReservationRecord(
                        r.getReservationId(),
                        b.getBrandName(),
                        m.getModelName(),
                        r.getStartTime().toString(),
                        r.getEndTime().toString()
                ));
            }

            if (r.getStartTime().toString().compareTo(data) <= 0 && data.compareTo(r.getEndTime().toString()) <= 0) {
                list.add(new ReservationRecord(
                        r.getReservationId(),
                        b.getBrandName(),
                        m.getModelName(),
                        r.getStartTime().toString(),
                        r.getEndTime().toString()
                ));
            }

            String reservationIdString = String.valueOf(r.getReservationId());
            if (reservationIdString.contains(data)) {
                list.add(new ReservationRecord(
                        r.getReservationId(),
                        b.getBrandName(),
                        m.getModelName(),
                        r.getStartTime().toString(),
                        r.getEndTime().toString()
                ));
            }
        }
        return list.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }


}
