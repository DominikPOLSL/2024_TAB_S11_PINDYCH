package com.example.carrent.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "\"Vehicle\"")
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

    public Vehicle() {
    }

    public Vehicle(int vehicleId, String equipment, String version, String purpose, double totalTime,
            double totalDistance, int modelId) {
        this.vehicleId = vehicleId;
        this.equipment = equipment;
        this.version = version;
        this.purpose = purpose;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;
        this.modelId = modelId;
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
