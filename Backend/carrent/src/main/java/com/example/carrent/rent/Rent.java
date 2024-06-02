package com.example.carrent.rent;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "\"Rent\"")
public class Rent {

    @Id
    @SequenceGenerator(name = "rent_sequence", sequenceName = "rent_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_sequence")
    @Column(name = "\"rentId\"")
    private int rentId;

    @Column(name = "distance")
    private double distance;

    @Column(name = "cost")
    private double cost;

    @Column(name = "\"reservationId\"")
    private int reservationId;

    public Rent() {}

    public Rent(int rentId, double distance, double cost, int reservationId) {
        this.rentId = rentId;
        this.distance = distance;
        this.cost = cost;
        this.reservationId = reservationId;
    }

    public int getRentId() {
        return rentId;
    }

    @GeneratedValue
    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rent)) return false;
        Rent rent = (Rent) o;
        return rentId == rent.rentId && Double.compare(rent.distance, distance) == 0 && Double.compare(rent.cost, cost) == 0 && reservationId == rent.reservationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentId, distance, cost, reservationId);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", distance=" + distance +
                ", cost=" + cost +
                ", reservationId=" + reservationId +
                '}';
    }
}
