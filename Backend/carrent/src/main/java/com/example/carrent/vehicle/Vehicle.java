package com.example.carrent.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.RestController;

/**
 * Entity representing a Vehicle.
 */
@Entity
@Table(name = "\"Vehicle\"")
@RestController
public class Vehicle {

    /**
     * Unique identifier for the vehicle.
     */
    @Id
    @SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
    @Column(name = "\"vehicleId\"")
    private int vehicleId;

    /**
     * Equipment details of the vehicle.
     */
    @Column(name = "\"equipment\"")
    private String equipment;

    /**
     * Version of the vehicle.
     */
    @Column(name = "\"version\"")
    private String version;

    /**
     * Purpose of the vehicle.
     */
    @Column(name = "\"purpose\"")
    private String purpose;

    /**
     * Total time the vehicle has been used.
     */
    @Column(name = "\"totalTime\"")
    private double totalTime;

    /**
     * Total distance the vehicle has traveled.
     */
    @Column(name = "\"totalDistance\"")
    private double totalDistance;

    /**
     * Identifier for the model of the vehicle.
     */
    @Column(name = "\"modelId\"")
    private int modelId;

    /**
     * Type of fuel used by the vehicle.
     */
    @Column(name = "\"fuel\"")
    private String fuel;

    /**
     * Year the vehicle was produced.
     */
    @Column(name = "\"yearofproduction\"")
    private int yearOfProduction;

    /**
     * Power of the vehicle.
     */
    @Column(name = "\"power\"")
    private int power;

    /**
     * Default constructor.
     */
    public Vehicle() {
    }

    /**
     * Parameterized constructor.
     *
     * @param vehicleId unique identifier for the vehicle
     * @param equipment equipment details of the vehicle
     * @param version version of the vehicle
     * @param purpose purpose of the vehicle
     * @param totalTime total time the vehicle has been used
     * @param totalDistance total distance the vehicle has traveled
     * @param modelId identifier for the model of the vehicle
     * @param fuel type of fuel used by the vehicle
     * @param yearOfProduction year the vehicle was produced
     * @param power power of the vehicle
     */
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

    // Getters and Setters with Doxygen comments

    /**
     * Gets the fuel type of the vehicle.
     *
     * @return the fuel type
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * Sets the fuel type of the vehicle.
     *
     * @param fuel the fuel type
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * Gets the year of production of the vehicle.
     *
     * @return the year of production
     */
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    /**
     * Sets the year of production of the vehicle.
     *
     * @param yearOfProduction the year of production
     */
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    /**
     * Gets the power of the vehicle.
     *
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power of the vehicle.
     *
     * @param power the power
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Gets the unique identifier of the vehicle.
     *
     * @return the vehicle id
     */
    public int getVehicleId() {
        return this.vehicleId;
    }

    /**
     * Sets the unique identifier of the vehicle.
     *
     * @param vehicleId the vehicle id
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the equipment details of the vehicle.
     *
     * @return the equipment details
     */
    public String getEquipment() {
        return this.equipment;
    }

    /**
     * Sets the equipment details of the vehicle.
     *
     * @param equipment the equipment details
     */
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    /**
     * Gets the version of the vehicle.
     *
     * @return the version
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Sets the version of the vehicle.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the purpose of the vehicle.
     *
     * @return the purpose
     */
    public String getPurpose() {
        return this.purpose;
    }

    /**
     * Sets the purpose of the vehicle.
     *
     * @param purpose the purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * Gets the total time the vehicle has been used.
     *
     * @return the total time
     */
    public double getTotalTime() {
        return this.totalTime;
    }

    /**
     * Sets the total time the vehicle has been used.
     *
     * @param totalTime the total time
     */
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * Gets the total distance the vehicle has traveled.
     *
     * @return the total distance
     */
    public double getTotalDistance() {
        return this.totalDistance;
    }

    /**
     * Sets the total distance the vehicle has traveled.
     *
     * @param totalDistance the total distance
     */
    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    /**
     * Gets the identifier for the model of the vehicle.
     *
     * @return the model id
     */
    public int getModelId() {
        return this.modelId;
    }

    /**
     * Sets the identifier for the model of the vehicle.
     *
     * @param modelId the model id
     */
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    // Fluent setters with Doxygen comments

    /**
     * Sets the vehicle ID and returns the current Vehicle object.
     *
     * @param vehicleId the vehicle id
     * @return the current Vehicle object
     */
    public Vehicle vehicleId(int vehicleId) {
        setVehicleId(vehicleId);
        return this;
    }

    /**
     * Sets the equipment details and returns the current Vehicle object.
     *
     * @param equipment the equipment details
     * @return the current Vehicle object
     */
    public Vehicle equipment(String equipment) {
        setEquipment(equipment);
        return this;
    }

    /**
     * Sets the version and returns the current Vehicle object.
     *
     * @param version the version
     * @return the current Vehicle object
     */
    public Vehicle version(String version) {
        setVersion(version);
        return this;
    }

    /**
     * Sets the purpose and returns the current Vehicle object.
     *
     * @param purpose the purpose
     * @return the current Vehicle object
     */
    public Vehicle purpose(String purpose) {
        setPurpose(purpose);
        return this;
    }

    /**
     * Sets the total time and returns the current Vehicle object.
     *
     * @param totalTime the total time
     * @return the current Vehicle object
     */
    public Vehicle totalTime(double totalTime) {
        setTotalTime(totalTime);
        return this;
    }

    /**
     * Sets the total distance and returns the current Vehicle object.
     *
     * @param totalDistance the total distance
     * @return the current Vehicle object
     */
    public Vehicle totalDistance(double totalDistance) {
        setTotalDistance(totalDistance);
        return this;
    }

    /**
     * Sets the model ID and returns the current Vehicle object.
     *
     * @param modelId the model id
     * @return the current Vehicle object
     */
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

/**
 * Record representing a printable version of a Vehicle.
 */
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

/**
 * Record representing a Vehicle's brand and model.
 */
record VehicleBrandModel(
        String model,
        String brand
){}
