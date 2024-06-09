package com.example.carrent.reservation;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Entity class representing a reservation.
 */
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

    @Column(name = "\"reserved\"")
    private boolean reserved;

    /**
     * Default constructor.
     */
    public Reservation() {
    }

    /**
     * Parameterized constructor.
     */
    public Reservation(int reservationId, Date startTime, Date endTime, boolean privateUsage, int employeeId,
            int carGiverId) {
        this.reservationId = reservationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.privateUsage = privateUsage;
        this.employeeId = employeeId;
        this.carGiverId = carGiverId;
    }

    /**
     * Getter for reservationId.
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Setter for reservationId.
     */
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Getter for startTime.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setter for startTime.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for endTime.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setter for endTime.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Getter for privateUsage.
     */
    public boolean getPrivateUsage() {
        return privateUsage;
    }

    /**
     * Setter for privateUsage.
     */
    public void setPrivateUsage(boolean privateUsage) {
        this.privateUsage = privateUsage;
    }

    /**
     * Getter for employeeId.
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Setter for employeeId.
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Getter for carGiverId.
     */
    public int getCarGiverId() {
        return carGiverId;
    }

    /**
     * Setter for carGiverId.
     */
    public void setCarGiverId(int carGiverId) {
        this.carGiverId = carGiverId;
    }

    /**
     * Getter for vehicleId.
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Setter for vehicleId.
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Getter for reserved.
     */
    public boolean getReserved() {
        return reserved;
    }

    /**
     * Setter for reserved.
     */
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Reservation))
            return false;
        Reservation that = (Reservation) o;
        return reservationId == that.reservationId && privateUsage == that.privateUsage && employeeId == that.employeeId
                && carGiverId == that.carGiverId && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime);
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

/**
 * Record representing a reservation for presentation purposes.
 */
record ReservationRecord(
        int id,
        String model,
        String brand,
        String startTime,
        String endTime,
        Boolean reserved) {
}

/**
 * Record for saving a reservation.
 */
record ReservationSave(
        String model,
        String brand,
        String startTime,
        String endTime,
        int employeeId,
        int carGiverId) {
}

/**
 * Record representing a rent for presentation purposes.
 */
record RentRecord(
        int id,
        String Brand,
        String Model,
        String Date) {
}
