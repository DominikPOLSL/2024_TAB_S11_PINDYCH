package com.example.carrent.reservation;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "\"Reservation\"")
public class Reservation {

    @Id
    @SequenceGenerator(name = "reservation_sequence", sequenceName = "reservation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
    @Column(name = "\"reservationId\"")
    private int reservationId;

    @Column(name = "\"startTime\"")
    private Date startTime;

    @Column(name = "\"endTime\"")
    private Date endTime;

    @Column(name = "\"privateUsage\"")
    private boolean privateUsage;

    @Column(name = "\"employeeId\"")
    private int employeeId;

    @Column(name = "\"carGiverId\"")
    private int carGiverId;

    @Column(name = "\"vehicleId\"")
    private int vehicleId;

    public Reservation() {}

    public Reservation(int reservationId, Date startTime, Date endTime, boolean privateUsage, int employeeId, int carGiverId) {
        this.reservationId = reservationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.privateUsage = privateUsage;
        this.employeeId = employeeId;
        this.carGiverId = carGiverId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isPrivateUsage() {
        return privateUsage;
    }

    public void setPrivateUsage(boolean privateUsage) {
        this.privateUsage = privateUsage;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCarGiverId() {
        return carGiverId;
    }

    public void setCarGiverId(int carGiverId) {
        this.carGiverId = carGiverId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && privateUsage == that.privateUsage && employeeId == that.employeeId && carGiverId == that.carGiverId && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, startTime, endTime, privateUsage, employeeId, carGiverId);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", privateUsage=" + privateUsage +
                ", employeeId=" + employeeId +
                ", carGiverId=" + carGiverId +
                '}';
    }
}
record ReservationRecord(
        int id,
        String model,
        String brand,
        String startTime,
        String endTime
){}
record ReservationSave(
        String model,
        String brand,
        String startTime,
        String endTime,
        int employeeId,
        int carGiverId
){}
record RentRecord(
        int id,
        String Brand,
        String Model,
        String Date
){}