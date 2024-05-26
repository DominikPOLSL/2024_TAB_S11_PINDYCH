package com.example.carrent.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Entity
@Table(name = "\"Vehicle\"")
@RestController
public class Vehicle {

    @Id
    @SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
    @Column(name = "\"vehicleId\"")
    private int vehicleId;

    @Column(name = "\"equipment\"")
    private String equipment;

    @Column(name = "\"version\"")
    private String version;

    @Column(name = "\"purpose\"")
    private String purpose;

    @Column(name = "\"totalTime\"")
    private double totalTime;

    @Column(name = "\"totalDistance\"")
    private double totalDistance;

    @Column(name = "\"modelId\"")
    private int modelId;

    @Column(name = "\"fuel\"")
    private String fuel;

    @Column(name = "\"yearofproduction\"")
    private int yearOfProduction;

    @Column(name = "\"power\"")
    private int power;
    public Vehicle() {
    }

    public Vehicle(int vehicleId, String equipment, String version, String purpose, double totalTime,
            double totalDistance, int modelId, String fuel, int yearOfProduction, int power) {
        this.vehicleId = vehicleId;
        this.equipment = equipment;
        this.version = version;
        this.purpose = purpose;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;
        this.modelId = modelId;
        this.fuel = fuel;
        this.yearOfProduction = yearOfProduction;
        this.power = power;
    }


    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getEquipment() {
        return this.equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getModelId() {
        return this.modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public Vehicle vehicleId(int vehicleId) {
        setVehicleId(vehicleId);
        return this;
    }

    public Vehicle equipment(String equipment) {
        setEquipment(equipment);
        return this;
    }

    public Vehicle version(String version) {
        setVersion(version);
        return this;
    }

    public Vehicle purpose(String purpose) {
        setPurpose(purpose);
        return this;
    }

    public Vehicle totalTime(double totalTime) {
        setTotalTime(totalTime);
        return this;
    }

    public Vehicle totalDistance(double totalDistance) {
        setTotalDistance(totalDistance);
        return this;
    }

    public Vehicle modelId(int modelId) {
        setModelId(modelId);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " vehicleId='" + getVehicleId() + "'" +
                ", equipment='" + getEquipment() + "'" +
                ", version='" + getVersion() + "'" +
                ", purpose='" + getPurpose() + "'" +
                ", totalTime='" + getTotalTime() + "'" +
                ", totalDistance='" + getTotalDistance() + "'" +
                ", modelId='" + getModelId() + "'" +
                "}";
    }
}
record VehiclePrint(
        int id,
        String model,
        String brand,
        String fuel,
        double distance,
        int yearOfProduction,
        int power,
        String guardianName,
        String guardianSurname
){}


record VehicleBrandModel(
        String model,
        String brand
){}
