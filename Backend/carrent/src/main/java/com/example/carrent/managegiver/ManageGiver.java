package com.example.carrent.managegiver;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Represents a management entity for assigning vehicles to car givers.
 */
@Entity
@Table(name = "\"ManageGiver\"")
public class ManageGiver {

    @Id
    @SequenceGenerator(name = "manage_sequence", sequenceName = "manage_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manage_sequence")
    @Column(name = "\"manageId\"")
    private int manageId;

    @Column(name = "\"vehicleId\"")
    private int vehicleId;

    @Column(name = "\"carGiverId\"")
    private int carGiverId;

    @Column(name = "maintenance")
    private int maintenance;

    public ManageGiver() {
    }

    /**
     * Constructs a ManageGiver object with specified attributes.
     *
     * @param manageId    the ID of the management entity.
     * @param vehicleId   the ID of the vehicle.
     * @param carGiverId  the ID of the car giver.
     * @param maintenance the maintenance status of the vehicle.
     */
    public ManageGiver(int manageId, int vehicleId, int carGiverId, int maintenance) {
        this.manageId = manageId;
        this.vehicleId = vehicleId;
        this.carGiverId = carGiverId;
        this.maintenance = maintenance;
    }

    /**
     * Gets the ID of the management entity.
     *
     * @return the manageId.
     */
    public int getManageId() {
        return manageId;
    }

    /**
     * Sets the ID of the management entity.
     *
     * @param manageId the manageId to set.
     */
    public void setManageId(int manageId) {
        this.manageId = manageId;
    }

    /**
     * Gets the ID of the vehicle.
     *
     * @return the vehicleId.
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the ID of the vehicle.
     *
     * @param vehicleId the vehicleId to set.
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the ID of the car giver.
     *
     * @return the carGiverId.
     */
    public int getCarGiverId() {
        return carGiverId;
    }

    /**
     * Sets the ID of the car giver.
     *
     * @param carGiverId the carGiverId to set.
     */
    public void setCarGiverId(int carGiverId) {
        this.carGiverId = carGiverId;
    }

    /**
     * Gets the maintenance status of the vehicle.
     *
     * @return the maintenance status.
     */
    public int getMaintenance() {
        return maintenance;
    }

    /**
     * Sets the maintenance status of the vehicle.
     *
     * @param maintenance the maintenance status to set.
     */
    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ManageGiver))
            return false;
        ManageGiver that = (ManageGiver) o;
        return manageId == that.manageId &&
                vehicleId == that.vehicleId &&
                carGiverId == that.carGiverId &&
                maintenance == that.maintenance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manageId, vehicleId, carGiverId, maintenance);
    }

    @Override
    public String toString() {
        return "ManageGiver{" +
                "manageId=" + manageId +
                ", vehicleId=" + vehicleId +
                ", carGiverId=" + carGiverId +
                ", maintenance=" + maintenance +
                '}';
    }
}
